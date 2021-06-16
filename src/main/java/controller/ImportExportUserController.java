package controller;

import model.Cardable;
import model.Deck;
import model.User;

import javax.smartcardio.Card;
import java.util.List;

public class ImportExportUserController {
    private static ImportExportUserController instance = null;

    public static ImportExportUserController getInstance() {
        if (instance == null) instance = new ImportExportUserController();
        return instance;
    }
    public void exportNewUser(User user1){
        String username = user1.getUsername();
        String password = user1.getPassword();
        String nickname = user1.getNickname();

    }

    public void exportChangesPassword(String newPassword){

    }

    public void exportNewNickname(String newNickname){

    }

    public void exportNewHighScore(int newHighScore){

    }

    public void exportNewBalance(int newBalance){

    }

    public void exportAllDecks(List<Deck> allDecks){

    }

    public void exportAllCards(List<Cardable> allCards){

    }
}
