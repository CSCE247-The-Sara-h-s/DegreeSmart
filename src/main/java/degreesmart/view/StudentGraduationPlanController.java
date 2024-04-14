package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

import degreesmart.model.Application;
import degreesmart.model.Student;

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
        Application.getInstance().logIn("Hdawson", "ILoveFootball35");
        headerPaneController.getPageTitle().setText("Graduation Plan");

        Student student = (Student) Application.getInstance().getActiveUser();

        try {
            degree.setText(student.getGraduationPlan().getRequirementSets().get(0).getName());
        } catch (Exception e) {
        }
        classification.setText("TODO");
        overallGpa.setText("" + student.getGpa());
        majorGpa.setText("TODO");
        creditsEarned.setText("" + student.getAttemptedHours());
        
        addSemester("Fall 2022", 1, 5, 0, 235, 16, 16,
            new String[]{
                "ENGL 101 Critical Reading and Composition",
                "MATH 141 Calculus I",
                "CSCE 145 Algorithmic Design I",
                "CSCE 190 Computing in the Modern World",
                "DANC 101 Dance Appreciation",
            },
            new String[]{
                "C",
                "C",
                "C",
                "C"
            },
            new double[]{
                3,
                4,
                4,
                1,
                3
            },
            new String[]{
                "A",
                "B+",
                "A",
                "A",
                "A"
            },
            new String[]{
                "",
                "C or better in MATH 112/115/116 or Math placement test score",
                "Prereq or Coreq: MATH 111 or 115",
                "Prereq or Coreq: CSCE 145, 204, 205, 206"
            }
        );
        addSemester("Spring 2023", 2, 5, 16, 235, 13, 16,
            new String[]{
                "ENGL 101 Critical Reading and Composition",
                "MATH 141 Calculus I",
                "CSCE 145 Algorithmic Design I",
                "CSCE 190 Computing in the Modern World",
                "DANC 101 Dance Appreciation",
            },
            new String[]{
                "C",
                "C",
                "C",
                "C"
            },
            new double[]{
                3,
                4,
                4,
                1,
                3
            },
            new String[]{
                "A",
                "B+",
                "A",
                "A",
                "A"
            },
            new String[]{
                "",
                "C or better in MATH 112/115/116 or Math placement test score",
                "Prereq or Coreq: MATH 111 or 115",
                "Prereq or Coreq: CSCE 145, 204, 205, 206"
            }
        );
        addSemester("Fall 2023", 3, 5, 29, 235, 12, 16,
            new String[]{
                "ENGL 101 Critical Reading and Composition",
                "MATH 141 Calculus I",
                "CSCE 145 Algorithmic Design I",
                "CSCE 190 Computing in the Modern World",
                "DANC 101 Dance Appreciation",
            },
            new String[]{
                "C",
                "C",
                "C",
                "C"
            },
            new double[]{
                3,
                4,
                4,
                1,
                3
            },
            new String[]{
                "A",
                "B+",
                "A",
                "A",
                "A"
            },
            new String[]{
                "",
                "C or better in MATH 112/115/116 or Math placement test score",
                "Prereq or Coreq: MATH 111 or 115",
                "Prereq or Coreq: CSCE 145, 204, 205, 206"
            }
        );
        addSemester("Spring 2024", 4, 5, 31, 235, 9, 16,
            new String[]{
                "ENGL 101 Critical Reading and Composition",
                "MATH 141 Calculus I",
                "CSCE 145 Algorithmic Design I",
                "CSCE 190 Computing in the Modern World",
                "DANC 101 Dance Appreciation",
            },
            new String[]{
                "C",
                "C",
                "C",
                "C"
            },
            new double[]{
                3,
                4,
                4,
                1,
                3
            },
            new String[]{
                "A",
                "B+",
                "A",
                "A",
                "A"
            },
            new String[]{
                "",
                "C or better in MATH 112/115/116 or Math placement test score",
                "Prereq or Coreq: MATH 111 or 115",
                "Prereq or Coreq: CSCE 145, 204, 205, 206"
            }
        );
        addSemester("Fall 2024", 5, 5, 40, 235, 4, 16,
            new String[]{
                "ENGL 101 Critical Reading and Composition",
                "MATH 141 Calculus I",
                "CSCE 145 Algorithmic Design I",
                "CSCE 190 Computing in the Modern World",
                "DANC 101 Dance Appreciation",
            },
            new String[]{
                "C",
                "C",
                "C",
                "C"
            },
            new double[]{
                3,
                4,
                4,
                1,
                3
            },
            new String[]{
                "A",
                "B+",
                "A",
                "A",
                "A"
            },
            new String[]{
                "",
                "C or better in MATH 112/115/116 or Math placement test score",
                "Prereq or Coreq: MATH 111 or 115",
                "Prereq or Coreq: CSCE 145, 204, 205, 206"
            }
        );
    }

    public void addSemester(String label, int numCourses, int maxCourses, int completedHours, int totalCreditHours, 
            int creditHours, int maxCreditHours, String[] courses, String[] minGrades, double[] credits, String[] grades,
            String[] prerequisites/*, String description*/) {
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
            ((PieChart)semester.lookup("#numCourses")).getData().add(
                new PieChart.Data("a", ((1.0 * numCourses) / maxCourses) * 100));
            ((PieChart)semester.lookup("#numCourses")).getData().add(
                new PieChart.Data("b", 100 - ((1.0 * numCourses) / maxCourses) * 100));
            ((PieChart)semester.lookup("#totalCreditHours")).getData().add(
                new PieChart.Data("a", ((1.0 * completedHours) / totalCreditHours) * 100));
            ((PieChart)semester.lookup("#totalCreditHours")).getData().add(
                new PieChart.Data("b", 100 - ((1.0 * completedHours) / totalCreditHours) * 100));
            ((PieChart)semester.lookup("#creditHours")).getData().add(
                new PieChart.Data("a", ((1.0 * creditHours) / maxCreditHours) * 100));
            ((PieChart)semester.lookup("#creditHours")).getData().add(
                new PieChart.Data("b", 100 - ((1.0 * creditHours) / maxCreditHours) * 100));

            ((Label)semester.lookup("#coursesStatus")).setText(numCourses + "/" + maxCourses);
            ((Label)semester.lookup("#totalCreditHoursStatus")).setText(completedHours + "/" + totalCreditHours);
            ((Label)semester.lookup("#creditHoursStatus")).setText(creditHours + "/" + maxCreditHours);

            GridPane details = (GridPane)semester.lookup("#semesterDetails");
            for (int i = 0; i < courses.length; i++) {
                Label tmp = new Label(courses[i]);
                GridPane.setRowIndex(tmp, i + 1);
                GridPane.setColumnIndex(tmp, 0);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < minGrades.length; i++) {
                Label tmp = new Label(minGrades[i]);
                GridPane.setRowIndex(tmp, i + 1);
                GridPane.setColumnIndex(tmp, 1);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < credits.length; i++) {
                Label tmp = new Label("" + credits[i]);
                GridPane.setRowIndex(tmp, i + 1);
                GridPane.setColumnIndex(tmp, 2);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < prerequisites.length; i++) {
                Label tmp = new Label("" + prerequisites[i]);
                GridPane.setRowIndex(tmp, i + 1);
                GridPane.setColumnIndex(tmp, 3);
                details.getChildren().add(tmp);
            }

            for (int i = 0; i < grades.length; i++) {
                Label tmp = new Label("" + grades[i]);
                GridPane.setRowIndex(tmp, i + 1);
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
