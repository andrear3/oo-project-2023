package DAO;

import Model.Location;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LocationDAO {
    ArrayList<String> top3Luoghi()throws SQLException;
    Location aggLocationPhoto(String location_name, Double x_coordinates, Double y_coordinates, Integer poto_count ) throws SQLException;

    public ArrayList<String> getAllLocations() throws SQLException;

}
