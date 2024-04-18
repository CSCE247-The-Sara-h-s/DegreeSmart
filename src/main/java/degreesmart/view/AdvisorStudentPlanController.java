package degreesmart.view;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AdvisorStudentPlanController extends AdvisorController implements Initializable {

    @FXML
    private HeaderPaneController headerPaneController;
    
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
         headerPaneController.getPageTitle().setText("VIEW STUDENTS");
     }
    

     @FXML
     private void goBack() {
        App.setRoot("student-list");
     }
}
