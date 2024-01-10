package Database;

import ImpDAO.UtenteDAOImp;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import ImpDAO.FotoDAOImp;


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

                FotoDAOImp fotoDAO =new FotoDAOImp();
                ArrayList<Integer> foto = new ArrayList<Integer>();
                foto=fotoDAO.fotoStessoLuogo("Napoli");





            } else {
                System.out.println("TEST: Connessione fallita");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
