package org.example.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {
    boolean Registrazione(Utente u) throws RemoteException;
    boolean Login(String username, String password) throws RemoteException;
    int InserisciEmozione(Emozione e) throws RemoteException;
    int CreaPlaylist(String nome, String canzone, String nomeU, String autore) throws RemoteException;
    int InserisciCanzone(String user, String nome, String canzone, String autore) throws RemoteException;
    int EliminaCanzone(String user, String nome, String canzone, String autore) throws RemoteException;
    ArrayList<Canzone> CercaBranoT(String titolo) throws RemoteException;
    ArrayList<Canzone> CercaBranoA(String autore) throws RemoteException;
    ArrayList<Canzone> CercaBranoY(String anno) throws RemoteException;
    ArrayList<Playlist> CercaPlaylist(String nomeU) throws RemoteException;
    ArrayList<Canzone> VisualizzaCanzoni() throws RemoteException;
    ArrayList<Emozione> CercaEmozioni(String canzone) throws RemoteException;
}
