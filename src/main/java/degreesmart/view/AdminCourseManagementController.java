package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AdminCourseManagementController extends AdminController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        headerPaneController.getPageTitle().setText("Course Management");
    }
}
