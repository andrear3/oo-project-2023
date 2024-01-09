package Controller;
import DAO.UtenteDAO;
import Model.*;
import ImpDAO.*;

import java.sql.SQLException;


public class Controller {

    //Variabili
    private Utente activeUser = new Utente();
    private UtenteDAOImp utenteDAO = new UtenteDAOImp();

    //Metodi
    public boolean checkUtenteExistsCTRL(String nickname, String password) throws SQLException {
            return utenteDAO.checkUtenteExists(nickname, password);
    }

    public Utente getUtenteDBCTRL(String nickname) throws SQLException {
        return utenteDAO.getUtenteDB(nickname);
    }

    public Controller(){
    };
}
