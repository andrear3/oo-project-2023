package ImpDAO;
import Database.DatabaseConnection;
import Model.Photo;
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

        resultSet.close();
        prepStat.close();
        connection.close();

        return resArray;
    }
    public void newPubCollection(String collection_name) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> resArray = new ArrayList<>();
        String sql = "INSERT INTO photogallery.public_collection VALUES(?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1,collection_name);

        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            System.out.println("Collezione creata!");
        }

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    public ArrayList<Integer> getAllPhotoFromCollection(String collection_name) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Integer> tempArrayPhoto = new ArrayList<Integer>();
        String sql = "SELECT photo_code FROM photogallery.shared_photo WHERE collection_name = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, collection_name);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            tempArrayPhoto.add(resultSet.getInt(1));
            System.out.println("getAll: ");
            //System.out.println(tempArrayPhoto.add(resultSet.getInt(1)));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayPhoto;
    }
}

