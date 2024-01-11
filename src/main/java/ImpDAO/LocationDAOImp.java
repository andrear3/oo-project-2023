package ImpDAO;

import Database.DatabaseConnection;

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
}