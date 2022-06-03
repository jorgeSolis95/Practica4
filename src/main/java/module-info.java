module com.example.practica4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.practica4 to javafx.fxml;
    exports com.example.practica4;
}