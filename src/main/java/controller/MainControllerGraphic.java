package controller;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import view.LogInViewGraphic;
import view.ProfileViewForGraphic;
import view.ScoreBoardViewGraphic;
import view.ShopViewGraphic;

public class MainControllerGraphic {

    public static void logout(Stage stage) throws Exception {
        LogInViewGraphic.getInstance().start(stage);
    }

    public static void showScoreBoard(Stage stage) throws Exception{
        ScoreBoardControllerGraphic.setPreviousMenu(MenuTypesGraphic.MAINCONTROLLERGRAPHIC);
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
}
