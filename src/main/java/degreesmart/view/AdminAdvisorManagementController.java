package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AdminAdvisorManagementController extends AdminController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private ComboBox comboBox;

    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        headerPaneController.getPageTitle().setText("Advisor Management");

        comboBox.setValue("Search for");

        // Stuck here.. not sure how to set the dropdown options?
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    switch(comboBox.getValue().toString().toLowerCase()) {
                    case "first name, last name":
                        break;
                    case "USC ID":
                        break;
                    }
                } catch (Exception e) {

                }
            }
        });
    }
}