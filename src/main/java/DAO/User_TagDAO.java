package DAO;

import Model.Photo;
import Model.User_Tag;

import java.sql.SQLException;

public interface User_TagDAO {
     public String PersoneTaggate(Integer photo_code) throws SQLException;

     public Photo aggiungiUserTag(Integer photo_code, String nickname2) throws SQLException;

}
