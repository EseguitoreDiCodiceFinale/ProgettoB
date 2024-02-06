package org.example.common;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Simone Donaduzzi - Alessio Zangarini
 * Classe che rappresenta una playlist nel database, con gli attributi nomePlaylist, listaCanzoni e nomeUtente
 *
 * Implemente Serializable per rendere la classe serializzabile
 */
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1;
    private String nomePlaylist;
    private ArrayList<Canzone> listaCanzoni;
    private String nomeUtente;

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo costruttore della classe canzone
     *
     * @param nomePlaylist Nome della playlist della canzone
     * @param listaCanzoni Lista delle canzoni contenute nella playlist
     * @param nomeUtente Nome dell'utente associato alla playlist
     */
    public Playlist(String nomePlaylist, ArrayList listaCanzoni, String nomeUtente){
        this.nomePlaylist=nomePlaylist;
        this.listaCanzoni=listaCanzoni;
        this.nomeUtente=nomeUtente;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del nome della playlist
     *
     * @return nomePlaylist Restituisce il valore del nome della playlist
     */
    public String getNomePlaylist() {
        return nomePlaylist;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore delle canzoni presenti nella playlist
     *
     * @return titolo Restituisce l'arrayList contenente tutte le canzoni presenti nella playlist
     */
    public ArrayList<Canzone> getListaCanzoni() {
        return listaCanzoni;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del nomeUtente
     *
     * @return titolo Restituisce il valore del nomeUtente
     */
    public String getNomeUtente() {
        return nomeUtente;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere i valori dell'oggetto playlist, come stringa
     *
     * @return titolo Restituisce la stringa contenenti i valori della playlist
     */
    @Override
    public String toString() {
        return "Nome Playlist: " + nomePlaylist + ", Canzoni: " + listaCanzoni +", Nome Utente: " + nomeUtente;
    }
}
