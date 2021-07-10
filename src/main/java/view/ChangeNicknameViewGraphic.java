package view;

import controller.ChangeNicknameControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChangeNicknameViewGraphic extends Application {

    private static Stage stage;
    private static User user;
    static ChangeNicknameViewGraphic instance = null;
    @FXML
    private TextField nickname;

    public static ChangeNicknameViewGraphic getInstance() {
        if (instance == null) instance = new ChangeNicknameViewGraphic();
        return instance;
    }

    public static void showNicknameChanged() {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Done");
        error.setContentText("your nickname was changed successfully!");
        error.showAndWait();
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
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void goBack() throws Exception {
        ChangeNicknameControllerGraphic.goBack(stage);
    }

    public void changeNickname() {
        try {
            ChangeNicknameControllerGraphic.changeNickname(nickname.getText(), user,stage);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
}
