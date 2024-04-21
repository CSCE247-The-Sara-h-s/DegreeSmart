package degreesmart.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import degreesmart.model.Application;
import degreesmart.model.AdvisingNote;

public class ModifyAdvisorNotesController extends AdvisorController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private VBox notesList;

    @FXML
    private TextField newNote;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        loadNotes();
    }

    @FXML
    private void goBack() {
        
        Application.getInstance().selectStudent(null);
        App.setRoot("advisor-notes-home");
    }

    @FXML
    private void addAdvisorNote() {
        Application.getInstance().addAdvisingNote(Application.getInstance().getSelectedStudent(), newNote.getText());
        newNote.setText("");
        loadNotes();
    }


    private void loadNotes() {
        notesList.getChildren().clear();
        headerPaneController.getPageTitle().setText("Advising Notes - " + Application.getInstance().getSelectedStudent().getFirstName() + " " + Application.getInstance().getSelectedStudent().getLastName());

        for (AdvisingNote note : Application.getInstance().getSelectedStudent().getAdvisingNotes()) {
            GridPane row = new GridPane();
            row.getColumnConstraints().add(new ColumnConstraints(50.0));

            Label delete = new Label("(X)");
            delete.setOnMouseClicked(e -> {
                Application.getInstance().getSelectedStudent().removeAdvisingNote(note);
                loadNotes();
            });
            row.getChildren().add(delete);
            GridPane.setConstraints(delete, 0, 0);

            Label message = new Label(note.toString());
            
            row.getChildren().add(message);
            GridPane.setConstraints(message, 1, 0);

            notesList.getChildren().add(row);
        }
    }
}
