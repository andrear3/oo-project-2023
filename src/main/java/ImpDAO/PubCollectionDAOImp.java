package ImpDAO;
import Database.DatabaseConnection;
import Model.PubCollection;

import java.sql.*;
import java.util.ArrayList;

public class PubCollectionDAOImp {
    public ArrayList<String> getAllPubCollection() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> resArray = new ArrayList<>();
        String sql = "SELECT collection_name FROM photogallery.public_collection WHERE TRUE";
        PreparedStatement prepStat = connection.prepareStatement(sql);

        ResultSet resultSet = prepStat.executeQuery();
        /*if (!resultSet.next()){
            System.out.println("No collection was found");
        }*/
        while (resultSet.next() != false){
            resArray.add(resultSet.getString("collection_name"));
            System.out.println(resultSet.getString("collection_name"));
        }
        return resArray;
    }
}

