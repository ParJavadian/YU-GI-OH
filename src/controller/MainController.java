package controller;

import controller.exeption.*;
import model.User;
import view.*;

public class MainController {
    private User user;
    private static MainController instance = null;

    public static MainController getInstance(User user) {
        if (instance == null) instance = new MainController(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private MainController(User user) {
        this.user = user;
    }

    public void goToMenu(String menu) {
        switch (menu) {
            case "Deck":
                DeckView.getInstance(this.user).getCommandForDeck();
                break;
            case "ScoreBoard":
                ScoreBoardView.getInstance(this.user).getCommandForScoreboard();
                break;
            case "Profile":
                ProfileView.getInstance(this.user).getCommandForProfile();
                break;
            case "Shop":
                ShopView.getInstance(this.user).getCommandForShop();
                break;
        }
    }


    public void newDuel(String username, int roundNumber) throws Exception {
        if(User.getUserByUsername(username)==null) throw new PlayerNotFound();
        if(this.user.getActiveDeck() == null) throw new NoActiveDeck(this.user.getUsername());
        if(User.getUserByUsername(username).getActiveDeck() == null) throw new NoActiveDeck(username);
        if(!this.user.getActiveDeck().isValid()) throw new InvalidDeck(this.user.getUsername());
        if(!User.getUserByUsername(username).getActiveDeck().isValid()) throw new InvalidDeck(username);
        if(roundNumber!=1 && roundNumber!=3) throw new UnsupportedRoundNumber();
        DuelView duelView = new DuelView(user, User.getUserByUsername(username), roundNumber);
        duelView.getCommandForDuel();
    }
    /*private void logOutUser(){

    }*/
}
