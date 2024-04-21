package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import degreesmart.model.AdvisingNote;
import degreesmart.model.Application;
import degreesmart.model.StudentApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;

public class StudentAdvisingNoteController extends StudentController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TableView<AdvisingNote> sanTable;


    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
       
        super.initialize(url, rb);
        StudentApplication application = StudentApplication.getInstance();
        String title = Application.getInstance().getFirstName() + "'s Advising Notes";
        headerPaneController.getPageTitle().setText(title);

        // Setup TableView columns
        TableColumn<AdvisingNote, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new Callback<CellDataFeatures<AdvisingNote, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<AdvisingNote, String> n) {
                 return new SimpleStringProperty(n.getValue().getTime().toString());
             }
          });

        TableColumn<AdvisingNote, String> advisorNameColumn = new TableColumn<>("Advisor Name");
        advisorNameColumn.setCellValueFactory(new Callback<CellDataFeatures<AdvisingNote, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<AdvisingNote, String> n) {
                 return new SimpleStringProperty(n.getValue().getAuthor().getFirstName() + n.getValue().getAuthor().getLastName());
             }
          });

        TableColumn<AdvisingNote, String> advisingNotesColumn = new TableColumn<>("Advising Notes");
        advisingNotesColumn.setCellValueFactory(new Callback<CellDataFeatures<AdvisingNote, String>, ObservableValue<String>>() {
             public ObservableValue<String> call(CellDataFeatures<AdvisingNote, String> n) {
                 return new SimpleStringProperty(n.getValue().getNote());
             }
          });

        sanTable.getColumns().addAll(dateColumn, advisorNameColumn, advisingNotesColumn);

        sanTable.setItems(FXCollections.observableArrayList(StudentApplication.getInstance().getActiveUser().getAdvisingNotes()));
    }
}
