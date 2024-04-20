package degreesmart.view;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import degreesmart.model.Application;
import degreesmart.model.UserList;
import degreesmart.model.Student;
import degreesmart.model.Advisor;

public class StudentListController extends AdvisorController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TableView<Student> tableView;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Student List");

        Advisor advisor = (Advisor) Application.getInstance().getActiveUser();
        tableView.setItems(FXCollections.observableList(advisor.getAssignedStudents()));
    }

    @FXML
    private void addStudentView() {
        App.setRoot("advisor-add");
    }

    @FXML
    private void RemoveStudentView() {
        App.setRoot("advisor-remove");
    }

    @FXML
    private void viewGraduationPlan(MouseEvent event) {
        System.out.println(tableView.getSelectionModel().getSelectedItem());
        App.setRoot("advisor-student-plan");
    }
}