package ImpDAO;

import DAO.UtenteDAO;
import Database.DatabaseConnection;
import Model.Utente;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAOImp implements UtenteDAO {
    //C.R.U.D. Retrieve Gender
    @Override
    public void printGender(String nickname) throws SQLException {
       Connection connection = DatabaseConnection.getInstance().getConnection();

       String sql = "SELECT gender FROM photogallery.utente WHERE nickname = ?"; //? è placeholder
       PreparedStatement prepstat = connection.prepareStatement(sql);
       prepstat.setString(1, nickname); //sostituisce placeholder

       ResultSet resultSet = prepstat.executeQuery();
       if (resultSet.next()){
            String genderRetrieved = resultSet.getString("gender");
            System.out.println(genderRetrieved);
       }
       else { 
            System.out.println("Nickname not found");
       }
    }
}
