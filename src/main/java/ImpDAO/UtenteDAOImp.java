package ImpDAO;

import DAO.UtenteDAO;
import Database.DatabaseConnection;
import Model.Utente;

import javax.xml.transform.Result;
import java.sql.*;

public class UtenteDAOImp implements UtenteDAO {

    //C.R.U.D.
    @Override
    public void printGender(String nickname) throws SQLException {
       Connection connection = DatabaseConnection.getInstance().getConnection();

       String sql = "SELECT gender FROM photogallery.utente WHERE nickname = ?"; //? è placeholder
       PreparedStatement prepStat = connection.prepareStatement(sql);
       prepStat.setString(1, nickname); //sostituisce placeholder

       ResultSet resultSet = prepStat.executeQuery();
       if (resultSet.next()){
            String genderRetrieved = resultSet.getString("gender");
            System.out.println(genderRetrieved);
       }
       else { 
            System.out.println("Nickname not found");
       }

       resultSet.close();
       prepStat.close();
       connection.close();
    }

    @Override
    public boolean checkUtenteExists(String nickname) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT * FROM photogallery.utente WHERE nickname = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        boolean userExistance = false;
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            userExistance = true;
        }
        return userExistance;
    }

    @Override
    public Utente getUtenteDB(String nickname) throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql = "SELECT nickname, birthdate, gender, name, surname FROM photogallery.utente WHERE nickname = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()){
            tempUtente.setNicknameUtente(resultSet.getString("nickname"));
            tempUtente.setBirthdateUtente(resultSet.getDate("birthdate"));
            tempUtente.setGenderUtente(resultSet.getString("gender"));
            tempUtente.setNameUtente(resultSet.getString("name"));
            tempUtente.setSurnameUtente(resultSet.getString("surname"));
        }
        else{
            System.out.println("Nickname not found");
        }

        //
        resultSet.close();
        prepStat.close();
        connection.close();

        return tempUtente;
    }

}
