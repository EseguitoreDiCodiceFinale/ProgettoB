package org.example.client;

import org.example.common.*;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;

public class Client extends Thread{
    private static Client instance=null;
    private final  short REGISTRYPORT = 1099;
    private ServerInterface server;
    private String UtenteAttuale;

    public Client() throws RemoteException, NotBoundException{
        super("ClientES");
        Connessione();
    }

    public static Client getInstance() throws RemoteException{
        if(instance==null){
            try {
                instance = new Client();
            } catch (NotBoundException e){}
        }
        return instance;
    }
    private synchronized void Connessione() throws RemoteException, NotBoundException{
        Registry registro = LocateRegistry.getRegistry(REGISTRYPORT);
        server = (ServerInterface) registro.lookup("ServerES");
        System.out.println("Connesso a: " + server.toString());
    }
    public void run(){}
    public synchronized int Registrazione(String username, String nome, String cognome, String via, String numero, String cap, String comune, String provincia, String email, String password) throws RemoteException {
        if(username.length()<3){
            return 1;
        }
        if(nome.length()<3){
            return 2;
        }
        if(nome.matches(".*\\\\d+.*")){
            return 3;
        }
        if(cognome.length()<3){
            return 4;
        }
        if(cognome.matches(".*\\\\d+.*")){
            return 5;
        }
        if(via.length()<3){
            return 6;
        }
        if(via.matches(".*\\\\d+.*")){
            return 7;
        }
        if(numero.matches(".*[a-zA-Z].*")){
            return 8;
        }
        if(cap.length()!=5){
            return 9;
        }
        if(cap.matches(".*[a-zA-Z].*")){
            return 10;
        }
        if(comune.length()<3){
            return 11;
        }
        if(comune.matches(".*\\\\d+.*")){
            return 12;
        }
        if(provincia.length()!=2){
            return 13;
        }
        if(provincia.matches(".*\\\\d+.*"))
        {
            return 14;
        }
        if(email.length()<5){
            return 15;
        }
        if(!email.contains("@")||!email.contains(".")){
            return 16;
        }
        if(password.length()<8){
            return 17;
        }
        String indirizzo=(via + "|" + numero +"|" + cap +"|"+ numero +"|"+ provincia);
        Utente u =new Utente(username, password, cognome, indirizzo, email, password);
        if(server.Registrazione(u))
        {
            return 0;
        }
        return 18;
    }
    public synchronized int Login(String username, String password) throws RemoteException {
        if(server.Login(username, password))
        {
            UtenteAttuale = username;
            return 0;
        }
        return 1;
    }

    public synchronized int InserisciEmozione(String categoria, String punteggio, String note, String canzone) throws RemoteException{
        Emozione e=new Emozione(categoria,punteggio, note, canzone, UtenteAttuale);
        if(server.InserisciEmozione(e)){
            return 0;
        }
        return 1;
    }

    public synchronized int CreaPlaylist(String nomeP, String nomeC) throws  RemoteException{
        return server.CreaPlaylist(nomeP, nomeC, UtenteAttuale);
    }

    public synchronized int InserisciCanzone(String nomeC, String nomeP) throws  RemoteException{
        return server.InserisciCanzone(UtenteAttuale, nomeC, nomeP);
    }

    public synchronized int EliminaCanzone(String nomeC, String nomeP) throws  RemoteException{
        return server.EliminaCanzone(UtenteAttuale, nomeC, nomeP);
    }

    public synchronized ArrayList<Canzone> CercaBranoT(String titolo) throws RemoteException{
        return server.CercaBranoT(titolo);
    }
    public synchronized ArrayList<Canzone> CercaBranoA(String autore) throws RemoteException{
        return server.CercaBranoA(autore);
    }
    public synchronized ArrayList<Canzone> CercaBranoY(String anno) throws RemoteException{
        return server.CercaBranoY(anno);
    }
    public synchronized ArrayList<Playlist> CercaPlaylist(String nomeP) throws RemoteException{
        return server.CercaPlaylist(UtenteAttuale, nomeP);
    }

    public synchronized ArrayList<Canzone> VisualizzaCanzoni() throws RemoteException{
        return server.VisualizzaCanzoni();
    }

    public synchronized ArrayList<Emozione> VisualizzaEmozioni() throws RemoteException{
        return server.VisualizzaEmozioni();
    }

    public synchronized ArrayList<Playlist> VisualizzaPlaylist() throws RemoteException{
        return server.VisualizzaPlaylist();
    }

    public static void main(String[] args){
        try {
            new Client().start();
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
