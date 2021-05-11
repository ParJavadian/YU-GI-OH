package view;

import controller.DuelController;
import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuelView {
    private DuelController duelController;

    static public void printText(String output) {
        System.out.println(output);
    }

    public static String scan() {
        return ScannerClassForView.getScanner().nextLine();
    }

    public DuelView(User user, User rival, int roundNumber) {
        duelController = new DuelController(user, rival, roundNumber);
    }

    public void getCommandForDuel() {
        String command;
        while (true) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command) ) break;
            duelController.manageEndGame();
            if(duelController.getShouldEndGameForView()) break;
        }
    }

    private boolean processCommand(String command) {
        if (command.equals("select -d")) {
            try {
                duelController.unselectCardFromCommand();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        Matcher matcher = getCommandMatcher(command, "select ([\\w -]+)");
        if (matcher.matches()) {
            Matcher isOpponent = getCommandMatcher(matcher.group(1), "(--opponent|-o)");
            Matcher isMonster = getCommandMatcher(matcher.group(1), "(--monster|-m)");
            Matcher isSpellAndTrap = getCommandMatcher(matcher.group(1), "(--spell|-s)");
            Matcher isFieldZone = getCommandMatcher(matcher.group(1), "(--field|-f)");
            Matcher isHand = getCommandMatcher(matcher.group(1), "(--hand|-h)");
            Matcher numberMatcher = getCommandMatcher(matcher.group(1), "[0-9]+");
            if (isMonster.find() && isOpponent.find() && numberMatcher.find()) {
                try {
                    duelController.selectCardOpponentMonsterZone(Integer.parseInt(numberMatcher.group()));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else if (isMonster.find() && numberMatcher.find()) {
                try {
                    duelController.selectCardPlayerMonsterZone(Integer.parseInt(numberMatcher.group()));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else if (isSpellAndTrap.find() && isOpponent.find() && numberMatcher.find()) {
                try {
                    duelController.selectCardOpponentTrapAndSpellZone(Integer.parseInt(numberMatcher.group()));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else if (isSpellAndTrap.find() && numberMatcher.find()) {
                try {
                    duelController.selectCardPlayerTrapAndSpellZone(Integer.parseInt(numberMatcher.group()));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else if (isFieldZone.find() && isOpponent.find()) {
                try {
                    duelController.selectCardOpponentFieldZone();
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else if (isFieldZone.find()) {
                try {
                    duelController.selectCardPlayerFieldZone();
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else if (isHand.find() && numberMatcher.find()) {
                try {
                    duelController.selectCardPlayerHand(Integer.parseInt(numberMatcher.group()));
                } catch (Exception exception) {
                    printText(exception.getMessage());
                }
            } else printText("invalid selection");
            return false;
        }

        if (command.equals("next phase")) {
            duelController.goNextPhase();
            return false;
        }
        if (command.equals("summon")) {
            try {
                duelController.summonMonster();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        matcher = getCommandMatcher(command, "set (--position|-p) (DO|OO)");
        if (matcher.matches()) {
            try {
                duelController.changePosition(matcher.group(2));
            } catch (Exception exception) {
                printText(exception.getMessage());

            }
            return false;
        }
        if (command.equals("set")) {
            try {
                duelController.preSet();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if (command.equals("flip-summon")) {
            try {
                duelController.flipSummon();
            } catch (Exception exception) {
                printText(exception.getMessage());

            }
            return false;
        }
        matcher = getCommandMatcher(command, "attack ([1-5])");
        if (matcher.matches()) {
            try {
                duelController.attackMonster(Integer.parseInt(matcher.group(1)));
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if (command.equals("attack direct")) {
            try {
                duelController.directAttack();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if (command.equals("activate effect")) {
            try {
                duelController.activateSpell();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        //TODO aya bayad un faal sazie spell tu nobate harif ro tu view bezanim?
        if (command.equals("show graveyard")) {
            try {
                duelController.showGraveyard();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        matcher = getCommandMatcher(command, "card show (--selected|-s)");
        if (matcher.matches()) {
            try {
                duelController.showCard();
            } catch (Exception exception) {
                printText(exception.getMessage());
            }
            return false;
        }
        if (command.equals("surrender")) {
            duelController.surrender();
            return true;
        }
        if (command.startsWith("menu enter ")) {
            printText("menu navigation is not possible");
            return false;
        }
        printText("invalid command");
        return false;
    }

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }
}