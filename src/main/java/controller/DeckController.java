package controller;

import controller.exeption.*;
import javafx.scene.image.Image;
import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeckController {
    private static DeckController instance = null;
    private static User user;

    private static ArrayList<Image> allImages = new ArrayList<>();
    private static ArrayList<Card> allCards = new ArrayList<>();

    private DeckController(User user) {
        DeckController.user = user;
    }

    public static DeckController getInstance(User user) {
        if (instance == null) instance = new DeckController(user);
        else if (user!=null && (DeckController.user == null || !DeckController.user.getUsername().equals(user.getUsername())))
            DeckController.user = user;
        return instance;
    }

    public List<Card> getAllCardsOfGame() {
        List<Card> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, TrapCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Comparator<Card> cardComparator = Comparator.comparing(Card::getName);
        allCards.sort(cardComparator);
        return allCards;
    }

    public Card getCardByName(String name) {
        List<Card> allCards = getAllCardsOfGame();
        for (Card card : allCards) {
            if (card.getNamePascalCase().equals(name))
                return card;
        }
        return null;
    }

    public void createDeck(String name) throws Exception {
        if (DeckController.user.getDeckByName(name) == null) {
            Deck deck = new Deck(name);
            DeckController.user.addDeck(deck);
            ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
            importExportUserController.exportAllDecksName(DeckController.user.getAllDecks(), DeckController.user);
        } else {
            throw new RepetitiveDeckName(name);
        }
    }

    public Deck createRandomDeckForAI() {
        Deck deck = new Deck("DeckForAI");
        User.getUserByUsername("@AI@").addDeck(deck);
        List<Card> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Collections.addAll(allCards, TrapCard.values());
        while (user.getDeckByName("DeckForAI").getMainSize() < 46) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, allCards.size());
            try {
                addCardToDeckAI(allCards.get(randomNum), "DeckForAI", false, false);
            } catch (Exception ignored) {
            }
        }
        return deck;
    }

    public void deleteDeck(String name) throws Exception {
        if (DeckController.user.getDeckByName(name) != null) {
            DeckController.user.addDeckCardsToUserAllCards(name);
            DeckController.user.deleteDeck(name);
            ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
            importExportUserController.exportAllDecksName(DeckController.user.getAllDecks(), DeckController.user);
        } else
            throw new DeckNotFound(name);
    }

    public void addCardToDeck(String cardName, String deckName, boolean isSide, boolean isAddedByCheating) throws Exception {
        Card card = DeckController.user.getCardByName(cardName);
        if (card != null) {
            Deck deck = DeckController.user.getDeckByName(deckName);
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
                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                        importExportUserController.exportCardsInSideDeck(DeckController.user, deckName);
                    } else {
                        deck.addCardToMainDeck(card);
                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                        importExportUserController.exportCardsInMainDeck(DeckController.user, deckName);
                    }
                    DeckController.user.removeCard(card);
                    AddCardToDeckView.getInstance().printTextInformation("card added to deck successfully");
                }
            } else throw new DeckNotFound(deckName);
        } else throw new CardNotFoundInUser(cardName);
    }

    public void addCardToDeckAI(Card card, String deckName, boolean isSide, boolean isAddedByCheating) throws Exception {
        String cardName = card.getNamePascalCase();
        Deck deck = DeckController.user.getDeckByName(deckName);
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
                    ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                    importExportUserController.exportCardsInSideDeck(DeckController.user, deckName);
                } else {
                    deck.addCardToMainDeck(card);
                    ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                    importExportUserController.exportCardsInMainDeck(DeckController.user, deckName);
                }
                DeckController.user.deleteCard(cardName);
            }
        } else throw new DeckNotFound(deckName);
    }

    public void activateDeck(String name) throws Exception {
        if (DeckController.user.getDeckByName(name) != null) {
            DeckController.user.setActiveDeck(DeckController.user.getDeckByName(name));
        } else
            throw new DeckNotFound(name);
    }

    public void removeCardFromDeck(String cardName, String deckName, boolean isSide) throws Exception {
        Deck deck = user.getDeckByName(deckName);
        if (deck != null) {
            Card card = DeckController.getInstance(user).getCardByName(cardName);
            if (card != null) {
                if (isSide) {
                    if (deck.cardExistsInDeck(card, true)) {
                        deck.removeCardFromSideDeck(card);
                        DeckController.user.addCardToUsersAllCards(card);
                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                        importExportUserController.exportCardsInSideDeck(DeckController.user, deckName);
                        importExportUserController.exportAllCards(DeckController.user);
                        RemoveCardFromSideDeckView.getInstance().printTextInformation("card removed form deck successfully");
                    } else throw new CardNotFoundInDeck(cardName, "side");
                } else {
                    if (deck.cardExistsInDeck(card, false)) {
                        deck.removeCardFromMainDeck(card);
                        DeckController.user.addCardToUsersAllCards(card);
                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                        importExportUserController.exportCardsInMainDeck(DeckController.user, deckName);
                        importExportUserController.exportAllCards(DeckController.user);
                        RemoveCardFromDeckView.getInstance().printTextInformation("card removed form deck successfully");
                    } else throw new CardNotFoundInDeck(cardName, "main");
                }
            } else
                throw new CardNotFoundForController();
        } else
            throw new DeckNotFound(deckName);
    }

    public void showAllDecks() {
        StringBuilder toPrint = new StringBuilder("Decks:\nActive deck:\n");
        List<Deck> allDecks = new ArrayList<>(DeckController.user.getAllDecks());
        Deck activeDeck = null;
        if (DeckController.user.getActiveDeck() != null) {
            for (Deck deck : allDecks) {
                if (DeckController.user.getActiveDeck().getDeckName().equals(deck.getDeckName())) {
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
    }

    public void showDeck(String deckName, boolean isSide) throws Exception {
        if (DeckController.user.getDeckByName(deckName) == null) throw new DeckNotFound(deckName);
        StringBuilder toPrint = new StringBuilder("Deck: " + deckName + "\n");
        if (isSide) toPrint.append("Side deck:\nMonsters:\n");
        else toPrint.append("client.Main deck:\nMonsters:\n");
        ArrayList<Card> monsterCards = new ArrayList<>();
        ArrayList<Card> spellAndTrapCards = new ArrayList<>();
        if (!isSide) {
            for (Card eachCard : DeckController.user.getDeckByName(deckName).getMainDeck()) {
                if (eachCard instanceof MonsterCard) monsterCards.add(eachCard);
                else spellAndTrapCards.add(eachCard);
            }
        } else {
            for (Card eachCard : DeckController.user.getDeckByName(deckName).getSideDeck()) {
                if (eachCard instanceof MonsterCard) monsterCards.add(eachCard);
                else spellAndTrapCards.add(eachCard);
            }
        }
        Comparator<Card> cardComparator = Comparator.comparing(Card::getNamePascalCase);
        monsterCards.sort(cardComparator);
        spellAndTrapCards.sort(cardComparator);
        for (Card eachCard : monsterCards) {
            toPrint.append(eachCard.getNamePascalCase()).append(":").append(eachCard.getDescription()).append("\n");
        }
        toPrint.append("Spell and Traps:");
        for (Card eachCard : spellAndTrapCards) {
            toPrint.append("\n").append(eachCard.getNamePascalCase()).append(":").append(eachCard.getDescription());
        }
    }

    public void showAllCards() {
        StringBuilder toPrint = new StringBuilder();
        List<Card> allCards = DeckController.user.getAllCards();
        Comparator<Card> cardComparator = Comparator.comparing(Card::getNamePascalCase);
        allCards.sort(cardComparator);
        for (Card card : allCards) {
            toPrint.append(card.getNamePascalCase()).append(":").append(card.getDescription()).append("\n");
        }
    }

    public void setUsersCards() {
        allCards = (ArrayList<Card>) user.getAllCards();
    }

    public void setMainDeckCards(Deck deck) {
        allCards = (ArrayList<Card>) deck.getMainDeck();
    }

    public static void initImages() {
        allImages.clear();
        for (Card card : allCards) {
            allImages.add(card.getImage());
        }
    }

    public ArrayList<Image> getImages(int start) {
        ArrayList<Image> myImages = new ArrayList<>(4);
        for (int i = start; i < start + 4; i++) {
            if (i < allImages.size()) {
                myImages.add(allImages.get(i));
            }
        }
        return myImages;
    }

    public ArrayList<Card> getCards(int start) {
        ArrayList<Card> cards = new ArrayList<>(4);
        for (int i = start; i < start + 4; i++) {
            if (allCards.size() > i)
                cards.add(allCards.get(i));
        }
        return cards;
    }

    public void setSideDeckCards(Deck deck) {
        allCards = (ArrayList<Card>) deck.getSideDeck();
    }
}
