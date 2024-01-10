package Controller;

import ImpDAO.UtenteDAOImp;
import Model.Utente;

import java.sql.SQLException;


public class Controller {

    //Variabili
    //UTENTE
    private Utente activeUser = new Utente();
    private UtenteDAOImp utenteDAO = new UtenteDAOImp();
    //LOCATION


    //Metodi
    public boolean checkUtenteExistsCTRL(String nickname,String password) throws SQLException {
            return utenteDAO.checkUtenteExists(nickname,password);
    }

    public Utente getUtenteDBCTRL(String nickname) throws SQLException {
        return utenteDAO.getUtenteDB(nickname);
    }

    public Controller(){
    };
}
