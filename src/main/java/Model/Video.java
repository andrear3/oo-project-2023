package Model;

import java.util.Date;

public class Video {

    private Integer video_code;

    private String video_desc;

    private String video_title;

    private String nickname;

    public Video(Integer video_code, String video_desc, String video_title, String nickname){
        this.video_code = video_code;
        this.video_desc = video_desc;
        this.video_title = video_title;
        this.nickname = nickname;
    }

    public Video(){

    }

    public Integer getvideocode() {
        return video_code;
    }

    public String getvideotitle() {
        return video_title;
    }

    public String getvideodesc() {
        return video_desc;
    }

    public String getvideonickname() {
        return nickname;
    }
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
