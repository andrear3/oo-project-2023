package ImpDAO;

import Database.DatabaseConnection;
import Model.Photo;
import Model.Video;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoDAOImp {

    public ArrayList<Video> videoStessoUtente(String nickname) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Video tempVideo = new Video();
        ArrayList<Video> tempArrayVideo = new ArrayList<>();
        String sql = "SELECT * FROM photogallery.video WHERE nickname = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            tempArrayVideo.add(new Video(resultSet.getInt("video_code"), resultSet.getString("video_desc"), resultSet.getString("video_title"), resultSet.getString("nickname")));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayVideo;
    }

    public ArrayList<Photo> fotoInVideo(Integer video_code) throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Photo> photos = new ArrayList<Photo>();
        String sql = "SELECT *\n" +
                "FROM photogallery.photo as PH NATURAL JOIN photogallery.is_in_video as V \n" +
                "WHERE V.video_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, video_code);

        ResultSet resultSet = prepStat.executeQuery();

        while (resultSet.next()) {
            photos.add(new Photo(resultSet.getInt("photo_code"), resultSet.getString("scope"), resultSet.getString("nickname"), resultSet.getString("location_name"), resultSet.getString("device"),resultSet.getDate("photo_date"),resultSet.getString("path"),resultSet.getDouble("x_coordinates"),resultSet.getDouble("y_coordinates")));
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return photos;
    }

    public String listaFotoVideo(Integer video_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String res = new String();
        String sql = "SELECT string_agg(photo_code::varchar, ', ') FROM photogallery.is_in_video WHERE video_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, video_code);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()) {
            res = resultSet.getString(1);
        }
        resultSet.close();
        prepStat.close();
        connection.close();
        return res;
    }

    public void modificaTitolo(Integer video_code, String title) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "UPDATE photogallery.video SET video_title = ? WHERE video_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, title);
        prepStat.setInt(2, video_code);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    public void modificaDesc(Integer video_code, String desc) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "UPDATE photogallery.video SET video_desc = ? WHERE video_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, desc);
        prepStat.setInt(2, video_code);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    public void aggiungiFotoVideo(Integer video_code, Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.is_in_video VALUES(?, ?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, video_code);
        prepStat.setInt(2, photo_code);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    public void eliminaFotoVideo(Integer video_code, Integer photo_code) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "DELETE FROM photogallery.is_in_video WHERE video_code = ? AND photo_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, video_code);
        prepStat.setInt(2, photo_code);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    public void nuovoVideo(String desc, String title, String nickname) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "INSERT INTO photogallery.video VALUES (DEFAULT, ?, ?, ?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, desc);
        prepStat.setString(2, title);
        prepStat.setString(3, nickname);
        ResultSet resultSet = prepStat.executeQuery();

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    public void deleteVideo(Integer video_code) throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql="DELETE FROM photogallery.video WHERE video_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);

        prepStat.setInt(1, video_code);
        ResultSet resultSet = prepStat.executeQuery();
        if(resultSet.next()){
            System.out.println("video eliminato");
        }
        resultSet.close();
        prepStat.close();
        connection.close();
    }

}
