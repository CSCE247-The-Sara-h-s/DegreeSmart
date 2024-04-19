package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdvisorRemoveController extends AdvisorController implements Initializable{
        
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
        headerPaneController.getPageTitle().setText("Student List");
    }

    @FXML
    private void search() {
        String searchText = searchBar.getText().toLowerCase().trim();
        if(searchText.isEmpty()) {
            searchResults.setAll(data);
        } else {
            searchResults = data.filtered(Person -> Person.toString().toLowerCase().contains(searchText));
        }
        listView.setItems(searchResults);
    }

    @FXML
    private void removeStudent() {
        String objectToRemove = searchBar.getText();
        if (student.contains(objectToRemove)) {
            student.remove(objectToRemove);
        } else {
            System.out.println("not found in the list");
        }
        // Remove A STUDENT FROM THE LIST
        //student.remove();
    }
    
}
