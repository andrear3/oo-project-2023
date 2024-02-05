package ImpDAO;

import DAO.User_TagDAO;
import Database.DatabaseConnection;
import Model.Photo;
import Model.User_Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_TagDAOImp implements User_TagDAO{

    public String personeTaggate(Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String res = new String();
        String sql = "SELECT string_agg(nickname, ', ') FROM photogallery.user_tag WHERE photo_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, photo_code);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            res = resultSet.getString(1);
        }
        resultSet.close();
        prepStat.close();
        connection.close();
        return res;
    }

    public Photo aggiungiUserTag(Integer photo_code, String nickname2) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.user_tag VALUES(?,?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, photo_code);
        prepStat.setString(2, nickname2);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();

        return null;
    }
}
