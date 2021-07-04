/*
package controller;

import javafx.application.Application;
import model.Deck;
import model.User;
import view.DeckView;
import view.ShowAllDecksGraphic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShowAllDecksController{
    public static User user;
    private static ShowAllDecksController instance = null;

    public static void setUser(User user) {
        ShowAllDecksController.user = user;
    }

    public static ShowAllDecksController getInstance() {
        if (instance == null) instance = new ShowAllDecksController();
        return instance;
    }

    public String showAllDecks() {
        StringBuilder toPrint = new StringBuilder("Decks:\nActive deck:\n");
            List<Deck> allDecks = new ArrayList<>(user.getAllDecks());
            Deck activeDeck = null;
            if (user.getActiveDeck() != null) {
                for (Deck deck : allDecks) {
                    if (user.getActiveDeck().getDeckName().equals(deck.getDeckName())) {
                        toPrint.append(deck.toString());
                        if (deck.isValid()) toPrint.append(", valid\n");
                        else toPrint.append(", invalid\n");
                        activeDeck = deck;
                    }
                }
            }

            toPrint.append("Other decks:\n");
            allDecks.remove(activeDeck);
            Comparator<Deck> deckComparator = Comparator.comparing(Deck::getDeckName);
            allDecks.sort(deckComparator);
            for (Deck deck : allDecks) {
                toPrint.append(deck.toString());
                if (allDecks.get(allDecks.size() - 1).equals(deck)) {
                    if (deck.isValid()) toPrint.append(", valid");
                    else toPrint.append(", invalid");
                } else {
                    if (deck.isValid()) toPrint.append(", valid\n");
                    else toPrint.append(", invalid\n");
                }
            }
            return String.valueOf(toPrint);
    }
}
*/
