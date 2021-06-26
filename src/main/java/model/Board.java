package model;

import controller.DuelController;

import java.util.ArrayList;
import java.util.List;

public class Board {
    /*private static final int[] playerGroundNumbers = {3,4,2,5,1};
    private static final int[] opponentGroundNumbers = {3,2,4,1,5};*/
    private static final int[] playerGroundNumbers = {2,3,1,4,0};
    private static final int[] opponentGroundNumbers = {2,1,3,0,4};
    private List<Cardable> cardsInHand;
    private List<Cardable> cardsInGraveyard;
    private MonsterCard[] monsters;
    private String[] monstersCondition;
    private Cardable[] spellsAndTraps;
    private String[] spellsAndTrapsCondition;
    private Cardable fieldZone;

    public Board() {
        this.cardsInHand = new ArrayList<>();
        this.cardsInGraveyard = new ArrayList<>();
        this.monsters = new MonsterCard[5];
        this.monstersCondition = new String[5];
        this.spellsAndTraps = new Cardable[5];
        this.spellsAndTrapsCondition = new String[5];
        this.fieldZone = null;
    }

    public List<Cardable> getCardsInHand() {
        return this.cardsInHand;
    }

    public Cardable getFieldZone() {
        if (this.fieldZone==null) {
            return null;
        } else return this.fieldZone;
    }

    public MonsterCard[] getMonsters() {
        return this.monsters;
    }

    public Cardable[] getSpellsAndTraps() {
        return this.spellsAndTraps;
    }

    public MonsterCard getMonsterByNumber(int number) {
        return this.monsters[number];
    }

    public Cardable getSpellAndTrapByNumber(int number) {
        return this.spellsAndTraps[number];
    }

    public String getMonsterConditionByNumber(int number) {
        return this.monstersCondition[number];
    }

    public String getSpellAndTrapConditionByNumber(int number) {
        return this.spellsAndTrapsCondition[number];
    }

    public List<Cardable> getCardsInGraveyard() {
        return this.cardsInGraveyard;
    }

    public Cardable getCardInHandByNumber(int number) {
        return this.cardsInHand.get(number);
    }

    public void removeCardFromHand(Cardable card) {
        this.cardsInHand.remove(card);
    }

    public void addCardToHand(Cardable card) {
        this.cardsInHand.add(card);
    }

    public int putMonster(MonsterCard monsterCard,String condition){
        for(int i=0;i<5;i++){
            if(this.monsters[playerGroundNumbers[i]] == null){
                this.monsters[playerGroundNumbers[i]] =monsterCard;
                this.monstersCondition[playerGroundNumbers[i]] =condition;
                return playerGroundNumbers[i];
            }
        }
        return 0;
    }

    public void removeMonster(int number, DuelController duelController,User owner) {
        this.monsters[number] = null;
        this.monstersCondition[number] = null;
        if(owner.getUsername().equals(duelController.getPlayer().getUsername())){
            duelController.setMonsterAttackPlayer(number,null);
            duelController.setMonsterDefencePlayer(number,null);
            duelController.getActionsOnThisCardPlayer(number).clear();
        }
        else {
            duelController.setMonsterAttackRival(number,null);
            duelController.setMonsterDefenceRival(number,null);
            duelController.getActionsOnThisCardRival(number).clear();
        }
    }

    public int putSpellOrTrap(Cardable card, String condition) {
        for(int i=0;i<5;i++){
            if(this.spellsAndTraps[playerGroundNumbers[i]] == null){
                this.spellsAndTraps[playerGroundNumbers[i]] =card;
                this.spellsAndTrapsCondition[playerGroundNumbers[i]] =condition;
                return playerGroundNumbers[i];
            }
        }
        return 0;
    }

    public void removeSpellOrTrap(int number) {
        this.spellsAndTraps[number] = null;
        this.spellsAndTrapsCondition[number] = null;
    }

    public boolean existsOnBoard(Cardable card) {
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

    public void putInFieldZone(Cardable card) {
        this.fieldZone = card;
    }

    public void removeFromFieldZone() {
        this.fieldZone = null;
    }

    public void putInGraveYard(Cardable card) {
        this.cardsInGraveyard.add(card);
    }

    public void removeFromGraveYard(Cardable card) {
        this.cardsInGraveyard.remove(card);
    }

    //TODO man ino ye kam avaz kardam bana be dalayeli ke tozih midam va dige in tabe lazem nabood-parmida
    /*//TODO hamraz ino ezafe kard:
    public boolean doesHaveMonsterInHand(){
        int counterOfMonsters = 0;
        for (Card card : cardsInHand) {
            if (card instanceof MonsterCard)
                counterOfMonsters ++;
        }
        return counterOfMonsters > 0;
    }*/

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