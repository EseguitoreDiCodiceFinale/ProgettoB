package org.example.common;

import java.io.Serializable;

/**
 * @author Simone Donaduzzi - Alessio Zangarini
 * Classe che rappresenta una canzone nel database, con gli attributi titolo, autore e anno
 *
 * Implemente Serializable per rendere la classe serializzabile
 */

public class Canzone implements Serializable {
    private static final long serialVersionUID = 1;
    private String titolo;
    private String autore;
    private String anno;
    // private String album;

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo costruttore della classe canzone
     *
     * @param titolo Titolo della canzone
     * @param autore Autore della canzone
     * @param anno Anno di uscita della canzone
     */
    public Canzone(String titolo, String autore, String anno) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        //this.album = album;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del titolo
     *
     * @return titolo Restituisce il valore del titolo
     */
    public String getTitolo(){
        return titolo;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore dell'autore
     *
     * @return autore Restituisce il valore dell'autore
     */
    public String getAutore(){
        return autore;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore dell'anno
     *
     * @return anno Restituisce il valore dell'anno
     */
    public String getAnno(){
        return anno;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere i valori dell'oggetto canzone, come stringa
     *
     * @return titolo Restituisce la stringa contenenti i valori della canzone
     */
    @Override
    public String toString() {
        return "Titolo: " + titolo + ", Autore: " + autore + ", Anno: " + anno;
    }
}
