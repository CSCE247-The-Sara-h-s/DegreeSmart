package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import degreesmart.model.Application;
import degreesmart.model.UserList;
import degreesmart.model.Student;
import degreesmart.model.Advisor;

public class AdvisorAddController extends AdvisorController implements Initializable {
    
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Student> studentTable;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Student List");

        Advisor advisor = (Advisor) Application.getInstance().getActiveUser();

        studentTable.setItems(FXCollections.observableList(Application.getInstance().getStudents()));
    }

    @FXML
    private void search() {
        studentTable.setItems(
            FXCollections.observableList(
                UserList.getInstance().searchStudents(
                    searchBar.getText())));
    }

    @FXML
    private void addStudent(MouseEvent event) {
        ((Advisor) Application.getInstance().getActiveUser()).addAssignedStudent(studentTable.getSelectionModel().getSelectedItem());
        ((Student) studentTable.getSelectionModel().getSelectedItem()).setAdvisor((Advisor) Application.getInstance().getActiveUser());
    }
}
