package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

            if (databaseConnection != null && databaseConnection.connection != null) {
                System.out.println("TEST: Connessione riuscita");

            } else {
                System.out.println("TEST: Connessione fallita");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
