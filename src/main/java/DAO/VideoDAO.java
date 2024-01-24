package DAO;

import Model.Photo;
import Model.Video;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VideoDAO {

     ArrayList<Video> videoStessoUtente(String nickname ) throws SQLException;
     ArrayList<Photo> fotoInVideo(Integer video_code) throws SQLException;
     public String listafotovideo(Integer video_code) throws SQLException;
     public void modificaTitolo(Integer video_code, String title) throws SQLException;
     public void modificaDesc(Integer video_code, String desc) throws SQLException;

     public void aggiungiFotoVideo(Integer video_code, Integer photo_code) throws SQLException;
     public void eliminaFotoVideo(Integer video_code, Integer photo_code) throws SQLException;



}
