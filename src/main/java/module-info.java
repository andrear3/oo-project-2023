module com.example.ooproject2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // API JDBC


    opens com.example.ooproject2023 to javafx.fxml;
    exports com.example.ooproject2023;
}