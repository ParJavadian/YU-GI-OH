package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Card> cardsInHand;
    List<Card> cardsInGraveyard;
    MonsterCard[] monsters;
    String[] monstersCondition;
    Card[] spellsAndTraps;
    String[] spellsAndTrapsCondition;
    Card fieldZone;
    private static final int[] playerGroundNumbers = {3,4,2,5,1};
    private static final int[] opponentGroundNumbers = {3,2,4,1,5};

    public Board() {
        this.cardsInHand = new ArrayList<>();
        this.cardsInGraveyard = new ArrayList<>();
        this.monsters = new MonsterCard[5];
        this.monstersCondition = new String[5];
        this.spellsAndTraps = new Card[5];
        this.spellsAndTrapsCondition = new String[5];
        this.fieldZone = null;
    }

    public void removeCardFromHand(Card card) {
        this.cardsInHand.remove(card);
    }

    public void addCardToHand(Card card) {
        this.cardsInHand.add(card);
    }

    public int putMonster(MonsterCard monsterCard,String condition){
        for(int i=0;i<5;i++){
            if(this.monsters[playerGroundNumbers[i]] == null){
                this.monsters[playerGroundNumbers[i]] =monsterCard;
                this.monstersCondition[playerGroundNumbers[i]] =condition;
                return i;
            }
        }
        return 0;
    }

    public void removeMonster(int number) {
        this.monsters[number] = null;
        this.monstersCondition[number] = null;
    }

    public void putSpellOrTrap(Card card, String condition) {
        for(int i=0;i<5;i++){
            if(this.spellsAndTraps[playerGroundNumbers[i]] == null){
                this.spellsAndTraps[playerGroundNumbers[i]] =card;
                this.spellsAndTrapsCondition[playerGroundNumbers[i]] =condition;
                return;
            }
        }
    }

    public void removeSpellOrTrap(int number) {
        this.spellsAndTraps[number] = null;
        this.spellsAndTrapsCondition[number] = null;
    }

    public boolean existsOnBoard(Card card) {
        for (int i = 0; i < 5; i++) {
            if (this.monsters[i].equals(card)) return true;
            if (this.spellsAndTraps[i].equals(card)) return true;
        }
        return false;
    }

    public void changeMonsterPosition(int index,String target){
        this.monstersCondition[index] = target;
    }

    public boolean isFullSpellAndTrapZone(){
        for (int i = 0; i < 5; i++){
            if (this.spellsAndTraps[i] == null) return false;
        }
        return true;
    }

    public boolean isFullMonsterZone() {
        for (int i = 0; i < 5; i++) {
            if (this.monsters[i] == null) return false;
        }
        return true;
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

    public List<Card> getCardsInHand() {
        return this.cardsInHand;
    }

    public Card getFieldZone() {
        if (this.fieldZone==null) {
            return null;
        } else return this.fieldZone;
    }

    public MonsterCard[] getMonsters() {
        return this.monsters;
    }

    public Card[] getSpellsAndTraps() {
        return this.spellsAndTraps;
    }

    public MonsterCard getMonsterByNumber(int number) {
        return this.monsters[number];
    }

    public Card getSpellAndTrapByNumber(int number) {
        return this.spellsAndTraps[number];
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

}