package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ModifyAdvisorNotesController extends AdvisorController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;


    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Advising Notes - S");

    }

    @FXML
    private void goBack() {
       App.setRoot("advisor-notes-home");
    }

    @FXML
    private void addAdvisorNote() {
        App.setRoot("advisor-add-note");
    }

    @FXML
    private void removeAdvisorNote() {
        App.setRoot("advisor-remove-note");
    }
    
}
