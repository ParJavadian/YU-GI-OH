package controller.exeption;

public class CardNotFoundForShop extends Exception {
    //TODO in mesle CardNotFoundForController e age lazem nist yademoon bashe pakesh konim(parmida)
    public CardNotFoundForShop() {
        super("there is no card with this name");
    }
}
