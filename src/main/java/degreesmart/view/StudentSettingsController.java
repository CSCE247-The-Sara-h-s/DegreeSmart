package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import java.lang.reflect.*;

public class StudentSettingsController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private GridPane content;

    @FXML
    private VBox settingsIconContainer;

    @FXML
    private ImageView settingsIconImageView;

    public void initialize(URL url, ResourceBundle rb) {
        content.setStyle("-fx-background-image: url(\"" 
            + App.class.getResource("/images/background.jpg").toExternalForm() +"\");");

        headerPaneController.getPageTitle().setText("Settings");

        headerPaneController.getComboBox().setValue("Home");
        headerPaneController.getComboBox().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    App.setRoot("student-graduation-plan");
                } catch (Exception e) {
                }
            }
        });
    }
}
