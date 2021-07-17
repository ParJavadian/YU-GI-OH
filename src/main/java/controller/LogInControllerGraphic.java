package controller;

import client.Main;
import controller.exeption.*;
import javafx.stage.Stage;
import model.User;
import view.*;

import java.io.IOException;

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
            System.out.println("allusers1: " + User.getAllUsers());
            sendToServer(user);
            MainViewGraphic.getInstance().setCurrentUser(user);
            MainViewGraphic.getInstance().start(stage);
        }
    }

    public static void sendToServer(User user) {
        try {
            Main.dataOutputStream.writeUTF("login " + user.getUsername());
            System.out.println("allusers2: " + User.getAllUsers());
            Main.dataOutputStream.flush();
            System.out.println("allusers3: " + User.getAllUsers());
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
