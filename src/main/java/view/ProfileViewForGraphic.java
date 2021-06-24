package view;

import controller.ProfileControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ProfileViewForGraphic extends Application {

    private static Stage stage;
    private static ProfileViewForGraphic instance = null;
    private static User user;

    public static ProfileViewForGraphic getInstance() {
        if (instance == null) instance = new ProfileViewForGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ProfileViewForGraphic.user = user;
    }

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

    public void changePassword() throws Exception {
        ProfileControllerGraphic.changePassword(user,stage);
    }

    public void changeNickname() throws Exception {
        ProfileControllerGraphic.changeNickname(user,stage);
    }

    public void goBack() throws Exception {
        ProfileControllerGraphic.goBack(stage);
    }

}
