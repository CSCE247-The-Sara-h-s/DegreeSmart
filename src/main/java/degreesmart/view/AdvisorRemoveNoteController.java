package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import degreesmart.model.Advisor;
import degreesmart.model.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AdvisorRemoveNoteController extends AdvisorController implements Initializable {
    
    @FXML
    private HeaderPaneController headerPaneController;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("Advising Notes");

    }
}
