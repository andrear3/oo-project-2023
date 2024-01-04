package Database;

import ImpDAO.UtenteDAOImp;
import Model.Utente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

            if (databaseConnection != null && databaseConnection.getConnection() != null) {
                System.out.println("TEST: Connessione riuscita");
                UtenteDAOImp utenteDAO = new UtenteDAOImp();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Inserisci il nickname per conoscere il suo gender: ");
                String nicknameTest = scanner.nextLine();
                scanner.close();

                Utente temp = new Utente();
                temp = utenteDAO.getUtenteDB(nicknameTest);
                utenteDAO.printGender(nicknameTest);

                System.out.println(temp.getBirthdateUtente());
                System.out.println(temp.getNameUtente());


            } else {
                System.out.println("TEST: Connessione fallita");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
