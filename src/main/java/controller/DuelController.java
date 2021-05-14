package controller;

import controller.exeption.*;
import model.*;
import view.DuelView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DuelController {

    private static final int[] playerGroundNumbers = {3, 4, 2, 5, 1};
    private static final int[] opponentGroundNumbers = {3, 2, 4, 1, 5};
    private User player;
    private User rival;
    private Round[] rounds;
    private int roundNumber;
    private SelectedCard selectedCard;
    private int roundCounter;
    private Phase phase;
    private boolean hasSummonedOrSetInThisTurn;
    /*boolean[] hasChangedPositionInThisTurn;
    boolean[] hasSetInThisTurn;
    boolean[] hasAttackedInThisTurn;*/
    MonsterZone monsterZone;
    boolean isStartTurn;
    private boolean shouldEndGameForView;
    //TODO check frequently: cancel, unselect, printBoard

    public DuelController(User player, User rival, int roundNumber) {
        this.player = player;
        this.rival = rival;
        Deck deck;
        if (this.player.getDeckByName("@" + this.player.getActiveDeck().getDeckName()) == null) {
            deck = new Deck("@" + this.player.getActiveDeck().getDeckName());
        } else {
            deck = this.player.getDeckByName("@" + this.player.getActiveDeck().getDeckName());
            this.player.removeDeck(deck);
        }
        ArrayList<Card> mainCards = new ArrayList<>(this.player.getActiveDeck().getMainDeck());
        ArrayList<Card> sideCards = new ArrayList<>(this.player.getActiveDeck().getSideDeck());
        deck.setDeck(mainCards, sideCards);
        this.player.addDeck(deck);
        this.player.setGameDeck(deck);
        if (this.rival.getDeckByName("@" + this.rival.getActiveDeck().getDeckName()) == null) {
            deck = new Deck("@" + this.rival.getActiveDeck().getDeckName());
        } else {
            deck = this.rival.getDeckByName("@" + this.rival.getActiveDeck().getDeckName());
            this.rival.removeDeck(deck);
        }
        mainCards = new ArrayList<>(this.rival.getActiveDeck().getMainDeck());
        sideCards = new ArrayList<>(this.rival.getActiveDeck().getSideDeck());
        deck.setDeck(mainCards, sideCards);
        this.rival.addDeck(deck);
        this.rival.setGameDeck(deck);
        this.rounds = new Round[roundNumber];
        this.roundNumber = roundNumber;
        this.roundCounter = 0;
        this.monsterZone = new MonsterZone(this);
        startNewGame(null);
    }

    public User getPlayer() {
        return this.player;
    }

    public User getRival() {
        return this.rival;
    }

    public boolean getShouldEndGameForView() {
        return this.shouldEndGameForView;
    }

    private void startNewGame(User winner) {
        if (winner != null) {
            User loser;
            if (winner.equals(this.player)) {
                loser = this.rival;
            } else {
                loser = this.player;
            }
            this.player = loser;
            this.rival = winner;
            clearLastTurn();
        } else this.isStartTurn = true;
        this.player.setNewBoard();
        this.rival.setNewBoard();
        startDrawPhase(true);
    }

    public void selectCardPlayerMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.player.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        }
        address = playerGroundNumbers[address - 1] - 1;
        if (monsters[address] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getMonsterByNumber(address), BoardZone.MONSTERZONE, address, this.player);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentMonsterZone(int address) throws Exception {
        MonsterCard[] monsters = this.rival.getBoard().getMonsters();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        }
        address = opponentGroundNumbers[address - 1] - 1;
        if (monsters[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getMonsterByNumber(address), BoardZone.MONSTERZONE, address, this.rival);
            DuelView.printText("card selected");
        }
    }

    public void selectCardPlayerTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.player.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        }
        address = playerGroundNumbers[address - 1] - 1;
        if (spellAndTrap[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.player.getBoard().getSpellAndTrapByNumber(address), BoardZone.SPELLANDTRAPZONE, address, this.player);
            DuelView.printText("card selected");
        }
    }

    public void selectCardOpponentTrapAndSpellZone(int address) throws Exception {
        Card[] spellAndTrap = this.rival.getBoard().getSpellsAndTraps();
        if ((address > 5) || (address < 1)) {
            throw new InvalidSelection();
        }
        address = opponentGroundNumbers[address - 1] - 1;
        if (spellAndTrap[address - 1] == null) {
            throw new NoCardFoundInThisPosition();
        } else {
            this.selectedCard = new SelectedCard(this.rival.getBoard().getSpellAndTrapByNumber(address), BoardZone.SPELLANDTRAPZONE, address, this.rival);

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

    private void unselectCard() throws Exception {
        if (this.selectedCard == null) {
            throw new NoCardSelected();
        } else {
            this.selectedCard = null;
        }
    }

    public void unselectCardFromCommand() throws Exception {
        if (this.selectedCard == null) {
            throw new NoCardSelected();
        } else {
            this.selectedCard = null;
            DuelView.printText("card deselected");
        }
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
            throw new ActionNotAllowedInThisPhase();
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
            this.player.getBoard().getCardsInHand().remove(this.selectedCard.getNumber() - 1);
            unselectCard();
            DuelView.printText("summoned successfully");
            hasSummonedOrSetInThisTurn = true;
            return;
        }
        if (monsterCard.getLevel() < 7) {
            if (getCountOfMonsterCardsInGround(this.player) < 1) {
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

    private void tributeOneMonsterForSummon() throws Exception {
        String input = DuelView.scan();
        if (input.equals("cancel")) return;
        int address = Integer.parseInt(input);
        address = playerGroundNumbers[address - 1] - 1;
        if (this.player.getBoard().getMonsterByNumber(address) == null) throw new NoMonsterHere1();
        this.player.getBoard().removeMonster(address);
        removeMonster(address);
        this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "OO");
        this.player.getBoard().getCardsInHand().remove(this.selectedCard.getNumber() - 1);
        unselectCard();
        DuelView.printText("summoned successfully");
        hasSummonedOrSetInThisTurn = true;
    }

    private void tributeTwoMonstersForSummon() throws Exception {
        String input1 = DuelView.scan();
        if (input1.equals("cancel")) return;
        int address1 = Integer.parseInt(input1) - 1;
        address1 = playerGroundNumbers[address1];
        if (this.player.getBoard().getMonsterByNumber(address1) == null) throw new NoMonsterHere1();
        String input2 = DuelView.scan();
        if (input2.equals("cancel")) return;
        int address2 = Integer.parseInt(input2) - 1;
        address2 = playerGroundNumbers[address2];
        if (this.player.getBoard().getMonsterByNumber(address2) == null) throw new NoMonsterHere1();
        if (address1 == address2) throw new sameAddresses();
        this.player.getBoard().removeMonster(address1);
        this.player.getBoard().removeMonster(address2);
        removeMonster(address1);
        removeMonster(address2);
        this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "OO");
        this.player.getBoard().getCardsInHand().remove((int) this.selectedCard.getNumber());
        unselectCard();
        DuelView.printText("summoned successfully");
        hasSummonedOrSetInThisTurn = true;
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
            monsterZone.setHasSetInThisTurn(this.player.getBoard().putMonster(monsterCard, "DH"), true);
            this.player.getBoard().getCardsInHand().remove(this.selectedCard.getNumber() - 1);
            unselectCard();
            DuelView.printText("set successfully");
            hasSummonedOrSetInThisTurn = true;
            printBoard();
            return;
        }
        if (monsterCard.getLevel() < 7) {
            if (getCountOfMonsterCardsInGround(this.player) < 1) {
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
        address = playerGroundNumbers[address - 1] - 1;
        if (this.player.getBoard().getMonsterByNumber(address) == null) throw new NoMonsterHere1();
        this.player.getBoard().removeMonster(address);
        removeMonster(address);
        monsterZone.setHasSetInThisTurn(this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "DH"), true);
        this.player.getBoard().getCardsInHand().remove((int) this.selectedCard.getNumber());
        unselectCard();
        DuelView.printText("set successfully");
        hasSummonedOrSetInThisTurn = true;
        printBoard();
    }

    private void tributeTwoMonstersForSet() throws Exception {
        String input1 = DuelView.scan();
        if (input1.equals("cancel")) return;
        int address1 = Integer.parseInt(input1);
        address1 = playerGroundNumbers[address1 - 1] - 1;
        if (this.player.getBoard().getMonsterByNumber(address1) == null) throw new NoMonsterHere1();
        String input2 = DuelView.scan();
        if (input2.equals("cancel")) return;
        int address2 = Integer.parseInt(input2);
        address2 = playerGroundNumbers[address2 - 1] - 1;
        if (this.player.getBoard().getMonsterByNumber(address2) == null) throw new NoMonsterHere1();
        if (address1 == address2) throw new sameAddresses();
        this.player.getBoard().getCardsInHand().remove((int) this.selectedCard.getNumber());
        this.player.getBoard().removeMonster(address1);
        this.player.getBoard().removeMonster(address2);
        removeMonster(address1);
        removeMonster(address2);
        monsterZone.setHasSetInThisTurn(this.player.getBoard().putMonster((MonsterCard) selectedCard.getCard(), "DH"), true);
        unselectCard();
        DuelView.printText("set successfully");
        hasSummonedOrSetInThisTurn = true;
        printBoard();
    }

    private void setSpell() throws Exception {
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2))))
            throw new ImproperPhase();
        SpellCard spellCard = (SpellCard) this.selectedCard.getCard();
        if (!spellCard.getIcon().equals(Icon.FIELD)) {
            if (this.player.getBoard().isFullSpellAndTrapZone())
                throw new FullSpellZone();
            this.player.getBoard().putSpellOrTrap(spellCard, "H");
        } else {
            if (this.player.getBoard().getFieldZone() != null) {
                this.player.getBoard().putInGraveYard(this.player.getBoard().getFieldZone());
                this.player.getBoard().removeFromFieldZone();
            }
            this.player.getBoard().putInFieldZone(spellCard);
        }
        this.player.getBoard().getCardsInHand().remove(this.selectedCard.getNumber() - 1);
        unselectCard();
        DuelView.printText("set successfully");
        printBoard();
    }

    private void setTrap() throws Exception {
        if (!(phase.equals(Phase.MAIN_PHASE1) || (phase.equals(Phase.MAIN_PHASE2))))
            throw new ImproperPhase();
        TrapCard trapCard = (TrapCard) this.selectedCard.getCard();
        if (!trapCard.getIcon().equals(Icon.FIELD)) {
            if (this.player.getBoard().isFullSpellAndTrapZone()) {
                throw new FullSpellZone();
            }
            this.player.getBoard().putSpellOrTrap(trapCard, "H");
        } else {
            if (this.player.getBoard().getFieldZone() != null) {
                this.player.getBoard().putInGraveYard(this.player.getBoard().getFieldZone());
                this.player.getBoard().removeFromFieldZone();
            }
            this.player.getBoard().putInFieldZone(trapCard);
        }
        this.player.getBoard().getCardsInHand().remove(this.selectedCard.getNumber() - 1);
        unselectCard();
        DuelView.printText("set successfully");
        printBoard();
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
        if (this.selectedCard == null)
            throw new NoCardSelected();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE) || this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals("DH"))
            throw new CanNotChangePosition();
        if (!(this.phase.equals(Phase.MAIN_PHASE1) || (this.phase.equals(Phase.MAIN_PHASE2))))
            throw new CantDoActionInThisPhase();
        if (this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals(targetPositionInShort) || this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals("DH"))
            throw new AlreadyInWantedPosition();
        if (monsterZone.getHasChangedPositionInThisTurn(this.selectedCard.getNumber()))
            throw new AlreadyChangedPosition();
        if (monsterZone.getHasAttackedInThisTurn(this.selectedCard.getNumber()) && this.phase.equals(Phase.MAIN_PHASE2))
            throw new HasAttackedInBattle();
        this.player.getBoard().changeMonsterPosition(this.selectedCard.getNumber(), targetPositionInShort);
        monsterZone.setHasChangedPositionInThisTurn(this.selectedCard.getNumber(), true);
        DuelView.printText("monster card position changed successfully");
        printBoard();
    }

    public void flipSummon() throws Exception {
        if (this.selectedCard == null)
            throw new NoCardSelected();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE))
            throw new CanNotChangePosition();
        if (!(this.phase.equals(Phase.MAIN_PHASE1) || this.phase.equals(Phase.MAIN_PHASE2)))
            throw new CantDoActionInThisPhase();
        if (!this.player.getBoard().getMonsterConditionByNumber(this.selectedCard.getNumber()).equals("DH") || monsterZone.getHasSetInThisTurn(this.selectedCard.getNumber()))
            throw new CanNotFlipSummon();
        this.player.getBoard().changeMonsterPosition(this.selectedCard.getNumber(), "OO");
        unselectCard();
        DuelView.printText("flip summoned successfully");
        printBoard();
    }

    public void attackMonster(int monsterNumber) throws Exception {
        monsterNumber = opponentGroundNumbers[monsterNumber - 1];
        if (this.selectedCard == null) throw new NoCardSelected();
        if (!(this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE) && (this.selectedCard.getCard() instanceof MonsterCard)))
            throw new CanNotAttack();
        if (!(phase.equals(Phase.BATTLE_PHASE)))
            throw new CantDoActionInThisPhase();
        if (monsterZone.getHasAttackedInThisTurn(this.selectedCard.getNumber()))
            throw new AlreadyAttacked();
        if (getCountOfMonsterCardsInGround(this.rival) == 0)
            throw new NoCardToAttack();
        String targetPosition = this.rival.getBoard().getMonsterConditionByNumber(monsterNumber);
        switch (targetPosition) {
            case "OO":
                attackMonsterOO(monsterNumber);
                break;
            case "DO":
                attackMonsterDO(monsterNumber);
                break;
            case "DH":
                attackMonsterDH(monsterNumber);
                break;
        }
    }

    private void attackMonsterOO(int monsterNumber) throws Exception {
        int attackerAttack = this.monsterZone.getPlayerAttackPoints(this.selectedCard.getNumber());
        int targetAttack = this.monsterZone.getRivalAttackPoints(monsterNumber);
        if (attackerAttack > targetAttack) {
            int damage = attackerAttack - targetAttack;
            this.rival.decreaseLifePoint(damage);
            this.rival.getBoard().putInGraveYard(this.rival.getBoard().getMonsterByNumber(monsterNumber));
            this.rival.getBoard().removeMonster(monsterNumber);
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("your opponent’s monster is destroyed and your opponent receives " + damage + " battle damage");
        } else if (attackerAttack == targetAttack) {
            this.rival.getBoard().removeMonster(monsterNumber);
            this.player.getBoard().removeMonster(this.selectedCard.getNumber());
            removeMonster(this.selectedCard.getNumber());
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("both you and your opponent monster cards are destroyed and no one receives damage");
        } else {
            int damage = targetAttack - attackerAttack;
            this.player.decreaseLifePoint(damage);
            this.player.getBoard().putInGraveYard(this.selectedCard.getCard());
            this.player.getBoard().removeMonster(this.selectedCard.getNumber());
            removeMonster(this.selectedCard.getNumber());
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("Your monster card is destroyed and you received" + damage + "battle damage");
        }
        unselectCard();
        printBoard();
    }

    private void attackMonsterDO(int monsterNumber) throws Exception {
        MonsterCard target = this.rival.getBoard().getMonsterByNumber(monsterNumber);
        int attackerAttack = this.monsterZone.getPlayerAttackPoints(this.selectedCard.getNumber());
        if (attackerAttack > target.getDefence()) {
            this.rival.getBoard().removeMonster(monsterNumber);
            this.rival.getBoard().putInGraveYard(target);
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("the defense position monster is destroyed");
        } else if (attackerAttack == target.getDefence()) {
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("no card is destroyed");
        } else {
            int damage = target.getDefence() - attackerAttack;
            this.player.decreaseLifePoint(damage);
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("no card is destroyed and you received" + damage + " battle damage");
        }
        unselectCard();
        printBoard();
    }

    private void attackMonsterDH(int monsterNumber) throws Exception {
        MonsterCard target = this.rival.getBoard().getMonsterByNumber(monsterNumber);
        int attackerAttack = this.monsterZone.getPlayerAttackPoints(this.selectedCard.getNumber());
        String targetName = this.rival.getBoard().getMonsterByNumber(monsterNumber).getName();
        this.rival.getBoard().changeMonsterPosition(monsterNumber, "DO");
        if (attackerAttack > target.getDefence()) {
            this.rival.getBoard().removeMonster(monsterNumber);
            this.rival.getBoard().putInGraveYard(target);
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("opponent’s monster card was " + targetName + " and the defense position monster is destroyed");
        } else if (attackerAttack == target.getDefence()) {
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("opponent’s monster card was " + targetName + " and no card is destroyed");
        } else {
            int damage = target.getDefence() - attackerAttack;
            this.player.decreaseLifePoint(damage);
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            DuelView.printText("opponent’s monster card was " + targetName + " and no card is destroyed and you received" + damage + " battle damage");
        }
        unselectCard();
        printBoard();
    }

    public void directAttack() throws Exception {
        if (this.selectedCard == null) throw new NoCardSelected();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.MONSTERZONE)) throw new CanNotAttack();
        if (!this.phase.equals(Phase.BATTLE_PHASE)) throw new ImproperPhase();
        int countOfMonsterCardsInGround = getCountOfMonsterCardsInGround(this.rival);
        if (countOfMonsterCardsInGround == 0) {
            rival.decreaseLifePoint(((MonsterCard) this.selectedCard.getCard()).getAttack());
            monsterZone.setHasAttackedInThisTurn(this.selectedCard.getNumber(), true);
            unselectCard();
            printBoard();
        } else throw new CanNotAttackDirectly();
        printBoard();
    }

    public void activateSpell() throws Exception {
        if (this.selectedCard == null)
            throw new NoCardSelected();
        if (!(this.selectedCard.getCard() instanceof SpellCard))
            throw new IsNotSpell();
        if (!((this.phase.equals(Phase.MAIN_PHASE1)) || (this.phase.equals(Phase.MAIN_PHASE2))))
            throw new CanNotActivateEffectOnThisTurn();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.HAND))
            throw new AlreadyActivated();
        SpellCard spellCard = (SpellCard) this.selectedCard.getCard();
        if (this.player.getBoard().isFullSpellAndTrapZone() && !spellCard.getIcon().equals(Icon.FIELD))
            throw new FullSpellZone();
//        if (/*sahrti dasht ke nemishod faal kard */) throw new UndonePreparationOfSpell();
        if (spellCard.getIcon().equals(Icon.FIELD)) {
            if (this.player.getBoard().getFieldZone() != null) {
                this.player.getBoard().putInGraveYard(this.player.getBoard().getFieldZone());
                this.player.getBoard().removeFromFieldZone();
            }
            //TODO age lazeme condition ham pas bede
            this.player.getBoard().putInFieldZone(spellCard);
        } else {
            this.player.getBoard().putSpellOrTrap(spellCard, "O");
        }
        this.player.getBoard().getCardsInHand().remove(this.selectedCard.getNumber() - 1);
        unselectCard();
        DuelView.printText("spell activated");
        printBoard();
    }

    //     if (/*ritual spell ya ritual summon she bad dasture dgei vared she*/)
    //      DuelView.printText("you should ritual summon right now");
 /*
    private void ritualSummon() throws Exception{
        if (this.selectedCard.getCard() == null) throw new NoCardSelected();
        if(/*agar ritual tu dastemun nabud ya majmue sutuh .. barabar nabud */ //)
            /*throw new CanNotRitualSummon();
        if (this.selectedCard.getCard() instanceof SpellCard) ritualSpell();
        if (this.selectedCard.getCard() instanceof MonsterCard) ritualMonster();
    }

    private void ritualSpell() throws Exception{

    }
    private void ritualMonster() throws Exception{

    }
*/

    private void ritualSummon() throws Exception {


    }

    private void activateSpellOrTrapInRivalsTurn() throws Exception {
        /*sharayet bar ghara bud*/
        if (true) {
            DuelView.printText("do you want to activate your trap and spell?");
            String input = DuelView.scan();
            if (input.equals("yes")) {
//                if (/*kare digei anjam shod*/) throw new NotYourTurnForThisAction();
                //faal sazie YEK card ke sharayetesh hast va ru zamine
                DuelView.printText("spell/trap activated");
            } else if (input.equals("no")) {
                DuelView.printText("now it will be " + this.player + "'s turn");
                printBoard();
            }
        }
    }

    private void specialSummon() throws Exception {
        if (this.selectedCard == null) throw new NoCardSelected();
        if (!this.selectedCard.getBoardZone().equals(BoardZone.HAND)) {
            throw new CanNotSpecialSummon();
        }
//        if (/*hayula special summon nashod*/) {
        DuelView.printText("you should special summon right now");
        //special summon kone
//        }
    }

    public void cheatLifePoint(String target, int lifePoint) {
        if (lifePoint < 2000) {
            if (target.equals("player"))
                this.player.increaseLifePoint(lifePoint);
            if (target.equals("opponent"))
                this.rival.decreaseLifePoint(lifePoint);
            DuelView.printText("shame on you for cheating!!!!");
        }
    }

    public void cheatMoney(int amount) {
        if (amount <= 5000)
            this.player.increaseMoney(amount);
        DuelView.printText("shame on you for cheating!!!!");
    }

    //TODO lotfan ino check konin ok bashe
    // be nazare man ke okeye(parmida)
    public void cheatToWinGame() {
        DuelView.printText("this is not a good way to win the game, but ok. Shame on you!");
        endGame(this.rival);
    }

    private void checkForChangesWhenAttackedMonster(int monsterNumber) {
        switch (this.rival.getBoard().getMonsterByNumber(monsterNumber)) {
            case COMMAND_KNIGHT:
                this.monsterZone.decreaseAllAttackPointsBy400();
                break;
        }
    }

    public void surrender() {
        //todo shayad lazem bashe darbare in bishter tafakor konim
        endGame(this.player);
    }

    public void manageEndGame() {
        List<Card> playersCardInHand = player.getBoard().getCardsInHand();
        List<Card> rivalsCardInHand = rival.getBoard().getCardsInHand();
        if (player.getLifePoint() <= 0) {
            endGame(player);
        } else if (rival.getLifePoint() <= 0) {
            endGame(rival);
        } else if (playersCardInHand.isEmpty()) {
            endGame(player);
        } else if (rivalsCardInHand.isEmpty()) {
            endGame(rival);
        }
    }

    private void endGame(User loser) {
        User winner;
        if (loser.equals(rival)) {
            winner = player;
        } else {
            winner = rival;
        }
        roundCounter++;
        if (roundNumber == 1) {
            winner.increaseScore(1000);
            winner.increaseMoney(1000 + winner.getLifePoint());
            loser.increaseMoney(100);
            shouldEndGameForView = true;
            DuelView.printText(loser.getUsername() + " won the game and the score is: " + winner.getScore() + "-" + loser.getScore());
        }
        if (roundNumber == 3) {
            this.rounds[this.roundCounter - 1] = new Round(winner, loser, winner.getLifePoint(), loser.getLifePoint());
            if (this.roundCounter == 2 && this.rounds[0].getWinner().equals(this.rounds[1].getWinner())) {
                int winnerLP1 = this.rounds[0].getLifePointByUser(winner);
                int winnerLP2 = this.rounds[1].getLifePointByUser(winner);
                int maxLP = Math.max(winnerLP1, winnerLP2);
                winner.increaseScore(3000);
                winner.increaseMoney(3000 + (3 * maxLP));
                loser.increaseMoney(300);
                shouldEndGameForView = true;
                DuelView.printText(loser.getUsername() + " won the game and the score is: " + winner.getScore() + "-" + loser.getScore());
                DuelView.printText(loser.getUsername() + " won the whole match with score: " + winner.getScore() + "-" + loser.getScore());
            } else if (roundCounter == 3) {
                int winnerLP1 = this.rounds[0].getLifePointByUser(winner);
                int winnerLP2 = this.rounds[1].getLifePointByUser(winner);
                int winnerLP3 = this.rounds[2].getLifePointByUser(winner);
                int maxLP = Math.max(winnerLP1, winnerLP2);
                maxLP = Math.max(maxLP, winnerLP3);
                winner.increaseScore(3000);
                winner.increaseMoney(3000 + (3 * maxLP));
                loser.increaseMoney(300);
                shouldEndGameForView = true;
                DuelView.printText(loser.getUsername() + " won the game and the score is: " + winner.getScore() + "-" + loser.getScore());
                DuelView.printText(loser.getUsername() + " won the whole match with score: " + winner.getScore() + "-" + loser.getScore());
            } else {
                DuelView.printText(loser.getUsername() + " won the game and the score is: " + winner.getScore() + "-" + loser.getScore());
                exchangeCardBetweenMainAndSide(this.player);
                exchangeCardBetweenMainAndSide(this.rival);
                startNewGame(winner);
            }
        }
    }

    private void exchangeCardBetweenMainAndSide(User user) {
        DuelView.printText("Do you want to exchange a card between main and side deck" + user.getNickname() + "?");
        String answer = DuelView.scan();
        if (answer.equals("yes") || answer.equals("Yes")) {
            DuelView.printText("enter card name from main and side");
            String main = DuelView.scan();
            Card mainCard = user.getCardByName(main);
            while (mainCard == null || !user.getGameDeck().getMainDeck().contains(mainCard)) {
                DuelView.printText("Please enter a card you have in your main deck!");
                main = DuelView.scan();
                mainCard = user.getCardByName(main);
            }
            String side = DuelView.scan();
            Card sideCard = user.getCardByName(side);
            while (sideCard == null || !user.getGameDeck().getSideDeck().contains(sideCard)) {
                DuelView.printText("Please enter a card you have in your side deck!");
                side = DuelView.scan();
                sideCard = user.getCardByName(side);
            }
            user.getGameDeck().getSideDeck().remove(sideCard);
            user.getGameDeck().getMainDeck().remove(mainCard);
            user.getGameDeck().getSideDeck().add(mainCard);
            user.getGameDeck().getMainDeck().add(sideCard);
        }
    }

    public void goNextPhase() {
        //TODO in kar dare hala
        if (this.phase.equals(Phase.DRAW_PHASE)) {
            this.phase = Phase.STANDBY_PHASE;
            DuelView.printText("phase: " + this.phase.getNamePascalCase());
        } else if (phase.equals(Phase.STANDBY_PHASE)) {
            this.phase = Phase.MAIN_PHASE1;
            DuelView.printText("phase: " + this.phase.getNamePascalCase());
            printBoard();
        } else if (phase.equals(Phase.MAIN_PHASE1)) {
            if (!isStartTurn)
                this.phase = Phase.BATTLE_PHASE;
            else
                this.phase = Phase.MAIN_PHASE2;
            DuelView.printText("phase: " + this.phase.getNamePascalCase());
        } else if (this.phase.equals(Phase.BATTLE_PHASE)) {
            this.phase = Phase.MAIN_PHASE2;
            DuelView.printText("phase: " + this.phase.getNamePascalCase());
        } else if (this.phase.equals(Phase.MAIN_PHASE2)) {
            this.phase = Phase.END_PHASE;
        } else if (this.phase.equals(Phase.END_PHASE)) {
            DuelView.printText("phase: " + phase.getNamePascalCase());
            DuelView.printText("its " + rival.getNickname() + "’s turn");
            changeTurn();
            startDrawPhase(false);
        }
    }

    private void startDrawPhase(boolean isFirstTime) {
        this.phase = Phase.DRAW_PHASE;
        ArrayList<Card> playerMainCards = (ArrayList<Card>) this.player.getGameDeck().getMainDeck();
        ArrayList<Card> rivalMainCards = (ArrayList<Card>) this.rival.getGameDeck().getMainDeck();
        if (playerMainCards.size() == 0) endGame(this.player);
        else {
            if (!isFirstTime) {
                this.player.getBoard().addCardToHand(playerMainCards.get(playerMainCards.size() - 1));
                this.player.getGameDeck().removeCardFromMainDeck(playerMainCards.get(playerMainCards.size() - 1));
                Collections.shuffle(this.player.getGameDeck().getMainDeck());
                DuelView.printText("new card added to the hand : " + playerMainCards.get(playerMainCards.size() - 1).getNamePascalCase());
            } else {
                for (int i = 0; i < 5; i++) {
                    this.player.getBoard().addCardToHand(playerMainCards.get(playerMainCards.size() - 1));
                    this.player.getGameDeck().removeCardFromMainDeck(playerMainCards.get(playerMainCards.size() - 1));
                    this.rival.getBoard().addCardToHand(rivalMainCards.get(rivalMainCards.size() - 1));
                    this.rival.getGameDeck().removeCardFromMainDeck(rivalMainCards.get(rivalMainCards.size() - 1));
                }
            }
        }
    }

    private void changeTurn() {
        if (this.isStartTurn) this.isStartTurn = false;
        //TODO inam hanooz kar dare
        User temp = this.player;
        this.player = rival;
        this.rival = temp;
        clearLastTurn();

        if (this.player.getUsername().equals("@AI@"))
            handleAITurn();

    }

    private void handleAITurn() {


        changeTurn();
    }

    private void clearLastTurn() {
        this.selectedCard = null;
        this.hasSummonedOrSetInThisTurn = false;
        monsterZone.newAll();
    }

    private void printBoard() {
        String toPrint = this.rival.getNickname() + ":" + this.rival.getLifePoint() + "\n";
        for (Card ignored : this.rival.getBoard().getCardsInHand()) {
            toPrint += "\tc";
        }
        toPrint += "\n";
        toPrint += this.rival.getGameDeck().getMainSize() + "\n";
        for (int i = 4; i > -1; i--) {
            toPrint += "\t";
            if (this.rival.getBoard().getSpellAndTrapConditionByNumber(i) == null) toPrint += "E";
            else toPrint += this.rival.getBoard().getSpellAndTrapConditionByNumber(i);
        }
        toPrint += "\n";
        for (int i = 4; i > -1; i--) {
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
        toPrint += "\t\t\t\t\t\t" + this.player.getGameDeck().getMainSize() + "\n";
        for (Card ignored : this.player.getBoard().getCardsInHand()) {
            toPrint += "c\t";
        }
        toPrint += "\n" + this.player.getNickname() + ":" + this.player.getLifePoint();
        DuelView.printText(toPrint);
    }

    public void showCard() throws Exception {
        if (this.selectedCard == null) {
            throw new NoCardSelected();
        } else if (!this.selectedCard.getOwner().equals(this.player)) {
            throw new InvisibleCard();
        } else
            DuelView.printText(this.selectedCard.getCard().toString());
    }

    public void showGraveyard() throws Exception {
        List<Card> graveyard = this.player.getBoard().getCardsInGraveyard();
        String toPrint = "";
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

    private void removeMonster(int address) {
        monsterZone.setHasAttackedInThisTurn(address, false);
        monsterZone.setHasChangedPositionInThisTurn(address, false);
        monsterZone.setHasSetInThisTurn(address, false);
    }


}

