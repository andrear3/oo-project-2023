package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private final String name = "postgres";
    private final String url = "jdbc:postgresql://localhost:5432/bd-project-2023";
    private final String password = "post1234";
    private final String driver = "org.postgresql.Driver";

    private Connection connection = null;
    private DatabaseConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Connessione avviata");
        } catch (SQLException e) {
            System.out.println("Errore durante la connessione");
            e.printStackTrace();
            throw new SQLException("Errore durante la connessione", e);
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.connection.isClosed()){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}

