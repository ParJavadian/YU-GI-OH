package controller;

import javafx.stage.Stage;
import view.MainViewGraphic;
import view.ProfileViewForGraphic;

public class ChooseDuelControllerGraphic {



    public static void goBack(Stage stage) throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }
}
