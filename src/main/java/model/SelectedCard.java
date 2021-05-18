package model;

public class SelectedCard {
    private Cardable card;
    private BoardZone boardZone;
    private Integer number;
    private User owner;

    public SelectedCard(Cardable card, BoardZone boardZone, Integer number, User owner){
        this.card = card;
        this.boardZone = boardZone;
        this.number = number;
        this.owner = owner;
    }

    public Cardable getCard() {
        return this.card;
    }

    public void setCard(Cardable card) {
        this.card = card;
    }

    public BoardZone getBoardZone() {
        return this.boardZone;
    }

    public void setBoardZone(BoardZone boardZone) {
        this.boardZone = boardZone;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
