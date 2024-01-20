package Controller;

import ImpDAO.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
private LocationDAOImp top3 =new LocationDAOImp();
    private Photo_TagDAOImp PhotoTagDAO = new Photo_TagDAOImp();
    private LocationDAOImp locationDAO= new LocationDAOImp();

    //Metodi
    public boolean checkUtenteExistsCTRL(String nickname,String password) throws SQLException {
            return utenteDAO.checkUtenteExists(nickname,password);
    }

    public Utente getUtenteDBCTRL(String nickname) throws SQLException {
        return utenteDAO.getUtenteDB(nickname);
    }
    public void newPubCollectionCTRL(String collection_name) throws SQLException {
        pubCollectionDAOImp.newPubCollection(collection_name);
    }
    public ImageIcon setImgPathSize(String fileName, Integer width, Integer height){
        String parziale = "src/main/resources/gallery/";
        String completa = parziale + fileName;
        ImageIcon temp = new ImageIcon(new ImageIcon(completa).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return temp;
    }


    public ArrayList<Integer> getAllPhotoFromCollectionCTRL (String collection_name) throws SQLException {
        return pubCollectionDAOImp.getAllPhotoFromCollection(collection_name);
    }

    public ArrayList<Photo> getAllInfoFromPhotoCodesCTRL (ArrayList<Integer> photoCodes) throws SQLException {
        return photoDAO.getAllInfoFromPhotoCodes(photoCodes);
    }
    public ArrayList<String> getAllPubCollectionCTRL() throws SQLException {
        return pubCollectionDAOImp.getAllPubCollection();
    }
    //foto
     public ArrayList<Integer> fotoStessoLuogoCTRL(String location_name) throws SQLException{ // da connettere ad una schermata
        return photoDAO.fotoStessoLuogo((location_name));
     }
    public ArrayList<String> top3LuoghiCTRL()throws SQLException{//da connettere a schermata per visualizzare immagine
       return top3.top3Luoghi();
    }
   public Utente modPassCTRL(String nickname,String password) throws SQLException{
        return utenteDAO.modPass(nickname,password);
   }

    public ArrayList<Photo> fotoStessoUtenteCTRL(String nickname) throws SQLException {
        return photoDAO.fotoStessoUtente(nickname);
    }

    public String PersoneTaggateCTRL(Integer photo_code) throws SQLException {
        return UserTagDAO.PersoneTaggate(photo_code);
    }

    public String SoggettoInFotoCTRL(Integer photo_code) throws SQLException {
        return PhotoTagDAO.SoggettoInFoto(photo_code);
    }

    public Photo aggiungiSoggettoCTRL(Integer photo_code, String tag_name) throws SQLException {
        return PhotoTagDAO.aggiungiSoggetto(photo_code, tag_name);
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
public Photo insertPhotoCTRL(Integer photo_code, String scope, String nickname, String location_name, String device, LocalDate photo_date, String path) throws SQLException{
     return photoDAO.insertPhoto(photo_code, scope, nickname, location_name, device, photo_date, path);
};
public Location aggLocationPhotoCTRL(String location_name,Double x_coordinates,Double y_coordinates,Integer poto_count)throws SQLException{
    return locationDAO.aggLocationPhoto(location_name,x_coordinates,y_coordinates,poto_count);
}
    public Photo aggiungiSoggetto2CTRL(Integer photo_code, String tag_name) throws SQLException {
        return PhotoTagDAO.aggiungiSoggetto2(photo_code, tag_name);
    }
    public Controller(){
    };
}
