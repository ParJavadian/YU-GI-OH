package Server.controller.exeption;

public class InvisibleCard extends Exception{
    public InvisibleCard() {
        super("card is not visible");
    }
}
