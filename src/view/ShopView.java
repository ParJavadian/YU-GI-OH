package view;

import controller.*;
import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ShopView {
    private static ShopView instance = null;
    private User user;

    public static ShopView getInstance(User user) {
        if (instance == null) instance = new ShopView(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    public ShopView(User user) {
        this.user = user;
    }

    public void getCommandForShop() {
        String command;
        while (true) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
    }

    private boolean processCommand(String command) {
        ShopController  shopController = ShopController.getInstance(this.user);
        Matcher matcher = getCommandMatcher(command, "shop buy ([\\w ]+)");
        if (matcher.matches()) {
            try {
                shopController.buyCard(matcher.group(1));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if(command.equals("shop show --all")){
            shopController.showAll();
            return false;
        }
        matcher = getCommandMatcher(command, "menu show-current");
        if (matcher.matches()) {
            printText("Shop Menu");
            return false;
        }
        if(command.startsWith("menu enter ")){
            printText("menu navigation is not possible");
            return false;
        }
        if (command.equals("menu exit")) return true;
        printText("invalid command");
        return false;
    }

    /*private void showMenu() {

    }

    public void exitMenu() {

    }

    public void printException(Exception output) {

    }*/

    public void printText(String output) {
        System.out.println(output);
    }

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }
}
