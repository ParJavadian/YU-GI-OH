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
    boolean[] hasSetInThisTurn;
    private static final int[] playerGroundNumbers = {3, 4, 2, 5, 1};
    private static final int[] opponentGroundNumbers = {3, 2, 4, 1, 5};

    //TODO havasa be cancel bashe(safhe 43 doc)
    //TODO aya jayi handle hardim ke shomare phase ro har seri chap kone?
    //TODO yademun nare bade har kari ke anjam shod unselect konim ke exception no card selected throw she
    //TODO print board bade literally har kari ke mikonim!

    public DuelController(User player, User rival, int roundNumber) {
        this.player = player;
        this.rival = rival;
        this.roundNumber = roundNumber;
        this.roundCounter = 0;
        this.selectedCard = null;
        this.hasSummonedOrSetInThisTurn = false;
        this.hasChangedPosition = new boolean[5];
        this.hasSetInThisTurn = new boolean[5];
    }

    public void selectCardPlayerMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.player.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (monsters[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getMonsterByNumber(playerGroundNumbers[address - 1]), BoardZone.MONSTERZONE, address - 1, this.player);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.rival.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (monsters[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getMonsterByNumber(opponentGroundNumbers[address - 1]), BoardZone.MONSTERZONE, address - 1, this.rival);

            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.player.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (spellAndTrap[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getSpellAndTrapByNumber(playerGroundNumbers[address - 1]), BoardZone.SPELLANDTRAPZONE, address - 1, this.player);

            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.rival.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        } else if (spellAndTrap[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getSpellAndTrapByNumber(opponentGroundNumbers[address - 1]), BoardZone.SPELLANDTRAPZONE, address - 1, this.rival);

            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerFieldZone() throws Exception {
        Card fieldZone = this.player.getBoard().getFieldZone();
        if (fieldZone == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getFieldZone(), BoardZone.FIELDZONE, 1, this.player);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentFieldZone() throws Exception {
        Card fieldZone = this.player.getBoard().getFieldZone();
        if (fieldZone == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getFieldZone(), BoardZone.FIELDZONE, 1, this.rival);
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
        this.selectedCard = new SelectedCard(this.player.getBoard().getCardInHandByNumber(address - 1), BoardZone.HAND, address - 1, this.player);
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
        //TODO special va ritual summon
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
                tributeOneMonsterForSummon();
            }
        } else {
            if (getCountOfMonsterCardsInGround(this.player) < 2) {
                throw new InsufficientForTribute();
            } else {
                tributeTwoMonstersForSummon();
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
    private void tributeOneMonsterForSummon() throws Exception {
        String input = DuelView.scan();
        if (input.equals("cancel")) return;
        int address = Integer.parseInt(input);
        if(this.player.getBoard().getMonsterByNumber(address-1) == null) throw new NoMonsterHere1();
        this.player.getBoard().removeMonster(address - 1);
        this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "OO");
        unselectCard();
        DuelView.printText("summoned successfully");
        hasSummonedOrSetInThisTurn = true;
    }

    private void tributeTwoMonstersForSummon() throws Exception {
        String input1 = DuelView.scan();
        if (input1.equals("cancel")) return;
        int address1 = Integer.parseInt(input1);
        if(this.player.getBoard().getMonsterByNumber(address1-1) == null) throw new NoMonsterHere1();
        String input2 = DuelView.scan();
        if (input2.equals("cancel")) return;
        int address2 = Integer.parseInt(input2);
        if(this.player.getBoard().getMonsterByNumber(address2-1) == null) throw new NoMonsterHere1();
        if (address1 == address2) throw new sameAddresses();
        this.player.getBoard().removeMonster(address1 - 1);
        this.player.getBoard().removeMonster(address2 - 1);
        this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "OO");
        unselectCard();
        DuelView.printText("summoned successfully");
        hasSummonedOrSetInThisTurn = true;
    }

    private int getCountOfMonsterCardsInGround(User user) {
        MonsterCard[] monsterCards = user.getBoard().getMonsters();
        int countOfMonsterCardsInGround = 0;
        for (int i = 0; i < 5; i++) {
            if (monsterCards[i] != null) {
                countOfMonsterCardsInGround++;
            }
        }
        return countOfMonsterCardsInGround;
    }

    public void preSet() throws Exception {
        if (this.selectedCard == null) throw new NoCardSelected();
        if (!(this.selectedCard.getBoardZone().equals(BoardZone.HAND))) throw new CanNotSet();
        if (this.selectedCard.getCard() instanceof MonsterCard) setMonster();
        else if (this.selectedCard.getCard() instanceof SpellCard) setSpell();
        else if (this.selectedCard.getCard() instanceof TrapCard) setTrap();
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
            this.hasSetInThisTurn[this.player.getBoard().putMonster(monsterCard, "DH")] = true;
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
                tributeOneMonsterForSet();
            }
        } else {
            if (getCountOfMonsterCardsInGround(this.player) < 2) {
                throw new InsufficientForTribute();
            } else {
                tributeTwoMonstersForSet();
            }
        }
    }

    private void tributeOneMonsterForSet() throws Exception {
        String input = DuelView.scan();
        if (input.equals("cancel")) return;
        int address = Integer.parseInt(input);
        if(this.player.getBoard().getMonsterByNumber(address-1) == null) throw new NoMonsterHere1();
        this.player.getBoard().removeMonster(address - 1);
        this.hasSetInThisTurn[this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "DH")] = true;
        unselectCard();
        DuelView.printText("set successfully");
        hasSummonedOrSetInThisTurn = true;
    }

    private void tributeTwoMonstersForSet() throws Exception {
        String input1 = DuelView.scan();
        if (input1.equals("cancel")) return;
        int address1 = Integer.parseInt(input1);
        if(this.player.getBoard().getMonsterByNumber(address1-1) == null) throw new NoMonsterHere1();
        String input2 = DuelView.scan();
        if (input2.equals("cancel")) return;
        int address2 = Integer.parseInt(input2);
        if(this.player.getBoard().getMonsterByNumber(address2-1) == null) throw new NoMonsterHere1();
        if (address1 == address2) throw new sameAddresses();
        this.hasSetInThisTurn[this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "DH")] = true;
        this.player.getBoard().removeMonster(address1 - 1);
        this.player.getBoard().removeMonster(address2 - 1);
        unselectCard();
        DuelView.printText("set successfully");
        hasSummonedOrSetInThisTurn = true;
    }

    private void setSpell() throws Exception{
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2))))
            throw new ImproperPhase();
        if (this.player.getBoard().isFullSpellAndTrapZone())
            throw new FullSpellZone();

        SpellCard spellCard = (SpellCard) this.selectedCard.getCard();
        this.player.getBoard().putSpellOrTrap(spellCard,"H");
        unselectCard();
        DuelView.printText("set successfully");

    }

    private void setTrap() throws Exception{
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2))))
            throw new ImproperPhase();
        if (this.player.getBoard().isFullSpellAndTrapZone())
            throw new FullSpellZone();

        TrapCard trapCard = (TrapCard) this.selectedCard.getCard();
        this.player.getBoard().putSpellOrTrap(trapCard,"H");
        unselectCard();
        DuelView.printText("set successfully");
    }

    public void changePosition(String targetPosition) throws Exception {
        String targetPositionInShort = "";
        switch (targetPosition) {
            case "attack":
                targetPositionInShort = "OO";
                break;
            case "defence":
                targetPositionInShort = "DO";
                break;
        }
        if (this.selectedCard == null) throw new NoCardSelected();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE)) throw new CanNotChangePosition();
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2))))
            throw new CantDoActionInThisPhase();
        if (this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals(targetPositionInShort) || this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals("DH"))
            throw new AlreadyInWantedPosition();
        if (this.hasChangedPosition[this.selectedCard.getNumber()]) {
            throw new AlreadyChangedPosition();
        }
        this.player.getBoard().changeMonsterPosition(this.selectedCard.getNumber(), targetPositionInShort);
        this.hasChangedPosition[this.selectedCard.getNumber()] = true;
        DuelView.printText("monster card position changed successfully");
        unselectCard();
    }

    public void flipSummon() throws Exception {
        if (this.selectedCard == null) throw new NoCardSelected();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE)) throw new CanNotChangePosition();
        if (!(this.phase.equals(Phase.MAIN_PHASE1) || this.phase.equals(Phase.MAIN_PHASE2)))
            throw new CantDoActionInThisPhase();
        if (!this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals("DH") || this.hasSetInThisTurn[this.selectedCard.getNumber()])
            throw new CanNotFlipSummon();
        this.player.getBoard().changeMonsterPosition(this.selectedCard.getNumber(), "OO");
        unselectCard();
        DuelView.printText("flip summoned successfully");
    }

    public void attackMonster(int monsterNumber) throws Exception{

        if (this.selectedCard == null) throw new NoCardSelected();
        if (!this.player.getBoard().existsOnBoard(this.selectedCard.getCard()) && !(this.selectedCard.getCard() instanceof MonsterCard))
            throw new CanNotAttack();
        if (!(phase.equals(Phase.BATTLE_PHASE)))
            throw new CantDoActionInThisPhase();
        //TODO baraye exception already attacked biayam ye array list dorost konim az monster hayii ke attack shodan o
        // bade tamum shodane battle phasse khalish konim?
        //TODO THIS CARD ALREADY ATTACKED
        if (getCountOfMonsterCardsInGround(this.rival) == 0)
            throw new NoCardToAttack();

        MonsterCard attacker = (MonsterCard) this.selectedCard.getCard();
        MonsterCard target = (MonsterCard) this.rival.getBoard().getMonsterByNumber(monsterNumber);
        String targetPosition = this.rival.getBoard().getMonsterConditionByNumber(monsterNumber);

        if (targetPosition.equals("OO"))
            attackMonsterOO(monsterNumber);
        else if (targetPosition.equals("DO"))
            attackMonsterDO(monsterNumber);
        else if (targetPosition.equals("DH"))
            attackMonsterDH(monsterNumber);
    }

    private void attackMonsterOO(int monsterNumber) {
        MonsterCard attacker = (MonsterCard) this.selectedCard.getCard();
        MonsterCard target = (MonsterCard) this.rival.getBoard().getMonsterByNumber(monsterNumber);

            if (attacker.getAttack() > target.getAttack()){
                int damage = attacker.getAttack() - target.getAttack();
                //TODO dige nemishe ba in attack zad
                this.rival.decreaseLifePoint(damage);
                this.rival.getBoard().putInGraveYard(target);
                this.rival.getBoard().removeMonster(monsterNumber);
                DuelView.printText("your opponent’s monster is destroyed and your opponent receives" +
                        damage + "battle damage");
                //TODO chra unselectCard(); ro error mide?
            }

            if (attacker.getAttack() == target.getAttack()){
                this.rival.getBoard().removeMonster(monsterNumber);
                this.player.getBoard().removeMonster(this.selectedCard.getNumber());
                DuelView.printText("both you and your opponent monster cards are destroyed and no" +
                        "one receives damage");
                //TODO chra unselectCard(); ro error mide?
            }

            if (attacker.getAttack() < target.getAttack()){
                int damage = target.getAttack() - attacker.getAttack();
                this.player.decreaseLifePoint(damage);
                this.player.getBoard().putInGraveYard(attacker);
                this.player.getBoard().removeMonster(this.selectedCard.getNumber());
                //TODO unselect
            }
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

        while (!input.equals("back")) {
            DuelView.printText("invalid command");
            input = DuelView.scan();
        }

    }

    private void ritualSummon() {

    }

    private void specialSummon() {

    }

    public void showCard() throws Exception {
        if (selectedCard == null) {
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

    private void changeTurn() {
        User temp = player;
        player = rival;
        rival = temp;
        hasSummonedOrSetInThisTurn = false;
    }


}

