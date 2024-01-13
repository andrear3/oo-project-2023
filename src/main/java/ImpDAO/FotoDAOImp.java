package ImpDAO;

import DAO.FotoDAO;
import Database.DatabaseConnection;
import Model.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FotoDAOImp implements FotoDAO {
    @Override
    public ArrayList<Integer> fotoStessoLuogo(String location_name ) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Integer> tempPhotoCode=new ArrayList<Integer>();
        String sql = "SELECT photo_code" +
                "  FROM photogallery.PHOTO " +
                "  WHERE location_name = ?"; //? è placeholder
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, location_name); //sostituisce placeholder

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()!= false){
           tempPhotoCode.add(resultSet.getInt("photo_code"));
            System.out.println(resultSet.getInt("photo_code"));
        }


        resultSet.close();
        prepStat.close();
        connection.close();
        return tempPhotoCode;
    }


    public ArrayList<Photo> fotoStessoUtente(String nickname) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Photo tempPhoto = new Photo();
        ArrayList<Photo> tempArrayPhoto = new ArrayList<Photo>();
        String sql = "SELECT * FROM photogallery.photo WHERE nickname = ? AND scope <> 'Eliminated' ORDER BY photo_code ASC";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            tempArrayPhoto.add(new Photo(resultSet.getInt("photo_code"), resultSet.getString("scope"), resultSet.getString("nickname"), resultSet.getString("location_name"), resultSet.getString("device"),resultSet.getDate("photo_date")));
        }


        //
        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayPhoto;
    }

}

