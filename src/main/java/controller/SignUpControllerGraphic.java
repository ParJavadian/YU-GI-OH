package controller;

import controller.exeption.*;
import javafx.stage.Stage;
import model.User;
import view.LogInViewGraphic;
import view.MainViewGraphic;
import view.SignUpViewGraphic;

import java.util.List;

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
        System.out.println("1");
        if (checkPassword.equals("")) {
            throw new EmptyConfirmPasswordBox();
        }
        if (!passwordsAreEqual(password,checkPassword)) {
            throw new DifferentPasswords();
        }
        System.out.println("2");
        List<User> allUsers = User.getAllUsers();
        if (allUsers != null) {
            for (User eachUser : allUsers) {
                if (eachUser.getUsername().equals(username)) {
                    throw new RepetitiveUsername(username);
                } else if (eachUser.getNickname().equals(nickname)) {
                    throw new RepetitiveNickname(nickname);
                }
            }
        }
        System.out.println("3");
        User user = new User(username, nickname, password);
        SignUpViewGraphic.getInstance().showAccountCreatedPopUp();
        MainViewGraphic.getInstance().setCurrentUser(user);
        MainViewGraphic.getInstance().start(stage);
        System.out.println("4");
    }

    public static boolean passwordsAreEqual(String firstPassword,String secondPassword) {
        return firstPassword.equals(secondPassword);
    }
}
