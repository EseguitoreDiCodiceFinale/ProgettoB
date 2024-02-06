package org.example.common;

import java.io.Serializable;

/**
 * @author Simone Donaduzzi - Alessio Zangarini
 * Classe che rappresenta un utente nel database, con gli attributi username, nome, cognome, indirizzo, email, password
 *
 * Implemente Serializable per rendere la classe serializzabile
 */
public class Utente implements Serializable {
    private static final long serialVersionUID = 1;
    private String username;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String password;

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo costruttore della classe canzone
     *
     * @param username Nome identificativo dell'utente
     * @param nome Nome dell'utente
     * @param cognome Cognome dell'utente
     * @param indirizzo Indirizzo dell'utente
     * @param email Email dell'utente
     * @param password Password dell'utente
     */
    public Utente(String username, String nome, String cognome, String indirizzo, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.email = email;
        this.password = password;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del nome utente
     *
     * @return nomePlaylist Restituisce il valore dell'username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del nome dell'utente
     *
     * @return nomePlaylist Restituisce il valore dello username
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore del cognome dell'utente
     *
     * @return nomePlaylist Restituisce il valore del cognome dell'utente
     */
    public String getCognome()
    {
        return cognome;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore dell'indirizzo dell'utente
     *
     * @return nomePlaylist Restituisce il valore dell'indirizzo dell'utente
     */
    public String getIndirizzo()
    {
        return indirizzo;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore dell'email dell'utente
     *
     * @return nomePlaylist Restituisce il valore dell'email dell'utente
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere il valore della password dell'utente
     *
     * @return nomePlaylist Restituisce il valore della password dell'utente
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @author Simone Donaduzzi - Alessio Zangarini
     * Metodo per ottenere i valori dell'oggetto utente, come stringa
     *
     * @return titolo Restituisce la stringa contenenti i valori dell'utente
     */
    @Override
    public String toString() {
        return "Nome utente: " + username + ", Nome " + nome + ", Cognome: " + cognome + ", Indirizzo: " + indirizzo + ", Email: " + email + ", Password" + password;
    }
}
