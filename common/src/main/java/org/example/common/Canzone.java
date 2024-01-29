package org.example.common;

import java.io.Serializable;

public class Canzone implements Serializable {
    private static final long serialVersionUID = 1;
    private String titolo;
    private String autore;
    private String anno;
    // private String album;

    public Canzone(String titolo, String autore, String anno) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        //this.album = album;
    }
    public String getTitolo(){
        return titolo;
    }
    public String getAutore(){
        return autore;
    }
    public String getAnno(){
        return anno;
    }

    @Override
    public String toString() {
        return "Titolo: " + titolo + ", Autore: " + autore + ", Anno: " + anno;
    }
}
