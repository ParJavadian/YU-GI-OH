package controller;

import controller.exeption.*;
import model.*;
import view.ShopView;


import java.util.List;

public class ShopController {

    private static ShopController instance = null;
    private User user;

    public static ShopController getInstance(User user) {
        if (instance == null) instance = new ShopController(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ShopController(User user) {
        this.user = user;
    }

    public void buyCard(String name) throws Exception {
        Cardable card = DeckController.getInstance(user).getCardByName(name);
        if (card != null) {
            if (this.user.getMoney() >= card.getPrice()) {
                this.user.decreaseMoney(card.getPrice());
                this.user.addCardToUsersAllCards(card);
//                ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//                importExportUserController.exportAllCards(this.user);
                ShopView.getInstance(this.user).printText("The card " + name + " was successfully bought!");
            } else {
                throw new NotEnoughMoney();
            }
        } else {
            throw new CardNotFoundForController();
        }
    }

    public void showAll() {
        List<Cardable> allCards =  DeckController.getInstance(user).getAllCardsOfGame();
        //TODO harja string=null bood ="" konim
        String toPrint = "";
        for (Cardable card : allCards){
            if (allCards.indexOf(card) == allCards.size()-1){
                toPrint += card.getNamePascalCase()+":"+card.getPrice();
            } else
                toPrint += card.getNamePascalCase()+":"+card.getPrice()+"\n";
        }
        ShopView.getInstance(this.user).printText(toPrint);
    }


    /*private void logOUtUser() {

    }*/

}
