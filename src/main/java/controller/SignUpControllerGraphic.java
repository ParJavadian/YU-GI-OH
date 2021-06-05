package controller;

import controller.exeption.*;
import javafx.stage.Stage;
import model.User;
import view.LogInViewGraphic;
import view.MainViewGraphic;
import view.SignUpViewGraphic;

public class SignUpControllerGraphic {
    public static void login(Stage stage) throws Exception {
        LogInViewGraphic.getInstance().start(stage);
    }

    public static void createAccount(String username,String nickname, String password, String checkPassword, Stage stage) throws Exception {
        if (username.equals("")) {
            throw new EmptyUsernameBox();
        }
        if (nickname.equals("")) {
            throw new EmptyNicknameBox();
        }
        if (password.equals("")) {
            throw new EmptyPasswordBox();
        }
        if (checkPassword.equals("")) {
            throw new EmptyConfirmPasswordBox();
        }
        if (!passwordsAreEqual(password,checkPassword)) {
            throw new DifferentPasswords();
        }
        User user = User.getUserByUsername(username);
        if (user != null) {
            throw new TakenUsername();
        }
        user = new User(username,nickname,password);
        SignUpViewGraphic.getInstance().showAccountCreatedPopUp();
        MainViewGraphic.getInstance().setCurrentUser(user);
        MainViewGraphic.getInstance().start(stage);
    }

    public static boolean passwordsAreEqual(String firstPassword,String secondPassword) {
        return firstPassword.equals(secondPassword);
    }
}
