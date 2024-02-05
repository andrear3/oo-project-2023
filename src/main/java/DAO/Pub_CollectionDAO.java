package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Pub_CollectionDAO {
    ArrayList<String> getAllPubCollection() throws SQLException;
    public ArrayList<String> getAllPubCollection2() throws SQLException;
    void newPubCollection(String collection_name) throws SQLException;
    ArrayList<Integer> getAllPhotoFromCollection(String collection_name) throws SQLException;
    void insertPhotoInCollection(Integer photo_code, String collection_name) throws SQLException;
    void deleteSharedPhoto(Integer photo_code) throws SQLException;
}

