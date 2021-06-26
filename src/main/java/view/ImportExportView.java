package view;

import controller.ImportExportCardController;
import model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportExportView {
    private static ImportExportView instance = null;
    private User user;

    public static ImportExportView getInstance(User user) {
        if (instance == null) instance = new ImportExportView(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ImportExportView(User user) {
        this.user = user;
    }

    public void getCommandForImportExport() {
        String command;
        while (ScannerClassForView.getScanner().hasNext()) {
            command = ScannerClassForView.getScanner().nextLine();
            if (processCommand(command)) break;
        }
    }

    private boolean processCommand(String command) {
        ImportExportCardController importExportController = ImportExportCardController.getInstance(this.user);
        Matcher matcher = getCommandMatcher(command, "import card ([\\w ]+)");
        if (matcher.matches()) {
            importExportController.importCard(matcher.group(1));
            return false;
        }
        matcher = getCommandMatcher(command, "export card ([\\w ]+)");
        if (matcher.matches()) {
            importExportController.exportCard(this.user.getCardByName(matcher.group(1)));
            return false;
        }
        matcher = getCommandMatcher(command, "menu show-current");
        if (matcher.matches()) {
            printText("Import-Export Menu");
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

    private Matcher getCommandMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }

    public void printText(String output) {
        System.out.println(output);
    }

}
