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

import degreesmart.model.StudentApplication;

public class StudentController implements Initializable {
	@FXML
    private HeaderPaneController headerPaneController;

    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        headerPaneController.getComboBox().getItems().addAll(
            "Settings",
            "Log Out"
        );
        headerPaneController.getComboBox().setValue("Hello <user>!");
        headerPaneController.getComboBox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    switch(headerPaneController.getComboBox().getValue().toString().toLowerCase()) {
                    case "settings":
                        App.setRoot("student-settings");
                        break;
                    case "log out":
                        StudentApplication.getInstance().logOut();
                        App.setRoot("loginpage");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

       	Label graduationPlan = new Label("Graduation Plan");
        Label parents = new Label("Parent List");

        graduationPlan.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("student-graduation-plan");
                } catch (Exception e) {
                }
            }
        });
        parents.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("parent-list");
                } catch (Exception e) {
                }
            }
        });

        HBox.setHgrow(graduationPlan, Priority.ALWAYS);
        HBox.setHgrow(parents, Priority.ALWAYS);

        graduationPlan.setMaxWidth(Double.MAX_VALUE);
        parents.setMaxWidth(Double.MAX_VALUE);

        graduationPlan.setAlignment(Pos.CENTER);
        parents.setAlignment(Pos.CENTER);

        headerPaneController.getLinks().getChildren().add(graduationPlan);
        headerPaneController.getLinks().getChildren().add(parents);
    }
}
