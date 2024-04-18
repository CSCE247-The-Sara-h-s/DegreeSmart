module degreesmart {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires javafx.base;
    requires javafx.graphics;

    opens degreesmart.view to javafx.fxml;

    exports degreesmart.view;
    exports degreesmart.model;
}
