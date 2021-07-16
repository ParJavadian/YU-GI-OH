package Server.controller;

import javafx.stage.Stage;
import model.User;
import Client.view.ChangeNicknameViewGraphic;
import Client.view.ChangePasswordViewGraphic;
import Client.view.MainViewGraphic;

public class ProfileControllerGraphic {
    public static void changePassword(User user, Stage stage) throws Exception {
        ChangePasswordViewGraphic.getInstance().setCurrentUser(user);
        ChangePasswordViewGraphic.getInstance().start(stage);
    }

    public static void changeNickname(User user,Stage stage) throws Exception {
        ChangeNicknameViewGraphic.getInstance().setCurrentUser(user);
        ChangeNicknameViewGraphic.getInstance().start(stage);
    }

    public static void goBack(Stage stage) throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }
}
