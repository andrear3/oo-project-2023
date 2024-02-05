package ImpDAO;

import DAO.FotoDAO;
import Database.DatabaseConnection;
import Model.Photo;
import Model.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FotoDAOImp implements FotoDAO {
    @Override
    public ArrayList<Integer> fotoStessoLuogo(String location_name ) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Integer> tempPhotoCode=new ArrayList<Integer>();
        String sql = "SELECT photo_code FROM photogallery.photo WHERE location_name = ?"; //? è placeholder
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
        ArrayList<Photo> tempArrayPhoto = new ArrayList<Photo>();
        String sql = "SELECT * FROM photogallery.photo WHERE nickname = ? AND scope <> 'Eliminated' ORDER BY photo_code ASC";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            tempArrayPhoto.add(new Photo(resultSet.getInt("photo_code"), resultSet.getString("scope"), resultSet.getString("nickname"), resultSet.getString("location_name"), resultSet.getString("device"),resultSet.getDate("photo_date"),resultSet.getString("path"),resultSet.getDouble("x_coordinates"),resultSet.getDouble("y_coordinates")));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayPhoto;
    }

    public ArrayList<Integer> fotoStessoUtenteOrPubbliche(String nickname) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Photo tempPhoto = new Photo();
        ArrayList<Integer> tempArrayPhoto = new ArrayList<Integer>();
        String sql = "SELECT photo_code FROM photogallery.photo WHERE (nickname = ? AND scope <> 'Eliminated') OR scope = 'Public' ORDER BY photo_code ASC";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            tempArrayPhoto.add(resultSet.getInt("photo_code"));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayPhoto;
    }

    public ArrayList<Photo> getAllInfoFromPhotoCodes (ArrayList<Integer> photoCodes) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Photo> tempArrayPhoto = new ArrayList<>();
        for(Integer i=0; i<photoCodes.size();i++) {
            Integer temp = photoCodes.get(i);
            String sql = "SELECT * FROM photogallery.photo WHERE photo_code = ?";
            PreparedStatement prepStat = connection.prepareStatement(sql);
            prepStat.setInt(1, temp);

            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                tempArrayPhoto.add(new Photo(resultSet.getInt("photo_code"), resultSet.getString("scope"), resultSet.getString("nickname"), resultSet.getString("location_name"), resultSet.getString("device"),resultSet.getDate("photo_date"),resultSet.getString("path"),resultSet.getDouble("x_coordinates"),resultSet.getDouble("y_coordinates")));
            }

            resultSet.close();
            prepStat.close();
        }
        connection.close();

        return tempArrayPhoto;
    }

    public ArrayList<String> getPathFromPhoto(Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> tempArrayPath = new ArrayList<>();
        String sql = "SELECT path FROM photogallery.photo WHERE photo_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, photo_code);

        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()) {
            tempArrayPath.add(resultSet.getString("path"));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayPath;
    }

    public Photo insertPhoto(Integer photo_code, String scope, String nickname, String location_name, String device, LocalDate photo_date, String path,Double x,Double y) throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Photo foto=new Photo();
        String sql="INSERT INTO photogallery.photo VALUES(DEFAULT,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);

        prepStat.setString(1,scope);
        prepStat.setString(2,nickname);
        prepStat.setString(3,location_name);
        prepStat.setString(4,device);
        prepStat.setDate(5, Date.valueOf(photo_date));
        prepStat.setString(6,path);
        prepStat.setDouble(7,x);
        prepStat.setDouble(8,y);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()){
            System.out.println("foto caricata");
        }
      return foto;
    }
public String getPath(Integer photo_code)throws SQLException{
    Connection connection = DatabaseConnection.getInstance().getConnection();
    String path=new String();
    String sql="SELECT path FROM photogallery.photo WHERE photo_code=?";
    PreparedStatement prepStat = connection.prepareStatement(sql);
    prepStat.setInt(1,photo_code);
    ResultSet resultSet = prepStat.executeQuery();
    if(resultSet.next()){
        path=resultSet.getString("path");
        System.out.println("path trovato");

    }

    return path;
}

    public void deletePhoto(Integer photo_code) throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Photo foto=new Photo();
        String sql="DELETE FROM photogallery.photo WHERE photo_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);

        prepStat.setInt(1,photo_code);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()){
            System.out.println("foto eliminata");
        }
        resultSet.close();
        prepStat.close();
        connection.close();
    }


}

