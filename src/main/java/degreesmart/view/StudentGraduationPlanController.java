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

        String title = Application.getInstance().getFirstName() + "'s Graduation Plan";
        headerPaneController.getPageTitle().setText(title);

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
        addCurrentSemester(application.getCurrentCourses());
        addPlannedSemesters(application.getPlannedCourses());
    }

    private VBox getSemesterVBox() {
        VBox semester = null;

        try {
            // https://coderanch.com/t/723526/java/fxml-include-dynamically-adding-removing
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("/library/graduation-plan-semester.fxml"));
            semester = fxmlLoader.<VBox>load();
            GridPane details = (GridPane)semester.lookup("#semesterDetails");
            HBox spacer = (HBox) semester.lookup("#spacer");

            ((ImageView)semester.lookup("#expandSemester")).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (details.isVisible()) {
                        details.setVisible(false);
                        details.setManaged(false);
                        spacer.setVisible(false);
                        spacer.setManaged(false);
                    } else {
                        details.setVisible(true);
                        details.setManaged(true);
                        spacer.setVisible(true);
                        spacer.setManaged(true);
                    }
                }
            });

            details.setVisible(false);
            details.setManaged(false);
            spacer.setVisible(false);
            spacer.setManaged(false);
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
                ((HBox) semester.lookup("#status")).setStyle("-fx-background-radius: 20; -fx-background-color: rgb(54, 188, 152);");
                row = 0;
            }
            col = 0;
            row++;
            Course course = completedCourse.getCourse();


            ArrayList<Label> labels = new ArrayList<Label>();
            Collections.addAll(
                labels,
                new Label(course.getShortName()),
                new Label(course.getName()),
                new Label("" + course.getCreditHours()),
                new Label(completedCourse.getGrade().toString()),
                new Label("Computer Science - Major")
            );

            EventHandler<MouseEvent> clickEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(course);
                }
            };

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
                label.setOnMouseClicked(clickEvent);
                GridPane.setRowIndex(label, row);
                GridPane.setColumnIndex(label, col++);
                details.getChildren().add(label);

                label.setStyle("-fx-padding: 3 5;");
                label.setMaxHeight(Double.MAX_VALUE);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setOnMouseEntered(enterEvent);
                label.setOnMouseExited(exitEvent);
            }
        }
    }

    private void addCurrentSemester(ArrayList<PlannedCourse> courses) {
        Collections.sort(courses, Collections.reverseOrder());

        String blockLabel = "";
        VBox semester = null;
        GridPane details = null;
        int row = 0;
        int col = 0;

        for (PlannedCourse plannedCourse : courses) {
            if (!plannedCourse.getTerm().toString().equals(blockLabel)) {
                semester = getSemesterVBox();
                details = (GridPane) semester.lookup("#semesterDetails");
                blockLabel = plannedCourse.getTerm().toString();
                ((Label) semester.lookup("#semesterName")).setText(blockLabel);
                ((HBox) semester.lookup("#status")).setStyle("-fx-background-radius: 20; -fx-background-color: rgb(255, 193, 7);");
                row = 0;
            }
            col = 0;
            row++;
            Course course = plannedCourse.getCourse();


            ArrayList<Label> labels = new ArrayList<Label>();
            Collections.addAll(
                labels,
                new Label(course.getShortName()),
                new Label(course.getName()),
                new Label("" + course.getCreditHours()),
                new Label("-"),
                new Label("Computer Science - Major")
            );

            EventHandler<MouseEvent> clickEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(course);
                }
            };

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
                label.setOnMouseClicked(clickEvent);
                GridPane.setRowIndex(label, row);
                GridPane.setColumnIndex(label, col++);
                details.getChildren().add(label);

                label.setStyle("-fx-padding: 3 5;");
                label.setMaxHeight(Double.MAX_VALUE);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setOnMouseEntered(enterEvent);
                label.setOnMouseExited(exitEvent);
            }

            HBox spacer = (HBox) semester.lookup("#spacer");

            details.setVisible(true);
            details.setManaged(true);
            spacer.setVisible(true);
            spacer.setManaged(true);
        }
    }

    private void addPlannedSemesters(ArrayList<PlannedCourse> courses) {
        Collections.sort(courses, Collections.reverseOrder());

        String blockLabel = "";
        VBox semester = null;
        GridPane details = null;
        int row = 0;
        int col = 0;

        for (PlannedCourse plannedCourse : courses) {
            if (!plannedCourse.getTerm().toString().equals(blockLabel)) {
                semester = getSemesterVBox();
                details = (GridPane) semester.lookup("#semesterDetails");
                blockLabel = plannedCourse.getTerm().toString();
                ((Label) semester.lookup("#semesterName")).setText(blockLabel);
                ((HBox) semester.lookup("#status")).setStyle("-fx-background-radius: 20; -fx-background-color: rgb(244, 81, 108);");
                row = 0;
            }
            col = 0;
            row++;
            Course course = plannedCourse.getCourse();


            ArrayList<Label> labels = new ArrayList<Label>();
            Collections.addAll(
                labels,
                new Label(course.getShortName()),
                new Label(course.getName()),
                new Label("" + course.getCreditHours()),
                new Label(Grade.D.toString()),
                new Label("Computer Science - Major")
            );

            EventHandler<MouseEvent> clickEvent = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(course);
                }
            };

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
                label.setOnMouseClicked(clickEvent);
                GridPane.setRowIndex(label, row);
                GridPane.setColumnIndex(label, col++);
                details.getChildren().add(label);

                label.setStyle("-fx-padding: 3 5;");
                label.setMaxHeight(Double.MAX_VALUE);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setOnMouseEntered(enterEvent);
                label.setOnMouseExited(exitEvent);
            }

            HBox spacer = (HBox) semester.lookup("#spacer");

            details.setVisible(true);
            details.setManaged(true);
            spacer.setVisible(true);
            spacer.setManaged(true);
        }
    }
}
