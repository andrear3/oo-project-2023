package Model;

public class Pub_Collection {
    //Attributi
    private String collection_name;

    //Costruttori
    public Pub_Collection(){

    }
    public Pub_Collection(String collection_name) {
        this.collection_name = collection_name;
    }

    //Setters
    public void setPubCollection(String collection_name) {
        this.collection_name = collection_name;
    }

    //Getters
    public String getPubCollection(){
        return collection_name;
    }
}
