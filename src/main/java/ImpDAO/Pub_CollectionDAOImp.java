package ImpDAO;
import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class Pub_CollectionDAOImp {
    public ArrayList<String> getAllPubCollection() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> resArray = new ArrayList<>();
        String sql = "SELECT collection_name FROM photogallery.public_collection WHERE TRUE";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();

        while (resultSet.next() != false){
            resArray.add(resultSet.getString("collection_name"));
            System.out.println(resultSet.getString("collection_name"));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return resArray;
    }

    public ArrayList<String> getAllPubCollection2() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> resArray = new ArrayList<>();
        String sql = "SELECT P.collection_name FROM photogallery.public_collection AS P JOIN photogallery.shared_photo AS S ON S.collection_name = P.collection_name WHERE S.photo_code IS NOT NULL GROUP BY P.collection_name";
        PreparedStatement prepStat = connection.prepareStatement(sql);

        ResultSet resultSet = prepStat.executeQuery();
        /*if (!resultSet.next()){
            System.out.println("No collection was found");
        }*/
        while (resultSet.next() != false){
            resArray.add(resultSet.getString("collection_name"));
            //System.out.println(resultSet.getString("collection_name"));
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

        int resultSet = prepStat.executeUpdate();
        if (resultSet > 0){
            System.out.println("Collezione creata!");
        }

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

    public void insertPhotoInCollection(Integer photo_code, String collection_name) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.shared_photo VALUES(?,?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, collection_name);
        prepStat.setInt(2, photo_code);

        int resultSet = prepStat.executeUpdate();


        if (resultSet > 0){
            System.out.println("Foto inserita con successo");
        }

        prepStat.close();
        connection.close();

    }

    public void deleteSharedPhoto(Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "DELETE FROM photogallery.shared_photo WHERE photo_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, photo_code);

        int resultSet = prepStat.executeUpdate();

        if (resultSet > 0) {
            System.out.println("Foto eliminata");
        }

        prepStat.close();
        connection.close();
    }

}

