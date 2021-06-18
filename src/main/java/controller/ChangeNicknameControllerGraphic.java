package controller;

import javafx.stage.Stage;
import view.ProfileViewForGraphic;

public class ChangeNicknameControllerGraphic {

    public static void goBack(Stage stage) throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }

}
