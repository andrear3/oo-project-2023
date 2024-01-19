package DAO;
import Model.*;
import java.sql.SQLException;

public interface Photo_TagDAO {
     public String SoggettoInFoto(Integer photo_code) throws SQLException;

     public Photo AggiungiSoggetto(Integer photo_code, String tag_name) throws SQLException;
}
