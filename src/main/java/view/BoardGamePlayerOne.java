package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    ImageView imageViewPlayer, imageViewRival;
    private static AnchorPane root;

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
        root = FXMLLoader.load(url);
        setImageView();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerLifePoint.setText(String.valueOf(player.getLifePoint()));
        rivalLifePoint.setText(String.valueOf(rival.getLifePoint()));


    }

    private void setImageView() {
        URL url = getClass().getResource("/images/profiles/profile ("+player.getProfileNumber()+").png");
        Image image = new Image(String.valueOf(url));
        imageViewPlayer = new ImageView(image);
        imageViewPlayer.setImage(image);
        root.getChildren().add(imageViewPlayer);
        url = getClass().getResource("/images/profiles/profile ("+rival.getProfileNumber()+").png");
        image = new Image(String.valueOf(url));
        imageViewRival = new ImageView(image);
        imageViewRival.setImage(image);
        root.getChildren().add(imageViewRival);
    }


}
