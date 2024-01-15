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
            //handler.disconnectDB(); ?
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
        try{
            int idAssocia;
            DatabaseHandler handler = new DatabaseHandler();
            handler.connectDB();
            ResultSet resultSet = handler.select("SELECT MAX(idassocia) AS ultimo_valore FROM associa");

            if (resultSet.next()) {
                idAssocia = resultSet.getInt("ultimo_valore");
            }
            else {
                idAssocia = 1;
            }

            final String inserisciAssocia =
                    "INSERT INTO associa (idassocia, punteggio, note, idutente, titolocanzone, autorecanzone, categoriaemozione)" +
                            "VALUES('" +
                            idAssocia + "','" +
                            e.getPunteggio() + "','" +
                            e.getNote() + "','" +
                            e.getUtente() + "','" +
                            e.getCanzone() + "','" +
                            //e.getAutore() + "','" +
                            e.getCategoria() + "');";

            boolean esito = handler.insert(inserisciAssocia);
            handler.disconnect();
        }catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
        return false;
    }


   /* public int CreaPlaylist(String nome, String titolo, String nomeU, String autore) throws RemoteException {
        try{
            int idPlaylist;
            DatabaseHandler handler = new DatabaseHandler();
            handler.connectDB();
            ResultSet resultSet = handler.select("SELECT MAX(idplaylist) AS ultimo_valore FROM playlist");

            if (resultSet.next()) {
                idPlaylist = resultSet.getInt("ultimo_valore");
            }
            else {
                idPlaylist = 1;
            }

            String tempAutore = "";
            final String inserisciPlaylist =
                    "INSERT INTO playlist (idplaylist, idutente, nome, titolocanzone, autorecanzone) " +
                            "VALUES('" +
                            idPlaylist + "','" +
                            nomeU + "','" +
                            nome + "','" +
                            titolo + "','" +
                            autore + "');";


            boolean esito = handler.insert(inserisciPlaylist);
            handler.disconnect();
            if(esito)
            {
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }*/

    @Override
    public int CreaPlaylist(String nome, String titolo, String nomeU, String autore) throws RemoteException {
        try{
            int idPlaylist;
            DatabaseHandler handler = new DatabaseHandler();
            handler.connectDB();
            ResultSet resultSet = handler.select("SELECT MAX(idplaylist) AS ultimo_valore FROM playlist");

            if (resultSet.next()) {
                idPlaylist = resultSet.getInt("ultimo_valore");
            }
            else {
                idPlaylist = 1;
            }

            final String inserisciPlaylist =
                    "INSERT INTO playlist (idplaylist, nome, idutente) " +
                            "VALUES('" +
                            idPlaylist + "','" +
                            nome + "','" +
                            nomeU + "');";


            final String inserisciCanzone =
                    "INSERT INTO playlistcanzone (idplaylist, titolo, autore) " +
                            "VALUES('" +
                            idPlaylist + "','" +
                            titolo + "','" +
                            autore + "');";


            boolean esito = handler.insert(inserisciPlaylist);
            boolean esito2 = handler.insert(inserisciCanzone);

            handler.disconnect();
            if(esito && esito2)
            {
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int InserisciCanzone(String nome, String titolo, String nomeU, String autore) throws RemoteException {
        try{
            DatabaseHandler handler = new DatabaseHandler();

            final String idPlaylist =
                    "SELECT idplaylist FROM playlist" +
                            " WHERE nome='" + nome + "' AND idutente='" + nomeU + "'";

            ResultSet resultSet = handler.select(idPlaylist);

            final String inserisciCanzone =
                    "INSERT INTO playlistcanzone (idplaylist, titolo, autore) " +
                            "VALUES('" +
                            resultSet.getString("idplaylist") + "','" +
                            titolo + "','" +
                            autore + "');";

            boolean esito = handler.insert(inserisciCanzone);
            handler.disconnect();
            if(esito)
            {
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int EliminaCanzone(String user, String nome, String canzone, String autore) throws RemoteException {
        return 0;
    }

    @Override
    public ArrayList<Canzone> CercaBranoT(String ti) throws RemoteException {
        try{
            final String selezionaCanzoni =
                    "SELECT * FROM canzone WHERE titolo='" + ti + "';";

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
    public ArrayList<Canzone> CercaBranoA(String au) throws RemoteException {
        try{
            final String selezionaCanzoni =
                    "SELECT * FROM canzone WHERE titolo='" + au + "';";

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
    public ArrayList<Canzone> CercaBranoY(String an) throws RemoteException {
        try{
            final String selezionaCanzoni =
                    "SELECT * FROM canzone WHERE titolo='" + an + "';";

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
    public ArrayList<Playlist> CercaPlaylist(String nomeU, String nomeP) throws RemoteException {

        return null;
    }

    @Override
    public ArrayList<Canzone> VisualizzaCanzoni() throws RemoteException {
        try{
            final String selezionaCanzoni =
                    "SELECT * FROM canzone;";

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
