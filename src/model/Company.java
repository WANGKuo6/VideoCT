package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {
    @Id
    @Column(length = 100)
    private String nameCompany;
    private String web;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Codec> codecs;

    public Company(){}
    public Company(String nameCompany,String web){
        this.nameCompany = nameCompany;
        this.web = web;
    }
    public String getNameCompany() {
        return nameCompany;
    }

    public String getWeb() {
        return web;
    }

    public List<Codec> getCodecs() {
        return codecs;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setCodecs(List<Codec> codecs) {
        this.codecs = codecs;
    }
}
