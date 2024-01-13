package Model;

public class User_Tag {

    private Integer photo_code;

    private String nickname;

    public User_Tag(Integer photo_code, String nickname)
    {
        this.photo_code = photo_code;
        this.nickname = nickname;
    }

    public User_Tag()
    {

    }

    public String getNickname() {
        return nickname;
    }
}
