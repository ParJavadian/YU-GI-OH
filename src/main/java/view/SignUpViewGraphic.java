package view;

import controller.SignUpControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

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
//        if (LogInViewGraphic.instance == null && (SignUpViewGraphic.instance == null)){
//            Media media = new Media(getClass().getResource("/velum.mp3").toURI().toString());
//            MediaPlayer mp = new MediaPlayer(media);
//            mp.setCycleCount(AudioClip.INDEFINITE);
//            mp.play();
//        }
        SignUpViewGraphic.stage = stage;
        URL url = getClass().getResource("/SignUp.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void login() throws Exception {
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
