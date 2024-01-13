package Model;

public class Tag {

    private String tag_name;

    public Tag(String tag_name)
    {
        this.tag_name = tag_name;
    }

    public Tag()
    {

    }

    //GETTERS

    public String getTag_Name(){
        return tag_name;
    }
}
