package ImpDAO;

import DAO.UtenteDAO;
import Database.DatabaseConnection;
import Model.Utente;

import java.sql.*;

public class UtenteDAOImp implements UtenteDAO {


    //Funzione con uso di stringa sql che ritorna se un utente esiste o meno (usato in fase di login)
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

        resultSet.close();
        prepStat.close();
        connection.close();

        return userExistance;
    }
    //Funzione con uso di stringa sql che restituisce i le informazioni di un utente
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

        resultSet.close();
        prepStat.close();
        connection.close();

        return tempUtente;
    }
    //Funzione con uso di stringa sql che va a modificare la password id un utente
    @Override
    public Utente modPass(String nickname, String password) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql = "UPDATE photogallery.utente SET passwordutente = ? WHERE nickname= ? ";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, password);
        prepStat.setString(2, nickname);
        Integer test = prepStat.executeUpdate();
        String sql2 = "SELECT * FROM photogallery.utente WHERE nickname = ?";
        prepStat = connection.prepareStatement(sql2);
        prepStat.setString(1, nickname);

        ResultSet resultSet = prepStat.executeQuery();
        if (resultSet.next()) {
            tempUtente.setNicknameUtente(resultSet.getString("nickname"));
            tempUtente.setBirthdateUtente(resultSet.getDate("birthdate"));
            tempUtente.setGenderUtente(resultSet.getString("gender"));
            tempUtente.setNameUtente(resultSet.getString("name"));
            tempUtente.setSurnameUtente(resultSet.getString("surname"));
        }
        resultSet.close();

        prepStat.close();
        connection.close();

        return tempUtente;
        }

    @Override
    public Utente modDataNascita(String nickname, java.util.Date dataN) throws SQLException {
        return null;
    }

    //Funzione con uso di stringa sql che va a modificare la data di nascita

       public Utente modDataNascita(String nickname, Date dataN) throws SQLException{
           Connection connection = DatabaseConnection.getInstance().getConnection();
           Utente tempUtente = new Utente();
           String sql="UPDATE photogallery.utente SET birthdate = ? WHERE nickname=? ";
           PreparedStatement prepStat = connection.prepareStatement(sql);
           prepStat.setDate(1,dataN);
           prepStat.setString(2, nickname);
           Integer test = prepStat.executeUpdate();
           String sql2="SELECT * FROM photogallery.utente WHERE nickname=? ";
           prepStat = connection.prepareStatement(sql2);
           prepStat.setString(1, nickname);
           ResultSet resultSet = prepStat.executeQuery();
           if (resultSet.next()) {
               tempUtente.setNicknameUtente(resultSet.getString("nickname"));
               tempUtente.setBirthdateUtente(resultSet.getDate("birthdate"));
               tempUtente.setGenderUtente(resultSet.getString("gender"));
               tempUtente.setNameUtente(resultSet.getString("name"));
               tempUtente.setSurnameUtente(resultSet.getString("surname"));
           }

           resultSet.close();
           prepStat.close();
           connection.close();

           return tempUtente;
        }
    //Funzione con uso di stringa sql che elimina un utente
        @Override
        public Utente eliminaUtente(String nickname)throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql="DELETE FROM photogallery.utente WHERE nickname= ? ";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1, nickname);
        int resultSet = prepStat.executeUpdate();
        if (resultSet > 0) {
            System.out.println("Utente eliminato");
        }

        prepStat.close();
        connection.close();

        return tempUtente;
    }
    //Funzione con uso di stringa sql usata per la registrazione di un utente
    @Override
    public Utente registraUtente(String nickname, String name, String surname, Date birthdate, String gender, String password)throws SQLException{
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Utente tempUtente = new Utente();
        String sql="INSERT INTO photogallery.utente  VALUES(?,?,?,?,?,?)";
        PreparedStatement prepStat = connection.prepareStatement(sql);
        prepStat.setString(1,nickname);
        prepStat.setDate(2,birthdate);
        prepStat.setString(3,gender);
        prepStat.setString(4,name);
        prepStat.setString(5,surname);
        prepStat.setString(6,password);

        int resultSet = prepStat.executeUpdate();

        if(resultSet > 0){
            System.out.println("Utente registrato");
        }
        prepStat.close();
        connection.close();

        return tempUtente;


    }


    }
