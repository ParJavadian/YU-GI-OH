package controller;

import controller.exeption.*;
import model.*;
import view.ShopView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;

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
        Card card = this.user.getCardByName(name);
        if (card != null) {
            if (this.user.getMoney() >= card.getPrice()) {
                this.user.decreaseMoney(card.getPrice());
                this.user.addCardToUsersAllCards(card);
            } else
                throw new NotEnoughMoney();
        } else
            throw new CardNotFoundForController();
    }

    public void showAll() {
        List<Card> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, TrapCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Comparator<Card> cardComparator = Comparator.comparing(Card::getName);
        allCards.sort(cardComparator);
        //TODO harja string=null bood ="" konim
        String toPrint = "";
        for (Card card : allCards){
            if (allCards.indexOf(card) == allCards.size()-1){
                toPrint += card.getName()+":"+card.getDescription();
            } else
                toPrint += card.getName()+":"+card.getDescription()+"\n";
        }
        ShopView.getInstance(this.user).printText(toPrint);
    }


    /*private void logOUtUser() {

    }*/

}
