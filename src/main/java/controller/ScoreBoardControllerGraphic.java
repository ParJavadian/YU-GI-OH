package controller;

import javafx.stage.Stage;
import model.User;
import view.MainViewGraphic;
import view.ScoreBoardView;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;

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


    public String showScoreboard() {
        List<User> allUsers = User.getAllUsers();
        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
        if (allUsers != null) {
            allUsers.sort(userComparator);
            User previousUser = null;
            int rank = 1;
            StringBuilder toPrint = new StringBuilder();
            int i = 1;
            int userCounter = 0;
            for (User eachUser : allUsers) {
                if (userCounter >= 10) {
                    return toPrint.toString();
                } else {
                    if (previousUser != null) {
                        if (previousUser.getScore() == eachUser.getScore()) {
                            i++;
                        } else {
                            rank += i;
                            i = 1;
                        }
                    }
                    toPrint.append(rank).append("-  NickName:").append(eachUser.getNickname()).append("      Score: ").append(eachUser.getScore()).append("\n");
                    previousUser = eachUser;
                    userCounter++;
                }
            }
            return toPrint.toString();
        }
        return "";
    }

    public static void goBack(Stage stage) throws Exception {
        MainViewGraphic.getInstance().start(stage);
//        PreviousMenuControllerGraphic.goBack(previousMenu,stage);
    }
}
