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



    protected ObservableList<Person> data = FXCollections.observableArrayList(
        new Person("Bennett, Lucas G.", "Sophomore", "Computer Science"),
        new Person("Brooks, Nathaniel L.", "Senior", "Computer Information Science"),
        new Person("Dawson, Henry A.", "Sophomore", "Computer Information Science"),
        new Person("Donovan, Aurora B.", "Sophomore", "Computer Information Science"),
        new Person("Hill, Tawnie M.", "Senior", "Computer Science"),
        new Person("Kate, Mary L.", "Freshman", "Computer Information Science"),
        new Person("Montgomery, Elijah C.", "Sophomore", "Computer Science"),
        new Person("Parker, Oliver D.", "Senior", "Computer Science"),
        new Person("Ramirez, Sophia E.", "Junior", "Computer Science"),
        new Person("Smith, John N.", "Freshman", "Computer Information Science"),
        new Person("Thompson, Isabella S.", "Junior", "Computer Science"),
        new Person("Wilson, Rhett J.", "Junior", "Computer Information Science")

    );

    public static class Person {
        private final StringProperty name;
        private final StringProperty year;
        private final StringProperty major;

        public Person(String name, String year,String major) {
            this.name = new SimpleStringProperty (name);
            this.year = new SimpleStringProperty (year);
            this.major = new SimpleStringProperty (major);
        }

        @SuppressWarnings("exports")
        public StringProperty nameProperty() {
            return name;
        }

        @SuppressWarnings("exports")
        public StringProperty yearProperty() {
            return year;
        }

        @SuppressWarnings("exports")
        public StringProperty majorProperty() {
            return major;
        }

        public String toString() {
            return name.toString();
        }
    }



}
