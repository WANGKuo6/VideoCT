package model;

import javax.persistence.*;

@Entity
public class Dll {
    @Id
    @Column(length = 100)
    private String nameDocument;
    @OneToOne
    @JoinColumn(name = "nameFormat")
    private Format format;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="dll")
    private Codec codec;

    public Dll(){}
    public Dll(String nameDocument,Format format){
        this.nameDocument = nameDocument;
        this.format = format;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public Format getFormat() {
        return format;
    }


    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Codec getCodec() {
        return codec;
    }

    public void setCodec(Codec codec) {
        this.codec = codec;
    }
}

