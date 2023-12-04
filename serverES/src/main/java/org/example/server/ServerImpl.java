package org.example.server;

import org.example.common.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private final short REGISTRYPORT = 1099;
    private final short SERVERPORT= 1099;
    public ServerImpl() throws RemoteException{
        super();
    }
    public void run(){
        try {
            Connessione();
        }catch (RemoteException e){
            System.out.println("Errore avvio del server");
        }
    }

    public void Connessione() throws RemoteException{
        ServerImpl server = new ServerImpl();
        ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, SERVERPORT);
        Registry registro = LocateRegistry.createRegistry(REGISTRYPORT);
        registro.rebind("ServerES", stub);
        System.out.println("Server Ready");
    }

    @Override
    public boolean Registrazione(Utente u) throws RemoteException{
        try{
            String[] indirizzo = u.getIndirizzo().split("|");
            final String registraUtente =
                    "INSERT INTO utente (userid, nome, cognome, email, password, via, numero, cap, comune, provincia) " +
                            "VALUES('" +
                            u.getUsername() + "','" +
                            u.getNome() + "','" +
                            u.getCognome() + "','" +
                            u.getEmail() + "','" +
                            u.getPassword() + "','" +
                            indirizzo[0] + "','" +
                            indirizzo[1] + "','" +
                            indirizzo[2] + "','" +
                            indirizzo[3] + "','" +
                            indirizzo[4] + "');";

            DatabaseHandler handler = new DatabaseHandler();
            boolean esito = handler.insert(registraUtente);
            handler.disconnect();
            return esito;

        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Login(String username, String password) throws RemoteException {
        try{
            final String verificaLogin =
                    "SELECT userid, password FROM utente" +
                            " WHERE userid='" + username + "' AND password='" + password + "'";

            DatabaseHandler handler = new DatabaseHandler();
            handler.connectDB();

            ResultSet resultSet = handler.select(verificaLogin);

            if (!resultSet.next()) {
                System.out.println("Utente non presente");
                return false;
            } else {
                return true;
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean InserisciEmozione(Emozione e) throws RemoteException {

        return false;
    }

    @Override
    public int CreaPlaylist(String nome, String titolo, String nomeU) throws RemoteException {
        return 0;
    }

    @Override
    public int InserisciCanzone(String user, String nome, String canzone) throws RemoteException {
        return 0;
    }

    @Override
    public int EliminaCanzone(String user, String nome, String canzone) throws RemoteException {
        return 0;
    }

    @Override
    public ArrayList<Canzone> CercaBranoT(String titolo) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Canzone> CercaBranoA(String autore) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Canzone> CercaBranoY(String anno) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Canzone> VisualizzaCanzoni() throws RemoteException {
        try{
            final String selezionaCanzoni =
                    "SELECT * FROM canzone";

            DatabaseHandler handler = new DatabaseHandler();
            handler.connectDB();

            ResultSet resultSet = handler.select(selezionaCanzoni);
            ArrayList<Canzone> listaCanzoni = new ArrayList<>();
            while(resultSet.next())
            {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                String anno = resultSet.getString("anno");
                String album = resultSet.getString("album");
                String durata = resultSet.getString("durata");
                String genere = resultSet.getString("genere");
                Canzone canzone = new Canzone(titolo, autore, anno, album);
                listaCanzoni.add(canzone);
            }
            return listaCanzoni;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Emozione> VisualizzaEmozioni() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<Playlist> VisualizzaPlaylist() throws RemoteException {
        return null;
    }
}

