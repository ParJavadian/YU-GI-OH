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
        System.out.println("8");
        while (ScannerClassForView.getScanner().hasNext()) {
            System.out.println("4");
            command = ScannerClassForView.getScanner().nextLine();
            System.out.println("5");
            if (processCommand(command)) {
                System.out.println("6");
                break;
            }
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
            Matcher matcher1 = getCommandMatcher(command + " ", "(--username|-u) ([\\w ]+) ");
            Matcher matcher2 = getCommandMatcher(command + " ", "(--nickname|-n) ([\\w ]+) ");
            Matcher matcher3 = getCommandMatcher(command + " ", "(--password|-p) ([\\w ]+) ");
            if (matcher1.find() && matcher2.find() && matcher3.find()) {
                try {
                    logInController.createUser(matcher1.group(2), matcher3.group(2), matcher2.group(2));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
            System.out.println("1");
            return false;
        }
        if (command.startsWith("user login ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command + " ", "(--username|-u) ([\\w ]+) ");
            Matcher matcher2 = getCommandMatcher(command + " ", "(--password|-p) ([\\w ]+) ");
            if (matcher1.find() && matcher2.find()) {
                try {
                    logInController.loginUser(matcher1.group(2), matcher2.group(2));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
            System.out.println("2");
            return false;
        }
        if (command.equals("menu exit")) {
            System.out.println("3");
            return true;
        }
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
