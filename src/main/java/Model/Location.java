package Model;

public class Location {
    private String location_name;
    private Double x_coordinates;
    private Double y_coordinates;
    private Integer photo_count;

    //Costruttore
    public Location ( String location_name,Double x_coordinates,Double y_coordinates,Integer photo_count){
                this.location_name=location_name;
                this.x_coordinates=x_coordinates;
                this.y_coordinates=y_coordinates;
                this.photo_count = photo_count;
    }
    public Location(){

    };
    //Setters
    public void setLocationName(String location_name) {
        this.location_name = location_name;
    }
    public void setXCoordinates(Double x_coordinates){this.x_coordinates=x_coordinates;}
    public void setYCoordinates(Double y_coordinates){this.y_coordinates=y_coordinates;}
    public void setPhotoCount(Integer photo_count){this.photo_count = photo_count;}

    //Getters
    public String getLocationName(){
        return location_name;
    }
    public Double getXCoordinates(){return x_coordinates;}
    public Double getYCoordinates() {return y_coordinates;}
    public Integer getPhotoCount() {return photo_count;}
}
