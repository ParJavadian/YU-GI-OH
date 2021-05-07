package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    List<Card> cardsInHand;
    List<Card> cardsInGraveyard;
    HashMap<MonsterCard,String> monstersHashmap;
    HashMap<Card,String> spellAndTrapHashmap;
    Card[] spellsAndTraps;
    String[] spellsAndTrapsCondition;
    MonsterCard[] monsters;
    String[] monstersCondition;
    Card fieldZone;
    private static final int[] playerGroundNumbers = {3,4,2,5,1};
    private static final int[] opponentGroundNumbers = {3,2,4,1,5};

    public Board() {
        this.cardsInHand = new ArrayList<>();
        this.cardsInGraveyard = new ArrayList<>();
        this.monstersHashmap = new HashMap<>();
        this.spellAndTrapHashmap = new HashMap<>();
        this.spellsAndTraps = new Card[5];
        this.spellsAndTrapsCondition = new String[5];
        this.monsters = new MonsterCard[5];
        this.monstersCondition = new String[5];
        this.fieldZone = null;
    }

    public HashMap<MonsterCard,String> getMonstersHashmap(){
        return this.monstersHashmap;
    }

    public HashMap<Card,String> getSpellAndTrapHashmap(){
        return this.spellAndTrapHashmap;
    }

    public List<Card> getCardsInHand() {
        return this.cardsInHand;
    }

    public Card getFieldZone() {
        if (this.fieldZone.equals(null)) {
            return null;
        } else return this.fieldZone;
    }

    public MonsterCard[] getMonsters() {
        return this.monsters;
    }

    public Card[] getSpellsAndTraps() {
        return this.spellsAndTraps;
    }

    public void removeCardFromHand(Card card) {
        this.cardsInHand.remove(card);
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    //TODO اینو چون به ترتیب آدمیزادی پیش میرفت فکر کردم به دردمون نمیخوره و کامنتش کردم حالا اگه لازمه جایی آنکامنتش کن
    /*public void putMonster(MonsterCard monsterCard, String condition) {
        for (int i = 0; i < 5; i++) {
            if (!this.monsters[i].equals(null)) {
                this.monsters[i] = monsterCard;
                this.monstersCondition[i] = condition;
            }
        }
    }*/

    public void putMonster(MonsterCard monsterCard,String condition){
        for(int i=0;i<5;i++){
            if(this.getMonsterByNumber(playerGroundNumbers[i]) == null){
                this.monsters[playerGroundNumbers[i]] =monsterCard;
                this.monstersCondition[playerGroundNumbers[i]] =condition;
            }
        }
    }

    public void removeMonster(MonsterCard monsterCard) {
        for (int i = 0; i < 5; i++) {
            if (this.monsters[i].equals(monsterCard)) {
                this.monsters[i] = null;
                this.monstersCondition[i] = null;
            }
        }
    }

    public void putSpellOrTrap(Card card, String condition) {
        for (int i = 0; i < 5; i++) {
            if (!this.spellsAndTraps[i].equals(null)) {
                this.spellsAndTraps[i] = card;
                this.spellsAndTrapsCondition[i] = condition;
            }
        }
    }

    public void removeSpellOrTrap(Card card) {
        for (int i = 0; i < 5; i++) {
            if (this.spellsAndTraps[i].equals(card)) {
                this.spellsAndTraps[i] = null;
                this.spellsAndTrapsCondition[i] = null;
            }
        }
    }

    public MonsterCard getMonsterByNumber(int number) {
        return this.monsters[number];
    }

    public Card getSpellAndTrapByNumber(int number) {
        return this.spellsAndTraps[number];
    }

    public Card getFieldZone(int number) {
        return this.fieldZone;
    }

    public String getMonsterConditionByNumber(int number) {
        return this.monstersCondition[number];
    }

    public String getSpellAndTrapConditionByNumber(int number) {
        return this.spellsAndTrapsCondition[number];
    }

    public List<Card> getCardsInGraveyard() {
        return this.cardsInGraveyard;
    }

    public Card getCardInHandByNumber(int number) {
        return this.cardsInHand.get(number);
    }

    public void putInFieldZone(Card card) {
        this.fieldZone = card;
    }

    public void removeFromFieldZone() {
        this.fieldZone = null;
    }

    public void putInGraveYard(Card card) {
        this.cardsInGraveyard.add(card);
    }

    public void removeFromGraveYard(Card card) {
        this.cardsInGraveyard.remove(card);
    }

    public boolean existsOnBoard(Card card) {
        for (int i = 0; i < 5; i++) {
            if (this.monsters[i].equals(card)) return true;
            if (this.spellsAndTraps[i].equals(card)) return true;
        }
        return false;
    }

    public boolean isInHand(Card card){
        for(Card eachCard : this.cardsInHand){
            if(eachCard.equals(card)) return true;
        }
        return false;
    }

    /*public String toStringForPlayer() {
        String toReturn = "--------------------------\n" +
                "\n";
        if (fieldZone.equals(null))
            toReturn += "E";
        else toReturn += "O";
        toReturn += "                     " + this.cardsInGraveyard.size() + "\n";
        for (int index = 0; index < 5; index++) {
            if (this.monsters[index].equals(null)) {
                toReturn += "   E";
            } else {
                toReturn += "   " + this.monstersCondition[index];
            }

        }
        return toReturn;
    }

    public String toStringForRival(){
        String toReturn = "";
        for(int i=0;i<cardsInHand.size()-1;i++){
            toReturn += "c\t";
        }
        toReturn+="c\n";
        toReturn+=this.
    }*/

    public boolean isFullMonsterZone() {
        for (int i = 0; i < 5; i++) {
            if (this.monsters[i] == null) return false;
        }
        return true;
    }


}
