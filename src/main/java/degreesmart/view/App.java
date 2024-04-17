package degreesmart.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import degreesmart.model.UserList;
import degreesmart.model.Application;
import degreesmart.model.StudentApplication;

import java.io.IOException;

public class App extends javafx.application.Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("DegreeSmart");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/icon.png")));
        scene = new Scene(loadFXML("loginpage"), 710, 675);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/library/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showUserHomePage() {
        if (Application.getInstance().userLoggedIn()) {
            switch (Application.getInstance().getActiveUser().getRole()) {
            case ADMINISTRATOR:
                setRoot("admin-home");
                break;
            case ADVISOR:
                setRoot("student-list");
                break;
            case STUDENT:
                setRoot("student-graduation-plan");
                break;
            case PARENT:
                setRoot("loginpage");
                Application.getInstance().logOut();
                break;
            }
        } else {
            // TODO - display failed login message
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
