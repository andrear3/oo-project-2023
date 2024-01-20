package DAO;
import Model.*;
import java.sql.SQLException;

public interface Photo_TagDAO {
     public String SoggettoInFoto(Integer photo_code) throws SQLException;

     public Photo aggiungiSoggetto(Integer photo_code, String tag_name) throws SQLException;
     public Photo aggiungiSoggetto2(Integer photo_code, String tag_name) throws SQLException;
}
