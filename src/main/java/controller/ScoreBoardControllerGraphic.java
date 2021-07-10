package controller;

import javafx.stage.Stage;
import view.MainViewGraphic;

public class ScoreBoardControllerGraphic {

    private static MenuTypesGraphic previousMenu;
    static ScoreBoardControllerGraphic instance = null;


    public static void setPreviousMenu(MenuTypesGraphic previousMenu) {
        ScoreBoardControllerGraphic.previousMenu = previousMenu;
    }

    public static ScoreBoardControllerGraphic getInstance() {
        if (instance == null) instance = new ScoreBoardControllerGraphic();
        return instance;
    }

    public static void goBack(Stage stage) throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }
}
