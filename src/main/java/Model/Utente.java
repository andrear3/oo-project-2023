package Model;

import java.util.Date;

public class Utente {

    //Attributi
    private String nickname;
    private final String name;
    private final String surname;
    private final Date birthdate;
    private final String gender;

    //Costruttore
    public Utente(String nickname, String name, String surname, Date birthdate, String gender){
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    //Setters
    public void setNicknameUtente(String nickname) {
        this.nickname = nickname;
    }
    //Getters
    public String getNicknameUtente(){
        return nickname;
    }

}
