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
        UserList.getInstance();
        stage.setTitle("DegreeSmart");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/icon.png")));
        scene = new Scene(loadFXML("loginpage"), 710, 675);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/library/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showUserHomePage() {
        if (Application.getInstance().userLoggedIn()) {
            try {
                switch (Application.getInstance().getActiveUser().getRole()) {
                case ADMINISTRATOR:
                    setRoot("admin-home");
                    Application.getInstance().logOut();
                    break;
                case ADVISOR:
                    setRoot("student-list");
                    Application.getInstance().logOut();
                    break;
                case STUDENT:
                    StudentApplication.getInstance();
                    setRoot("student-graduation-plan");
                    break;
                case PARENT:
                    setRoot("loginpage");
                    Application.getInstance().logOut();
                    break;
                }
            } catch (Exception e) {
            }
        } else {
            // TODO - display failed login message
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
