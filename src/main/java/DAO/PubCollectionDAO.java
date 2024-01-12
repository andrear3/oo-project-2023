package DAO;
import Model.PubCollection;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PubCollectionDAO {
    ArrayList<String> getAllPubCollection() throws SQLException;
}

