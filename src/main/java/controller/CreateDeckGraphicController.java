package controller;

import controller.exeption.*;
import javafx.stage.Stage;
import model.Deck;
import model.User;
import view.ChangePasswordViewGraphic;
import view.CreateDeckGraphic;
import view.DeckViewGraphic;
import view.ProfileViewForGraphic;

public class CreateDeckGraphicController {
    public static void createDeck(User user, String deckName) throws Exception{
        if (deckName.equals(""))
            throw new EmptyDeckNameBox();
        if (user.getDeckByName(deckName) != null)
            throw new RepetitiveDeckName(deckName);
        Deck deck = new Deck(deckName);
        user.addDeck(deck);
        CreateDeckGraphic.confirmCreatedDeck();
    }
}
