package DAO;

import Model.Photo;
import Model.Video;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VideoDAO {

     ArrayList<Video> videoStessoUtente(String nickname ) throws SQLException;

     ArrayList<Integer> fotoInVideo(Integer video_code) throws SQLException;



}
