package view;

import controller.DeckController;
import controller.ImportExportUserController;
import model.Deck;
import model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckView {
    private static DeckView instance = null;
    private User user;

    public static DeckView getInstance(User user) {
        if (instance == null) instance = new DeckView(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private DeckView(User user) {
        this.user = user;
    }

    public void getCommandForDeck() {
        String command;
        while (ScannerClassForView.getScanner().hasNext()) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
    }

    private boolean processCommand(String command) {
        Matcher matcher;
        DeckController deckController = DeckController.getInstance(this.user);
        matcher = getCommandMatcher(command, "deck create ([\\w ]+)");
        if (matcher.matches()) {
            try {
                deckController.createDeck(matcher.group(1));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        matcher = getCommandMatcher(command, "deck delete ([\\w ]+)");
        if (matcher.matches()) {
            try {
                deckController.deleteDeck(matcher.group(1));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        matcher = getCommandMatcher(command, "deck set-activate ([\\w ]+)");
        if (matcher.matches()) {
            try {
                deckController.activateDeck(matcher.group(1));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if (command.startsWith("deck add-card ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command + " ", "(--card|-c) ([\\w ]+) ");
            Matcher matcher2 = getCommandMatcher(command + " ", "(--deck|-d) ([\\w ]+) ");
            if (matcher1.find() && matcher2.find()) {
                Matcher matcher3 = getCommandMatcher(command, " (--side|-s)");
                try {
                    deckController.addCardToDeck(matcher1.group(2), matcher2.group(2), matcher3.find(),false);
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
        }

        if (command.startsWith("deck rm-card ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command + " ", "(--card|-c) ([\\w ]+) ");
            Matcher matcher2 = getCommandMatcher(command + " ", "(--deck|-d) ([\\w ]+) ");
            if (matcher1.find() && matcher2.find()) {
                Matcher matcher3 = getCommandMatcher(command, " (--side|-s)");
                try {
                    deckController.removeCardFromDeck(matcher1.group(2), matcher2.group(2), matcher3.find());
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
        }

        //TODO in tabe ro ezafe kardam
        if (command.startsWith("taehc deck add-card taehc ") && command.matches("[\\w -]+")){
            Matcher matcher1 = getCommandMatcher(command + " ", "(--card|-c) ([\\w ]+) ");
            Matcher matcher2 = getCommandMatcher(command + " ", "(--deck|-d) ([\\w ]+) ");
            if (matcher1.find() && matcher2.find()) {
                Matcher matcher3 = getCommandMatcher(command, " (--side|-s)");
                try {
                    deckController.addCardToDeck(matcher1.group(2), matcher2.group(2), matcher3.find(),true);
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
        }

        matcher = getCommandMatcher(command, "deck show --all");
        if (matcher.matches()) {
            deckController.showAllDecks();
            return false;
        }
        if (command.startsWith("deck show ") && command.matches("[\\w -]+")) {
            Matcher matcher1 = getCommandMatcher(command + " ", "(--deck-name|-dn) ([\\w ]+) ");
            if (matcher1.find()) {
                Matcher matcher2 = getCommandMatcher(command, " (--side|-s)");
                try {
                    deckController.showDeck(matcher1.group(2), matcher2.find());
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            }
        }
        matcher = getCommandMatcher(command, "deck show (--cards|-c)");
        if (matcher.matches()) {
            deckController.showAllCards();
            return false;
        }
        matcher = getCommandMatcher(command, "menu show-current");
        if (matcher.matches()) {
            printText("Deck Menu");
            return false;
        }
        if (command.startsWith("menu enter ")) {
            printText("menu navigation is not possible");
            return false;
        }
        matcher = getCommandMatcher(command, "menu exit");
        if (matcher.matches()) {
            return true;
        }

        printText("invalid command");
        return false;
    }

    public void printText(String output) {
        System.out.println(output);
    }

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }
}
