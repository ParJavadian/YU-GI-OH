package controller;

import model.User;
import view.ScoreBoardView;

import java.util.Comparator;
import java.util.List;

public class ScoreBoardControllerGraphic {

    static ScoreBoardControllerGraphic instance = null;
//    private static User user;

    public static ScoreBoardControllerGraphic getInstance() {
        if (instance == null) instance = new ScoreBoardControllerGraphic();
        return instance;
    }

//    public void setCurrentUser(User user) { ScoreBoardControllerGraphic.user = user;
//    }


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
}
