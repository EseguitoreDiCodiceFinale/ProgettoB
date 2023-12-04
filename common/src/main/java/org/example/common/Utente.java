package org.example.common;

public class Utente {
    private String username;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String password;

    public Utente(String username, String nome, String cognome, String indirizzo, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.email = email;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getNome()
    {
        return nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public String getIndirizzo()
    {
        return indirizzo;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString() {
        return "Nome utente: " + username + ", Nome " + nome + ", Cognome: " + cognome + ", Indirizzo: " + indirizzo + ", Email: " + email + ", Password" + password;
    }
}
