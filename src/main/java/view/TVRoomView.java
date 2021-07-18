package view;

import view.Losses;
import view.MainViewGraphic;
import view.ProfileViewForGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class TVRoomView extends Application {

    private static Stage stage;
    static TVRoomView instance = null;
    private static User user;

    public static TVRoomView getInstance() {
        if (instance == null) instance = new TVRoomView();
        return instance;
    }

    public void setCurrentUser(User user) {
        TVRoomView.user = user;
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
