package Model;

public class PubCollection {
    //Attributi
    private String collection_name;

    //Costruttore
    public PubCollection(String collection_name) {
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
