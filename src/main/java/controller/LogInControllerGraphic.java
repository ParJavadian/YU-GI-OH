package controller;

import controller.exeption.EmptyPasswordBox;
import controller.exeption.EmptyUsernameBox;
import controller.exeption.WrongPassword;
import controller.exeption.WrongPasswordForSignInMenu;
import javafx.stage.Stage;
import model.User;
import view.MainView;
import view.MainViewGraphic;
import view.SignUpViewGraphic;

public class LogInControllerGraphic {
    public static void signup(Stage stage) throws Exception {
        SignUpViewGraphic.getInstance().start(stage);
    }

    public static void login(String username, String password, Stage stage) throws Exception {
        if (username.equals("")) {
            throw new EmptyUsernameBox();
        }
        if (password.equals("")) {
            throw new EmptyPasswordBox();
        }
        User user = User.getUserByUsername(username);
        if (User.getUserByUsername(username) == null || (!user.getPassword().equals(password))) {
            throw new WrongPasswordForSignInMenu();
        }
        MainViewGraphic.getInstance().setCurrentUser(user);
        MainViewGraphic.getInstance().start(stage);
    }

}
