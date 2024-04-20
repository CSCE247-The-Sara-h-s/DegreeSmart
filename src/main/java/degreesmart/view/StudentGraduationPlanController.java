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
import java.util.Collections;

import degreesmart.model.Application;
import degreesmart.model.StudentApplication;
import degreesmart.model.Student;
import degreesmart.model.Course;
import degreesmart.model.CourseList;
import degreesmart.model.CompletedCourse;
import degreesmart.model.PlannedCourse;
import degreesmart.model.Grade;
import degreesmart.model.Semester;

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

        Student student = application.getActiveUser();

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

        addCompletedSemesters(application.getCompletedCourses());

        // HashMap<String, ArrayList<CompletedCourse>> completedCoursesByTerm = new HashMap<String, ArrayList<CompletedCourse>>();
        // for (CompletedCourse course : application.getCompletedCourses()) {
        //     String term = course.getSemester() + " " + course.getYear();

        //     if (!completedCoursesByTerm.containsKey(term)) {
        //         completedCoursesByTerm.put(term, new ArrayList<CompletedCourse>());
        //     }
        //     completedCoursesByTerm.get(term).add(course);
        // }

        // for (String term : completedCoursesByTerm.keySet()) {
        //     // https://stackoverflow.com/a/30611899
        //     addSemester(term,
        //         new ArrayList<String>(completedCoursesByTerm.get(term).stream()
        //             .map((c) -> (c.getCourse().getShortName() == null)? "" : c.getCourse().getShortName()
        //                 + " - " + c.getCourse().getName())
        //             .collect(Collectors.toList())),
        //         new ArrayList<String>(completedCoursesByTerm.get(term).stream()
        //             .map((c) -> Grade.D.toString())
        //             .collect(Collectors.toList())),
        //         new ArrayList<Double>(completedCoursesByTerm.get(term).stream()
        //             .map((c) -> c.getCourse().getCreditHours())
        //             .collect(Collectors.toList())),
        //         new ArrayList<String>(completedCoursesByTerm.get(term).stream()
        //             .map((c) -> c.getGrade().toString())
        //             .collect(Collectors.toList())),
        //         new ArrayList<String>(completedCoursesByTerm.get(term).stream()
        //             .map((c) -> (c.getCourse().getPrerequisites().size() == 0)? "" : c.getCourse().getPrerequisites().toString())
        //             .collect(Collectors.toList()))
        //     );
        // }

        HashMap<String, ArrayList<PlannedCourse>> currentCoursesByTerm = new HashMap<String, ArrayList<PlannedCourse>>();
        for (PlannedCourse course : application.getCurrentCourses()) {
            String term = course.getSemester() + " " + course.getYear();

            if (!currentCoursesByTerm.containsKey(term)) {
                currentCoursesByTerm.put(term, new ArrayList<PlannedCourse>());
            }
            currentCoursesByTerm.get(term).add(course);
        }

        for (String term : currentCoursesByTerm.keySet()) {
            addSemester(term + "  (current)",
                new ArrayList<String>(currentCoursesByTerm.get(term).stream()
                    .map((c) -> (c.getCourse().getShortName() == null)? "" : c.getCourse().getShortName()
                        + " - " + c.getCourse().getName())
                    .collect(Collectors.toList())),
                new ArrayList<String>(currentCoursesByTerm.get(term).stream()
                    .map((c) -> Grade.F.toString())
                    .collect(Collectors.toList())),
                new ArrayList<Double>(currentCoursesByTerm.get(term).stream()
                    .map((c) -> c.getCourse().getCreditHours())
                    .collect(Collectors.toList())),
                new ArrayList<String>(currentCoursesByTerm.get(term).stream()
                    .map((c) -> "-")
                    .collect(Collectors.toList())),
                new ArrayList<String>(currentCoursesByTerm.get(term).stream()
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
            addSemester(term + "  (planned)",
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

    public VBox getSemesterVBox() {
        VBox semester = null;

        try {
            // https://coderanch.com/t/723526/java/fxml-include-dynamically-adding-removing
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("/library/graduation-plan-semester.fxml"));
            semester = fxmlLoader.<VBox>load();
            GridPane details = (GridPane)semester.lookup("#semesterDetails");

            ((ImageView)semester.lookup("#expandSemester")).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (details.isVisible()) {
                        details.setVisible(false);
                        details.setManaged(false);
                    } else {
                        details.setVisible(true);
                        details.setManaged(true);
                    }
                }
            });

            details.setVisible(false);
            details.setManaged(false);
            semesterList.getChildren().add(semester);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return semester;
    }

    private void addCompletedSemesters(ArrayList<CompletedCourse> courses) {
        Collections.sort(courses, Collections.reverseOrder());

        String blockLabel = "";
        VBox semester = null;
        GridPane details = null;
        int row = 0;
        int col = 0;

        for (CompletedCourse completedCourse : courses) {
            if (!completedCourse.getTerm().toString().equals(blockLabel)) {
                semester = getSemesterVBox();
                details = (GridPane) semester.lookup("#semesterDetails");
                blockLabel = completedCourse.getTerm().toString();
                ((Label) semester.lookup("#semesterName")).setText(blockLabel);
                row = 0;
            }
            col = 0;
            row++;

            Course course = completedCourse.getCourse();
            EventHandler<MouseEvent> clickEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(course);
                }
            };

            Label code = new Label(course.getShortName());
            code.setOnMouseClicked(clickEvent);
            GridPane.setRowIndex(code, row);
            GridPane.setColumnIndex(code, col++);
            details.getChildren().add(code);

            Label name = new Label(course.getName());
            name.setOnMouseClicked(clickEvent);
            GridPane.setRowIndex(name, row);
            GridPane.setColumnIndex(name, col++);
            details.getChildren().add(name);

            Label creditHours = new Label("" + course.getCreditHours());
            creditHours.setOnMouseClicked(clickEvent);
            GridPane.setRowIndex(creditHours, row);
            GridPane.setColumnIndex(creditHours, col++);
            details.getChildren().add(creditHours);

            Label grade = new Label(Grade.B.toString());
            grade.setOnMouseClicked(clickEvent);
            GridPane.setRowIndex(grade, row);
            GridPane.setColumnIndex(grade, col++);
            details.getChildren().add(grade);

            Label requirement = new Label("Computer Science - Major");
            requirement.setOnMouseClicked(clickEvent);
            GridPane.setRowIndex(requirement, row);
            GridPane.setColumnIndex(requirement, col++);
            details.getChildren().add(requirement);

            ArrayList<Label> labels = new ArrayList<Label>();
            Collections.addAll(labels, code, name, creditHours, grade, requirement);

            EventHandler<MouseEvent> enterEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (Label label : labels) {
                        label.setStyle("-fx-padding: 3 5; -fx-background-color: rgba(101, 8, 8, 0.2);");
                    }
                }
            };

            EventHandler<MouseEvent> exitEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (Label label : labels) {
                        label.setStyle("-fx-padding: 3 5; -fx-background-color: transparent;");
                    }
                }
            };

            for (Label label : labels) {
                label.setStyle("-fx-padding: 3 5;");
                label.setMaxHeight(Double.MAX_VALUE);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setOnMouseEntered(enterEvent);
                label.setOnMouseExited(exitEvent);
            }
        }
    }

    private void addCurrentSemesters(ArrayList<PlannedCourse> courses) {
        VBox semester = getSemesterVBox();
        Collections.sort(courses);
        GridPane details = (GridPane)semester.lookup("#semesterDetails");

        String blockLabel = "";
        // for (Course course : courses);
    }

    private void addPlannedSemesters(ArrayList<PlannedCourse> courses) {
        VBox semester = getSemesterVBox();
        Collections.sort(courses);
        GridPane details = (GridPane)semester.lookup("#semesterDetails");

        String blockLabel = "";
        // for (Course course : courses)
    }


    public void addSemester(String label, ArrayList<String> courses, ArrayList<String> minGrades, 
            ArrayList<Double> credits, ArrayList<String> grades, ArrayList<String> prerequisites) {
        try {
            VBox semester = getSemesterVBox();
            ((Label)semester.lookup("#semesterName")).setText(label);

            ArrayList<EventHandler<MouseEvent>> events = new ArrayList<EventHandler<MouseEvent>>();
            for (int i = 0; i < courses.size(); i++) {
                String course = courses.get(i);
                events.add(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(course);
                    }
                });
            }

            GridPane details = (GridPane)semester.lookup("#semesterDetails");
            for (int i = 0; i < courses.size(); i++) {
                Label tmp = new Label(courses.get(i));
                tmp.setOnMouseClicked(events.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 0);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < minGrades.size(); i++) {
                Label tmp = new Label(minGrades.get(i));
                tmp.setOnMouseClicked(events.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 1);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < credits.size(); i++) {
                Label tmp = new Label("" + credits.get(i));
                tmp.setOnMouseClicked(events.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 2);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < prerequisites.size(); i++) {
                Label tmp = new Label("" + prerequisites.get(i));
                tmp.setOnMouseClicked(events.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 3);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < grades.size(); i++) {
                Label tmp = new Label(grades.get(i));
                tmp.setOnMouseClicked(events.get(i));
                GridPane.setRowIndex(tmp, i + 2);
                GridPane.setColumnIndex(tmp, 4);
                details.getChildren().add(tmp);
            }
        } catch (Exception e) {
        }
    }
}
