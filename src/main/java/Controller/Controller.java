package Controller;

import ImpDAO.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class Controller {

    //Variabili

    //UTENTE
    private Utente activeUser = new Utente();
    private UtenteDAOImp utenteDAO = new UtenteDAOImp();

    //FOTO
    private Photo PH = new Photo();
    private FotoDAOImp photoDAO= new FotoDAOImp();

    //PUBLIC COLLECTION
    private PubCollection pubCollection = new PubCollection();
    private PubCollectionDAOImp pubCollectionDAOImp = new PubCollectionDAOImp();

    private User_TagDAOImp UserTagDAO = new User_TagDAOImp();

    //Metodi
    public boolean checkUtenteExistsCTRL(String nickname,String password) throws SQLException {
            return utenteDAO.checkUtenteExists(nickname,password);
    }

    public Utente getUtenteDBCTRL(String nickname) throws SQLException {
        return utenteDAO.getUtenteDB(nickname);
    }

    public ImageIcon setImgPathSize(String fileName, Integer width, Integer height){
        String parziale = "src/main/resources/gallery/";
        String completa = parziale + fileName;
        ImageIcon temp = new ImageIcon(new ImageIcon(completa).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return temp;
    }

    public ArrayList<String> getAllPubCollectionCTRL() throws SQLException {
        return pubCollectionDAOImp.getAllPubCollection();
    }
    //foto
     public ArrayList<Integer> fotoStessoLuogoCTRL(String location_name) throws SQLException{ // da connettere ad una schermata
        return photoDAO.fotoStessoLuogo((location_name));
     }
   // public ArrayList<String> top3LuoghiCTRL()throws SQLException{
     //   return LocationDAOImp.top3Luoghi();
    //}
   public Utente modPassCTRL(String nickname,String password) throws SQLException{
        return utenteDAO.modPass(nickname,password);
   }

    public ArrayList<Photo> fotoStessoUtenteCTRL(String nickname) throws SQLException {
        return photoDAO.fotoStessoUtente(nickname);
    }

    public String PersoneTaggateCTRL(Integer photo_code) throws SQLException {
        return UserTagDAO.PersoneTaggate(photo_code);
    }
    public Utente modDNCTRL(String nickname, Date dataN) throws SQLException{
        return  utenteDAO.modDN(nickname,dataN);
    }
    public Utente eliminaUCTRL(String nickname) throws  SQLException{
        return  utenteDAO.eliminaU(nickname);
    };
 public Utente registraUCTRL(String nickname, String name, String surname, Date birthdate, String gender, String password)throws SQLException{
     return  utenteDAO.registraU( nickname,name,surname,birthdate,gender,password);
 };



    public Controller(){
    };
}
