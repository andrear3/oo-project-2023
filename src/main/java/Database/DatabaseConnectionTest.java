package Database;

import ImpDAO.*;
import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import ImpDAO.FotoDAOImp;
import ImpDAO.User_TagDAOImp;


public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try {
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

            if (databaseConnection != null && databaseConnection.getConnection() != null) {
                System.out.println("TEST: Connessione riuscita");
               // UtenteDAOImp utenteDAO = new UtenteDAOImp();

               // Scanner scanner = new Scanner(System.in);
               // System.out.println("Inserisci il nickname per conoscere il suo gender: ");
               // String nicknameTest = scanner.nextLine();
               // scanner.close();

                //Utente temp = new Utente();
               // temp = utenteDAO.getUtenteDB(nicknameTest);
               // utenteDAO.printGender(nicknameTest);

               // System.out.println(temp.getBirthdateUtente());
               // System.out.println(temp.getNameUtente());

                FotoDAOImp fotoDAO =new FotoDAOImp();
                ArrayList<Integer> foto = new ArrayList<Integer>();
                foto=fotoDAO.fotoStessoLuogo("Napoli");

                LocationDAOImp luogoDao=new LocationDAOImp();
                ArrayList<String> luogo= new ArrayList<String>();
                luogo=luogoDao.top3Luoghi();

                PubCollectionDAOImp pubDAO = new PubCollectionDAOImp();
                ArrayList<String> pubArray = new ArrayList<>();
                pubArray = pubDAO.getAllPubCollection();

                User_TagDAOImp usertagDAO = new User_TagDAOImp();
                String test = new String();
                test = usertagDAO.PersoneTaggate(36);
                System.out.println(test);





            } else {
                System.out.println("TEST: Connessione fallita");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
