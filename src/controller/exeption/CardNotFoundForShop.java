package controller.exeption;

public class CardNotFoundForShop extends Exception {
    public CardNotFoundForShop() {
        super("there is no card with this name");
    }
}
