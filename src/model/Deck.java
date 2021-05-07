package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Deck {
    private String deckName;
    private HashMap<Integer, Card> mainDeck;
    private HashMap<Integer, Card> sideDeck;
    private int IDCounter;

    public Deck(String deckName) {
        this.deckName = deckName;
        this.mainDeck = new HashMap<>();
        this.sideDeck = new HashMap<>();
        this.IDCounter = 0;
    }

    public String getDeckName() {
        return this.deckName;
    }

    public HashMap<Integer, Card> getMainDeck() {
        return this.mainDeck;
    }

    public HashMap<Integer, Card> getSideDeck() {
        return this.sideDeck;
    }

    public void addCardToMainDeck(Card card) {
        IDCounter++;
        this.mainDeck.put(IDCounter, card);
    }

    public void addCardToSideDeck(Card card) {
        IDCounter++;
        this.sideDeck.put(IDCounter, card);
    }

    public void removeCardFromMainDeck(Card card) {
        for (int i = 1; i < IDCounter; i++) {
            if (this.mainDeck.get(i).equals(card)) {
                this.mainDeck.remove(i, card);
                return;
            }
        }
    }

    public void removeCardFromSideDeck(Card card) {
        for (int i = 1; i < IDCounter; i++) {
            if (this.sideDeck.get(i).equals(card)) {
                this.sideDeck.remove(i, card);
                return;
            }
        }
    }

    public boolean isValid() {
        return ((this.mainDeck.size() >= 40) &&
                (this.mainDeck.size() <= 60) &&
                (this.sideDeck.size() <= 15));
    }

    public int getSideSize() {
        return this.sideDeck.size();
    }

    public int getMainSize() {
        return this.mainDeck.size();
    }

    public int getTotalSize() {
        return this.getMainSize() + this.getSideSize();
    }

    public int numberOfWantedCard(Card wantedCard) {
        int number = 0;
        for (Card eachCard : this.sideDeck.values()) {
            if (eachCard.equals(wantedCard))
                number++;
        }
        for (Card eachCard : this.mainDeck.values()) {
            if (eachCard.equals(wantedCard))
                number++;
        }
        return number;
    }

    public String toStringForMainDeck() {
        String toBeReturned = "Deck: " + this.deckName + "\nMain deck:\nMonsters:\n";
        ArrayList<String> monsters = new ArrayList<>();
        ArrayList<String> spellAndTraps = new ArrayList<>();
        for (Card card : this.mainDeck.values()) {
            if (card instanceof MonsterCard)
                monsters.add(card.getNamePascalCase() + ": " + card.getDescription());
            else if ((card instanceof SpellCard) || (card instanceof TrapCard))
                spellAndTraps.add(card.getNamePascalCase() + ": " + card.getDescription());
        }
        Collections.sort(monsters);
        Collections.sort(spellAndTraps);
        for (String eachMonster : monsters) {
            toBeReturned += eachMonster + "\n";
        }
        toBeReturned += "Spell and Traps:\n";
        for (String eachSpellAndTrap : spellAndTraps) {
            toBeReturned += eachSpellAndTrap + "\n";
        }
        return toBeReturned;
    }

    public String toStringForSideDeck() {
        String toBeReturned = "Deck: " + this.deckName + "\nSide deck:\nMonsters:\n";
        ArrayList<String> monsters = new ArrayList<>();
        ArrayList<String> spellAndTraps = new ArrayList<>();
        for (Card card : this.sideDeck.values()) {
            if (card instanceof MonsterCard)
                monsters.add(card.getNamePascalCase() + ": " + card.getDescription());
            else if ((card instanceof SpellCard) || (card instanceof TrapCard))
                spellAndTraps.add(card.getNamePascalCase() + ": " + card.getDescription());
        }
        Collections.sort(monsters);
        Collections.sort(spellAndTraps);
        for (String eachMonster : monsters) {
            toBeReturned += eachMonster + "\n";
        }
        toBeReturned += "Spell and Traps:\n";
        for (String eachSpellAndTrap : spellAndTraps) {
            toBeReturned += eachSpellAndTrap + "\n";
        }
        return toBeReturned;
    }

    @Override
    public String toString() {
        return this.deckName + ": main deck " + this.mainDeck.size() + ", side deck" + this.sideDeck.size() + ", " + this.isValid();
    }

}
