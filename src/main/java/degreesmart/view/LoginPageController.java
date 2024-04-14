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
import degreesmart.model.Role;

public class LoginPageController implements Initializable {
	@FXML
	private TextField usernameField;

	@FXML
	private TextField passwordField;
    
    @FXML
    private void logIn(MouseEvent event) {
    	Application application = Application.getInstance();

    	application.logIn(usernameField.getText(), passwordField.getText());

    	if (application.userLoggedIn()) {
    		try {
    			switch (application.getActiveUser().getRole()) {
		    	case ADMINISTRATOR:
		    		App.setRoot("admin-home");
		    		application.logOut();
		    		break;
		    	case ADVISOR:
		    		App.setRoot("student-list");
		    		application.logOut();
		    		break;
		    	case STUDENT:
		    		App.setRoot("student-graduation-plan");
		    		break;
		    	case PARENT:
		    		App.setRoot("loginpage");
		    		application.logOut();
		    		break;
		    	}
	    	} catch (Exception e) {
	    	}
    	} else {
    		// TODO - display failed login message
    	}
    }

    public void initialize(URL url, ResourceBundle rb) {
    	if (Application.getInstance().userLoggedIn()) {
    		Application.getInstance().logOut();
    	}
    	usernameField.setPromptText("<testing> Enter a user type here: (\"student\", etc..)");
    	// https://stackoverflow.com/a/38374747
    	Platform.runLater(() -> usernameField.getParent().requestFocus());
    }
}