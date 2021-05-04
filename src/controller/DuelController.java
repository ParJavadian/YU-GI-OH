package controller;

import controller.exeption.*;
import model.*;
import view.DuelView;

import java.util.List;
import java.util.ListIterator;

public class DuelController {

    private User player;
    private User rival;
    private int roundNumber;
    private Card selectedCard;
    private int roundCounter;
    private Phase phase;

    //TODO havasa be cancel bashe(safhe 43 doc)
    //TODO aya jayi handle hardim ke shomare phase ro har seri chap kone?

    public DuelController(User player, User rival, int roundNumber) {
        this.player = player;
        this.rival = rival;
        this.roundNumber = roundNumber;
        this.roundCounter = 0;
        this.selectedCard = null;
    }

    public void selectCardPlayerMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.player.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (monsters[address - 1].equals(null)) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = this.player.getBoard().getMonsterByNumber(address);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.rival.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (monsters[address - 1].equals(null)) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = this.rival.getBoard().getMonsterByNumber(address);
            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.player.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (spellAndTrap[address - 1].equals(null)) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = this.player.getBoard().getSpellAndTrapByNumber(address);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.rival.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (spellAndTrap[address - 1].equals(null)) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = this.rival.getBoard().getSpellAndTrapByNumber(address);
            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerFieldZone() throws Exception {
        Card fieldZone = this.player.getBoard().getFieldZone();
        if (fieldZone.equals(null)) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = this.player.getBoard().getFieldZone();
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentFieldZone() throws Exception {
        Card fieldZone = this.rival.getBoard().getFieldZone();
        if (fieldZone.equals(null)) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = this.rival.getBoard().getFieldZone();
            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerHand(int address) throws Exception {
        List<Card> cardsInHand = this.player.getBoard().getCardsInHand();
        if ((address > cardsInHand.size()) || (address < 1)) {
            throw new InvalidSelection();
        } else if (cardsInHand.get(address - 1).equals(null)) {
            throw new NoCardFoundInThisPosition();
        }
        this.selectedCard = this.player.getBoard().getCardInHandByNumber(address);
        DuelView.printText("card selected");
    }

    public void unselectCard() throws Exception {
        if (this.selectedCard == null) {
            throw new NoCardSelected();
        } else {
            this.selectedCard = null;
            DuelView.printText("card deselected");
        }
    }

    public void summonMonster() throws Exception {
        MonsterCard monsterCard = (MonsterCard) selectedCard;
        MonsterCard[] monsterCards = this.player.getBoard().getMonsters();
        int numberOfMonsterCard = 0;
        for (int i = 0; i < 5; i++) {
            if (!monsterCards[i].equals(null)){
                numberOfMonsterCard++;
            }
        }
        if (this.selectedCard == null) {
            throw new NoCardSelected();
        } else if (true/*todo*/) {
            throw new CanNotSummon();
        } else if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2)))) {
            throw new ImproperPhase();
        } else if (this.player.getBoard().isFullMonsterZone()) {
            throw new FullMonsterZone();
        } else if (hasSummonedInThisTurn()) {
            throw new AlreadySummoned();
        } else if (monsterCard.getLevel() <= 4) {
            //Todo
            DuelView.printText("summoned successfully");
        } else if (monsterCard.getLevel()<7){
            if (numberOfMonsterCard < 1){
                throw new InsufficientForTribute();
            } else {
                tributeOneMonster();
                //Todo
                DuelView.printText("summoned successfully");
            }
        } else {
            if (numberOfMonsterCard < 2){
                throw new InsufficientForTribute();
            } else {
                tributeTwoMonsters();
                //Todo
                DuelView.printText("summoned successfully");
            }
        }
    }


    private void tributeOneMonster(int address) throws Exception {
        selectCardPlayerMonsterZone(address);
    }

    private void tributeTwoMonsters(int address1, int address2) throws Exception{
        //TODO inja alan bayad ye exception midad ke address1 != address2 vali tu doc nist!
    }

    public void preSet() {

    }

    private void setMonster() {

    }

    private void setSpell() {

    }

    private void setTrap() {

    }

    public void changePosition(String targetPosition) {

    }

    public void flipSummon() {

    }

    public void attackMonster(int monsterNumber) {

    }

    private void attackMonsterOO(int monsterNumber) {

    }

    private void attackMonsterDO(int monsterNumber) {

    }

    private void attackMonsterDH(int monsterNumber) {

    }

    public void directAttack() {

    }

    public void activateSpell() {

    }

    private void printBoard() {
        String toPrint = this.rival.getNickname() + ":" + this.rival.getLifePoint() + "\n";
        for (Card eachCard : this.rival.getBoard().getCardsInHand()) {
            toPrint += "\tc";
        }
        toPrint += "\n";
        toPrint += this.rival.getActiveDeck().getTotalSize() + "\n";
        for (int i = 0; i < 5; i++) {
            toPrint += "\t";
            if (this.rival.getBoard().getSpellAndTrapConditionByNumber(i) == null) toPrint += "E";
            else toPrint += this.rival.getBoard().getSpellAndTrapConditionByNumber(i);
        }
        toPrint += "\n";
        for (int i = 0; i < 5; i++) {
            toPrint += "\t";
            if (this.rival.getBoard().getMonsterConditionByNumber(i) == null) toPrint += "E";
            else toPrint += this.rival.getBoard().getMonsterConditionByNumber(i);
        }
        toPrint += "\n" + this.rival.getBoard().getCardsInGraveyard().size() + "\t\t\t\t\t\t";
        if (this.rival.getBoard().getFieldZone() == null) toPrint += "E\n";
        else toPrint += "O\n";
        toPrint += "\n--------------------------\n\n";
        if (this.player.getBoard().getFieldZone() == null) toPrint += "E\t\t\t\t\t\t";
        else toPrint += "O\t\t\t\t\t\t";
        toPrint += this.player.getBoard().getCardsInGraveyard().size() + "\n";
        for (int i = 0; i < 5; i++) {
            toPrint += "\t";
            if (this.player.getBoard().getMonsterConditionByNumber(i) == null) toPrint += "E";
            else toPrint += this.player.getBoard().getMonsterConditionByNumber(i);
        }
        toPrint += "\n";
        for (int i = 0; i < 5; i++) {
            toPrint += "\t";
            if (this.player.getBoard().getSpellAndTrapConditionByNumber(i) == null) toPrint += "E";
            else toPrint += this.player.getBoard().getSpellAndTrapConditionByNumber(i);
        }
        toPrint += "\t\t\t\t\t\t" + this.player.getActiveDeck().getTotalSize() + "\n";
        for (Card eachCard : this.player.getBoard().getCardsInHand()) {
            toPrint += "c\t";
        }
        toPrint += "\n" + this.player.getNickname() + ":" + this.player.getLifePoint();
        DuelView.printText(toPrint);
    }

    private void activateSpellOrTrapInRivalsTurn() {

    }

    public void showGraveyard() throws Exception{
        List<Card> graveyard = this.player.getBoard().getCardsInGraveyard();
        String toPrint = null;
        if (graveyard.isEmpty())
            throw new GraveYardEmpty();
        else {
            ListIterator<Card> li = graveyard.listIterator();
            while (li.hasNext()) {
                li.next();
            }
            while (li.hasPrevious()) {
                Card previous = li.previous();
                if (graveyard.indexOf(previous) == 0)
                toPrint += previous.getName()+":"+previous.getDescription();
                else toPrint += previous.getName()+":"+previous.getDescription()+"\n";
            }
        }

    }

    private void ritualSummon() {

    }

    private void specialSummon() {

    }

    public void showCard() {

    }

    public void surrender() {

    }

    private User shouldEndGame() {
        return null;
    }

    private void endGame(User loser) {

    }

    public void goNextPhase() {

    }

    private boolean hasSummonedInThisTurn() {
        return false;
    }


}
