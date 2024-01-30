package org.example.server;

import org.example.common.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Handler;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private final short REGISTRYPORT = 1099;
    private final short SERVERPORT= 1099;
    public Connection dbconnection;
    public ServerImpl() throws RemoteException{
        super();
    }
    public void run() throws RemoteException, SQLException {
        ServerImpl server= new ServerImpl();
        Registry registro;
        try{
            registro = LocateRegistry.createRegistry(REGISTRYPORT);
            registro.rebind("ServerES", server);
            System.out.println("Connessione al server riuscita");
        }catch (Exception e){
            System.out.println("Errore avvio del server");
            e.printStackTrace();
        }
        DatabaseHandler connection = new DatabaseHandler();
        dbconnection = connection.connectDB();
        if (dbconnection != null) {
          //  connection.DBInitialization();
            System.out.println("Connessione al database effettuata con successo");
        } else {
            System.out.println("Connessione al database NON effettuata");
        }
        /*try{
            DatabaseHandler dbh = new DatabaseHandler();
            dbh.DBInitialization();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        try {
            Connessione();
        }catch (RemoteException e){
            System.out.println("Errore avvio del server");
            e.printStackTrace();
        }
    }

    public void Connessione() throws RemoteException{
        ServerImpl server = new ServerImpl();
        Registry registro = LocateRegistry.createRegistry(REGISTRYPORT);
        registro.rebind("ServerES", server);
        System.out.println("Server Ready");
    }*/
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

            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection= handler.connectDB();
            boolean esito = handler.insert(registraUtente, dbconnection);
            System.out.println(esito);
            handler.disconnect(dbconnection);
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

            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection = handler.connectDB();
            //handler.disconnectDB(); ?
            ResultSet resultSet = handler.select(verificaLogin, dbconnection);

            if (!resultSet.next()) {
                System.out.println("Utente non presente");
                handler.disconnect(dbconnection);
                return false;
            } else {
                handler.disconnect(dbconnection);
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
            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection= handler.connectDB();
            ResultSet resultSet = handler.select("SELECT MAX(idassocia) AS ultimo_valore FROM associa", dbconnection);

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

            boolean esito = handler.insert(inserisciAssocia, dbconnection);
            handler.disconnect(dbconnection);
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
            int idPlaylist = 0;
            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection= handler.connectDB();
            //ResultSet resultSet = handler.select("SELECT MAX(idplaylist) AS ultimo_valore FROM playlist", dbconnection);
            ResultSet resultSet = handler.select("SELECT MAX(playlist.idplaylist) AS ultimo_valore FROM playlist " +
                   "LEFT JOIN utente ON playlist.idutente=utente.userid", dbconnection);

            final String verificaCanzone =
                    "SELECT * FROM canzone" +
                            " WHERE titolo='" + titolo + "' AND autore='" + autore + "'";

            ResultSet resultSetCanzone = handler.select(verificaCanzone, dbconnection);
            if (!resultSetCanzone.next())
            {
                return 2;
            }


            if (resultSet.next())
            {
                idPlaylist = resultSet.getInt("ultimo_valore") + 1;
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
            boolean esito = handler.insert(inserisciPlaylist, dbconnection);
            boolean esito2 = handler.insert(inserisciCanzone, dbconnection);
            handler.disconnect(dbconnection);
            if(esito && esito2)
            {
                return 0;
            }else{
                return 1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public int InserisciCanzone(String nome, String titolo, String nomeU, String autore) throws RemoteException {
        try{
            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection= handler.connectDB();

            final String idPlaylist =
                    "SELECT idplaylist FROM playlist" +
                            " WHERE nome='" + nome + "' AND idutente='" + nomeU + "'";

            ResultSet resultSet = handler.select(idPlaylist, dbconnection);

            final String inserisciCanzone =
                    "INSERT INTO playlistcanzone (idplaylist, titolo, autore) " +
                            "VALUES('" +
                            resultSet.getString("idplaylist") + "','" +
                            titolo + "','" +
                            autore + "');";

            boolean esito = handler.insert(inserisciCanzone, dbconnection);
            handler.disconnect(dbconnection);
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
      /*  try{
            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection= handler.connectDB();

            final String idPlaylist =
                    "SELECT idplaylist FROM playlist" +
                            " WHERE nome='" + nome + "' AND idutente='" + nomeU + "'";

            ResultSet resultSet = handler.select(idPlaylist, dbconnection);

            final String inserisciCanzone =
                    "INSERT INTO playlistcanzone (idplaylist, titolo, autore) " +
                            "VALUES('" +
                            resultSet.getString("idplaylist") + "','" +
                            titolo + "','" +
                            autore + "');";

            boolean esito = handler.insert(inserisciCanzone, dbconnection);
            handler.disconnect(dbconnection);
            if(esito)
            {
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }*/
        return 0;
    }

    @Override
    public ArrayList<Canzone> CercaBranoT(String ti) throws RemoteException {
        try{
            final String selezionaCanzoni =
                    "SELECT * FROM canzone WHERE titolo='" + ti + "';";

            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection= handler.connectDB();

            ResultSet resultSet = handler.select(selezionaCanzoni, dbconnection);
            ArrayList<Canzone> listaCanzoni = new ArrayList<>();
            while(resultSet.next())
            {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                String anno = resultSet.getString("anno");
                //String album = resultSet.getString("album");
                //String durata = resultSet.getString("durata");
                //String genere = resultSet.getString("genere");
                Canzone canzone = new Canzone(titolo, autore, anno);
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
                    "SELECT * FROM canzone WHERE autore='" + au + "';";

            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection=handler.connectDB();

            ResultSet resultSet = handler.select(selezionaCanzoni, dbconnection);
            ArrayList<Canzone> listaCanzoni = new ArrayList<>();
            while(resultSet.next())
            {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                String anno = resultSet.getString("anno");
                //String album = resultSet.getString("album");
                //String durata = resultSet.getString("durata");
                //String genere = resultSet.getString("genere");
                Canzone canzone = new Canzone(titolo, autore, anno);
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
                    "SELECT * FROM canzone WHERE anno='" + an + "';";

            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection=handler.connectDB();

            ResultSet resultSet = handler.select(selezionaCanzoni, dbconnection);
            ArrayList<Canzone> listaCanzoni = new ArrayList<>();
            while(resultSet.next())
            {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                String anno = resultSet.getString("anno");
                Canzone canzone = new Canzone(titolo, autore, anno);
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
                    "SELECT titolo, autore, anno FROM canzone;";

            DatabaseHandler handler = DatabaseHandler.getInstance();
            dbconnection=handler.connectDB();

            ResultSet resultSet = handler.select(selezionaCanzoni, dbconnection);
            ArrayList<Canzone> listaCanzoni = new ArrayList<>();
            while(resultSet.next())
            {
                String titolo = resultSet.getString("titolo");
                String autore = resultSet.getString("autore");
                String anno = resultSet.getString("anno");
                // String album = resultSet.getString("album");
                // String durata = resultSet.getString("durata");
                // String genere = resultSet.getString("genere");
                Canzone canzone = new Canzone(titolo, autore, anno);
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
