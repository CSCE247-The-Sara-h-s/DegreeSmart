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

public class AdminController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        
        
        Label studentManagement = new Label("Student Management");
        Label courseManagement = new Label("Course Management");
        Label advisorManagement = new Label("Advisor Management");

        studentManagement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("student-management");
                } catch (Exception e) {
                }
            }
        });

        courseManagement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("course-management");
                } catch (Exception e) {
                }
            }
        });

        advisorManagement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("advisor-management");
                } catch (Exception e) {
                }
            }
        });

        HBox.setHgrow(studentManagement, Priority.ALWAYS);
        HBox.setHgrow(courseManagement, Priority.ALWAYS);
        HBox.setHgrow(advisorManagement, Priority.ALWAYS);

        studentManagement.setMaxWidth(Double.MAX_VALUE);
        courseManagement.setMaxWidth(Double.MAX_VALUE);
        advisorManagement.setMaxWidth(Double.MAX_VALUE);

        studentManagement.setAlignment(Pos.CENTER);
        courseManagement.setAlignment(Pos.CENTER);
        advisorManagement.setAlignment(Pos.CENTER);

        headerPaneController.getLinks().getChildren().add(studentManagement);
        headerPaneController.getLinks().getChildren().add(courseManagement);
        headerPaneController.getLinks().getChildren().add(advisorManagement);
    }
}

