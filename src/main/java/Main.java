import controller.ImportExportUserController;

import view.LogInView;

public class Main {

    public static void main(String[] args) {
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.importAllUsers();
        importExportUserController.importAllCards();
        importExportUserController.importAllDecks();
        LogInView.getInstance().getCommandForLogin();
    }
}
/*
user login -u hamraz -p 123
menu enter Deck
deck set-activate one
menu exit
duel -n -ai -r 3
next phase
 */