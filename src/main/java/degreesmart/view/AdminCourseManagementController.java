package degreesmart.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.List;
import degreesmart.model.Course;
import degreesmart.model.CourseList;

public class AdminCourseManagementController extends AdminController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private Label resultLabel;

    @FXML
    private TableView<Course> courseTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        // Set up columns in the table
        TableColumn<Course, String> subjectCol = new TableColumn<>("Subject");
        subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<Course, String> numberCol = new TableColumn<>("Number");
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Course, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Course, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        courseTable.getColumns().addAll(subjectCol, numberCol, nameCol, descriptionCol);
    }

    // Method to handle search action
    @FXML
    private void searchCourses() {
        
    }

    // Method to display search results in the table
    private void showSearchResults(List<Course> courses) {
        ObservableList<Course> courseList = FXCollections.observableArrayList(courses);
        courseTable.setItems(courseList);
        resultLabel.setText("Results for " + searchField.getText().trim());
    }
}
