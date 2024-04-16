package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;

import degreesmart.model.Application;
import degreesmart.model.Role;

public class CreateAccountController implements Initializable {
	@FXML
	private ComboBox roleSelect;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField uscIdField;

	@FXML
	private void createAccount(MouseEvent event) {
		String message = Application.getInstance().createAccount(
			getRole(), usernameField.getText(), passwordField.getText(),
			emailField.getText(), firstNameField.getText(), lastNameField.getText(), uscIdField.getText());

		if (message.length() > 0) {
			System.out.println(message);
		}

		App.showUserHomePage();

	}

	@FXML
	private void roleChanged(ActionEvent event) {
		if (getRole() == Role.STUDENT) {
			uscIdField.setVisible(true);
			uscIdField.setManaged(true);
		} else {
			uscIdField.setVisible(false);
			uscIdField.setManaged(false);
			uscIdField.setText("");
		}
	}

	@FXML
	private void cancel(MouseEvent event) {
		try {
    		App.setRoot("loginpage");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}

	private Role getRole() {
		switch(roleSelect.getValue().toString().toLowerCase()) {
        case "student":
            return Role.STUDENT;
        case "parent":
            return Role.PARENT;
         case "advisor":
            return Role.UNAPPROVED_ADVISOR;
        case "administrator":
            return Role.ADMINISTRATOR;
        default:
        	return null;
        }
	}

    public void initialize(URL url, ResourceBundle rb) {
    	roleSelect.getItems().addAll(
            "Student",
            "Parent",
            "Advisor",
            "Administrator"
        );
        roleSelect.setValue("Student");

    	Platform.runLater(() -> usernameField.getParent().requestFocus());
    }
}