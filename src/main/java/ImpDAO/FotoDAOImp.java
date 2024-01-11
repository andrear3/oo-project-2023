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


}

