package degreesmart.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

public class LoginPageController implements Initializable {
	@FXML
	private TextField usernameField;
    
    @FXML
    private void DEV_test_login(KeyEvent event) {
    	if (event.getCode() != KeyCode.ENTER) {
    		return;
    	}

    	try {
    		switch (usernameField.getText().toLowerCase()) {
	    	case "admin":
	    	case "administrator":
	    		App.setRoot("adminHome");
	    		break;
	    	case "advisor":
	    		App.setRoot("student-list");
	    		break;
	    	case "student":
	    		App.setRoot("student-graduation-plan");
	    		break;
	    	case "parent":
	    		App.setRoot("loginpage");
	    		break;
	    	}
    	} catch (Exception e) {
    	}
    }

    public void initialize(URL url, ResourceBundle rb) {
    	usernameField.setPromptText("<testing> Enter a user type here: (\"student\", etc..)");
    	// https://stackoverflow.com/a/38374747
    	Platform.runLater(() -> usernameField.getParent().requestFocus());
    }
}