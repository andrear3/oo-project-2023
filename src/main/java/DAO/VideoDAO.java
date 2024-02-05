package DAO;

import Model.Photo;
import Model.Video;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VideoDAO {
     ArrayList<Video> videoStessoUtente(String nickname ) throws SQLException;
     ArrayList<Photo> fotoInVideo(Integer video_code) throws SQLException;
     String listafotovideo(Integer video_code) throws SQLException;
     void modificaTitolo(Integer video_code, String title) throws SQLException;
     void modificaDesc(Integer video_code, String desc) throws SQLException;
     void aggiungiFotoVideo(Integer video_code, Integer photo_code) throws SQLException;
     void eliminaFotoVideo(Integer video_code, Integer photo_code) throws SQLException;
     void nuovoVideo(String desc, String title, String nickname) throws SQLException;
     void deleteVideo(Integer video_code) throws SQLException;

}
