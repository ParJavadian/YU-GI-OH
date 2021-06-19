package controller;

import controller.exeption.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import view.ChangePasswordViewGraphic;
import view.ProfileView;
import view.ProfileViewForGraphic;

public class ChangePasswordControllerGraphic {


    public static void changePassword(User user, String oldPassword, String newPassword,Stage stage) throws Exception {
        if (oldPassword.equals("")) {
            throw new EmptyOldPasswordBox();
        }
        if (newPassword.equals("")) {
            throw new EmptyNewPasswordBox();
        }
        if(!user.getPassword().equals(oldPassword))
            throw new WrongPassword();
        if(user.getPassword().equals(newPassword))
            throw new SamePassword();
        user.setPassword(newPassword);
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(user);
        ChangePasswordViewGraphic.showPasswordChanged(user);
        ProfileViewForGraphic.getInstance().start(stage);
        //ProfileView.getInstance(user).printText("password changed successfully!");
    }


    public static void goBack(Stage stage) throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }
}
