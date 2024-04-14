module degreesmart {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens degreesmart.view to javafx.fxml;
    opens degreesmart.model to degreesmart;

    exports degreesmart.view;
    exports degreesmart.model;
}
