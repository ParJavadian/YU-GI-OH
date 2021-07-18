package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class Achievements extends Application implements Initializable {

    private static Stage stage;
    static Achievements instance = null;
    private static User user;
    public Label numberOfTrophy, numberOfBronze, numberOfSilver, numberOfGold, numberOfGifts;


    public static Achievements getInstance() {
        if (instance == null) instance = new Achievements();
        return instance;
    }

    public void setCurrentUser(User user) {
        Achievements.user = user;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Achievements.stage = stage;
        URL url = getClass().getResource("/Achievements.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goBack() throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }

    public void setNumbers(){
        numberOfBronze.setText(String.valueOf(user.getNumberOfBronze()));
        numberOfSilver.setText(String.valueOf(user.getNumberOfSilver()));
        numberOfGold.setText(String.valueOf(user.getNumberOfGold()));
        numberOfTrophy.setText(String.valueOf(user.getNumberOfTrophy()));
        numberOfGifts.setText(String.valueOf(user.getNumberOfGifts()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNumbers();
    }

    public void nextPage(MouseEvent event) throws Exception{
        Losses.getInstance().setCurrentUser(user);
        Losses.getInstance().start(stage);
    }
}

