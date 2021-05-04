package view;

import controller.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogInView {
    private static LogInView instance = null;

    public static LogInView getInstance() {
        if (instance == null) instance = new LogInView();
        return instance;
    }

    private LogInView() {
    }

    public void getCommandForLogin() {
        String command;
        while (true) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) System.exit(0);
        }
    }

    private boolean processCommand(String command) {
        LogInController logInController = LogInController.getInstance();
        if (command.startsWith("menu enter ")) {
            printText("please login first");
            return false;
        }
        if (command.equals("menu show-current")) {
            printText("Login Menu");
            return false;
        }
        if (command.startsWith("user create ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command, "(--username|-u) ([\\w ]+)");
            Matcher matcher2 = getCommandMatcher(command, "(--nickname|-n) ([\\w ]+)");
            Matcher matcher3 = getCommandMatcher(command, "(--password|-p) ([\\w ]+)");
            if (matcher1.find() && matcher2.find() && matcher3.find()) {
                try {
                    logInController.createUser(matcher1.group(2), matcher3.group(2), matcher2.group(2));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
            return false;
        }
        if (command.startsWith("user login ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command, "(--username|-u) ([\\w]+)");
            Matcher matcher2 = getCommandMatcher(command, "(--password|-p) ([\\w]+)");
            if (matcher1.find() && matcher2.find()) {
                try {
                    logInController.loginUser(matcher1.group(2), matcher2.group(2));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
            return false;
        }
        if (command.equals("menu exit")) return true;
        printText("invalid command");
        return false;
    }

    /*
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

    /*public void goToMainMenu(User user) {

    }*/
    /*
    public void exit() {
        System.exit(0);
    }

     */
}
