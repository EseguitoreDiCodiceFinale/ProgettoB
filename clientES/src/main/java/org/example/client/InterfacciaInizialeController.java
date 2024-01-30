package org.example.client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.common.Canzone;
import org.example.common.Playlist;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class InterfacciaInizialeController {
    @FXML
    public Button inserisciemozioni;
    @FXML
    public Button btlogout;
    @FXML
    public Button visualizzacanzoni;
    @FXML
    public Button visualizzaplaylist;
    @FXML
    public Button creaplaylist;
    @FXML
    public Button aggiungibrano;
    @FXML
    public Button eliminabrano;
    @FXML
    private Button accedi;

    @FXML
    private TextField codicepostale;

    @FXML
    private TextField cognome;

    @FXML
    private TextField comune;

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private TextField nome;

    @FXML
    private TextField eliminaAutorePlaylist;

    @FXML
    private TextField playlistAutore;

    @FXML
    private TextField playlistAutoreCanzone;

    @FXML
    private TextField nomeUtente1;

    @FXML
    private TextField nomeutenteid;

    @FXML
    private TextField numerocivico;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField password;

    @FXML
    private TextField password1;

    @FXML
    private TextField provincia;

    @FXML
    private Button registrati;

    @FXML
    private Button registrati1;

    @FXML
    private Button ricercaa;
    @FXML
    private Button emozionec;
    @FXML
    private Button playlistc;


    @FXML
    private Button ricercat;

    @FXML
    private Pane signin_pane;
    @FXML
    private Pane mostracanzoni_pane;
    @FXML
    private Pane signup_pane;
    @FXML
    private Pane emotion_pane;
    @FXML
    private Pane emotionV_pane;
    @FXML
    private AnchorPane logocentrale;
    @FXML
    private Pane playlist_pane;
    @FXML
    private Pane playlistI_pane;
    @FXML
    private Pane visualizza_playlist_pane;
    @FXML
    public Pane eliminacanzone_pane;
    @FXML
    private TextArea areaemozioni;

    @FXML
    private Label titolo;

    @FXML
    private Button visualizzac;
    @FXML
    private TextField nomeCanzoneV;
    @FXML
    private Label titolo1;

    @FXML
    private TextField titolor;
    @FXML
    private TextField nomePlaylistI;
    @FXML
    private TextField playlistCanzoneI;
    @FXML
    private TextField nomePlaylistIC;
    @FXML
    private TextField playlistCanzoneIC;


    @FXML
    private TextField via;
    @FXML
    private Label titoloP;
    @FXML
    private Label titoloInserisci;
    @FXML
    private Label titoloInserisci2;

    @FXML
    private TextField nomePlaylist;
    @FXML
    private TextField playlistCanzone;
    @FXML
    private TextField emozioneCanzone;
    @FXML
    private TextField emozioneNomeUtente;
    @FXML
    private TextField emozioneAutore;
    @FXML
    private ChoiceBox choicebox_emozione;
    @FXML
    private ChoiceBox<String> choiceboxRicerca;
    @FXML
    private ChoiceBox choicebox_score;
    @FXML
    private TextField tf_note;
    @FXML
    private TextField textFiledPlaylist;
    @FXML
    private ListView textAreaPlaylist;
    @FXML
    private ListView textareacanzoni;
    @FXML
    private Label label_elencoCanzoni;
    @FXML
    private TextField autorePC;
    @FXML
    private  TextField autorePI;
    @FXML
    private TextField autorePD;
    @FXML
    private TextArea TextAreaVisualizzaPlaylist;


    @FXML
    private Button visualizza;
    private Stage stage;
    private Scene scene;


    @FXML
    public void initialize() {
        choiceboxRicerca.getItems().addAll("Titolo","Autore","Anno");
        choiceboxRicerca.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            String selectedItem = choiceboxRicerca.getItems().get(newValue.intValue());

            switch (selectedItem) {
                case "Titolo":
                    //ricerca con titolo
                    break;
                case "Autore":
                    //ricerca con autore
                    break;
                case "Anno":
                    //ricerca con anno
                    break;
                default:
                    // Gestire eventuali altri casi
                    break;
            }
        });


        signin_pane.setVisible(false);
        mostracanzoni_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotion_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        logocentrale.setVisible(true);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        creaplaylist.setVisible(false);
        aggiungibrano.setVisible(false);
        eliminabrano.setVisible(false);
        visualizza.setVisible(false);
        inserisciemozioni.setVisible(false);
        visualizzaplaylist.setVisible(false);
        btlogout.setVisible(false);

    }


    @FXML
    public void completaRegistrazioneButton(ActionEvent event) throws IOException {
        if(!(nomeutenteid.getText().equals("") || nome.getText().equals("")  || cognome.getText().equals("")  ||email.getText().equals("")  || password.getText().equals("") || via.getText().equals("") || numerocivico.getText().equals("") || codicepostale.getText().equals("") ||  codicepostale.getText().length()<5 || comune.getText().equals("") || provincia.getText().equals("")))
        {
            //controlli client
            switch (Client.getInstance().Registrazione(nomeutenteid.getText(), nome.getText(), cognome.getText(), via.getText(), numerocivico.getText(), codicepostale.getText(), comune.getText(), provincia.getText(), email.getText(), password.getText())) {
                case 0:
                    signup_pane.setVisible(false);
                    nomeutenteid.clear();
                    nome.clear();
                    cognome.clear();
                    via.clear();
                    numerocivico.clear();
                    codicepostale.clear();
                    comune.clear();
                    provincia.clear();
                    email.clear();
                    password.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registrazione Riuscita");
                    alert.setHeaderText(null);
                    alert.setContentText("Registrazione Effettuata con successo");
                    alert.showAndWait();
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Errore");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Username non lungo abbastanza");
                    alert1.showAndWait();
                    break;
                case 2:
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Errore");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Nome non lungo abbastanza");
                    alert2.showAndWait();
                    break;
                case 3:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Errore");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Nome contiene numeri");
                    alert3.showAndWait();
                    break;
                case 4:
                    Alert alert4 = new Alert(Alert.AlertType.INFORMATION);
                    alert4.setTitle("Errore");
                    alert4.setHeaderText(null);
                    alert4.setContentText("Cognome contiene numeri");
                    alert4.showAndWait();
                    break;
                case 5:
                    Alert alert5 = new Alert(Alert.AlertType.INFORMATION);
                    alert5.setTitle("Errore");
                    alert5.setHeaderText(null);
                    alert5.setContentText("Cognome non lungo abbastanza");
                    alert5.showAndWait();
                    break;
                case 6:
                    Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
                    alert6.setTitle("Errore");
                    alert6.setHeaderText(null);
                    alert6.setContentText("Nome via contiene numeri");
                    alert6.showAndWait();
                    break;
                case 7:
                    Alert alert7 = new Alert(Alert.AlertType.INFORMATION);
                    alert7.setTitle("Errore");
                    alert7.setHeaderText(null);
                    alert7.setContentText("Nome via non lungo abbastanza");
                    alert7.showAndWait();
                    break;
                case 8:
                    Alert alert8 = new Alert(Alert.AlertType.INFORMATION);
                    alert8.setTitle("Errore");
                    alert8.setHeaderText(null);
                    alert8.setContentText("Numero civico contiene lettere");
                    alert8.showAndWait();
                    break;
                case 9:
                    Alert alert9 = new Alert(Alert.AlertType.INFORMATION);
                    alert9.setTitle("Errore");
                    alert9.setHeaderText(null);
                    alert9.setContentText("Lunghezza CAP errata");
                    alert9.showAndWait();
                    break;
                case 10:
                    Alert alert10 = new Alert(Alert.AlertType.INFORMATION);
                    alert10.setTitle("Errore");
                    alert10.setHeaderText(null);
                    alert10.setContentText("CAP contiene lettere");
                    alert10.showAndWait();
                    break;
                case 11:
                    Alert alert11 = new Alert(Alert.AlertType.INFORMATION);
                    alert11.setTitle("Errore");
                    alert11.setHeaderText(null);
                    alert11.setContentText("Comune non lungo abbastanza");
                    alert11.showAndWait();
                    break;
                case 12:
                    Alert alert12 = new Alert(Alert.AlertType.INFORMATION);
                    alert12.setTitle("Errore");
                    alert12.setHeaderText(null);
                    alert12.setContentText("Comune contiene numeri");
                    alert12.showAndWait();
                    break;
                case 13:
                    Alert alert13 = new Alert(Alert.AlertType.INFORMATION);
                    alert13.setTitle("Errore");
                    alert13.setHeaderText(null);
                    alert13.setContentText("Lunghezza provincia errata");
                    alert13.showAndWait();
                    break;
                case 14:
                    Alert alert14 = new Alert(Alert.AlertType.INFORMATION);
                    alert14.setTitle("Errore");
                    alert14.setHeaderText(null);
                    alert14.setContentText("Provincia contiene numeri");
                    alert14.showAndWait();
                    break;
                case 15:
                    Alert alert15 = new Alert(Alert.AlertType.INFORMATION);
                    alert15.setTitle("Errore");
                    alert15.setHeaderText(null);
                    alert15.setContentText("Email non lunga abbastanza");
                    alert15.showAndWait();
                    break;
                case 16:
                    Alert alert16 = new Alert(Alert.AlertType.INFORMATION);
                    alert16.setTitle("Errore");
                    alert16.setHeaderText(null);
                    alert16.setContentText("Formato email errato");
                    alert16.showAndWait();
                    break;
                case 17:
                    Alert alert17 = new Alert(Alert.AlertType.INFORMATION);
                    alert17.setTitle("Errore");
                    alert17.setHeaderText(null);
                    alert17.setContentText("Password non lunga abbastanza, minimo 8 caratteri");
                    alert17.showAndWait();
                    break;
                case 18:
                    Alert alert18 = new Alert(Alert.AlertType.INFORMATION);
                    alert18.setTitle("Errore");
                    alert18.setHeaderText(null);
                    alert18.setContentText("Nome utente già in uso");
                    alert18.showAndWait();
                    break;
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Campi non compilati");
            alert.showAndWait();
        }
    }

    @FXML
    public void completaLoginButton(ActionEvent event) throws IOException {
        if(!(nomeUtente1.equals("") || password1.equals(""))){
            switch (Client.getInstance().Login(nomeUtente1.getText(), password1.getText())) {
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Accesso Riuscito");
                    alert.setHeaderText(null);
                    alert.setContentText("Accesso Effettuato con successo");
                    alert.showAndWait();
                    signin_pane.setVisible(false);
                    logocentrale.setVisible(false);
                    creaplaylist.setVisible(true);
                    inserisciemozioni.setVisible(true);
                    accedi.setVisible(false);
                    registrati.setVisible(false);
                    aggiungibrano.setVisible(true);
                    visualizzaplaylist.setVisible(true);
                    eliminabrano.setVisible(true);
                    btlogout.setVisible(true);
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Accesso Fallito");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Nome utente o password errati");
                    alert1.showAndWait();
                    break;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Campi non compilati");
            alert.showAndWait();
        }
    }

    @FXML
    void inserisciEmozioniCButton(ActionEvent event) throws RemoteException {
        if(!(choicebox_emozione.getValue().toString().equals("") || choicebox_score.getValue().toString().equals("") || emozioneCanzone.getText().equals(""))){
            switch (Client.getInstance().InserisciEmozione(choicebox_emozione.getValue().toString(), choicebox_score.getValue().toString(), tf_note.getText(), emozioneCanzone.getText())){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Inserimento Riuscito");
                    alert.setHeaderText(null);
                    alert.setContentText("Inserimento Effettuato con successo");
                    alert.showAndWait();
                    emotion_pane.setVisible(false);
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Errore");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Emozione già registrata");
                    alert1.showAndWait();
                    break;
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Campi non compilati");
            alert.showAndWait();
        }
    }

    @FXML
    void creaPlaylistCButton(ActionEvent event) throws RemoteException {
        //controlli client
        if(!(nomePlaylist.getText().equals("") || playlistCanzone.getText().equals(""))) {
            switch (Client.getInstance().CreaPlaylist(nomePlaylist.getText(), playlistCanzone.getText(), autorePC.getText())) {
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText("Playlist creata con successo");
                    playlist_pane.setVisible(false);
                    alert.showAndWait();
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Errore");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Playlist già presente");
                    alert1.showAndWait();
                    break;
                case 2:
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Errore");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Canzone non esiste");
                    alert2.showAndWait();
                    break;
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Campi non compilati");
            alert.showAndWait();
        }
    }

    @FXML
    void aggiungiCanzoneCButton(ActionEvent event) throws RemoteException{
        //controlli client
        if(!(nomePlaylistI.getText().equals("")|| playlistCanzoneI.getText().equals(""))){
            switch (Client.getInstance().InserisciCanzone(playlistCanzoneI.getText(), nomePlaylistI.getText(), autorePI.getText())){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("La canzone è stata inserita con successo");
                    alert.showAndWait();
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Errore");
                    alert1.setHeaderText(null);
                    alert1.setContentText("La playlist non esiste");
                    alert1.showAndWait();
                    break;
                case 2:
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Errore");
                    alert2.setHeaderText(null);
                    alert2.setContentText("La canzone non esiste");
                    alert2.showAndWait();
                    break;
                case 3:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Errore");
                    alert3.setHeaderText(null);
                    alert3.setContentText("La canzone è già presente");
                    alert3.showAndWait();
                    break;
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Campi non compilati");
            alert.showAndWait();
        }
    }
    @FXML
    void eliminaCanzoneCButton(ActionEvent event) throws RemoteException{
        //controlli client
        if(!(nomePlaylistIC.getText().equals("")|| playlistCanzoneIC.getText().equals(""))){
            switch (Client.getInstance().EliminaCanzone(playlistCanzoneIC.getText(), nomePlaylistIC.getText(), autorePD.getText())){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("La canzone è stata eliminata con successo");
                    alert.showAndWait();
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Errore");
                    alert1.setHeaderText(null);
                    alert1.setContentText("La playlist non esiste");
                    alert1.showAndWait();
                    break;
                case 2:
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Errore");
                    alert2.setHeaderText(null);
                    alert2.setContentText("La canzone non esiste");
                    alert2.showAndWait();
                    break;
                case 3:
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Errore");
                    alert3.setHeaderText(null);
                    alert3.setContentText("La canzone non è nella playlist");
                    alert3.showAndWait();
                    break;
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Campi non compilati");
            alert.showAndWait();
        }
    }

    @FXML
    void ricercaTitoloButton(ActionEvent event) {
        String input = titolor.getText();
        try {
            ArrayList<Canzone> output = Client.getInstance().CercaBranoT(input);
            if (output.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Risultato ricerca:");
                alert.setHeaderText(null);
                alert.setContentText("Il titolo non è stato trovato");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ricerca per titolo");
                alert.setHeaderText(null);
                for(var item : output)
                {
                    alert.setContentText(alert.getContentText().concat("\n" + item.toString()));
                }
                alert.showAndWait();
            }
        }
        catch(Exception e){ e.printStackTrace();}
    }
    @FXML
    void ricercaAutoreButton(ActionEvent event) {
        String input = titolor.getText();
        try {
            ArrayList<Canzone> output = Client.getInstance().CercaBranoA(input);
            if (output.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Risultato ricerca:");
                alert.setHeaderText(null);
                alert.setContentText("l'autore non è stato trovato");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ricerca per Autore");
                alert.setHeaderText(null);
                for(var item : output)
                {
                    alert.setContentText(alert.getContentText().concat("\n" + item.toString()));
                }
                alert.showAndWait();
            }
        }
        catch(Exception e){ e.printStackTrace();}
    }
    @FXML
    void ricercaAnnoButton(ActionEvent event) {
        String input = titolor.getText();
        try {
            ArrayList<Canzone> output = Client.getInstance().CercaBranoY(input);
            if (output.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Risultato ricerca:");
                alert.setHeaderText(null);
                alert.setContentText("l'anno non è stato trovato");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ricerca per Anno");
                alert.setHeaderText(null);
                for(var item : output)
                {
                    alert.setContentText(alert.getContentText().concat("\n" + item.toString()));
                }
                alert.showAndWait();
            }
        }
        catch(Exception e){ e.printStackTrace();}
    }
    @FXML
    public void cercaPlaylistButton(ActionEvent event){
       /* String input = nomePlaylist.getText();
        try {
            ArrayList<Playlist> output = Client.getInstance().CercaPlaylist(input);
            if (output.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Risultato ricerca:");
                alert.setHeaderText(null);
                alert.setContentText("la playlist non è stato trovata");
                alert.showAndWait();
            } else {

            }
        }
        catch(Exception e){ e.printStackTrace();} */
    }
    @FXML
    public void visualizzaEmozioniCButton(ActionEvent event){

    }
    @FXML
    public void logoutButton(ActionEvent event){
        mostracanzoni_pane.setVisible(false);
        signin_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        emotion_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        logocentrale.setVisible(false);
        creaplaylist.setVisible(false);
        aggiungibrano.setVisible(false);
        inserisciemozioni.setVisible(false);
        visualizzaplaylist.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        btlogout.setVisible(false);
        eliminabrano.setVisible(false);
        registrati.setVisible(true);
        login.setVisible(true);
        visualizzacanzoni.setVisible(true);
        visualizza.setVisible(true);
        accedi.setVisible(true);

    }

    @FXML
    void registratiButton(ActionEvent event) throws IOException {
        signin_pane.setVisible(false);
        signup_pane.setVisible(true);
        emotionV_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        logocentrale.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }

    @FXML
    void accediButton(ActionEvent event) throws IOException {
        signup_pane.setVisible(false);
        signin_pane.setVisible(true);
        emotionV_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        logocentrale.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }

    @FXML
    void visualizzaEmozioniButton(ActionEvent event) {
        //da fare
        signin_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotionV_pane.setVisible(true);
        emotion_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        logocentrale.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }
    @FXML
    void creaPlaylistButton(ActionEvent event) {
        emotion_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        playlist_pane.setVisible(true);
        playlistI_pane.setVisible(false);
        logocentrale.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }
    @FXML
    void inserisciEmozioniButton(ActionEvent event) {
        emotion_pane.setVisible(true);
        emotionV_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        logocentrale.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }
    @FXML
    void aggiungiBranoButton(ActionEvent event) {
        emotion_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(true);
        logocentrale.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }

    @FXML
    void  eliminaBranoButton(ActionEvent event){
        eliminacanzone_pane.setVisible(true);
        emotion_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        playlist_pane.setVisible(false);
        logocentrale.setVisible(false);
        playlistI_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }

    @FXML
    void visualizzaPlaylistButton(ActionEvent event){
        visualizza_playlist_pane.setVisible(true);
        signin_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        emotion_pane.setVisible(false);
        logocentrale.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        mostracanzoni_pane.setVisible(false);
    }

    @FXML
    void visualizzacanzoniButton(ActionEvent event) throws RemoteException {
        mostracanzoni_pane.setVisible(true);
        signin_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        emotion_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(false);
        logocentrale.setVisible(false);

        //Canzone canzone = new Canzone("", "","");
        for(var item : Client.getInstance().VisualizzaCanzoni())
        {
            textareacanzoni.getItems().add(item);
        }
    }

    @FXML
    void visualizzaplaylistButton(ActionEvent event) throws RemoteException {
        mostracanzoni_pane.setVisible(false);
        signin_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        emotion_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        eliminacanzone_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(true);
        logocentrale.setVisible(false);

        Playlist playlist = new Playlist("", null,"");
        for(var item : Client.getInstance().VisualizzaPlaylist())
        {
            textAreaPlaylist.getItems().add(item);
        }
    }
/*
    @FXML
    void visualizzaemozioniButtone(ActionEvent event) throws RemoteException {
        mostracanzoni_pane.setVisible(false);
        signin_pane.setVisible(false);
        signup_pane.setVisible(false);
        emotionV_pane.setVisible(false);
        emotion_pane.setVisible(false);
        playlist_pane.setVisible(false);
        playlistI_pane.setVisible(false);
        visualizza_playlist_pane.setVisible(true);
        logocentrale.setVisible(false);

        Playlist playlist = new Playlist("", null,"");
        for(var item : Client.getInstance().VisualizzaPlaylist())
        {
            textAreaPlaylist.getItems().add(item);
        }
    }
*/
}
