package DAO;

import Model.Photo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface FotoDAO {
     public ArrayList<Integer> fotoStessoLuogo(String location_name ) throws SQLException;

     ArrayList<Photo> fotoStessoUtente(String nickname ) throws SQLException;

    public ArrayList<Integer> fotoStessoUtenteOrPubbliche(String nickname) throws SQLException;

    //void DettagliFoto(String photo_code) throws SQLException;

    //void FotoUtente(String photo_code, String username) throws SQLException;

    //void FotoPrivateUtente(String photo_code, String username) throws SQLException;

    //void FotoStessoSoggetto(String soggetto);

    //void FotoStessoLuogo(String luogo);

     ArrayList<Photo> getAllInfoFromPhotoCodes (ArrayList<Integer> photoCodes) throws SQLException;

    public ArrayList<String> getPathFromPhoto(Integer photo_code) throws SQLException;
    Photo insertPhoto(Integer photo_code, String scope, String nickname, String location_name, String device, LocalDate photo_date, String path,Double x,Double y) throws SQLException;
    public void deletePhoto(Integer photo_code) throws SQLException;
    String getPath(Integer photo_code)throws SQLException;
}
