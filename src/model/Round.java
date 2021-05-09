package model;

public class Round {
    private User winner;
    private User loser;
    private int winnerLifePoint;
    private int loserLifePoint;

    public Round(User winner, User loser, int winnerLifePoint, int loserLifePoint) {
        this.winner = winner;
        this.loser = loser;
        this.winnerLifePoint = winnerLifePoint;
        this.loserLifePoint = loserLifePoint;
    }

    public User getWinner() {
        return this.winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getLoser() {
        return this.loser;
    }

    public void setLoser(User loser) {
        this.loser = loser;
    }

    public int getWinnerLifePoint() {
        return this.winnerLifePoint;
    }

    public void setWinnerLifePoint(int winnerLifePoint) {
        this.winnerLifePoint = winnerLifePoint;
    }

    public int getLoserLifePoint() {
        return this.loserLifePoint;
    }

    public void setLoserLifePoint(int loserLifePoint) {
        this.loserLifePoint = loserLifePoint;
    }

    public int getLifePointByUser(User user){
        if(this.winner.equals(user)) return this.winnerLifePoint;
        else return this.loserLifePoint;
    }
}
