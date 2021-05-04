package view;

import controller.MainController;
import controller.ShopController;
import model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainView {
    private static MainView instance = null;
    private User user;

    public static MainView getInstance(User user) {
        if (instance == null) instance = new MainView(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private MainView(User user) {
        this.user = user;
    }

    public void getCommandForMain() {
        String command;
        while (true) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
        printText("user logged out successfully!");
    }

    private boolean processCommand(String command) {
        MainController mainController = MainController.getInstance(this.user);
        if (command.startsWith("duel ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command, "(--new|-n)");
            Matcher matcher2 = getCommandMatcher(command, "(--second-player|-sp) ([\\w ]+)");
            Matcher matcher3 = getCommandMatcher(command, "(--rounds|-r) ([0-9]+)");
            Matcher matcher4 = getCommandMatcher(command, "(--ai|-ai)");
            if (matcher1.find() && matcher2.find() && matcher3.find()) {
                try {
                    mainController.newDuel(matcher2.group(2), Integer.parseInt(matcher3.group(2)));
                }
                catch (Exception exception){
                    printText(exception.getMessage());
                }
                return false;
            }
            if(matcher4.find() && matcher1.find() && matcher3.find()){
                try{
                    //TODO NEW GAME WITH AI
                }
                catch (Exception exception){
                    printText(exception.getMessage());
                }
            }
        }
        Matcher matcher = getCommandMatcher(command, "menu enter ([\\w ]+)");
        if (matcher.matches()) {
            mainController.goToMenu(matcher.group(1));
            return false;
        }
        matcher = getCommandMatcher(command, "menu show-current");
        if (matcher.matches()) {
            printText("Main Menu");
            return false;
        }
        if (command.equals("user logout")) return true;
        printText("invalid command");
        return false;
    }

    /*public void printException(Exception output) {

    }*/

    public void printText(String output) {
        System.out.println(output);
    }

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }

    /*public void showMenu() {

    }*/

    /*public void exitMenu() {

    }*/
}
