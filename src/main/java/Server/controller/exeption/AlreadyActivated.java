package Server.controller.exeption;

public class AlreadyActivated extends Exception {
    public AlreadyActivated() {
        super("you have already activated this card");
    }
}
