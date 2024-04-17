package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AdvisorNotesHomeController extends AdvisorController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private TextField searchBar;
    


    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        headerPaneController.getPageTitle().setText("ADVISING NOTES");
    }

}
