package controller;

import controller.exeption.*;
import javafx.stage.Stage;
import model.User;
import view.LogInView;
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
        if (user == null) {
            throw new UsernameNotFound();
        } else if (!user.getPassword().equals(password)) {
            throw new UsernameNotFound();
        } else {
            MainViewGraphic.getInstance().setCurrentUser(user);
            MainView.getInstance(User.getUserByUsername(username)).getCommandForMain();
        }



//        LogInController.getInstance().createUser(username,password,nickn);
//        if (username.equals("")) {
//            throw new EmptyUsernameBox();
//        }
//        if (password.equals("")) {
//            throw new EmptyPasswordBox();
//        }
//        User user = User.getUserByUsername(username);
//        if (User.getUserByUsername(username) == null || (!user.getPassword().equals(password))) {
//            throw new WrongPasswordForSignInMenu();
//        }
//        MainViewGraphic.getInstance().setCurrentUser(user);
//        MainViewGraphic.getInstance().start(stage);
    }

}
