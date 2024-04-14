package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AdminHomeController extends AdminController implements Initializable {
    @FXML
    private GridPane background;

    @FXML
    private HeaderPaneController headerPaneController;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        background.setStyle("-fx-background-image: url(\"" 
            + App.class.getResource("/images/background.jpg").toExternalForm() +"\");" +
            "-fx-background-size: cover;" +
            "-fx-background-position: center;");

        headerPaneController.getPageTitle().setText("Welcome");


    }
}