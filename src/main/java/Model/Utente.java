package Model;

import java.util.Date;


public class Utente {

    //Attributi
    private String nickname;
    private String name;
    private String surname;
    private Date birthdate;
    private String gender;
    private String password;

    //Costruttore
    public Utente(String nickname, String name, String surname, Date birthdate, String gender,String password){
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.password=password;
    }

    public Utente(){

    };

    //Setters
    public void setNicknameUtente(String nickname) {
        this.nickname = nickname;
    }

    public void setNameUtente(String name){
        this.name = name;
    }

    public void setSurnameUtente(String surname){
        this.surname = surname;
    }

    public void setBirthdateUtente(Date birthdate){
        this.birthdate = birthdate;
    }

    public void setGenderUtente(String gender){
        this.gender = gender;
    }
    public void setPassword(String password){this.password=password; }

    //Getters
    public String getNicknameUtente(){
        return nickname;
    }

    public String getNameUtente(){
        return name;
    }

    public String getSurnameUtente(){
        return surname;
    }

    public Date getBirthdateUtente(){
        return birthdate;
    }

    public String getGenderUtente(){
        return gender;
    }

}
