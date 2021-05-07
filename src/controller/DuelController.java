package controller;

import controller.exeption.*;
import model.*;
import view.DuelView;

import java.util.List;

public class DuelController {

    private User player;
    private User rival;
    private int roundNumber;
    private SelectedCard selectedCard;
    private int roundCounter;
    private Phase phase;
    private boolean hasSummonedOrSetInThisTurn;
    boolean[] hasChangedPosition;
    /*private static final int[] playerGroundNumbers = {3, 4, 2, 5, 1};
    private static final int[] opponentGroundNumbers = {3, 2, 4, 1, 5};*/

    //TODO havasa be cancel bashe(safhe 43 doc)
    //TODO aya jayi handle hardim ke shomare phase ro har seri chap kone?
    //TODO yademun nare bade har kari ke anjam shod unselect konim ke exception no card selected throw she

    public DuelController(User player, User rival, int roundNumber) {
        this.player = player;
        this.rival = rival;
        this.roundNumber = roundNumber;
        this.roundCounter = 0;
        this.selectedCard = null;
        this.hasSummonedOrSetInThisTurn = false;
        this.hasChangedPosition = new boolean[5];
    }

    public void selectCardPlayerMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.player.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (monsters[address - 1]==null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getMonsterByNumber(address-1),BoardZone.MONSTERZONE,address-1,this.player);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.rival.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (monsters[address - 1]==null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getMonsterByNumber(address-1),BoardZone.MONSTERZONE,address-1,this.rival);

            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.player.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (spellAndTrap[address - 1]==null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getSpellAndTrapByNumber(address-1),BoardZone.SPELLANDTRAPZONE,address-1,this.player);

            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.rival.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (spellAndTrap[address - 1]==null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getSpellAndTrapByNumber(address-1),BoardZone.SPELLANDTRAPZONE,address-1,this.rival);

            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerFieldZone() throws Exception {
        Card fieldZone = this.player.getBoard().getFieldZone();
        if (fieldZone==null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getFieldZone(),BoardZone.FIELDZONE,1,this.player);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentFieldZone() throws Exception {
        Card fieldZone = this.player.getBoard().getFieldZone();
        if (fieldZone==null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getFieldZone(),BoardZone.FIELDZONE,1,this.rival);
            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerHand(int address) throws Exception {
        List<Card> cardsInHand = this.player.getBoard().getCardsInHand();
        if ((address > cardsInHand.size()) || (address < 1)) {
            throw new InvalidSelection();
        } else if (cardsInHand.get(address - 1) == null) {
            throw new NoCardFoundInThisPosition();
        }
        this.selectedCard = new SelectedCard(this.player.getBoard().getCardInHandByNumber(address-1),BoardZone.HAND,address-1,this.player);
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
        if (this.selectedCard == null) {
            throw new NoCardSelected();
        }
        //TODO in exception payinie halate "مد نظر قابلیت احضار عادی را نداشته باشد monster" ro nadare hanooz
        if (!(this.selectedCard.getCard() instanceof MonsterCard && this.selectedCard.getBoardZone().equals(BoardZone.HAND))) {
            throw new CanNotSummon();
        }
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2)))) {
            throw new ImproperPhase();
        }
        if (this.player.getBoard().isFullMonsterZone()) {
            throw new FullMonsterZone();
        }
        if (hasSummonedOrSetInThisTurn) {
            throw new AlreadySummoned();
        }
        MonsterCard monsterCard = (MonsterCard) selectedCard.getCard();
        if (monsterCard.getLevel() <= 4) {
            this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "OO");
            unselectCard();
            DuelView.printText("summoned successfully");
            hasSummonedOrSetInThisTurn = true;
            return;
        }
        if (monsterCard.getLevel() < 7) {
            if (getCountOfMonsterCardsInGround(this.player) < 1) {
                unselectCard();
                throw new InsufficientForTribute();
            } else {
                String input = DuelView.scan();
                if(input.equals("cancel")) return;
                tributeOneMonster(Integer.parseInt(input));
                //Todo
                unselectCard();
                DuelView.printText("summoned successfully");
                hasSummonedOrSetInThisTurn = true;
            }
        } else {
            if (getCountOfMonsterCardsInGround(this.player) < 2) {
                throw new InsufficientForTribute();
            } else {
                tributeTwoMonsters();
                //Todo
                DuelView.printText("summoned successfully");
                hasSummonedOrSetInThisTurn = true;
            }
        }
    }
/*
    //TODO man(hamraz) inja gozashtam ke be jaye void, int return kone ke int tedad tribute haye monsteras ke age
    // tribute mikhast addressesho begire

    public int summonMonster() throws Exception {
        if (this.selectedCard == null) {
            throw new NoCardSelected();
            return 0;
        }
        //TODO in exception payinie halate "مد نظر قابلیت احضار عادی را نداشته باشد monster" ro nadare hanooz
        if (!(this.selectedCard instanceof MonsterCard) || !this.player.getBoard().isInHand(this.selectedCard)) {
            throw new CanNotSummon();
            return 0;
        }
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2)))) {
            throw new ImproperPhase();
            return 0;
        }
        if (this.player.getBoard().isFullMonsterZone()) {
            throw new FullMonsterZone();
            return 0;
        }
        if (hasSummonedInThisTurn()) {
            throw new AlreadySummoned();
            return 0;
        }
        MonsterCard monsterCard = (MonsterCard) selectedCard;
        MonsterCard[] monsterCards = this.player.getBoard().getMonsters();
        int countOfMonsterCardsInGround = 0;
        for (int i = 0; i < 5; i++) {
            if (!monsterCards[i].equals(null)) {
                countOfMonsterCardsInGround++;
            }
        }
        if (monsterCard.getLevel() <= 4) {
        //TODO
            DuelView.printText("summoned successfully");
            return 0;
        }
        if (monsterCard.getLevel() < 7) {
            if (countOfMonsterCardsInGround < 1) {
                throw new InsufficientForTribute();
                return 0;
            } else {
                tributeOneMonster();
                //Todo
                DuelView.printText("summoned successfully");
                return 1;
            }
        } else {
            if (countOfMonsterCardsInGround < 2) {
                throw new InsufficientForTribute();
                return 0;
            } else {
                tributeTwoMonsters();
                //Todo
                DuelView.printText("summoned successfully");
                return 2;
            }
        }
    }
*/
    private void tributeOneMonster(int address) throws Exception {
        if(address)
        selectCardPlayerMonsterZone(address);
    }

    private void tributeTwoMonsters(int address1, int address2) throws Exception {
        //TODO inja alan bayad ye exception midad ke address1 != address2 vali tu doc nist!
    }

    private int getCountOfMonsterCardsInGround(User user) {
        MonsterCard[] monsterCards = user.getBoard().getMonsters();
        int countOfMonsterCardsInGround = 0;
        for (int i = 0; i < 5; i++) {
            if (monsterCards[i]!=null) {
                countOfMonsterCardsInGround++;
            }
        }
        return countOfMonsterCardsInGround;
    }

    public void preSet() throws Exception{
        if(this.selectedCard == null) throw new NoCardSelected();
        if(!(this.selectedCard.getBoardZone().equals(BoardZone.HAND))) throw new CanNotSet();
        if(this.selectedCard.getCard() instanceof MonsterCard) setMonster();
        else if(this.selectedCard.getCard() instanceof SpellCard) setSpell();
        else if(this.selectedCard.getCard() instanceof TrapCard) setTrap();
    }

    private void setMonster() throws Exception {
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2)))) {
            throw new ImproperPhase();
        }
        if (this.player.getBoard().isFullMonsterZone()) {
            throw new FullMonsterZone();
        }
        if (hasSummonedOrSetInThisTurn) {
            throw new AlreadySummoned();
        }
        MonsterCard monsterCard = (MonsterCard) this.selectedCard.getCard();
        if (monsterCard.getLevel() <= 4) {
            this.player.getBoard().putMonster(monsterCard, "DH");
            unselectCard();
            DuelView.printText("set successfully");
            hasSummonedOrSetInThisTurn = true;
            return;
        }
        if (monsterCard.getLevel() < 7) {
            if (getCountOfMonsterCardsInGround(this.player) < 1) {
                unselectCard();
                throw new InsufficientForTribute();
            } else {
                String input = DuelView.scan();
                if(input.equals("cancel")) return;
                tributeOneMonster(Integer.parseInt(input));
                //Todo
                unselectCard();
                DuelView.printText("summoned successfully");
                hasSummonedOrSetInThisTurn = true;
            }
        } else {
            if (getCountOfMonsterCardsInGround(this.player) < 2) {
                throw new InsufficientForTribute();
            } else {
                String input1 = DuelView.scan();
                if(input1.equals("cancel")) return;
                String input2 = DuelView.scan();
                if(input2.equals("cancel")) return;
                tributeTwoMonsters(Integer.parseInt(input1),Integer.parseInt(input2));
                //Todo
                unselectCard();
                DuelView.printText("summoned successfully");
                hasSummonedOrSetInThisTurn = true;
            }
        }
    }

    private void setSpell() {

    }

    private void setTrap() {

    }

    public void changePosition(String targetPosition) throws Exception{
        String targetPositionInShort = "";
        switch (targetPosition){
            case "attack":
                targetPositionInShort = "OO";
                break;
            case "defence":
                targetPositionInShort = "DO";
                break;
        }
        if(this.selectedCard == null) {
            throw new NoCardSelected();
        } else if (!this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE)) {
            throw new CanNotChangePosition();
        } else if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2)))) {
            throw new CantDoActionInThisPhase();
        } else if (this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals(targetPositionInShort) || this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals("DH")) {
            throw new AlreadyInWantedPosition();
        } else if (this.hasChangedPosition[this.selectedCard.getNumber()]){
            throw new AlreadyChangedPosition();
        } else {
            this.player.getBoard().changeMonsterPosition(this.selectedCard.getNumber(),targetPositionInShort);
            this.hasChangedPosition[this.selectedCard.getNumber()] = true;
            DuelView.printText("monster card position changed successfully");
            unselectCard();
        }
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

    public void directAttack() throws Exception {
        int countOfMonsterCardsInGround = getCountOfMonsterCardsInGround(this.rival);
        if (countOfMonsterCardsInGround == 0) {
            rival.decreaseLifePoint(((MonsterCard) this.selectedCard.getCard()).getAttack());
        } else throw new CanNotAttackDirectly();
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

    public void showGraveyard() throws Exception {
        List<Card> graveyard = this.player.getBoard().getCardsInGraveyard();
        String toPrint = null;
        if (graveyard.isEmpty())
            throw new GraveYardEmpty();
        else {
            for (Card cardInGraveyard : graveyard) {
                if (graveyard.indexOf(cardInGraveyard) == graveyard.size() - 1) {
                    toPrint += cardInGraveyard.getName() + ":" + cardInGraveyard.getDescription();
                } else {
                    toPrint += cardInGraveyard.getName() + ":" + cardInGraveyard.getDescription() + "\n";
                }
            }
        }
        DuelView.printText(toPrint);

        String input = DuelView.scan();

       while (!input.equals("back")){
           DuelView.printText("invalid command");
           input = DuelView.scan();
       }

    }

    private void ritualSummon() {

    }

    private void specialSummon() {

    }

    public void showCard() throws Exception {
        if (selectedCard==null) {
            throw new NoCardSelected();
        } else if (/*TODO*/) { //todo nemidoonam az koja befahmim card select shode male kodoom bazikone!
            throw new InvisibleCard();
        } else
            DuelView.printText(selectedCard.toString()); // todo gofte bood too bakhshaye ghabli tozih dadim.bardashtam in bood ke toStringesho mikhad
    }

    public void surrender() { //todo faghat payamesho chap kardam vali nemidoonam chetori az bazi kharej sham ya daste jadidi shoro konam va ina
        if (roundCounter == roundNumber) {
            DuelView.printText(rival.getUsername() + " won the whole match with score: " + rival.getScore() + "-" + player.getScore());
        } else {
            DuelView.printText(rival.getUsername() + " won the game and the score is: " + rival.getScore() + "-" + player.getScore());
        }
    }

    private User shouldEndGame() { // todo benazaram nabayad User return kone.void bashe va endGame ro call kone okeye
        List<Card> playersCardInHand = player.getBoard().getCardsInHand();
        List<Card> rivalsCardInHand = rival.getBoard().getCardsInHand();
        if (player.getLifePoint() < 0) {
            endGame(player);
        } else if (rival.getLifePoint() < 0) {
            endGame(rival);
        } else if (playersCardInHand.isEmpty()) {
            endGame(player);
        } else if (rivalsCardInHand.isEmpty()) {
            endGame(rival);
        }
        return null;
    }

    private void endGame(User loser) { //todo duplicate code dare ba surrender.vali chon surrender User pass nemide nemitoonam ino call konam vasash
        User otherUser;
        if (loser.equals(rival)) {
            otherUser = player;
        } else {
            otherUser = rival;
        }
        if (roundCounter == roundNumber) {
            DuelView.printText(loser.getUsername() + " won the whole match with score: " + loser.getScore() + "-" + otherUser.getScore());
        } else {
            DuelView.printText(loser.getUsername() + " won the game and the score is: " + loser.getScore() + "-" + otherUser.getScore());
        }
    }

    public void goNextPhase() {
        if (phase.equals(Phase.DRAW_PHASE)) {
            phase = Phase.STANDBY_PHASE;
            DuelView.printText(phase.getNamePascalCase());
        } else if (phase.equals(Phase.STANDBY_PHASE)) {
            phase = Phase.MAIN_PHASE1;
            DuelView.printText(phase.getNamePascalCase());
        } else if (phase.equals(Phase.MAIN_PHASE1)) {
            phase = Phase.BATTLE_PHASE;
            DuelView.printText(phase.getNamePascalCase());
        } else if (phase.equals(Phase.BATTLE_PHASE)) {
            phase = Phase.MAIN_PHASE2;
            DuelView.printText(phase.getNamePascalCase());
        } else if (phase.equals(Phase.MAIN_PHASE2)) {
            phase = Phase.END_PHASE;
            DuelView.printText(phase.getNamePascalCase());
            DuelView.printText("its " + rival.getNickname() + "’s turn");
            changeTurn();
        }
    }

//    private boolean hasSummonedInThisTurn() {
//        return false;
//    } todo has summoned in this turn ro boolean gozashtam jaye tabe

    //todo tabe tarif kardam ke jaye player va rival ro avaz kone
    private void changeTurn() {
        User temp = player;
        player = rival;
        rival = temp;
        hasSummonedOrSetInThisTurn = false;
    }


}

