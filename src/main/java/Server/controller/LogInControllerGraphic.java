package Server.controller;

import Server.controller.exeption.*;
import javafx.stage.Stage;
import model.User;
import Client.view.MainViewGraphic;
import Client.view.SignUpViewGraphic;

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
            MainViewGraphic.getInstance().start(stage);
        }
    }
}
