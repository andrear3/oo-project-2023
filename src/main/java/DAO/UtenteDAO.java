package DAO;

import Model.Utente;
import java.sql.SQLException;
import java.util.Date;

public interface UtenteDAO {
    boolean checkUtenteExists(String nickname, String password) throws SQLException;
    Utente getUtenteDB(String nickname) throws SQLException;
    Utente modPass(String nickname, String password) throws SQLException;
    Utente modDN(String nickname, Date dataN) throws SQLException;
    Utente modDN(String nickname, java.sql.Date dataN) throws SQLException;
    Utente eliminaU(String nickname)throws SQLException;
    Utente registraU(String nickname, String name, String surname, java.sql.Date birthdate, String gender, String password)throws SQLException;

}
