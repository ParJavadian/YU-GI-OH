package Server.controller.exeption;

public class FullSideDeck extends Exception {
    public FullSideDeck() {
        super("side deck is full");
    }
}
