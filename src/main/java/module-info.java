module degreesmart {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens degreesmart.view to javafx.fxml;

    exports degreesmart.view;
    exports degreesmart.model;
}
