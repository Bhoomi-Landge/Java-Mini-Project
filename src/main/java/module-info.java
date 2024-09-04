module com.example.firstproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires java.datatransfer;

    opens com.example.firstproject to javafx.fxml;
    exports com.example.firstproject;
}