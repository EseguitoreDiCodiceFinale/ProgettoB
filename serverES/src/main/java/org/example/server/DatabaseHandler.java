package org.example.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.logging.Handler;

/**
 * @author Simone Donaduzzi
 * Viene utilizzato dal server per gestire il database
 */
public class DatabaseHandler {


    private final String DBUrl = "jdbc:postgresql://localhost:5432/";
    private final String DBName = "databaseEmotionalSongs";
    private final String user = "postgres";
    private final String password = "password1";

    private  static DatabaseHandler instance;

    /**
     * @author Simone Donaduzzi
     * Costruttore per la classe DatabaseHandler.
     *
     * @throws SQLException se si verifica un errore si SQL
     */
    public DatabaseHandler() throws SQLException {
        super();
    }

    /**
     * @author Simone Donaduzzi
     * Serve a creare un'istanza con l'utente
     *
     */
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

    /**
     * @author Simone Donaduzzi
     * Metodo per stabilire la connessione con il database
     *
     * @return conn restituisce i dati della connessione effettuata con il database
     *
     * @throws SQLException se si verifica un errore di SQL
     * @throws RemoteException se si verifica un errore durante la comunicazione remota
     */
    public Connection connectDB() throws SQLException, RemoteException {
        String dbUrl = DBUrl + DBName;
        Connection conn = DriverManager.getConnection(dbUrl, user, password);
        fillTableEmozione(conn);
        System.out.println("Connesso al database");
        return conn;
    }

    /**
     * @author Simone Donaduzzi
     * Metodo per la funzione insert SQL
     *
     * @param query Contiene i dati da inserire nel database
     * @param conn Contiene i dati della connessione corrente
     *
     * @throws true Restituisce true quando viene esequito l'insert con successo
     *
     * @throws SQLException se si verifica un errore SQL
     */
    public boolean insert (String query, Connection conn) throws SQLException
    {
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        return true;
    }

    /**
     * @author Simone Donaduzzi
     * Metodo per la funzione select SQL
     *
     * @param query Contiene i dati da selezionare nel database
     * @param conn Contiene i dati della connessione corrente
     *
     * @throws true Restituisce il risultato del select
     *
     * @throws SQLException se si verifica un errore SQL
     */
    public ResultSet select (String query, Connection conn) throws SQLException
    {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * @author Simone Donaduzzi
     * Metodo per chiudere la connessione
     *
     * @param conn Contiene i dati della connessione corrente
     *
     * @throws SQLException se si verifica un errore SQL
     */
    public void disconnect(Connection conn) throws SQLException {
        conn.close();
    }

    /**
     * @author Simone Donaduzzi
     * Metodo per riempire la tabella emozione
     *
     * @param conn Contiene i dati della connessione corrente
     *
     * @throws SQLException se si verifica un errore SQL
     */
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
}
