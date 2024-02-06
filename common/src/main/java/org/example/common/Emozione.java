package org.example.common;

import java.io.Serializable;


/**
 * @author Simone Donaduzzi - Alessio Zangarini
 * Classe che rappresenta un'emozione nel database, con gli attributi categoria, punteggio, note, canzone, autore e utente
 *
 * Implemente Serializable per rendere la classe serializzabile
 */
public class Emozione implements Serializable {
    private static final long serialVersionUID = 1;
    private String categoria;
    private String punteggio;
    private String note;
    private String canzone;
    private String autore;
    private String utente;

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo costruttore della classe emozione
     *
     * @param categoria Descrive il tipo di emozione provata
     * @param punteggio Punteggio assegnato all'emozione
     * @param note Note rilasciate dall'utente
     * @param canzone Titolo della canzone associata all'emozione
     * @param autore Autore della canzone associata all'emozione
     * @param utente Utente che ha assegnato l'emozione alla canzone
     */
    public Emozione(String categoria, String punteggio, String note, String canzone, String autore, String utente) {
        this.categoria = categoria;
        this.punteggio = punteggio;
        this.note = note;
        this.canzone = canzone;
        this.autore = autore;
        this.utente = utente;
    }


    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore della categoria
     *
     * @return titolo Restituisce il valore della categoria
     */
    public String getCategoria(){
        return categoria;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del punteggio
     *
     * @return titolo Restituisce il valore del punteggio
     */
    public String getPunteggio(){
        return punteggio;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore delle note
     *
     * @return titolo Restituisce il valore delle note
     */
    public String getNote(){
        return note;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del titolo della canzone
     *
     * @return titolo Restituisce il valore del titolo della canzone
     */
    public String getCanzone(){
        return canzone;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore dell'autore
     *
     * @return titolo Restituisce il valore dell'autore
     */
    public String getAutore(){ return  autore;}

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore dell'utente
     *
     * @return titolo Restituisce il valore dell'utente
     */
    public String getUtente(){
        return utente;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere i valori dell'oggetto emozione, come stringa
     *
     * @return titolo Restituisce la stringa contenenti i valori dell'emozione
     */
    @Override
    public String toString() {
        return "Categoria dell'emozione: " + categoria + ", Canzone: " + canzone + ", Autore: "+ autore +", Nome utente:" + utente + ", Punteggio assegnato dall'utente: " + punteggio + ", Note lasciate dall'utente: " + note;
    }
}
