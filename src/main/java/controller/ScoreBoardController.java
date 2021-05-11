package controller;

import model.Card;
import model.User;
import view.ScoreBoardView;

import java.util.Comparator;
import java.util.List;


public class ScoreBoardController {
    private static ScoreBoardController instance = null;
    private User user;

    public static ScoreBoardController getInstance(User user) {
        if (instance == null) instance = new ScoreBoardController(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ScoreBoardController(User user) {
        this.user = user;
    }

    public void showScoreboard() {
        List<User> allUsers = User.getAllUsers();
        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
        allUsers.sort(userComparator);
        User previousUser = null;
        int rank = 1;
        String toPrint = null;
        int i = 1;
        for (User eachUser : allUsers){
            if (previousUser != null){
                if (previousUser.getScore() == eachUser.getScore()){
                    i++;
                } else {
                    rank += i;
                    i = 1;
                }
            }
            if (allUsers.indexOf(eachUser) == allUsers.size()-1){
                toPrint += rank+"- "+eachUser.getNickname()+": "+eachUser.getScore();
            } else {
                toPrint += rank + "- " + eachUser.getNickname() + ": " + eachUser.getScore() + "\n";
            }
            previousUser = eachUser;
        }
        ScoreBoardView.getInstance(user).printText(toPrint);
    }
}
