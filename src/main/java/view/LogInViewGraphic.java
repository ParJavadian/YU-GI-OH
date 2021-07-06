package view;

import controller.ImportExportUserController;
import controller.LogInControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;

public class LogInViewGraphic extends Application {
    private static Stage stage;
    static LogInViewGraphic instance = null;
    static MediaPlayer mediaPlayer;
    static String current;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public static LogInViewGraphic getInstance() {
        if (instance == null) instance = new LogInViewGraphic();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        if ((LogInViewGraphic.instance == null) && (SignUpViewGraphic.instance == null)) {
            ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
            importExportUserController.importAllUsers();
            importExportUserController.importAllCards();
            importExportUserController.importAllDecks();
            importExportUserController.importActiveDeck();
//            Media media = new Media(getClass().getResource("/velum.mp3").toURI().toString());
//            mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
//            mediaPlayer.play();
//            current = "play";
        }


        LogInViewGraphic.stage = stage;
        URL url = getClass().getResource("/Login.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void signup() throws Exception {
        LogInControllerGraphic.signup(stage);
    }

    public void login() {
        try {
            LogInControllerGraphic.login(username.getText(), password.getText(), stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }


}
