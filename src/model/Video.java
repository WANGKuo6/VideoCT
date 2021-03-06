package model;
import javax.persistence.*;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameVideo;
    @ManyToOne
    @JoinColumn(name = "nameFormat")
    private Format format;

    public Video(){}
    public Video(String nameVideo, Format format){
        this.nameVideo = nameVideo;
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public Format getNameFormat() {
        return format;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public void setNameFormat(Format format) {
        this.format = format;
    }



}
