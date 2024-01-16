package ImpDAO;

import DAO.UtenteDAO;
import Database.DatabaseConnection;
import Model.Utente;

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
        if (resultSet.next()) {
            String genderRetrieved = resultSet.getString("gender");
            System.out.println(genderRetrieved);
        } else {
            System.out.println("Nickname not found");
        }

        resultSet.close();
        prepStat.close();
        connection.close();
    }

    @Override
    public boolean checkUtenteExists(String nickname, String password) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT * FROM photogallery.utente WHERE nickname = ? AND passwordutente = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);
        prepStat.setString(2, password);

        boolean userExistance = false;
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()) {
            userExistance = true;
        }
        return userExistance;
    }

    @Override
    public Utente getUtenteDB(String nickname) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql = "SELECT nickname, birthdate, gender, name, surname FROM photogallery.utente WHERE nickname = ?";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()) {
            tempUtente.setNicknameUtente(resultSet.getString("nickname"));
            tempUtente.setBirthdateUtente(resultSet.getDate("birthdate"));
            tempUtente.setGenderUtente(resultSet.getString("gender"));
            tempUtente.setNameUtente(resultSet.getString("name"));
            tempUtente.setSurnameUtente(resultSet.getString("surname"));
        } else {
            System.out.println("Nickname not found");
        }

        //
        resultSet.close();
        prepStat.close();
        connection.close();

        return tempUtente;
    }
    @Override
    public Utente modPass(String nickname, String password) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql = "UPDATE photogallery.utente SET passwordutente = ? WHERE nickname= ? ";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, password);
        prepStat.setString(2, nickname);
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()) {
            tempUtente.setPassword(resultSet.getString("password"));
        }


        resultSet.close();
        prepStat.close();
        connection.close();

        return tempUtente;
        }

    @Override
    public Utente modDN(String nickname, java.util.Date dataN) throws SQLException {
        return null;
    }

    @Override
       public Utente modDN(String nickname, Date dataN) throws SQLException{
           Connection connection = DatabaseConnection.getInstance().getConnection();
           Utente tempUtente = new Utente();
           String sql="UPDATE photogallery.utente SET birthdate = ? WHERE nickname=? ";
           PreparedStatement prepStat = connection.prepareStatement(sql);
           prepStat.setDate(1,dataN);
           prepStat.setString(2,nickname);
           ResultSet resultSet = prepStat.executeQuery();
           if (resultSet.next()) {
               tempUtente.setBirthdateUtente(resultSet.getDate("birthdate"));
           }
           resultSet.close();
           prepStat.close();
           connection.close();

           return tempUtente;
        }
        @Override
    public Utente eliminaU(String nickname)throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql="DELETE FROM photogallery.utente WHERE nickname= ? ";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);
        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()) {
            System.out.println("utente eliminato ");
        }

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempUtente;
    }


    }
