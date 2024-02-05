package Model;

public class Tag {
    //Attributi
    private String tag_name;

    //Costruttori
    public Tag(String tag_name)
    {
        this.tag_name = tag_name;
    }
    public Tag(){};

    //Getters
    public String getTagName(){
        return tag_name;
    }

}
