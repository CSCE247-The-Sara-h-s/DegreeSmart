package degreesmart.view;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudentListController extends AdvisorController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> nameCol;
    
    @FXML
    private TableColumn<Person, String> yearCol;

    @FXML
    private TableColumn<Person, String> majorCol;

    private ObservableList<Person> data = FXCollections.observableArrayList(
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

    public void initialize(URL url, ResourceBundle rb) {
       super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Student List");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        majorCol.setCellValueFactory(cellData -> cellData.getValue().majorProperty());

        tableView.setItems(data);
    }

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
    }

}