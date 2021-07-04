package controller;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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


    public TextFlow showScoreboard(User user) {
        TextFlow textFlow = new TextFlow();
        Text text1 = new Text();
        //Text text2 = new Text();
        List<User> allUsers = User.getAllUsers();
        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
//        if (allUsers != null) {
        allUsers.sort(userComparator);
        User previousUser = null;
        int rank = 1;
        //StringBuilder toPrint = new StringBuilder();
        int i = 1;
        int userCounter = 0;
        for (User eachUser : allUsers) {
            if (userCounter >= 20) {
                return textFlow;
            }
            if (eachUser.equals(user)) {
                text1.setFill(Color.RED);
            } else {
                text1.setFill(Color.BLUE);
            }
            if (previousUser != null) {
                if (previousUser.getScore() == eachUser.getScore()) {
                    i++;
                } else {
                    rank += i;
                    i = 1;
                }
            }
            text1 = new Text(rank + "-  NickName:" + eachUser.getNickname() + "      Score: " + eachUser.getScore());
            previousUser = eachUser;
            userCounter++;
            textFlow.getChildren().add(text1);
        }
        return textFlow;
//        }
//        return textFlow;
    }


//    public String showScoreboard(User user) {
//        List<User> allUsers = User.getAllUsers();
//        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
//        if (allUsers != null) {
//            allUsers.sort(userComparator);
//            User previousUser = null;
//            int rank = 1;
//            StringBuilder toPrint = new StringBuilder();
//            int i = 1;
//            int userCounter = 0;
//            for (User eachUser : allUsers) {
//                if (eachUser.equals(user)) {
//
//                }
//                if (userCounter >= 20) {
//                    return toPrint.toString();
//                } else {
//                    if (previousUser != null) {
//                        if (previousUser.getScore() == eachUser.getScore()) {
//                            i++;
//                        } else {
//                            rank += i;
//                            i = 1;
//                        }
//                    }
//                    toPrint.append(rank).append("-  NickName:").append(eachUser.getNickname()).append("      Score: ").append(eachUser.getScore()).append("\n");
//                    previousUser = eachUser;
//                    userCounter++;
//                }
//            }
//            return toPrint.toString();
//        }
//        return "";
//    }

    public static void goBack(Stage stage) throws Exception {
        MainViewGraphic.getInstance().start(stage);
//        PreviousMenuControllerGraphic.goBack(previousMenu,stage);
    }
}
