package DAO;
import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PubCollectionDAO {
    ArrayList<String> getAllPubCollection() throws SQLException;

    void newPubCollection(String collection_name) throws SQLException;

    ArrayList<Integer> getAllPhotoFromCollection(String collection_name) throws SQLException;
}

