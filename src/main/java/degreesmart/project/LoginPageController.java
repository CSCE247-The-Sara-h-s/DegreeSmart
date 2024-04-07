package degreesmart.project;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class LoginPageController {
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
	    		App.setRoot("adminHome");
	    		break;
	    	case "advisor":
	    		App.setRoot("loginpage");
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
}