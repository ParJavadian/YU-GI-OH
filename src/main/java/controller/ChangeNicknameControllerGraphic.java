package controller;

import controller.exeption.EmptyNicknameBox;
import controller.exeption.RepetitiveNickname;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;
import view.ChangeNicknameViewGraphic;
import view.ChangePasswordViewGraphic;
import view.ProfileView;
import view.ProfileViewForGraphic;

import javax.jws.soap.SOAPBinding;

public class ChangeNicknameControllerGraphic {

    public static void goBack(Stage stage) throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }

    public static void changeNickname (String nickname, User user,Stage stage) throws Exception {
        if (nickname.equals("")) {
            throw new EmptyNicknameBox();
        }
        if(User.getUserByNickname(nickname)!=null){
            throw new RepetitiveNickname(nickname);
        }
        user.setNickname(nickname);
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(user);
        ChangeNicknameViewGraphic.showNicknameChanged(user);
        //ChangePasswordViewGraphic.showPasswordChanged(user);
        ProfileViewForGraphic.getInstance().start(stage);
//        ProfileView.getInstance(this.user).printText("nickname changed successfully!");
    }



}
