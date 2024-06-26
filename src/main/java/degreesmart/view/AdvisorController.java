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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import degreesmart.model.Student;


public class AdvisorController implements Initializable {
	@FXML
    private HeaderPaneController headerPaneController;

    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        
       	Label studentList = new Label("Student List");
        Label advisorNotes = new Label("Advisor Notes");

        studentList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("student-list");
                } catch (Exception e) {
                }
            }
        });
        advisorNotes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("advisor-notes-home");
                } catch (Exception e) {
                }
            }
        });

        HBox.setHgrow(studentList, Priority.ALWAYS);
        HBox.setHgrow(advisorNotes, Priority.ALWAYS);

        studentList.setMaxWidth(Double.MAX_VALUE);
        advisorNotes.setMaxWidth(Double.MAX_VALUE);

        studentList.setAlignment(Pos.CENTER);
        advisorNotes.setAlignment(Pos.CENTER);

        headerPaneController.getLinks().getChildren().add(studentList);
        headerPaneController.getLinks().getChildren().add(advisorNotes);
    }
}
