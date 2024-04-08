package degreesmart.project;

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
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import java.lang.reflect.*;

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
        headerPaneController.getPageTitle().setText("Graduation Plan");

        degree.setText("BSCS");
        classification.setText("Sophomore");
        overallGpa.setText("3.700");
        majorGpa.setText("3.875");
        creditsEarned.setText("5");
        
        addSemester("Fall 2022", 1, 5, 0, 235, 16, 16);
        addSemester("Spring 2023", 2, 5, 16, 235, 13, 16);
        addSemester("Fall 2023", 3, 5, 29, 235, 12, 16);
        addSemester("Spring 2024", 4, 5, 31, 235, 9, 16);
        addSemester("Fall 2024", 5, 5, 40, 235, 4, 16);
    }

    public void addSemester(String label, int numCourses, int maxCourses, int completedHours, int totalCreditHours, 
            int creditHours, int maxCreditHours /*, String[] courses, String[] grades, int[] creditHours,
            String[] prerequisites, String description*/) {
        try {
           // https://coderanch.com/t/723526/java/fxml-include-dynamically-adding-removing
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("/library/graduation-plan-semester.fxml"));

            VBox semester = fxmlLoader.<VBox>load();

            ((ImageView)semester.lookup("#expandSemester")).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("TODO");
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

            semesterList.getChildren().add(semester);
        } catch (Exception e) {
        }
    }

    public void expandSemester(MouseEvent event) {
        System.out.println("TODO");
    }
}
