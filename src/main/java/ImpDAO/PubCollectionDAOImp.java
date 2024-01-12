package ImpDAO;
import Database.DatabaseConnection;
import Model.PubCollection;

import java.sql.*;
import java.util.ArrayList;

public class PubCollectionDAOImp {
    public ArrayList<String> getAllPubCollection() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> resArray = new ArrayList<>();
        String sql = "SELECT * FROM photogallery.public_collection ORDER BY collection_name ASC";
        PreparedStatement prepStat = connection.prepareStatement(sql);

        ResultSet resultSet = prepStat.executeQuery();
        if (!resultSet.next()){
            System.out.println("No collection was found");
        }
        while (resultSet.next()){
            resArray.add(resultSet.getString("collection_name"));
            System.out.println(resArray.get(0));
        }
        return resArray;
    }
}

