package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Format {
    @Id
    @Column(length = 100)
    private String nameFormat;
    private String extension;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="format")
    private Dll dll;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "format")
    private List<Video> videos;
    @ManyToMany(mappedBy = "formats")
    private List<Player> players;

    public Format(){}
    public Format(String nameFormat, String extension){
        this.nameFormat = nameFormat;
        this.extension = extension;
    }
    public String getNameFormat() {
        return nameFormat;
    }

    public String getExtension() {
        return extension;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public Dll getDll() {
        return dll;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public void setDll(Dll dll) {
        this.dll = dll;
    }

}
