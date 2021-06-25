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

    public List<Cardable> getAllCardsOfGame() {
        List<Cardable> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, TrapCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Comparator<Cardable> cardComparator = Comparator.comparing(Cardable::getName);
        allCards.sort(cardComparator);
        return allCards;
    }

    public Cardable getCardByName(String name) {
        List<Cardable> allCards = getAllCardsOfGame();
        for (Cardable card : allCards) {
            if (card.getNamePascalCase().equals(name))
                return card;
        }
        return null;
    }

    private DeckController(User user) {
        this.user = user;
    }

    public void createDeck(String name) throws Exception {
        if (this.user.getDeckByName(name) == null) {
            Deck deck = new Deck(name);
            this.user.addDeck(deck);
//            ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//            importExportUserController.exportAllDecksName(this.user.getAllDecks(),this.user);
            DeckView.getInstance(this.user).printText("deck created successfully!");
        } else {
            throw new RepetitiveDeckName(name);
        }
    }

    public Deck createRandomDeckForAI() {
        Deck deck = new Deck("DeckForAI");
        this.user.addDeck(deck);
//        System.out.println("12");
        List<Cardable> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Collections.addAll(allCards, TrapCard.values());
//        System.out.println(user.getDeckByName("DeckForAI")+"8");
        while (user.getDeckByName("DeckForAI").getMainSize() < 46) {
//            System.out.println("12");
            int randomNum = ThreadLocalRandom.current().nextInt(0, allCards.size());
//            int randomNum = getRandomNumber(allCards.size());
            try {
                addCardToDeckAI(allCards.get(randomNum), "DeckForAI", false, false);
            } catch (Exception exception) {
//                System.out.println(exception.getMessage());
            }
        }
        return deck;
    }

    private int getRandomNumber(int bound) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < bound; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.get(0);
    }

    public void deleteDeck(String name) throws Exception {
        if (this.user.getDeckByName(name) != null) {
            this.user.deleteDeck(name);
//            ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//            importExportUserController.exportAllDecksName(this.user.getAllDecks(),this.user);
            DeckView.getInstance(this.user).printText("deck deleted successfully");
        } else
            throw new DeckNotFound(name);
    }

    public void addCardToDeck(String cardName, String deckName, boolean isSide, boolean isAddedByCheating) throws Exception {
        Cardable card = this.user.getCardByName(cardName);
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
//                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                        importExportUserController.exportCardsInSideDeck(this.user,deckName);
                    } else {
                        deck.addCardToMainDeck(card);
//                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                        importExportUserController.exportCardsInMainDeck(this.user,deckName);
                    }
                    this.user.deleteCard(cardName);
                    DeckView.getInstance(this.user).printText("card added to deck successfully");
                }
            } else throw new DeckNotFound(deckName);
        } else throw new CardNotFoundInUser(cardName);
    }

    public void addCardToDeckAI(Cardable card, String deckName, boolean isSide, boolean isAddedByCheating) throws Exception {
        String cardName = card.getNamePascalCase();
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
//                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                        importExportUserController.exportCardsInSideDeck(this.user,deckName);
                } else {
                    deck.addCardToMainDeck(card);
//                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                        importExportUserController.exportCardsInMainDeck(this.user,deckName);
                }
                this.user.deleteCard(cardName);
//                DeckView.getInstance(this.user).printText("card added to deck successfully");
            }
        } else throw new DeckNotFound(deckName);
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
            Cardable card = DeckController.getInstance(user).getCardByName(cardName);
            if (card != null) {
                if (isSide) {
                    if (deck.cardExistsInDeck(card, true)) {
                        deck.removeCardFromSideDeck(card);
                        this.user.addCardToUsersAllCards(card);
//                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                        importExportUserController.exportCardsInSideDeck(this.user,deckName);
//                        importExportUserController.exportAllCards(this.user);
                        DeckView.getInstance(user).printText("card removed form deck successfully");
                    } else throw new CardNotFoundInDeck(cardName, "side");
                } else {
                    if (deck.cardExistsInDeck(card, false)) {
                        deck.removeCardFromMainDeck(card);
                        this.user.addCardToUsersAllCards(card);
//                        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                        importExportUserController.exportCardsInMainDeck(this.user,deckName);
//                        importExportUserController.exportAllCards(this.user);
                        DeckView.getInstance(user).printText("card removed form deck successfully");
                    } else throw new CardNotFoundInDeck(cardName, "main");
                }
            } else
                throw new CardNotFoundForController();
        } else
            throw new DeckNotFound(deckName);
    }

    public void showAllDecks() {
        StringBuilder toPrint = new StringBuilder("Decks:\nActive deck:\n");
        List<Deck> allDecks = new ArrayList<>(this.user.getAllDecks());
        Deck activeDeck = null;
        if (this.user.getActiveDeck() != null) {
            for (Deck deck : allDecks) {
                if (this.user.getActiveDeck().getDeckName().equals(deck.getDeckName())) {
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
        DeckView.getInstance(this.user).printText(toPrint.toString());
    }

    public void showDeck(String deckName, boolean isSide) throws Exception {
        if (this.user.getDeckByName(deckName) == null) throw new DeckNotFound(deckName);
        String toPrint = "Deck: " + deckName + "\n";
        if (isSide) toPrint += "Side deck:\nMonsters:\n";
        else toPrint += "Main deck:\nMonsters:\n";
        ArrayList<Cardable> monsterCards = new ArrayList<>();
        ArrayList<Cardable> spellAndTrapCards = new ArrayList<>();
        if (!isSide) {
            for (Cardable eachCard : this.user.getDeckByName(deckName).getMainDeck()) {
                if (eachCard instanceof MonsterCard) monsterCards.add(eachCard);
                else spellAndTrapCards.add(eachCard);
            }
        } else {
            for (Cardable eachCard : this.user.getDeckByName(deckName).getSideDeck()) {
                if (eachCard instanceof MonsterCard) monsterCards.add(eachCard);
                else spellAndTrapCards.add(eachCard);
            }
        }
        Comparator<Cardable> cardComparator = Comparator.comparing(Cardable::getNamePascalCase);
        monsterCards.sort(cardComparator);
        spellAndTrapCards.sort(cardComparator);
        for (Cardable eachCard : monsterCards) {
            toPrint += eachCard.getNamePascalCase() + ":" + eachCard.getDescription() + "\n";
        }
        toPrint += "Spell and Traps:";
        for (Cardable eachCard : spellAndTrapCards) {
            toPrint += "\n" + eachCard.getNamePascalCase() + ":" + eachCard.getDescription();
        }
        DeckView.getInstance(this.user).printText(toPrint);
//        return toPrint;
    }

    public void showAllCards() {
        String toPrint = "";
        List<Cardable> allCards = this.user.getAllCards();
        Comparator<Cardable> cardComparator = Comparator.comparing(Cardable::getNamePascalCase);
        allCards.sort(cardComparator);
        for (Cardable card : allCards) {
            toPrint += card.getNamePascalCase() + ":" + card.getDescription() + "\n";
        }
        DeckView.getInstance(this.user).printText(toPrint);
    }
}
