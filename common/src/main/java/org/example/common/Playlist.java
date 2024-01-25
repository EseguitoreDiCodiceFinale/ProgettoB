package org.example.common;
import java.io.Serializable;
import java.util.ArrayList;
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1;
    private String nomePlaylist;
    private ArrayList<Canzone> listaCanzoni;
    private String nomeUtente;

    public Playlist(String nomePlaylist, ArrayList listaCanzoni, String nomeUtente){
        this.nomePlaylist=nomePlaylist;
        this.listaCanzoni=listaCanzoni;
        this.nomeUtente=nomeUtente;
    }

    public String getNomePlaylist() {
        return nomePlaylist;
    }

    public ArrayList<Canzone> getListaCanzoni() {
        return listaCanzoni;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    @Override
    public String toString() {
        return "Playlist" +  "Nome Playlist:" + nomePlaylist + ", Canzoni: " + listaCanzoni +", Nome Utente:" + nomeUtente;
    }
}
