module com.apiot.picviewer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.apiot.picviewer to javafx.fxml;
    exports com.apiot.picviewer;
}