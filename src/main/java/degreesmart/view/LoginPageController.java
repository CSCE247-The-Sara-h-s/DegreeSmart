package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import degreesmart.model.Application;
import degreesmart.model.StudentApplication;
import degreesmart.model.Role;

public class LoginPageController implements Initializable {
	@FXML
	private TextField usernameField;

    @FXML
    private TextField showPasswordField;

	@FXML
	private PasswordField hidePasswordField;

    @FXML
    private void logInMouse(MouseEvent event) {
        logIn();
    }

    @FXML
    private void logInKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            logIn();
        }
    }

    @FXML
    private void createAccountMouse(MouseEvent event) {
    	createAccount();
    }

    @FXML
    private void createAccountKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            createAccount();
        }
    }

    @FXML
    private void focusPassword(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            hidePasswordField.requestFocus();
        }
    }

    @FXML
    private void showPassword(MouseEvent event) {
        showPassword();
    }

    @FXML
    private void hidePassword(MouseEvent event) {
        hidePassword();
    }

    public void initialize(URL url, ResourceBundle rb) {
    	if (Application.getInstance().userLoggedIn()) {
    		Application.getInstance().logOut();
    	}
    	// https://stackoverflow.com/a/38374747
    	Platform.runLater(() -> usernameField.getParent().requestFocus());
    }

    private void showPassword() {
        showPasswordField.setText(hidePasswordField.getText());
        hidePasswordField.setVisible(false);
        hidePasswordField.setManaged(false);
        showPasswordField.setVisible(true);
        showPasswordField.setManaged(true);
        showPasswordField.getParent().requestFocus();
    }

    private void hidePassword() {
        showPasswordField.setVisible(false);
        showPasswordField.setManaged(false);
        hidePasswordField.setVisible(true);
        hidePasswordField.setManaged(true);
    }

    private void logIn() {
        String message = Application.getInstance().logIn(usernameField.getText(), hidePasswordField.getText());
        if (message.length() > 0) {
            System.out.println(message);
        }

        App.showUserHomePage();
    }

    private void createAccount() {
        App.setRoot("create-account");
    }
}