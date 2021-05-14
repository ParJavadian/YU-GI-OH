package model;

import controller.*;

public class MonsterZone {
    private DuelController duelController;
    private boolean[] hasChangedPositionInThisTurn;
    private boolean[] hasSetInThisTurn;
    private boolean[] hasAttackedInThisTurn;
    private Integer[] playerAttackPoints;
    private Integer[] rivalAttackPoints;

    public MonsterZone(DuelController duelController){
        this.duelController = duelController;
    }

    public void setRivalAttackPoints(int attackPoint,int index) {
        this.rivalAttackPoints[index] = attackPoint;
    }

    public Integer getPlayerAttackPoints(int index) {
        return this.playerAttackPoints[index];
    }

    public void setPlayerAttackPoints(int attackPoint,int index) {
        this.playerAttackPoints[index] = attackPoint;
    }

    public Integer getRivalAttackPoints(int index) {
        return this.rivalAttackPoints[index];
    }

    public boolean getHasChangedPositionInThisTurn(int index) {
        return this.hasChangedPositionInThisTurn[index];
    }

    public void setHasChangedPositionInThisTurn(int index, boolean target) {
        this.hasChangedPositionInThisTurn[index] = target;
    }

    public boolean getHasSetInThisTurn(int index) {
        return this.hasSetInThisTurn[index];
    }

    public void setHasSetInThisTurn(int index, boolean target) {
        this.hasSetInThisTurn[index] = target;
    }

    public boolean getHasAttackedInThisTurn(int index) {
        return this.hasAttackedInThisTurn[index];
    }

    public void setHasAttackedInThisTurn(int index, boolean target) {
        this.hasAttackedInThisTurn[index] = target;
    }

    //ina ro gozashtam age ahyanan lazem shod vali az newAll estefade kardam
    public void newHasSetInThisTurn() {
        this.hasSetInThisTurn= new boolean[5];
    }

    public void newHasAttackedInThisTurn() {
        this.hasAttackedInThisTurn= new boolean[5];
    }

    public void newHasChangedPositionInThisTurn() {
        this.hasChangedPositionInThisTurn= new boolean[5];
    }

    public void newAll(){
        this.hasSetInThisTurn= new boolean[5];
        this.hasAttackedInThisTurn= new boolean[5];
        this.hasChangedPositionInThisTurn= new boolean[5];
        Board playerBoard = this.duelController.getPlayer().getBoard();
        Board rivalBoard= this.duelController.getRival().getBoard();
        for (int i = 0; i < 5; i++) {
            if(playerBoard.getMonsterByNumber(i)==null) this.playerAttackPoints[i]=null;
            else this.playerAttackPoints[i]=playerBoard.getMonsterByNumber(i).getAttack();
            if(rivalBoard.getMonsterByNumber(i)==null) this.rivalAttackPoints[i]=null;
            else this.rivalAttackPoints[i]=rivalBoard.getMonsterByNumber(i).getAttack();
        }
    }

    public void decreaseAllAttackPointsBy400(){
        Board playerBoard = this.duelController.getPlayer().getBoard();
        Board rivalBoard= this.duelController.getRival().getBoard();
        for (int i = 0; i < 5; i++) {
            if(playerBoard.getMonsterByNumber(i)!=null) this.playerAttackPoints[i]=this.playerAttackPoints[i]-400;
            if(rivalBoard.getMonsterByNumber(i)!=null) this.rivalAttackPoints[i]=this.rivalAttackPoints[i]-400;
        }
    }

    public void increaseAllAttackPointsBy400(){
        Board playerBoard = this.duelController.getPlayer().getBoard();
        Board rivalBoard= this.duelController.getRival().getBoard();
        for (int i = 0; i < 5; i++) {
            if(playerBoard.getMonsterByNumber(i)!=null) this.playerAttackPoints[i]=this.playerAttackPoints[i]+400;
            if(rivalBoard.getMonsterByNumber(i)!=null) this.rivalAttackPoints[i]=this.rivalAttackPoints[i]+400;
        }
    }
}
