package org.example.common;

import java.io.Serializable;

public class Emozione implements Serializable {
    private static final long serialVersionUID = 1;
    private String categoria;
    private String punteggio;
    private String note;
    private String canzone;
    private String autore;
    private String utente;


    public Emozione(String categoria, String punteggio, String note, String canzone, String autore, String utente) {
        this.categoria = categoria;
        this.punteggio = punteggio;
        this.note = note;
        this.canzone = canzone;
        this.autore = autore;
        this.utente = utente;
    }


    public String getCategoria(){
        return categoria;
    }
    public String getPunteggio(){
        return punteggio;
    }
    public String getNote(){
        return note;
    }
    public String getCanzone(){
        return canzone;
    }
    public String getAutore(){ return  autore;}
    public String getUtente(){
        return utente;
    }
    @Override
    public String toString() {
        return "Categoria dell'emozione: " + categoria + ", Canzone: " + canzone + ", Autore: "+ autore +", Nome utente:" + utente + ", Punteggio assegnato dall'utente: " + punteggio + ", Note lasciate dall'utente: " + note;
    }
}
