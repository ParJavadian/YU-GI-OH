package controller;

import controller.exeption.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import view.ShopView;


import java.util.ArrayList;

public class ShopController {

    private static ShopController instance = null;
    private final static ArrayList<Image> allImages = new ArrayList<>();
    private final static ArrayList<Card> allCards = (ArrayList<Card>)DeckController.getInstance(null).getAllCardsOfGame();
    private User user;

    public static ShopController getInstance(User user) {
        if (instance == null) instance = new ShopController(user);
        instance.user = user;
        return instance;
    }

    private ShopController(User user) {
        this.user = user;
        initImages();
    }

    public void buyCard(String name) throws Exception {
        Card card = DeckController.getInstance(user).getCardByName(name);
        if (card != null) {
            if (this.user.getMoney() >= card.getPrice()) {
                this.user.decreaseMoney(card.getPrice());
                this.user.addCardToUsersAllCards(card);
                ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
                importExportUserController.exportAllCards(this.user);
                ShopView.getInstance(this.user).printText("The card " + name + " was successfully bought!");
            } else {
                throw new NotEnoughMoney();
            }
        } else {
            throw new CardNotFoundForController();
        }
    }

    public void showAll() {
        StringBuilder toPrint = new StringBuilder();
        for (Card card : allCards){
            if (allCards.indexOf(card) == allCards.size()-1){
                toPrint.append(card.getNamePascalCase()).append(":").append(card.getPrice());
            } else
                toPrint.append(card.getNamePascalCase()).append(":").append(card.getPrice()).append("\n");
        }
        ShopView.getInstance(this.user).printText(toPrint.toString());
    }

    private void initImages() {
        for (Card card : allCards) {
            allImages.add(card.getImage());
        }
    }

    public ArrayList<Image> getImages(int start){
        ArrayList<Image> myImages = new ArrayList<>(4);
        for (int i = start; i < start+4; i++) {
            myImages.add(allImages.get(i));
        }
        return myImages;
    }

    public ArrayList<Card> getCards(int start){
        ArrayList<Card> cards = new ArrayList<>(4);
        for (int i = start; i < start+4; i++) {
            cards.add(allCards.get(i));
        }
        return cards;
    }

    public int getTotalCardsNumber(){
        return allImages.size();
    }


}
