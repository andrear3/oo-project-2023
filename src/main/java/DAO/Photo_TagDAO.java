package DAO;
import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Photo_TagDAO {
     public String SoggettoInFoto(Integer photo_code) throws SQLException;
}
