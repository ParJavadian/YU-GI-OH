package controller;

import javafx.stage.Stage;
import view.ProfileViewForGraphic;

public class ChangePasswordControllerGraphic {

    public static void goBack(Stage stage) throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }
}
