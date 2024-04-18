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

        nameCol.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> col) {
                final TableCell<Person, String> cell = new TableCell<>();
                cell.textProperty().bind(cell.itemProperty());
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            App.setRoot("advisor-student-plan");
                        }
                    }
                });
                return cell ;
            }
        });
        yearCol.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> col) {
                final TableCell<Person, String> cell = new TableCell<>();
                cell.textProperty().bind(cell.itemProperty());
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            App.setRoot("advisor-student-plan");
                        }
                    }
                });
                return cell ;
            }
        });
        majorCol.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> col) {
                final TableCell<Person, String> cell = new TableCell<>();
                cell.textProperty().bind(cell.itemProperty());
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            App.setRoot("advisor-student-plan");
                        }
                    }
                });
                return cell ;
            }
        });
        
    }


}