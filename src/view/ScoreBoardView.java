package view;

import controller.ScoreBoardController;
import model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreBoardView {
    private static ScoreBoardView instance = null;
    private User user;

    public static ScoreBoardView getInstance(User user) {
        if (instance == null) instance = new ScoreBoardView(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ScoreBoardView(User user) {
        this.user = user;
    }

    public void getCommandForScoreboard() {
        String command;
        while (true) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
    }

    private boolean processCommand(String command) {
        if (command.startsWith("menu enter ")) {
            printText("menu navigation is not possible");
            return false;
        }

        Matcher matcher = getCommandMatcher(command, "menu show-current");
        if (matcher.matches()) {
            printText("Scoreboard Menu");
            return false;
        }
        if (command.equals("scoreboard show")) {
            ScoreBoardController.getInstance(this.user).showScoreboard();
            return false;
        }
        if (command.equals("menu exit")) return true;
        printText("invalid command");
        return false;
    }

    /*

      private void showMenu() {

      }
      public void exitMenu() {

      }

      public void printException(Exception output) {

      }
  */
    public void printText(String output) {
        System.out.println(output);
    }

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }
}
