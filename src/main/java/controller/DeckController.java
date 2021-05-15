package controller;

import controller.exeption.*;
import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeckController {
    private static DeckController instance = null;
    private User user;

    public static DeckController getInstance(User user) {
        if (instance == null) instance = new DeckController(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private DeckController(User user) {
        this.user = user;
    }

    public void createDeck(String name) throws Exception {
        if (this.user.getDeckByName(name) == null) {
            Deck deck = new Deck(name);
            this.user.addDeck(deck);
            DeckView.getInstance(this.user).printText("deck created successfully!");
        } else {
            DeckView.getInstance(this.user).printText("deck with name " + name + " already exists");
            throw new RepetitiveDeckName(name);
        }
    }

    public Deck createRandomDeckForAI(){
        Deck deck = new Deck("DeckForAI");
        List<Card> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Collections.addAll(allCards, TrapCard.values());

        while (user.getDeckByName("DeckForAI").getMainSize() < 46){
            int randomNum = ThreadLocalRandom.current().nextInt(0, allCards.size());
            try {
                addCardToDeck(allCards.get(randomNum).getName(),"DeckForAi",false,false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return deck;
    }

    public void deleteDeck(String name) throws Exception {
        if (this.user.getDeckByName(name) != null) {
            this.user.deleteDeck(name);
            DeckView.getInstance(this.user).printText("deck deleted successfully");
        } else
            throw new DeckNotFound(name);
    }

    public void addCardToDeck(String cardName, String deckName, boolean isSide, boolean isAddedByCheating) throws Exception {
        Card card = this.user.getCardByName(cardName);
        if (card != null) {
            Deck deck = this.user.getDeckByName(deckName);
            if (deck != null) {
                if (!isAddedByCheating) {
                    if (isSide && deck.getSideSize() >= 15)
                        throw new FullSideDeck();
                    else if (!(isSide) && (deck.getMainSize() >= 60))
                        throw new FullMainDeck();
                }
                if ((card instanceof MonsterCard ||
                        (card instanceof SpellCard && ((SpellCard) card).getStatus().equals(Status.UNLIMITED))
                        || (card instanceof TrapCard && ((TrapCard) card).getStatus().equals(Status.UNLIMITED)))
                        && deck.numberOfWantedCard(card) == 3)
                    throw new ThreeSameCards(cardName, deckName);
                else if (((card instanceof SpellCard && ((SpellCard) card).getStatus().equals(Status.LIMITED))
                        || (card instanceof TrapCard && ((TrapCard) card).getStatus().equals(Status.LIMITED)))
                        && deck.numberOfWantedCard(card) == 1)
                    throw new OneCardForLimited(cardName, deckName);
                else {
                    if (isSide) {
                        deck.addCardToSideDeck(card);
                    } else {
                        deck.addCardToMainDeck(card);
                    }
                    this.user.deleteCard(cardName);
                    DeckView.getInstance(this.user).printText("card added to deck successfully");
                }
            } else throw new DeckNotFound(deckName);
        } else throw new CardNotFoundInUser(cardName);
    }

    public void activateDeck(String name) throws Exception {
        if (this.user.getDeckByName(name) != null) {
            this.user.setActiveDeck(this.user.getDeckByName(name));
            DeckView.getInstance(this.user).printText("deck activated successfully");
        } else
            throw new DeckNotFound(name);
    }

    public void removeCardFromDeck(String cardName, String deckName, boolean isSide) throws Exception {
        Deck deck = user.getDeckByName(deckName);
        if (deck != null) {
            Card card = user.getCardByName(cardName);
            if (card != null) {
                if (isSide) {
                    if (deck.cardExistsInDeck(card, true)) {
                        deck.removeCardFromSideDeck(card);
                        this.user.addCardToUsersAllCards(card);
                        DeckView.getInstance(user).printText("card removed form deck successfully");
                    } else throw new CardNotFoundInDeck(cardName, "side");
                } else {
                    if (deck.cardExistsInDeck(card, false)) {
                        deck.removeCardFromMainDeck(card);
                        this.user.addCardToUsersAllCards(card);
                        DeckView.getInstance(user).printText("card removed form deck successfully");
                    } else throw new CardNotFoundInDeck(cardName, "main");
                }
            } else
                throw new CardNotFoundForController();
        } else
            throw new DeckNotFound(deckName);
    }

    public void showAllDecks() {
        String toPrint = "Decks:\nActive deck:\n";
        List<Deck> allDecks = this.user.getAllDecks();
        Deck activeDeck = null;
        for (Deck deck : allDecks) {
            if (deck.equals(this.user.getActiveDeck())) {
                toPrint += deck.toString();
                if (deck.isValid()) toPrint += ", valid\n";
                else toPrint += ", invalid\n";
                activeDeck = deck;
            }
        }

        allDecks.remove(activeDeck);
        Comparator<Deck> deckComparator = Comparator.comparing(Deck::getDeckName);
        allDecks.sort(deckComparator);
        for (Deck deck : allDecks) {
            toPrint += deck.toString();
            if (allDecks.get(allDecks.size() - 1).equals(deck)) {
                if (deck.isValid()) toPrint += ", valid";
                else toPrint += ", invalid";
            } else {
                if (deck.isValid()) toPrint += ", valid\n";
                else toPrint += ", invalid\n";
            }
        }
        DeckView.getInstance(this.user).printText(toPrint);
    }

    public void showDeck(String deckName, boolean isSide) throws Exception {
        if (this.user.getDeckByName(deckName) == null) throw new DeckNotFound(deckName);
        String toPrint = "Deck: " + deckName + "\n";
        if (isSide) toPrint += "Side deck:\nMonsters:\n";
        else toPrint += "Main deck:\nMonsters:\n";
        ArrayList<Card> monsterCards = new ArrayList<>();
        ArrayList<Card> spellAndTrapCards = new ArrayList<>();
        if (!isSide) {
            for (Card eachCard : this.user.getDeckByName(deckName).getMainDeck()) {
                if (eachCard instanceof MonsterCard) monsterCards.add(eachCard);
                else spellAndTrapCards.add(eachCard);
            }
        } else {
            for (Card eachCard : this.user.getDeckByName(deckName).getSideDeck()) {
                if (eachCard instanceof MonsterCard) monsterCards.add(eachCard);
                else spellAndTrapCards.add(eachCard);
            }
        }
        Comparator<Card> cardComparator = Comparator.comparing(Card::getNamePascalCase);
        monsterCards.sort(cardComparator);
        spellAndTrapCards.sort(cardComparator);
        for (Card eachCard : monsterCards) {
            toPrint += eachCard.getNamePascalCase() + ":" + eachCard.getDescription() + "\n";
        }
        toPrint += "Spell and Traps:";
        for (Card eachCard : spellAndTrapCards) {
            toPrint += "\n" + eachCard.getNamePascalCase() + ":" + eachCard.getDescription();
        }
        DeckView.getInstance(this.user).printText(toPrint);
//        return toPrint;
    }

    public void showAllCards() {
        String toPrint = "";
        List<Card> allCards = this.user.getAllCards();
        Comparator<Card> cardComparator = Comparator.comparing(Card::getNamePascalCase);
        allCards.sort(cardComparator);
        for (Card card : allCards) {
            toPrint += card.getNamePascalCase() + ":" + card.getDescription() + "\n";
        }
        DeckView.getInstance(this.user).printText(toPrint);
    }
}
