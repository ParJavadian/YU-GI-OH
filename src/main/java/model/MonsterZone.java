package model;

public class MonsterZone {
    private boolean[] hasChangedPositionInThisTurn;
    private boolean[] hasSetInThisTurn;
    private boolean[] hasAttackedInThisTurn;


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
    }
}
