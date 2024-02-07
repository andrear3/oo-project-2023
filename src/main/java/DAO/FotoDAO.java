package DAO;

import Model.Photo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface FotoDAO {
     ArrayList<Integer> fotoStessoLuogo(String location_name ) throws SQLException;
     ArrayList<Photo> fotoStessoUtente(String nickname ) throws SQLException;
     ArrayList<Integer> fotoStessoUtenteOrPubbliche(String nickname) throws SQLException;
     ArrayList<Photo> getAllInfoFromPhotoCodes (ArrayList<Integer> photoCodes) throws SQLException;
     ArrayList<String> getPathFromPhoto(Integer photo_code) throws SQLException;
     Photo insertPhoto(Integer photo_code, String scope, String nickname, String location_name, String device, LocalDate photo_date, String path,Double x,Double y) throws SQLException;
     void deletePhoto(Integer photo_code) throws SQLException;

     void publicPhoto(Integer photo_code) throws SQLException;

     void privatePhoto(Integer photo_code) throws SQLException;
     String getPath(Integer photo_code)throws SQLException;
}
