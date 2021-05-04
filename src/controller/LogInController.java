package controller;

import controller.exeption.RepetitiveNickname;
import controller.exeption.RepetitiveUsername;
import controller.exeption.UsernameNotFound;
import controller.exeption.WrongPassword;
import model.User;
import view.LogInView;
import view.MainView;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class LogInController {

    private static LogInController instance = null;

    public static LogInController getInstance() {
        if (instance == null) instance = new LogInController();
        return instance;
    }

    private LogInController() {

    }

    public void createUser(String username, String password, String nickname) throws Exception {
        List<User> allUsers = User.getAllUsers();
        for (User eachUser : allUsers) {
            if (eachUser.getUsername().equals(username)) {
                throw new RepetitiveUsername(username);
            } else if (eachUser.getNickname().equals(nickname)) {
                throw new RepetitiveNickname(nickname);
            } else {
                new User(username, nickname, password);
                LogInView.getInstance().printText("user created successfully!");
            }
        }
    }

    public void loginUser(String userName, String password) throws Exception{
        User user = User.getUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFound();
        } else if (!user.getPassword().equals(password)) {
            throw new WrongPassword();
        } else {
            LogInView.getInstance().printText("user logged in successfully!");
            MainView.getInstance(User.getUserByUsername(userName)).getCommandForMain();
        }
    }



}
