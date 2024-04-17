package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import java.lang.reflect.*;
import java.util.stream.Collectors;

import degreesmart.model.Application;
import degreesmart.model.StudentApplication;
import degreesmart.model.CompletedCourse;
import degreesmart.model.PlannedCourse;
import degreesmart.model.Grade;

public class StudentGraduationPlanController extends StudentController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private Label degree;

    @FXML
    private Label classification;

    @FXML
    private Label overallGpa;

    @FXML
    private Label majorGpa;

    @FXML
    private Label creditsEarned;

    @FXML
    private VBox semesterList;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        StudentApplication application = StudentApplication.getInstance();

        headerPaneController.getPageTitle().setText(Application.getInstance().getFirstName() + "'s Graduation Plan");

        try {
            degree.setText(application.getMajors().get(0).getName());
        } catch (Exception e) {
            degree.setText("Undeclared");
        }

        classification.setText(application.getClassification());
        overallGpa.setText("" + application.getOverallGpa());
        majorGpa.setText("" + application.getMajorGpa());
        creditsEarned.setText("" + application.getCreditHours());

        HashMap<String, ArrayList<CompletedCourse>> completedCoursesByTerm = new HashMap<String, ArrayList<CompletedCourse>>();
        for (CompletedCourse course : application.getCompletedCourses()) {
            String term = course.getSemester() + " " + course.getYear();

            if (!completedCoursesByTerm.containsKey(term)) {
                completedCoursesByTerm.put(term, new ArrayList<CompletedCourse>());
            }
            completedCoursesByTerm.get(term).add(course);
        }

        for (String term : completedCoursesByTerm.keySet()) {
            // https://stackoverflow.com/a/30611899
            addSemester(term,
                new ArrayList<String>(completedCoursesByTerm.get(term).stream()
                    .map((c) -> (c.getCourse().getShortName() == null)? "" : c.getCourse().getShortName()
                        + " - " + c.getCourse().getName())
                    .collect(Collectors.toList())),
                new ArrayList<String>(completedCoursesByTerm.get(term).stream()
                    .map((c) -> Grade.D.toString())
                    .collect(Collectors.toList())),
                new ArrayList<Double>(completedCoursesByTerm.get(term).stream()
                    .map((c) -> c.getCourse().getCreditHours())
                    .collect(Collectors.toList())),
                new ArrayList<String>(completedCoursesByTerm.get(term).stream()
                    .map((c) -> c.getGrade().toString())
                    .collect(Collectors.toList())),
                new ArrayList<String>(completedCoursesByTerm.get(term).stream()
                    .map((c) -> (c.getCourse().getPrerequisites().size() == 0)? "" : c.getCourse().getPrerequisites().toString())
                    .collect(Collectors.toList()))
            );
        }
        
        HashMap<String, ArrayList<PlannedCourse>> plannedCoursesByTerm = new HashMap<String, ArrayList<PlannedCourse>>();
        for (PlannedCourse course : application.getPlannedCourses()) {
            String term = course.getSemester() + " " + course.getYear();

            if (!plannedCoursesByTerm.containsKey(term)) {
                plannedCoursesByTerm.put(term, new ArrayList<PlannedCourse>());
            }
            plannedCoursesByTerm.get(term).add(course);
        }

        for (String term : plannedCoursesByTerm.keySet()) {
            addSemester(term,
                new ArrayList<String>(plannedCoursesByTerm.get(term).stream()
                    .map((c) -> (c.getCourse().getShortName() == null)? "" : c.getCourse().getShortName()
                        + " - " + c.getCourse().getName())
                    .collect(Collectors.toList())),
                new ArrayList<String>(plannedCoursesByTerm.get(term).stream()
                    .map((c) -> Grade.F.toString())
                    .collect(Collectors.toList())),
                new ArrayList<Double>(plannedCoursesByTerm.get(term).stream()
                    .map((c) -> c.getCourse().getCreditHours())
                    .collect(Collectors.toList())),
                new ArrayList<String>(plannedCoursesByTerm.get(term).stream()
                    .map((c) -> "-")
                    .collect(Collectors.toList())),
                new ArrayList<String>(plannedCoursesByTerm.get(term).stream()
                    .map((c) -> (c.getCourse().getPrerequisites().size() == 0)? "" : c.getCourse().getPrerequisites().toString())
                    .collect(Collectors.toList()))
            );
        }
    }

    public void addSemester(String label, ArrayList<String> courses, ArrayList<String> minGrades, 
            ArrayList<Double> credits, ArrayList<String> grades, ArrayList<String> prerequisites) {
        try {
           // https://coderanch.com/t/723526/java/fxml-include-dynamically-adding-removing
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("/library/graduation-plan-semester.fxml"));

            VBox semester = fxmlLoader.<VBox>load();

            ((ImageView)semester.lookup("#expandSemester")).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    GridPane details = (GridPane)semester.lookup("#semesterDetails");
                    if (details.isVisible()) {
                        details.setVisible(false);
                        details.setManaged(false);
                    } else {
                        details.setVisible(true);
                        details.setManaged(true);
                    }
                }
            });

            ((Label)semester.lookup("#semesterName")).setText(label);
           
            GridPane details = (GridPane)semester.lookup("#semesterDetails");
            for (int i = 0; i < courses.size(); i++) {
                Label tmp = new Label(courses.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 0);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < minGrades.size(); i++) {
                Label tmp = new Label(minGrades.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 1);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < credits.size(); i++) {
                Label tmp = new Label("" + credits.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 2);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < prerequisites.size(); i++) {
                Label tmp = new Label("" + prerequisites.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 3);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < grades.size(); i++) {
                Label tmp = new Label(grades.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 4);
                details.getChildren().add(tmp);
            }

            details.setVisible(false);
            details.setManaged(false);

            semesterList.getChildren().add(semester);
        } catch (Exception e) {
        }
    }

    public void expandSemester(MouseEvent event) {
        System.out.println("TODO");
    }
}
