package controller;

import controller.exeption.RepetitiveNickname;
import controller.exeption.SamePassword;
import controller.exeption.WrongPassword;
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
        if(User.getUserByNickname(nickname)!=null){
            throw new RepetitiveNickname(nickname);
        }
        this.user.setNickname(nickname);
        ProfileView.getInstance(this.user).printText("nickname changed successfully!");
    }

    public void changePassword (String oldPassword, String newPassword) throws Exception {
        if(!this.user.getPassword().equals(oldPassword)) throw new WrongPassword();
        if(this.user.getPassword().equals(newPassword)) throw new SamePassword();
        this.user.setPassword(newPassword);
    }
}
