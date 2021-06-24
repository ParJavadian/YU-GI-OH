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
        while (ScannerClassForView.getScanner().hasNext()) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
    }

    private boolean processCommand(String command) {
        ProfileController profileController = ProfileController.getInstance(this.user);
        Matcher matcher = getCommandMatcher(command + " ", "profile change (--nickname|-n) ([\\w ]+) ");
        if (matcher.matches()) {
            try {
                profileController.changeNickname(matcher.group(2));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }

        matcher = getCommandMatcher(command + " ", "profile change (--username|-u) ([\\w ]+) ");
        if (matcher.matches()) {
            try {
                profileController.changeUsername(matcher.group(2));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }

        if ((command.startsWith("profile change -p") && command.matches("[\\w -]+")) || (command.startsWith("profile change --password") && command.matches("[\\w -]+"))){
            Matcher matcher2 = getCommandMatcher(command + " ", "(--current|-c) ([\\w ]+) ");
            Matcher matcher3 = getCommandMatcher(command + " ", "(--new|-n) ([\\w ]+) ");

                if (matcher2.find()) {
                    if (matcher3.find()) {
                        try {
                            profileController.changePassword(matcher2.group(2).trim(), matcher3.group(2).trim());
                        } catch (Exception exception) {
                            printText(exception.getMessage());
                        }
                    }
                }
            return false;
        }
        if (command.startsWith("menu enter ")) {
            printText("menu navigation is not possible");
            return false;
        }
        matcher = getCommandMatcher(command, "menu show-current");
        if (matcher.matches()) {
            printText("Profile Menu");
            return false;
        }

        if (command.equals("menu exit")) return true;
        printText("invalid command");
        return false;
    }

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }

    public void printText(String output) {
        System.out.println(output);
    }
}
