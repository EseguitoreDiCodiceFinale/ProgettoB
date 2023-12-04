module clientES{
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.rmi;
    requires common;

    opens org.example.client to javafx.fxml;
    exports org.example.client;
}