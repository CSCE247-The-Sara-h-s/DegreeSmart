module degreesmart.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens degreesmart.project to javafx.fxml;
    exports degreesmart.project;
}
