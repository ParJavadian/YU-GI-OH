package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class TVRoomView extends Application {

    private static Stage stage;
    static TVRoomView instance = null;

    public static TVRoomView getInstance() {
        if (instance == null) instance = new TVRoomView();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        TVRoomView.stage = stage;
        URL url = getClass().getResource("/TVRoom.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goBack() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }
}
