package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChangeNicknameViewGraphic extends Application {

    private static Stage stage;
    static ChangeNicknameViewGraphic instance = null;
    private static User user;

    public static ChangeNicknameViewGraphic getInstance() {
        if (instance == null) instance = new ChangeNicknameViewGraphic();
        return instance;
    }


    public void setCurrentUser(User user) {
        ChangeNicknameViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ChangeNicknameViewGraphic.stage = stage;
        URL url = getClass().getResource("/ChangeNickname.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

}
