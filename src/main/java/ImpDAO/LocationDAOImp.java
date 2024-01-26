package ImpDAO;

import Database.DatabaseConnection;
import Model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationDAOImp {
    public ArrayList<String> top3Luoghi()throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> tempLuogo=new ArrayList<String>();
        String sql=" SELECT location_name, photo_count " +
                " FROM photogallery.location "+
                " ORDER BY photo_count DESC";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        ResultSet resultSet = prepStat.executeQuery();

for(int i =0;i<3;i++){
    if(resultSet.next()!= false)
        tempLuogo.add(resultSet.getString("location_name"));
    System.out.println(resultSet.getString("location_name"));

}
        resultSet.close();
        prepStat.close();
        connection.close();
        return tempLuogo;

    }

    public ArrayList<String> getAllLocations() throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ArrayList<String> tempLocationTags=new ArrayList<String>();
        String sql = "SELECT location_name FROM photogallery.location WHERE TRUE"; //? è placeholder
        PreparedStatement prepStat = connection.prepareStatement(sql);

        ResultSet resultSet = prepStat.executeQuery();
        while (resultSet.next()!= false){
            tempLocationTags.add(resultSet.getString("location_name"));
            System.out.println(resultSet.getString("location_name"));
        }


        resultSet.close();
        prepStat.close();
        connection.close();

        return tempLocationTags;
    }
public Location aggLocationPhoto(String location_name,Double x_coordinates,Double y_coordinates,Integer poto_count ) throws SQLException{
    Connection connection = DatabaseConnection.getInstance().getConnection();
    Location location=new Location();
    String sql="INSERT INTO photogallery.location VALUES(?,?,?,DEFAULT)";
    PreparedStatement prepStat = connection.prepareStatement(sql);
    prepStat.setString(1,location_name);
    prepStat.setDouble(2,y_coordinates);
    prepStat.setDouble(3,x_coordinates);
    ResultSet resultSet = prepStat.executeQuery();
    if(resultSet.next()){
        System.out.println("Location caricata correttamente");
    }

    return location;
}
}