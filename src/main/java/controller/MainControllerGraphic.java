package controller;

import javafx.stage.Stage;
import model.User;
import view.*;

public class MainControllerGraphic {

    public static void logout(Stage stage) throws Exception {
        LogInViewGraphic.getInstance().start(stage);
    }

    public static void showScoreBoard(User user,Stage stage) throws Exception{
        ScoreBoardControllerGraphic.setPreviousMenu(MenuTypesGraphic.MAINCONTROLLERGRAPHIC);
        ScoreBoardViewGraphic.getInstance().setCurrentUser(user);
        ScoreBoardViewGraphic.getInstance().start(stage);
    }

    public static void showProfileMenu(User user, Stage stage) throws Exception {
//        ProfileControllerGraphic.setPreviousMenu(MenuTypesGraphic.MAINCONTROLLERGRAPHIC);
        ProfileViewForGraphic.getInstance().setCurrentUser(user);
        ProfileViewForGraphic.getInstance().start(stage);
    }

    public static void showShopMenu(User user, Stage stage) throws Exception {
//        ProfileControllerGraphic.setPreviousMenu(MenuTypesGraphic.MAINCONTROLLERGRAPHIC);
        ShopViewGraphic.getInstance().setCurrentUser(user);
        ShopViewGraphic.getInstance().start(stage);
    }

    public static void showDeckMenu(User user, Stage stage) throws Exception {
        AllDecksViewGraphic.getInstance().setCurrentUser(user);
        AllDecksViewGraphic.getInstance().start(stage);
    }

    public static void showNewDuelMenu(User user, Stage stage) throws Exception{
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().start(stage);
    }

//    public static void showDeckMenu(User user, Stage stage) throws Exception {
//        DeckViewGraphic.getInstance().setCurrentUser(user);
//        DeckViewGraphic.getInstance().start(stage);
//    }
}
