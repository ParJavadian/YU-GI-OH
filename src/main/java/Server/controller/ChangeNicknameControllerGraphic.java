package Server.controller;

import Server.controller.exeption.EmptyNicknameBox;
import Server.controller.exeption.RepetitiveNickname;
import javafx.stage.Stage;
import model.User;
import Client.view.ChangeNicknameViewGraphic;
import Client.view.ProfileViewForGraphic;
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
        ChangeNicknameViewGraphic.showNicknameChanged();
        ProfileViewForGraphic.getInstance().start(stage);
    }
}
