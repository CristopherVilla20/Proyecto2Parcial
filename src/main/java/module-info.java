module com.mycompany.proyecto2par {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyecto2par to javafx.fxml;
    exports com.mycompany.proyecto2par;
}