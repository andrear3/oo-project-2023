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

        //
        resultSet.close();
        prepStat.close();
        connection.close();

        return tempUtente;
        }
        public Utente modNick(String nickname,String newNickname) throws SQLException{
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Utente tempUtente = new Utente();
            Utente tempUtente2 = new Utente();

            String sqlDrop="ALTER TABLE photogallery.partecipating_users DROP CONSTRAINT partecipating_users_nickname_fkey";
            String sql = "UPDATE photogallery.utente SET nickname = ? WHERE nickname= ?";
            String sql2= "UPDATE photogallery.partecipating_users SET nickname = ? WHERE nickname= ?";
            String sqlConstraint="ALTER TABLE photogallery.partecipating_users ADD CONSTRAINT partecipating_users_nickname_fkey FOREIGN KEY (nickname) REFERENCES photogallery.utente(nickname) ";
            PreparedStatement prepStat = connection.prepareStatement(sql);
            PreparedStatement prepStat2 = connection.prepareStatement(sql2);
            PreparedStatement prepStatDrop=connection.prepareStatement(sqlDrop);
            PreparedStatement prepStatConstraint=connection.prepareStatement(sqlConstraint);
            prepStat.setString(1, newNickname);
            prepStat.setString(2, nickname);
            prepStat2.setString(1,newNickname);
            prepStat2.setString(2,nickname);

            prepStatDrop.executeQuery();

            ResultSet resultSet = prepStat.executeQuery();
            if (resultSet.next()) {
                tempUtente.setNicknameUtente(resultSet.getString("nickname"));
            }
            ResultSet resultSet2 = prepStat2.executeQuery();
            if (resultSet2.next()) {
                tempUtente2.setNicknameUtente(resultSet2.getString("nickname"));
            }
            prepStatConstraint.executeQuery();


            //
            resultSet.close();
            prepStat.close();
            connection.close();

            return tempUtente;
        }


    }
