package controller;

import model.Card;
import model.User;

import java.util.regex.Matcher;

public class ImportExportController {
    private static ImportExportController instance = null;
    private User user;

    public static ImportExportController getInstance(User user) {
        if (instance == null) instance = new ImportExportController(user);
        else if(!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ImportExportController(User user){
    this.user = user;
    }


    public void importCard(Card card){

    }

    public void exportCard(Card card){

    }

}
