package degreesmart.project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

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
}