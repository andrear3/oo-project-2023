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
    private UtenteDAOImp utenteDAOImp = new UtenteDAOImp();
    //FOTO
    private Photo photo = new Photo();
    private FotoDAOImp fotoDAOImp = new FotoDAOImp();

    //PUBLIC COLLECTION
    private Pub_Collection pubCollection = new Pub_Collection();
    private Pub_CollectionDAOImp pubCollectionDAOImp = new Pub_CollectionDAOImp();
    private User_TagDAOImp userTagDAOImp = new User_TagDAOImp();
    private LocationDAOImp top3 =new LocationDAOImp();
    private Photo_TagDAOImp photoTagDAOImp = new Photo_TagDAOImp();
    private LocationDAOImp locationDAOImp = new LocationDAOImp();
    private VideoDAOImp videoDAOImp = new VideoDAOImp();

    //Metodi
    public boolean checkUtenteExistsCTRL(String nickname,String password) throws SQLException {
            return utenteDAOImp.checkUtenteExists(nickname,password);
    }
    public Utente getUtenteDBCTRL(String nickname) throws SQLException {
        return utenteDAOImp.getUtenteDB(nickname);
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
        return fotoDAOImp.getAllInfoFromPhotoCodes(photoCodes);
    }
    public ArrayList<String> getAllPubCollectionCTRL() throws SQLException {
        return pubCollectionDAOImp.getAllPubCollection();
    }
    public ArrayList<String> getAllPubCollection2CTRL() throws SQLException {
        return pubCollectionDAOImp.getAllPubCollection2();
    }
    public ArrayList<String> getAllTags() throws SQLException {
        return photoTagDAOImp.getAllTags();
    }
    public ArrayList<String> getAllLocations() throws SQLException {
        return locationDAOImp.getAllLocations();
    }
     public ArrayList<Integer> fotoStessoLuogoCTRL(String location_name) throws SQLException{ // da connettere ad una schermata
        return fotoDAOImp.fotoStessoLuogo((location_name));
     }
    public ArrayList<String> top3LuoghiCTRL()throws SQLException{//da connettere a schermata per visualizzare immagine
       return top3.top3Luoghi();
    }
   public Utente modPassCTRL(String nickname,String password) throws SQLException{
        return utenteDAOImp.modPass(nickname,password);
   }
    public ArrayList<Photo> fotoStessoUtenteCTRL(String nickname) throws SQLException {
        return fotoDAOImp.fotoStessoUtente(nickname);
    }
    public ArrayList<Integer> foto_StessoUtente_o_PubblicheCTRL(String nickname) throws SQLException{
        return fotoDAOImp.fotoStessoUtenteOrPubbliche(nickname);
    }
    public void deletePhotoCTRL(Integer photo_code) throws SQLException{
        fotoDAOImp.deletePhoto(photo_code);
    }

    public void publicPhotoCTRL(Integer photo_code) throws SQLException{
        fotoDAOImp.publicPhoto(photo_code);
    }

    public void privatePhotoCTRL(Integer photo_code) throws SQLException{
        fotoDAOImp.privatePhoto(photo_code);
    }
    public ArrayList<Integer> fotoStessoSoggettoCTRL(String tag_name) throws SQLException {
        return photoTagDAOImp.fotoStessoSoggetto(tag_name);
    }
    public String PersoneTaggateCTRL(Integer photo_code) throws SQLException {
        return userTagDAOImp.personeTaggate(photo_code);
    }
    public String SoggettoInFotoCTRL(Integer photo_code) throws SQLException {
        return photoTagDAOImp.soggettoInFoto(photo_code);
    }
    public void aggiungiSoggettoCTRL(Integer photo_code, String tag_name) throws SQLException {
        photoTagDAOImp.aggiungiSoggetto(photo_code, tag_name);
    }
    public Photo aggiungiUserTagCTRL(Integer photo_code, String nickname2) throws SQLException {
        return userTagDAOImp.aggiungiUserTag(photo_code, nickname2);
    }
    public Utente modDNCTRL(String nickname, Date dataN) throws SQLException{
        return  utenteDAOImp.modDataNascita(nickname,dataN);
    }
    public Utente eliminaUCTRL(String nickname) throws  SQLException{
        return  utenteDAOImp.eliminaUtente(nickname);
    };
    public Utente registraUCTRL(String nickname, String name, String surname, Date birthdate, String gender, String password)throws SQLException{
        return  utenteDAOImp.registraUtente( nickname,name,surname,birthdate,gender,password);
    };
    public Photo insertPhotoCTRL(Integer photo_code, String scope, String nickname, String location_name, String device, LocalDate photo_date, String path,Double x, Double y) throws SQLException{
        return fotoDAOImp.insertPhoto(photo_code, scope, nickname, location_name, device, photo_date, path,x,y);
    };
    public Location aggLocationPhotoCTRL(String location_name,Double x_coordinates,Double y_coordinates,Integer poto_count)throws SQLException{
        return locationDAOImp.aggLocationPhoto(location_name,x_coordinates,y_coordinates,poto_count);
    }
    public void aggiungiSoggetto2etagCTRL(Integer photo_code, String tag_name,String tag_utente) throws SQLException {
         photoTagDAOImp.aggiungiSoggetto2etag(photo_code, tag_name,tag_utente);
    }
    public Integer getPhoto_codeCTRL(Integer a)throws SQLException{
        return photoTagDAOImp.getPhotoCode(a);
    }
    public String getPathCTRL(Integer photo_code)throws SQLException{
       return fotoDAOImp.getPath(photo_code);
    }
    public ArrayList<Video> videoStessoUtenteCTRL(String nickname) throws SQLException {
        return videoDAOImp.videoStessoUtente(nickname);
    }
    public ArrayList<Photo> fotoInVideoCTRL(Integer video_code) throws SQLException {
        return videoDAOImp.fotoInVideo(video_code);
    }
    public String listafotovideoCTRL(Integer video_code) throws SQLException{
        return videoDAOImp.listaFotoVideo(video_code);
    }
    public void modificaTitoloCTRL(Integer video_code, String title) throws SQLException{
        videoDAOImp.modificaTitolo(video_code, title);
    }
    public void modificaDescCTRL(Integer video_code, String desc) throws SQLException{
        videoDAOImp.modificaDesc(video_code, desc);
    }
    public void aggiungiFotoVideoCTRL(Integer video_code, Integer photo_code) throws SQLException{
        videoDAOImp.aggiungiFotoVideo(video_code, photo_code);
    }
    public void eliminaFotoVideoCTRL(Integer video_code, Integer photo_code) throws SQLException{
        videoDAOImp.eliminaFotoVideo(video_code, photo_code);
    }
    public void nuovoVideoCTRL(String desc, String title, String nickname) throws SQLException{
        videoDAOImp.nuovoVideo(desc, title, nickname);
    }
    public void deleteVideoCTRL(Integer video_code) throws SQLException{
        videoDAOImp.deleteVideo(video_code);
    }
    public Controller(){
    };
}
