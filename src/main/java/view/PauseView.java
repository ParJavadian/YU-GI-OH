package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class PauseView extends Application {

    private static Stage stage;
    public static PauseView instance = null;

    @Override
    public void start(Stage stage) throws Exception {
        PauseView.stage = stage;
        URL url = getClass().getResource("/PauseMenu.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
//        stage.setTitle("YU GI OH");
//        stage.show();
    }

    public void muteUnmute() {

    }

    public void resume() {

    }

    public void endGame() {

    }

}
