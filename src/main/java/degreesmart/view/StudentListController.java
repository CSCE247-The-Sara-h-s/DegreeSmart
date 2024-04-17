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



    public void initialize(URL url, ResourceBundle rb) {
       super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Student List");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        majorCol.setCellValueFactory(cellData -> cellData.getValue().majorProperty());

        tableView.setItems(data);
    }


}