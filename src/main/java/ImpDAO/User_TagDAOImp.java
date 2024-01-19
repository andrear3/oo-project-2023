package ImpDAO;

import DAO.User_TagDAO;
import Database.DatabaseConnection;
import Model.User_Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_TagDAOImp implements User_TagDAO{

    public String PersoneTaggate(Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String Res = new String();
        String sql = "SELECT string_agg(nickname, ', ') FROM photogallery.user_tag WHERE photo_code = ?";
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
