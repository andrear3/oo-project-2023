package DAO;

import Model.Utente;
import java.sql.SQLException;

public interface UtenteDAO {
    void printGender(String nickname) throws SQLException;
}
