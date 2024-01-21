package ImpDAO;

import DAO.Photo_TagDAO;
import Database.DatabaseConnection;
import Model.Photo;

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

    //Questa funzione funziona, ma ha degli errori che non riesco a fixare del tutto.
    public Photo aggiungiSoggetto(Integer photo_code, String tag_name) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.photo_tag VALUES(?,?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, tag_name);
        prepStat.setInt(2, photo_code);
        ResultSet resultSet = prepStat.executeQuery();
        /*
        if(resultSet.next()) {
            Res = resultSet.getString(1);
        }
         */
        resultSet.close();
        prepStat.close();
        connection.close();

        return null;
    }
    public Photo aggiungiSoggetto2(Integer photo_code, String tag_name) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.photo_tag VALUES(?,DEFAULT)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, tag_name);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();

        return null;
    }
public Integer getPhoto_code(Integer a)throws SQLException{
    Connection connection = DatabaseConnection.getInstance().getConnection();
    Integer p_c = null;
    String sql="SELECT MAX(photo_code) FROM photogallery.photo  ";
    PreparedStatement prepStat =connection.prepareStatement(sql);
    ResultSet resultSet=prepStat.executeQuery();

    if ( resultSet.next()){
       p_c=resultSet.getInt(1);
    }
    return p_c;


}

}
