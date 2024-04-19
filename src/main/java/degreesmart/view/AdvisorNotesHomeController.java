package degreesmart.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdvisorNotesHomeController extends AdvisorController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<Person> listView;

    @FXML
    private ObservableList<Person> searchResults;


    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Advising Notes");

        listView.setItems(data);
    }

    @FXML
    private void search() {
        String searchText = searchBar.getText().toLowerCase().trim();
        if(searchText.isEmpty()) {
            searchResults.setAll(student);
        } else {
            searchResults = student.filtered(Person -> Person.toString().toLowerCase().contains(searchText));
        }
        listView.setItems(searchResults);
    }

    @FXML
    private void viewStudentNotes() {
        App.setRoot("modify-advisor-notes");
    }


}
