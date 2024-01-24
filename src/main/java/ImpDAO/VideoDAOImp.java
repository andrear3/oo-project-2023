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
        ArrayList<Video> tempArrayVideo = new ArrayList<Video>();
        String sql = "SELECT * FROM photogallery.video WHERE nickname = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            tempArrayVideo.add(new Video(resultSet.getInt("video_code"), resultSet.getString("video_desc"), resultSet.getString("video_title"), resultSet.getString("nickname")));
        }


        //
        resultSet.close();
        prepStat.close();
        connection.close();

        return tempArrayVideo;
    }

    public ArrayList<Integer> fotoInVideo(Integer video_code) throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<Integer> photos = new ArrayList<Integer>();
        String sql = "SELECT photo_code FROM photogallery.is_in_video WHERE video_code = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setInt(1, video_code);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            photos.add(resultSet.getInt("photo_code"));
        }


        //
        resultSet.close();
        prepStat.close();
        connection.close();

        return photos;
    }
}
