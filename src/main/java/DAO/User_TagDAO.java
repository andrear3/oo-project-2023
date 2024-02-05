package DAO;

import Model.Photo;

import java.sql.SQLException;

public interface User_TagDAO {
     public String personeTaggate(Integer photo_code) throws SQLException;
     public Photo aggiungiUserTag(Integer photo_code, String nickname2) throws SQLException;
}
