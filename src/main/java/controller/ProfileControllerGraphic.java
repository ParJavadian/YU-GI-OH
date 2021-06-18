package controller;

import javafx.stage.Stage;
import model.User;
import view.ChangeNicknameViewGraphic;
import view.ChangePasswordViewGraphic;

public class ProfileControllerGraphic {

    public static void setPreviousMenu(MenuTypesGraphic previousMenu) {
        ProfileControllerGraphic.previousMenu = previousMenu;
    }

    private static MenuTypesGraphic previousMenu;

    public static void changePassword(User user, Stage stage) throws Exception {
        ChangePasswordViewGraphic.getInstance().setCurrentUser(user);
        ChangePasswordViewGraphic.getInstance().start(stage);
    }

    public static void changeNickname(User user,Stage stage) throws Exception {
        ChangeNicknameViewGraphic.getInstance().setCurrentUser(user);
        ChangeNicknameViewGraphic.getInstance().start(stage);
    }

    public static void goBack(Stage stage){
        PreviousMenuControllerGraphic.goBack(previousMenu,stage);
    }
}
