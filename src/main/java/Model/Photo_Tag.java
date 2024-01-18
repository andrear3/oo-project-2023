package Model;

public class Photo_Tag {

    private Integer photo_code;

    private String tag_name;

    public Photo_Tag(String tag_name, Integer photo_code)
    {
        this.photo_code = photo_code;
        this.tag_name = tag_name;
    }

    public Photo_Tag()
    {

    }

    public String getTag() {
        return tag_name;
    }
}
