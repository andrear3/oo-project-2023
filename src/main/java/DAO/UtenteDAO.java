package DAO;

import Model.Utente;
import java.sql.SQLException;
import java.util.Date;

public interface UtenteDAO {
    boolean checkUtenteExists(String nickname, String password) throws SQLException;
    Utente getUtenteDB(String nickname) throws SQLException;
    Utente modPass(String nickname, String password) throws SQLException;
    Utente modDataNascita(String nickname, Date dataN) throws SQLException;
    Utente eliminaUtente(String nickname)throws SQLException;
    Utente registraUtente(String nickname, String name, String surname, java.sql.Date birthdate, String gender, String password)throws SQLException;
}
