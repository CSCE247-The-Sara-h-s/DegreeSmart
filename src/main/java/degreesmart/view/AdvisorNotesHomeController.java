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


    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Advising Notes");

        listView.setItems(data);
    }

    @FXML
    private void search() {
        String searchText = searchBar.getText();
        ObservableList<Person> searchResults = data.filtered(item -> item.nameProperty().equals(searchText));
        listView.setItems(searchResults);
    }


}
