package Model;

public class Video {
    //Attributi
    private Integer video_code;
    private String video_desc;
    private String video_title;
    private String nickname;

    //Costruttori
    public Video(Integer video_code, String video_desc, String video_title, String nickname){
        this.video_code = video_code;
        this.video_desc = video_desc;
        this.video_title = video_title;
        this.nickname = nickname;
    }
    public Video(){

    }
    //Getters
    public Integer getVideoCode() {
        return video_code;
    }
    public String getVideoTitle() {
        return video_title;
    }
    public String getVideoDesc() {
        return video_desc;
    }
    public String getVideoNickname() {
        return nickname;
    }
    //Setters
    public void setVideoCode(Integer video_code){
        this.video_code = video_code;
    }
    public void setVideoTitle(String video_title){
        this.video_title = video_title;
    }
    public void setVideoDesc(String video_desc){
        this.video_desc = video_desc;
    }
    public void setVideoNickname(String nickname){
        this.nickname = nickname;
    }
}
