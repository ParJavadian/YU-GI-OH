package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChangePasswordViewGraphic extends Application {

    private static Stage stage;
    static ChangePasswordViewGraphic instance = null;
    private static User user;

    public static ChangePasswordViewGraphic getInstance() {
        if (instance == null) instance = new ChangePasswordViewGraphic();
        return instance;
    }


    public void setCurrentUser(User user) {
        ChangePasswordViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ChangePasswordViewGraphic.stage = stage;
        URL url = getClass().getResource("/ChangePassword.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

}
