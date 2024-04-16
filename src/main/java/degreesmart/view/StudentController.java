package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

import degreesmart.model.Application;

public class StudentController implements Initializable {
	@FXML
    private HeaderPaneController headerPaneController;

    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        
       	Label graduationPlan = new Label("Graduation Plan");
        Label advisingNotes = new Label("View Advising Notes");
        Label whatIf = new Label("What If Scenario");
        Label gpaCalculator = new Label("GPA Calculator");

        graduationPlan.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("student-graduation-plan");
                } catch (Exception e) {
                }
            }
        });
        advisingNotes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("advising-notes");
                } catch (Exception e) {
                }
            }
        });
        whatIf.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("what-if");
                } catch (Exception e) {
                }
            }
        });
        gpaCalculator.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("gpa-calculator");
                } catch (Exception e) {
                }
            }
        });

        HBox.setHgrow(graduationPlan, Priority.ALWAYS);
        HBox.setHgrow(advisingNotes, Priority.ALWAYS);
        HBox.setHgrow(whatIf, Priority.ALWAYS);
        HBox.setHgrow(gpaCalculator, Priority.ALWAYS);

        graduationPlan.setMaxWidth(Double.MAX_VALUE);
        advisingNotes.setMaxWidth(Double.MAX_VALUE);
        whatIf.setMaxWidth(Double.MAX_VALUE);
        gpaCalculator.setMaxWidth(Double.MAX_VALUE);

        graduationPlan.setAlignment(Pos.CENTER);
        advisingNotes.setAlignment(Pos.CENTER);
        whatIf.setAlignment(Pos.CENTER);
        gpaCalculator.setAlignment(Pos.CENTER);

        headerPaneController.getLinks().getChildren().add(graduationPlan);
        headerPaneController.getLinks().getChildren().add(advisingNotes);
        headerPaneController.getLinks().getChildren().add(whatIf);
        headerPaneController.getLinks().getChildren().add(gpaCalculator);
    }
}
