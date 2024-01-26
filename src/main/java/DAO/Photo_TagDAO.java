package DAO;
import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Photo_TagDAO {
     public String SoggettoInFoto(Integer photo_code) throws SQLException;

     public void aggiungiSoggetto(Integer photo_code, String tag_name) throws SQLException;
     public void aggiungiSoggetto2etag(Integer photo_code, String tag_name,String tag_utente) throws SQLException;
     public Integer getPhoto_code(Integer a)throws SQLException;

     public ArrayList<Integer> fotoStessoSoggetto(String tag_name ) throws SQLException;

     public ArrayList<String> getAllTags() throws SQLException;
}
