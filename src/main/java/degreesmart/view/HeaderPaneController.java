package degreesmart.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

public class HeaderPaneController {
	@FXML
	private HBox links;

	@FXML
	private Label pageTitle;

	@FXML
	private ComboBox headerComboBox;

	public HBox getLinks() {
		return links;
	}

	public Label getPageTitle() {
		return pageTitle;
	}

	public ComboBox getComboBox() {
		return headerComboBox;
	}
}
