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

    private VideoDAOImp videoDAO = new VideoDAOImp();

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

    public void insertPhotoInCollectionCTRL(Integer photo_code, String collection_name) throws SQLException {
        pubCollectionDAOImp.insertPhotoInCollection(photo_code,collection_name);
    }

    public void deleteSharedPhotoCTRL(Integer photo_code) throws SQLException {
        pubCollectionDAOImp.deleteSharedPhoto(photo_code);
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

    public ArrayList<Integer> fotoStessoSoggettoCTRL(String tag_name) throws SQLException {
        return PhotoTagDAO.fotoStessoSoggetto(tag_name);
    }

    public String PersoneTaggateCTRL(Integer photo_code) throws SQLException {
        return UserTagDAO.PersoneTaggate(photo_code);
    }

    public String SoggettoInFotoCTRL(Integer photo_code) throws SQLException {
        return PhotoTagDAO.SoggettoInFoto(photo_code);
    }

    public void aggiungiSoggettoCTRL(Integer photo_code, String tag_name) throws SQLException {
        PhotoTagDAO.aggiungiSoggetto(photo_code, tag_name);
    }

    public Photo aggiungiUserTagCTRL(Integer photo_code, String nickname2) throws SQLException {
        return UserTagDAO.aggiungiUserTag(photo_code, nickname2);
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
public Photo insertPhotoCTRL(Integer photo_code, String scope, String nickname, String location_name, String device, LocalDate photo_date, String path,Double x, Double y) throws SQLException{
     return photoDAO.insertPhoto(photo_code, scope, nickname, location_name, device, photo_date, path,x,y);
};
public Location aggLocationPhotoCTRL(String location_name,Double x_coordinates,Double y_coordinates,Integer poto_count)throws SQLException{
    return locationDAO.aggLocationPhoto(location_name,x_coordinates,y_coordinates,poto_count);
}
    public Photo aggiungiSoggetto2etagCTRL(Integer photo_code, String tag_name,String tag_utente) throws SQLException {
        return PhotoTagDAO.aggiungiSoggetto2etag(photo_code, tag_name,tag_utente);
    }
    public Integer getPhoto_codeCTRL(Integer a)throws SQLException{
    return PhotoTagDAO.getPhoto_code(a);
    }
    public String getPathCTRL(Integer photo_code)throws SQLException{
       return photoDAO.getPath(photo_code);
    }
    public ArrayList<Video> videoStessoUtenteCTRL(String nickname) throws SQLException {
        return videoDAO.videoStessoUtente(nickname);
    }

    public ArrayList<Photo> fotoInVideoCTRL(Integer video_code) throws SQLException {
        return videoDAO.fotoInVideo(video_code);
    }

    public String listafotovideoCTRL(Integer video_code) throws SQLException{
        return videoDAO.listafotovideo(video_code);
    }

    public void modificaTitoloCTRL(Integer video_code, String title) throws SQLException{
        videoDAO.modificaTitolo(video_code, title);
    }
    public void modificaDescCTRL(Integer video_code, String desc) throws SQLException{
        videoDAO.modificaDesc(video_code, desc);
    }

    public void aggiungiFotoVideoCTRL(Integer video_code, Integer photo_code) throws SQLException{
        videoDAO.aggiungiFotoVideo(video_code, photo_code);
    }

    public void eliminaFotoVideoCTRL(Integer video_code, Integer photo_code) throws SQLException{
        videoDAO.eliminaFotoVideo(video_code, photo_code);
    }
    public Controller(){
    };
}
