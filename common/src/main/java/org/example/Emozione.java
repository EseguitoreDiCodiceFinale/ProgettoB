package org.example;

public class Emozione {
    private String categoria;
    private String punteggio;
    private String note;
    private String canzone;
    private String utente;


    public Emozione(String categoria, String punteggio, String note, String canzone, String utente) {
        this.categoria = categoria;
        this.punteggio = punteggio;
        this.note = note;
        this.canzone = canzone;
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
    public String getUtente(){
        return utente;
    }
    @Override
    public String toString() {
        return "Categoria dell'emozione: " + categoria + ", Canzone: " + canzone + ", Nome utente:" + utente + ", Punteggio assegnato dall'utente: " + punteggio + ", Note lasciate dall'utente: " + note;
    }
}
