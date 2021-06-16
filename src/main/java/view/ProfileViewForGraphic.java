package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ProfileViewForGraphic extends Application {

    private static Stage stage;
    private static ProfileViewForGraphic instance = null;
    private User user;

//    private ProfileViewForGraphic(User user) {
//        this.user = user;
//    }

//    public static ProfileViewForGraphic getInstance(User user) {
//        if (instance == null) instance = new ProfileViewForGraphic(user);
//        else if (!instance.user.equals(user)) instance.user = user;
//        return instance;
//    }

    @Override
    public void start(Stage stage) throws Exception {
        ProfileViewForGraphic.stage = stage;
        URL url = getClass().getResource("/Profile.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

}
