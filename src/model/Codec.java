package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Codec {
    @Id
    @Column(length = 100)
    private String nameCodec;

    @OneToOne
    @JoinColumn(name = "nameDocument")
    private Dll dll;
    @ManyToOne
    @JoinColumn(name = "infoCommercial")
    private Company company;
    @ManyToMany(mappedBy = "codecs")
    List<Player> players;

    public Codec(){}
    public Codec(String nameCodec, Dll dll){
        this.nameCodec = nameCodec;
        this.dll = dll;
    }
    public Codec(String nameCodec, Dll dll, Company company){
        this.nameCodec = nameCodec;
        this.dll = dll;
        this.company = company;
    }

    public String getNameCodec() {
        return nameCodec;
    }

    public Dll getDll() {
        return dll;
    }

    public Company getCompany() {
        return company;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public void setNameCodec(String nameCodec) {
        this.nameCodec = nameCodec;
    }

    public void setDll(Dll dll) {
        this.dll = dll;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
