package controller;

import model.Cardable;
import model.User;

public class ImportExportCardController {
    private static ImportExportCardController instance = null;
    private User user;

    public static ImportExportCardController getInstance(User user) {
        if (instance == null) instance = new ImportExportCardController(user);
        else if(!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ImportExportCardController(User user){
    this.user = user;
    }

    public void importCard(Cardable card){

    }

    public void exportCard(Cardable card){

    }

}
