package view;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

import java.net.URL;

public class SignUpViewGraphic extends Application {
    private static Stage stage;
    static SignUpViewGraphic instance = null;
    @FXML
    private TextField username;
    @FXML
    private TextField nickname;
    @FXML
    private TextField password;
    @FXML
    private TextField checkPassword;

    public static SignUpViewGraphic getInstance() {
        if (instance == null) instance = new SignUpViewGraphic();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (LogInViewGraphic.instance == null && (SignUpViewGraphic.instance == null)){

            SoundController.getInstance().playWhenStart();


            ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
            importExportUserController.importAllUsers();
            importExportUserController.importProfileNumber();
            importExportUserController.importAllCards();
            importExportUserController.importAllDecks();
            importExportUserController.importActiveDeck();
            Deck deck = DeckController.getInstance(User.getUserByUsername("@AI@")).createRandomDeckForAI();
            User.getUserByUsername("@AI@").setActiveDeck(deck);
//            Media media = new Media(getClass().getResource("/TheHours.mp3").toURI().toString());
//            MediaPlayer mp = new MediaPlayer(media);
//            mp.setCycleCount(AudioClip.INDEFINITE);
//            mp.play();
        }

        SignUpViewGraphic.stage = stage;
        URL url = getClass().getResource("/SignUp.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void login() throws Exception {

        //todo pak shavad

//        Label label = new Label("This is a Popup");
//
//        // create a popup
//        Popup popup = new Popup();
//
//        // set background
//        label.setStyle(" -fx-background-color: white;");
//
//        // add the label
//        popup.getContent().add(label);
//
//        // set size of label
//        label.setMinWidth(80);
//        label.setMinHeight(50);
//        popup.show(stage);


        //todo
//        ImportExportCardController.exportMonsterCardForCsv(MonsterCard.AXE_RAIDER);
//        ImportExportCardController.exportTrapCardForCsv(TrapCard.MAGIC_JAMMER);
//        ImportExportCardController.exportSpellCardForCsv(SpellCard.MAGNUM_SHIELD);
        //System.out.println(ImportExportCardController.importCardForCsv("MAGIC_JAMMER"));

        SignUpControllerGraphic.login(stage);
    }

    public void createAccount() {
        try {
            SignUpControllerGraphic.createAccount(username.getText(),nickname.getText(), password.getText(), checkPassword.getText(),stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("");
            error.setContentText(e.getMessage());
            error.showAndWait();
//            Alert error = new Alert(Alert.AlertType.WARNING);
//            error.setHeaderText("Error");
//            error.setContentText(e.getMessage());
//            error.showAndWait();
        }
    }

    public void showAccountCreatedPopUp(){
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("");
        error.setContentText("account created successfully");
        error.showAndWait();
    }


}
