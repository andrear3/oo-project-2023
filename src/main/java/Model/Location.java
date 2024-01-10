package Model;

public class Location {

    private String location_name;

    private Double x_coordinates;

    private Double y_coordinates;

    private Integer poto_count;

    //Costruttore
    public Location ( String location_name,Double x_coordinates,Double y_coordinates,Integer poto_count ){
                this.location_name=location_name;
                this.x_coordinates=x_coordinates;
                this.y_coordinates=y_coordinates;
                this.poto_count=poto_count;

    }
    public Location(){

    };
    //SETTERS
    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setX_coordinates(Double x_coordinates){this.x_coordinates=x_coordinates;}

    public void setY_coordinates(Double y_coordinates){this.y_coordinates=y_coordinates;}

    public void setPoto_count(Integer poto_count){this.poto_count=poto_count;}

    //GETTERS
    public String getLocation_name(){
        return location_name;
    }

    public Double getX_coordinates(){return x_coordinates;}

    public Double getY_coordinates() {return y_coordinates;}

    public Integer getPoto_count() {return poto_count;}
}
