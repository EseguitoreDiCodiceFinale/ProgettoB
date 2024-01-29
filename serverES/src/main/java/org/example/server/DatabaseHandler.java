package org.example.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.logging.Handler;

public class DatabaseHandler {


    private final String DBUrl = "jdbc:postgresql://localhost:5432/";
    private final String DBName = "databaseEmotionalSongs";
    private final String user = "postgres";
    private final String password = "password1";

    private  static DatabaseHandler instance;
    //private Connection conn;

    public DatabaseHandler() throws SQLException {
        super();
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            synchronized (DatabaseHandler.class) {
                if (instance == null) {
                    try {
                        instance = new DatabaseHandler();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
    public Connection connectDB() throws SQLException, RemoteException {
        String dbUrl = DBUrl + DBName;
        Connection conn = DriverManager.getConnection(dbUrl, user, password);
        fillTableEmozione(conn);
        //fillTableCanzone(conn);
        System.out.println("Connesso al database");
        return conn;
    }

    public boolean insert (String query, Connection conn) throws SQLException
    {
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        return true;
    }

    public ResultSet select (String query, Connection conn) throws SQLException
    {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }

    public void disconnect(Connection conn) throws SQLException {
        conn.close();
    }

    public void fillTableEmozione(Connection conn) throws SQLException{
        final String isEmpty="SELECT * FROM emozione";
        DatabaseHandler data =  DatabaseHandler.getInstance();
        ResultSet res = data.select(isEmpty, conn);
        if(!res.next()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Amazement', 'Feeling of wonder or happiness')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Solemnity', 'Feeling of transcendence, inspiration. Thrills.')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Tenderness', 'Sensuality, affect, feeling of love')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Nostalgia', 'Dreamy, melancholic, sentimental feelings')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Calmness', 'Relaxation, serenity, meditativeness')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Power', 'Feeling strong, heroic, triumphant, energetic')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Joy', 'Feels like dancing, bouncy feeling, animated, amused')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Tension', 'Feeling Nervous, impatient, irritated')");
            statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Sadness', 'ess Feeling Depressed, sorrowful')");
        }
    }

    public  void fillTableCanzone(Connection conn, String fileName) throws SQLException {

    }
    /*
    public void DBInitialization() throws SQLException, RemoteException {
        String createQuery = "SELECT 1 FROM pg_database WHERE datname = ?";
        PreparedStatement st = conn.prepareStatement(createQuery);
        st.setString(1, DBName);
        ResultSet rs = st.executeQuery();
        if(!rs.next()) {
            createQuery = "CREATE DATABASE " + DBName;
            st = conn.prepareStatement(createQuery);
            st.executeUpdate();
            //disconnect();
            //connectDB();
            CreateTableUtente();
            CreateTablePlaylist();
            CreateTableEmozione();
            CreateTableCanzone();
            CreateTableAssocia();
            FillTableEmozione();
            CreateTablePlaylistCanzone();
            System.out.println("Tabelle create");
        }
        //disconnect();
    }

    public void CreateTableUtente() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(
                "CREATE TABLE " + "utente" + "( " +
                        " userid VARCHAR(255), " +
                        " nome VARCHAR(255), " +
                        " cognome VARCHAR(255), " +
                        " email VARCHAR(255), " +
                        " password VARCHAR(255), " +
                        " via VARCHAR(255), " +
                        " numero VARCHAR(255), " +
                        " cap VARCHAR(5), " +
                        " comune VARCHAR(255), " +
                        " provincia VARCHAR(2), " +
                        " PRIMARY KEY ( userid ));"); {
            statement.executeUpdate();
        }
    }


    public void CreateTablePlaylist() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(
                "CREATE TABLE " + "playlist" + "( " +
                        " idutente VARCHAR(255), " +
                        " nome VARCHAR(255), " +
                        " PRIMARY KEY ( idutente, nome )," +
                        " FOREIGN KEY (idutente) REFERENCES utente(userid)," +
                        "ON UPDATE CASCADE ON DELETE CASCADE);"); {
            statement.executeUpdate();
        }
    }

    public void CreateTablePlaylistCanzone() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(
                "CREATE TABLE " + "playlistcanzone" + "( " +
                        " idplaylist INTEGER, " +
                        " titolo VARCHAR(255), " +
                        " autore VARCHAR(255) " +
                        " PRIMARY KEY ( idplaylist, titolo, autore )," +
                        " FOREIGN KEY (idplaylist) REFERENCES playlist(idplaylist)," +
                        " FOREIGN KEY (canzone) REFERENCES canzone(titolo)," +
                        " FOREIGN KEY (autore) REFERENCES canzone(autore)," +
                        " ON UPDATE CASCADE ON DELETE CASCADE);"); {
            statement.executeUpdate();
        }
    }

    public void CreateTableEmozione() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(
                "CREATE TABLE " + "emozione" + "( " +
                        " categoria VARCHAR(255), " +
                        " descrizione VARCHAR(255), " +
                        " PRIMARY KEY ( categoria ));"); {
            statement.executeUpdate();
        }
    }

    public void FillTableEmozione() throws SQLException{
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Amazement', 'Feeling of wonder or happiness')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Solemnity', 'Feeling of transcendence, inspiration. Thrills.')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Tenderness', 'Sensuality, affect, feeling of love')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Nostalgia', 'Dreamy, melancholic, sentimental feelings')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Calmness', 'Relaxation, serenity, meditativeness')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Power', 'Feeling strong, heroic, triumphant, energetic')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Joy', 'Feels like dancing, bouncy feeling, animated, amused')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Tension', 'Feeling Nervous, impatient, irritated')");
        statement.executeUpdate("INSERT INTO emozione " + "VALUES ('Sadness', 'ess Feeling Depressed, sorrowful')");
    }

    public void CreateTableCanzone() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(
                "CREATE TABLE " + "canzone" + "( " +
                        " titolo VARCHAR(255), " +
                        " autore VARCHAR(255), " +
                        " anno VARCHAR(255), " +
                        " album VARCHAR(255), " +
                        " durata VARCHAR(255), " +
                        " genere VARCHAR(255), " +
                        " PRIMARY KEY ( titolo, autore ));"); {
            statement.executeUpdate();
        }
    }

    public void CreateTableAssocia() throws SQLException{
        PreparedStatement statement = conn.prepareStatement(
                "CREATE TABLE " + "associa" + "( " +
                        " idassocia INTEGER AUTO_INCREMENT, " +
                        " punteggio VARCHAR(255), " +
                        " note VARCHAR(255), " +
                        " idutente VARCHAR(255), " +
                        " titolocanzone VARCHAR(255), " +
                        " autorecanzone VARCHAR(255), " +
                        " categoriaemozione VARCHAR(255), " +
                        " PRIMARY KEY ( idassocia )," +
                        " FOREIGN KEY (idutente) REFERENCES utente(userid)," +
                        " FOREIGN KEY (categoriaemozione) REFERENCES emozione(categoria)," +
                        " FOREIGN KEY (titolocanzone) REFERENCES utente(titolo)," +
                        " FOREIGN KEY (autorecanzone) REFERENCES canzone(autore)" +
                        "ON UPDATE CASCADE ON DELETE CASCADE);"); {
            statement.executeUpdate();
        }
    } */



    /*public static void main(String[] args) {
        String q = "select * from ";
        ResultSet rs = null;

        try {
            DatabaseHandler handler = new DatabaseHandler();
            handler.connectDB();

            rs = handler.select(q);

            handler.disconnect();
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }*/

    }
