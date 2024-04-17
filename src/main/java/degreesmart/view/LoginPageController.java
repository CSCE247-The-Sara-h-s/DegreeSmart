package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

import degreesmart.model.Application;
import degreesmart.model.StudentApplication;
import degreesmart.model.Role;

public class LoginPageController implements Initializable {
	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;
    
    @FXML
    private void logIn(MouseEvent event) {
        String message = Application.getInstance().logIn(usernameField.getText(), passwordField.getText());
        if (message.length() > 0) {
            System.out.println(message);
        }

    	App.showUserHomePage();
    }

    @FXML
    private void createAccount(MouseEvent event) {
    	try {
    		App.setRoot("create-account");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public void initialize(URL url, ResourceBundle rb) {
    	if (Application.getInstance().userLoggedIn()) {
    		Application.getInstance().logOut();
    	}
    	// https://stackoverflow.com/a/38374747
    	Platform.runLater(() -> usernameField.getParent().requestFocus());
    }
}