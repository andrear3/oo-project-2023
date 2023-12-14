module com.example.ooproject2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // API JDBC


    opens GUI to javafx.fxml;
    exports GUI;
}