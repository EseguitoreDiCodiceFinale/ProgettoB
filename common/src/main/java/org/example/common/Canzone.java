package org.example.common;

public class Canzone {
    private String titolo;
    private String autore;
    private String anno;
    private String album;

    public Canzone(String titolo, String autore, String anno, String album) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.album = album;
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
    public  String getAlbum(){
        return album;
    }
    @Override
    public String toString() {
        return "Titolo: " + titolo + ", Autore: " + autore + ", Anno: " + anno + ", Album: " + album;
    }
}
