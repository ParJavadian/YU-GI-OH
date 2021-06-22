package controller;

import controller.exeption.*;
import view.*;
import model.*;

public class ProfileController {
    private static ProfileController instance = null;
    private User user;

    public static ProfileController getInstance (User user) {
        if (instance == null) instance = new ProfileController(user);
        else if(!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ProfileController(User user){
        this.user = user;
    }

    public void changeNickname (String nickname) throws Exception {
        if (this.user.getNickname().equals(nickname)) throw new SameNewNickname();
        else if(User.getUserByNickname(nickname)!=null)
            throw new RepetitiveNickname(nickname);

        this.user.setNickname(nickname);
        ProfileView.getInstance(this.user).printText("nickname changed successfully!");
    }

    public void changeUsername(String username) throws Exception{
        if (this.user.getUsername().equals(username)) throw new SameNewUsername();
        else if (User.getUserByUsername(username) != null){
            throw new RepetitiveUsername(username);
        }
        this.user.setUsername(username);
        ProfileView.getInstance(this.user).printText("username changed successfully!");
    }

    public void changePassword (String oldPassword, String newPassword) throws Exception {
        if(!this.user.getPassword().equals(oldPassword))
            throw new WrongPassword();
        if(this.user.getPassword().equals(newPassword))
            throw new SamePassword();
        this.user.setPassword(newPassword);
        ProfileView.getInstance(this.user).printText("password changed successfully!");
    }
}
