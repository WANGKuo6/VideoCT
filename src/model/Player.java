package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Player {
	@Id
    @Column(length = 100)
    private String namePlayer;
    private int nbKoctets;
    @ManyToMany
    @JoinTable(name = "Player_Format",
        joinColumns = {@JoinColumn(name="namePlayer",
            referencedColumnName = "namePlayer")},
        inverseJoinColumns = {@JoinColumn(name = "nameFormat",
            referencedColumnName = "nameFormat")})
    private List<Format> formats;
    @ManyToMany
    @JoinTable(name = "Player_Codec",
            joinColumns = {@JoinColumn(name="namePlayer",
                    referencedColumnName = "namePlayer")},
            inverseJoinColumns = {@JoinColumn(name = "nameCodec",
                    referencedColumnName = "nameCodec")})
    private List<Codec> codecs;
    
    public Player() {}
    public Player(String namePlayer, int nbKoctets){
        this.namePlayer = namePlayer;
        this.nbKoctets = nbKoctets;
    }
    public Player(String namePlayer, int nbKoctets, List<Format> formats, List<Codec> codecs){
        this.namePlayer = namePlayer;
        this.nbKoctets = nbKoctets;
        this.formats = formats;
        this.codecs =codecs;
    }
    public String getNamePlayer() {
        return namePlayer;
    }
    public int getNbKoctets() {
        return nbKoctets;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public List<Codec> getCodecs() {
        return codecs;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setNbKoctets(int nbKoctets) {
        this.nbKoctets = nbKoctets;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public void setCodecs(List<Codec> codecs) {
        this.codecs = codecs;
    }

}
