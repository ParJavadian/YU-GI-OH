package controller;

import controller.exeption.SamePassword;
import controller.exeption.WrongPassword;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import view.ProfileView;
import view.ProfileViewForGraphic;

public class ChangePasswordControllerGraphic {


    public static void changePassword(User user, String oldPassword, String newPassword) throws Exception {
        if(!user.getPassword().equals(oldPassword))
            throw new WrongPassword();
        if(user.getPassword().equals(newPassword))
            throw new SamePassword();
        user.setPassword(newPassword);
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(user);
        ProfileView.getInstance(user).printText("password changed successfully!");
    }



    public static void goBack(Stage stage) throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }
}
