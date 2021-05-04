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
        Card card = user.getCardByName(name);
        if (card != null) {
            if (user.getMoney() >= card.getPrice()) {
                user.decreaseMoney(card.getPrice());
                user.addCardToUsersAllCards(card);
            } else
                throw new NotEnoughMoney();
        } else
            throw new CardNotFoundForShop();
    }

    public void showAll() {
        List<Card> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, TrapCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Comparator<Card> cardComparator = Comparator.comparing(Card::getName);
        String toPrint = null;
        for (Card card : allCards){
            if (allCards.indexOf(card) == allCards.size()-1){
                toPrint += card.getName()+":"+card.getDescription();
            } else
                toPrint += card.getName()+":"+card.getDescription()+"\n";
        }
        ShopView.getInstance(user).printText(toPrint);
    }


    /*private void logOUtUser() {

    }*/

}
