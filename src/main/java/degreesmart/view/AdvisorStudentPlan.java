package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AdvisorStudentPlan extends AdvisorController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;
    
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
         headerPaneController.getPageTitle().setText("VIEW STUDENTS");
     }
    
}
