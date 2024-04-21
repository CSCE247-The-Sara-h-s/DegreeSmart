package degreesmart.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import degreesmart.model.Application;
import degreesmart.model.UserList;
import degreesmart.model.Student;
import degreesmart.model.Advisor;

public class AdvisorNotesHomeController extends AdvisorController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<Student> studentTable;


    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Advising Notes");
        Advisor advisor = (Advisor) Application.getInstance().getActiveUser();

        studentTable.setItems(FXCollections.observableList(advisor.getAssignedStudents()));
    }

    @FXML
    private void search() {
        studentTable.setItems(
            FXCollections.observableList(
                UserList.getInstance().searchStudents(
                    searchBar.getText(), ((Advisor) Application.getInstance().getActiveUser()).getAssignedStudents())));
    }

    @FXML
    private void viewStudentNotes(MouseEvent event) {
        Application.getInstance().selectStudent((Student) studentTable.getSelectionModel().getSelectedItem());
        App.setRoot("modify-advisor-notes");
    }
}
