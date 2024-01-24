package Model;

import java.util.Date;

public class Photo {

    //Attributi
    private Integer photo_code;

    private String scope;

    private String nickname;

    private String location_name;

    private String device;

    private Date photo_date;


    private String path;

    private Double x_coordinates;

    private Double y_coordinates;

    //Costruttore

    public Photo(Integer photo_code, String scope, String nickname, String location_name, String device, Date photo_date, String path, Double x_coordinates, Double y_coordinates){
        this.device = device;
        this.photo_date = photo_date;
        this.location_name = location_name;
        this.nickname = nickname;
        this.scope = scope;
        this.photo_code = photo_code;
        this.path = path;
        this.x_coordinates = x_coordinates;
        this.y_coordinates = y_coordinates;
    }

    public Photo(){

    }

    //Setters

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setPhoto_code(Integer photo_code) {
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

    public String getPath() {
        return path;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getPhoto_code() {
        return photo_code;
    }

    public String getScope() {
        return scope;
    }

    public Double getX_coordinates() {return x_coordinates;}
    public Double getY_coordinates() {return y_coordinates;}
}
