package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import java.lang.reflect.*;

public class StudentParentListController extends StudentController implements Initializable {
	@FXML
    private HeaderPaneController headerPaneController;

    public void initialize(URL url, ResourceBundle rb) {
    	super.initialize(url, rb);
    	headerPaneController.getPageTitle().setText("Parent List");
    }
}
