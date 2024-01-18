package ImpDAO;

import DAO.Photo_TagDAO;
import Database.DatabaseConnection;
import Model.Photo_Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Photo_TagDAOImp implements Photo_TagDAO{

    public String SoggettoInFoto(Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String Res = new String();
        String sql = "SELECT string_agg(tag_name, ', ') FROM photogallery.photo_tag WHERE photo_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, photo_code);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            Res = resultSet.getString(1);
        }
        resultSet.close();
        prepStat.close();
        connection.close();
        return Res;
    }
}
