package ImpDAO;

import DAO.Photo_TagDAO;
import Database.DatabaseConnection;
import Model.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void aggiungiSoggetto(Integer photo_code, String tag_name) throws SQLException {
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
    }

    public ArrayList<Integer> fotoStessoSoggetto(String tag_name ) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Integer> tempPhotoCode=new ArrayList<Integer>();
        String sql = "SELECT photo_code FROM photogallery.photo_tag WHERE tag_name = ?"; //? è placeholder
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, tag_name); //sostituisce placeholder

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

    public ArrayList<String> getAllTags() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> tempPhotoTags=new ArrayList<String>();
        String sql = "SELECT tag_name FROM photogallery.tag WHERE TRUE"; //? è placeholder
        PreparedStatement prepStat = connection.prepareStatement(sql);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()!= false){
            tempPhotoTags.add(resultSet.getString("tag_name"));
            System.out.println(resultSet.getString("tag_name"));
        }


        resultSet.close();
        prepStat.close();
        connection.close();

        return tempPhotoTags;
    }
    public Photo aggiungiSoggetto2etag(Integer photo_code, String tag_name,String tag_utente) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.photo_tag VALUES(?,?)";
        String sql2 = "INSERT INTO photogalley.user_tag VALUES(?,?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        PreparedStatement prepStat2 = connection.prepareStatement(sql2);
        prepStat.setString(1, tag_name);
        prepStat.setInt(2, photo_code);
        prepStat2.setInt(1,photo_code);
        prepStat2.setString(2,tag_utente);
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            System.out.println("soggetto inserito correttamente");
        }

        ResultSet resultSet2=prepStat2.executeQuery();
        if(resultSet2.next()){
            System.out.println("tag utente inserito correttamente ");
        }


        resultSet.close();
        prepStat.close();
        resultSet2.close();
        prepStat2.close();
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
