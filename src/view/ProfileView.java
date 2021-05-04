package view;

import controller.*;
import model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileView {
    private static ProfileView instance = null;
    private User user;

    public static ProfileView getInstance(User user) {
        if (instance == null) instance = new ProfileView(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ProfileView(User user) {
        this.user = user;
    }

    public void getCommandForProfile() {
        String command;
        while (true) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
    }

    private boolean processCommand(String command) {
        ProfileController profileController = ProfileController.getInstance(this.user);
        Matcher matcher = getCommandMatcher(command, "profile change (--nickname|-n) ([\\w]+)");
        if (matcher.matches()) {
            try {
                profileController.changeNickname(matcher.group(2));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if (command.startsWith("profile change ") && command.matches("[\\w ]+")) {
            Matcher matcher1 = getCommandMatcher(command, "(--password|-p)");
            Matcher matcher2 = getCommandMatcher(command, "(--current|-c) ([\\w ]+)");
            Matcher matcher3 = getCommandMatcher(command, "(--new|-n) ([\\w ]+)");
            if (matcher1.find() && matcher2.find()) {
                try {
                    profileController.changePassword(matcher2.group(2), matcher3.group(2));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
                return false;
            }
        }
        /*matcher = getCommandMatcher(command, "profile change --(password|p) --(current|c) ([\\w]+) --(new|n) ([\\w]+)");
        if (matcher.matches()) {
            ProfileController.getInstance(this.user).changePassword(matcher.group(3), matcher.group(5));
            return false;
        }*/
        if(command.startsWith("menu enter ")){
            printText("menu navigation is not possible");
            return false;
        }
        if (command.equals("menu exit")) return true;
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
}
