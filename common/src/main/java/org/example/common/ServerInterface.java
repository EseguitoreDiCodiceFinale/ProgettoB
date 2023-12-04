package org.example.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote {
    boolean Registrazione(Utente u) throws RemoteException;
    boolean Login(String username, String password) throws RemoteException;
    boolean InserisciEmozione(Emozione e) throws RemoteException;
    int CreaPlaylist(String nome, String canzone, String nomeU) throws RemoteException;
    int InserisciCanzone(String user, String nome, String canzone) throws RemoteException;
    int EliminaCanzone(String user, String nome, String canzone) throws RemoteException;
    ArrayList<Canzone> CercaBranoT(String titolo) throws RemoteException;
    ArrayList<Canzone> CercaBranoA(String autore) throws RemoteException;
    ArrayList<Canzone> CercaBranoY(String anno) throws RemoteException;
    ArrayList<Canzone> VisualizzaCanzoni() throws RemoteException;
    ArrayList<Emozione> VisualizzaEmozioni() throws RemoteException;
    ArrayList<Playlist> VisualizzaPlaylist() throws RemoteException;
}
