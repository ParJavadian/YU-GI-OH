package controller;

import javafx.scene.image.Image;
import model.Card;
import model.User;

import java.util.ArrayList;

public class AdminPanelController {

    private static ShopController instance = null;
    private final static ArrayList<Image> allImages = new ArrayList<>();
    private final static ArrayList<Card> allCards = (ArrayList<Card>)DeckController.getInstance(null).getAllCardsOfGame();

    public static ShopController getInstance() {
        if (instance == null) instance = new AdminPanelController(user);
        instance.user = user;
        return instance;
    }
}
