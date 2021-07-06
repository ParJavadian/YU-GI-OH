package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardGamePlayerOne extends Application implements Initializable {
    private static Stage stage;
    public static User player;
    public static  User rival;
    private static BoardGamePlayerOne instance = null;
    public Label chatBox;
    public Label rivalLifePoint;
    public Label playerLifePoint;
    public int numberOfRounds;

    public void setPlayer(User player) {
        BoardGamePlayerOne.player = player;
    }

    public void setRival(User rival) {
        BoardGamePlayerOne.rival = rival;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public static BoardGamePlayerOne getInstance() {
        if (instance == null) instance = new BoardGamePlayerOne();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BoardGamePlayerOne.stage = stage;
        URL url = getClass().getResource("/BoardGamePlayerOne.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (playerLifePoint != null && rivalLifePoint != null && player != null && rival != null) {
            playerLifePoint.setText(String.valueOf(player.getLifePoint()));
            rivalLifePoint.setText(String.valueOf(rival.getLifePoint()));
        }
    }


    public void pauseMenu(MouseEvent event) {

    }
}
