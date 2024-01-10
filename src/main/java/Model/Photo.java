package Model;

import java.util.Date;

public class Photo {

    //Attributi
    private String photo_code;

    private String scope;

    private String nickname;

    private String location_name;

    private String device;

    private Date photo_date;

    //Costruttore

    public Photo(String photo_code, String scope, String nickname, String location_name, String device, Date photo_date){
        this.device = device;
        this.photo_date = photo_date;
        this.location_name = location_name;
        this.nickname = nickname;
        this.scope = scope;
        this.photo_code = photo_code;
    }

    public Photo(){

    }

    //Setters

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setPhoto_code(String photo_code) {
        this.photo_code = photo_code;
    }

    public void setPhoto_date(Date photo_date) {
        this.photo_date = photo_date;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    //Getters

    public String getDevice() {
        return device;
    }

    public Date getPhoto_date() {
        return photo_date;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhoto_code() {
        return photo_code;
    }

    public String getScope() {
        return scope;
    }
}
