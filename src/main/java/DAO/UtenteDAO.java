package DAO;

import Model.Utente;
import java.sql.SQLException;
import java.sql.Date;

public interface UtenteDAO {
    //printGender è temporanea
    void printGender(String nickname) throws SQLException;

    //String setUtenteDB(String nickname, String name, String surname, Date Birthdate, String gender);

    Utente getUtenteDB(String nickname) throws SQLException; //Lazy load?

    //[]int getGalleriaPersonaleDB(String nickname);

    //[]String getListaCollezioniPubbliche(String nickname);
}
