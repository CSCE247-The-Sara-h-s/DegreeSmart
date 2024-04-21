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

public class StudentAdvisingNoteController extends StudentController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TableView<AdvisingNote> userTableView;

    @FXML
    private TextField searchField;


    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
       
        super.initialize(url, rb);
        StudentApplication application = StudentApplication.getInstance();
        String title = Application.getInstance().getFirstName() + "'s Advising Notes";
        headerPaneController.getPageTitle().setText(title);



        // Setup TableView columns
        TableColumn<AdvisingNote, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<AdvisingNote, String> advisorNameColumn = new TableColumn<>("Advisor Name");
        advisorNameColumn.setCellValueFactory(new PropertyValueFactory<>("advisorName"));

        TableColumn<AdvisingNote, String> advisingNotesColumn = new TableColumn<>("Advising Notes");
        advisingNotesColumn.setCellValueFactory(new PropertyValueFactory<>("advisingNotes"));

        userTableView.getColumns().addAll(dateColumn, advisorNameColumn, advisingNotesColumn);

        searchField.setOnKeyPressed(event -> searchUsers());

    }

    private void searchUsers() {String searchText = searchField.getText().toLowerCase().trim();

    }
}
