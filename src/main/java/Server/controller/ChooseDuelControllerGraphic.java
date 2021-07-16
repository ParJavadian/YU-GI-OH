package Server.controller;

import javafx.stage.Stage;
import Client.view.ChooseSecondPlayerViewGraphic;

public class ChooseDuelControllerGraphic {
    public static void goBack(Stage stage) throws Exception {
        ChooseSecondPlayerViewGraphic.getInstance().start(stage);
    }
}
