package ImpDAO;

import DAO.FotoDAO;
import Database.DatabaseConnection;
import Model.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FotoDAOImp implements FotoDAO {
    @Override
    public Photo fotoStessoLuogo(String location_name ) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Photo tempPhotoCode=new Photo();
        String sql = "SELECT photo_code" +
                "  FROM photogallery.PHOTO " +
                "  WHERE location_name = ?"; //? è placeholder
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, location_name); //sostituisce placeholder

        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            tempPhotoCode.setPhoto_code(resultSet.getString("photo_code"));
        }
        else {
            System.out.println("Location non trovata");
        }

        resultSet.close();
        prepStat.close();
        connection.close();
        return tempPhotoCode;
    }
}
