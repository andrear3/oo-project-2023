package Controller;

import ImpDAO.FotoDAOImp;
import ImpDAO.UtenteDAOImp;
import Model.Photo;
import Model.Utente;

import java.sql.SQLException;


public class Controller {

    //Variabili
    //UTENTE
    private Utente activeUser = new Utente();
    private UtenteDAOImp utenteDAO = new UtenteDAOImp();

    //
    private Photo PH =new Photo();
    private FotoDAOImp photoDAO=new FotoDAOImp();

    //Metodi
    public boolean checkUtenteExistsCTRL(String nickname,String password) throws SQLException {
            return utenteDAO.checkUtenteExists(nickname,password);
    }

    public Utente getUtenteDBCTRL(String nickname) throws SQLException {
        return utenteDAO.getUtenteDB(nickname);
    }
    //foto
     public Photo fotoStessoLuogoCTRL(String location_name) throws SQLException{ // da connettere ad una schermata
        return photoDAO.fotoStessoLuogo((location_name));
     }

    public Controller(){
    };
}
