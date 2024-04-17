package degreesmart.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HeaderPaneController {
	@FXML
	private HBox links;

	@FXML
	private Label pageTitle;


	public HBox getLinks() {
		return links;
	}

	public Label getPageTitle() {
		return pageTitle;
	}

	@FXML
    private void showUserHomePage(MouseEvent event) {
        App.showUserHomePage();
    }

	@FXML
    private void handleSettingsClick(MouseEvent event) {
        App.setRoot("student-settings");
        
    }

    @FXML
    private void handleLogoutClick(MouseEvent event) {
        App.setRoot("loginpage");
    }
}
