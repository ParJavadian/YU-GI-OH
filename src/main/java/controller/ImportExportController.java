package controller;

import model.Cardable;
import model.User;

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

    public void importCard(Cardable card){

    }

    public void exportCard(Cardable card){

    }

}
