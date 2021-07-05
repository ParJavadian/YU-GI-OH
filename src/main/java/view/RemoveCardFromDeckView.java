package view;

import controller.DeckController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RemoveCardFromDeckView extends Application implements Initializable {
    private static Stage stage;
    private static Deck deck;
    private static RemoveCardFromDeckView instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    private static Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;
    private static AnchorPane root;
    @FXML
    private AnchorPane anchorPane1, anchorPane2, anchorPane3, anchorPane4;
    @FXML
    private Label label;


    public static RemoveCardFromDeckView getInstance() {
        if (instance == null) instance = new RemoveCardFromDeckView();
        return instance;
    }

    public void setCurrentUser(User user) {
        RemoveCardFromDeckView.user = user;
    }

    public void setCurrentDeck(Deck deck) {
        RemoveCardFromDeckView.deck = deck;
    }

    @Override
    public void start(Stage stage) throws Exception {
        RemoveCardFromDeckView.stage = stage;
        URL url = getClass().getResource("/RemoveCardFromDeck.fxml");
        root = FXMLLoader.load(url);
        DeckController.getInstance(user).setMainDeckCards(deck);
        DeckController.initImages();
        setImagesAndCards();
        addImages();
        removeBadAnchorPanes();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    private void setImagesAndCards() {
        images = DeckController.getInstance(user).getImages(firstCardNumber);
        if (images.size() > 0) image1 = setImageView(images.get(0), 83);
        if (images.size() > 1) image2 = setImageView(images.get(1), 283);
        if (images.size() > 2) image3 = setImageView(images.get(2), 483);
        if (images.size() > 3) image4 = setImageView(images.get(3), 683);
        ArrayList<Card> cards = DeckController.getInstance(user).getCards(firstCardNumber);
        if (cards.size() > 0) card1 = cards.get(0);
        if (cards.size() > 1) card2 = cards.get(1);
        if (cards.size() > 2) card3 = cards.get(2);
        if (cards.size() > 3) card4 = cards.get(3);
    }

    private ImageView setImageView(Image image, int x) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(140);
        imageView.setFitHeight(204);
        imageView.setX(x);
        imageView.setY(80);
        return imageView;
    }

    private void addImages() {
        if (root != null) {
            if (image1 != null && !root.getChildren().contains(image1)) root.getChildren().add(image1);
            if (image2 != null && !root.getChildren().contains(image2)) root.getChildren().add(image2);
            if (image3 != null && !root.getChildren().contains(image3)) root.getChildren().add(image3);
            if (image4 != null && !root.getChildren().contains(image4)) root.getChildren().add(image4);
        }
    }

    private void removeBadAnchorPanes() {
        if (root != null) {
            if (image1 == null || image1.getImage() == null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane1")) {
                        child.setVisible(false);
                        break;
                    }
                }
            }
            if (image2 == null || image2.getImage() == null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane2")) {
                        child.setVisible(false);
                        break;
                    }
                }
            }
            if (image3 == null || image3.getImage() == null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane3")) {
                        child.setVisible(false);
                        break;
                    }
                }
            }
            if (image4 == null || image4.getImage() == null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane4")) {
                        child.setVisible(false);
                        break;
                    }
                }
            }
        }
    }

    public void removeCardFromDeck(Card card, boolean isSide) {
        try {
            DeckController.getInstance(user).removeCardFromDeck(card.getNamePascalCase(), deck.getDeckName(), isSide);
            resetImagesAndCards();
        } catch (Exception e) {
            printTextError(e.getMessage());
        }
    }

    private void resetImagesAndCards() {
        DeckController.getInstance(user).setMainDeckCards(deck);
        //DeckController.getInstance(user).setUsersCards();
        DeckController.initImages();
        images = DeckController.getInstance(user).getImages(firstCardNumber);
        ArrayList<Card> cards = DeckController.getInstance(user).getCards(firstCardNumber);
        removeImages();
        if (images.size() > 0) image1 = setImageView(images.get(0), 83);
        else if (image1 != null) image1.setImage(null);
        if (images.size() > 1) image2 = setImageView(images.get(1), 283);
        else if (image2 != null) image2.setImage(null);
        if (images.size() > 2) image3 = setImageView(images.get(2), 483);
        else if (image3 != null) image3.setImage(null);
        if (images.size() > 3) image4 = setImageView(images.get(3), 683);
        else if (image4 != null) image4.setImage(null);
        addImages();
        if (cards.size() > 0) card1 = cards.get(0);
        else card1 = null;
        if (cards.size() > 1) card2 = cards.get(1);
        else card2 = null;
        if (cards.size() > 2) card3 = cards.get(2);
        else card3 = null;
        if (cards.size() > 3) card4 = cards.get(3);
        else card4 = null;
        addGoodAnchorPanes();
        removeBadAnchorPanes();
    }

    private void addGoodAnchorPanes() {
        if (root != null) {
            if (image1 != null && image1.getImage() != null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane1")) {
                        child.setVisible(true);
                        break;
                    }
                }
            }
            if (image2 != null && image2.getImage() != null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane2")) {
                        child.setVisible(true);
                        break;
                    }
                }
            }
            if (image3 != null && image3.getImage() != null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane3")) {
                        child.setVisible(true);
                        break;
                    }
                }
            }
            if (image4 != null && image4.getImage() != null) {
                for (Node child : root.getChildren()) {
                    if (child instanceof AnchorPane && child.getId().equals("anchorPane4")) {
                        child.setVisible(true);
                        break;
                    }
                }
            }
        }
    }

    public void printTextError(String output) {
        Alert alert = new Alert(Alert.AlertType.ERROR, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    private void removeImages() {
        if (root != null) {
            if (image1 != null) root.getChildren().remove(image1);
            if (image2 != null) root.getChildren().remove(image2);
            if (image3 != null) root.getChildren().remove(image3);
            if (image4 != null) root.getChildren().remove(image4);
        }
    }

    public void printTextInformation(String output) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DeckController.getInstance(user).setMainDeckCards(deck);
        DeckController.initImages();
        setImagesAndCards();
        addImages();
        removeBadAnchorPanes();
        label.setText(deck.getDeckName());
    }

    public void back() throws Exception {
        AllDecksViewGraphic.getInstance().setCurrentUser(user);
        AllDecksViewGraphic.getInstance().start(stage);
    }

    public void goNextPage() {
        if (firstCardNumber + 4 >= deck.getMainDeck().size()) return;
        firstCardNumber += 4;
        resetImagesAndCards();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        resetImagesAndCards();
    }


    public void removeCardFromDeck1() {
        removeCardFromDeck(card1, false);
    }

    public void removeCardFromDeck2() {
        removeCardFromDeck(card2, false);
    }

    public void removeCardFromDeck3() {
        removeCardFromDeck(card3, false);
    }

    public void removeCardFromDeck4() {
        removeCardFromDeck(card4, false);
    }


    public void addCardToDeck() throws Exception {
        AddCardToDeckView.getInstance().setCurrentUser(user);
        AddCardToDeckView.getInstance().setCurrentDeck(deck);
        AddCardToDeckView.getInstance().setHasComeFromSide(false);
        AddCardToDeckView.getInstance().start(stage);
    }

    public void openSideDeckMenu() throws Exception {
        RemoveCardFromSideDeckView.getInstance().setCurrentUser(user);
        RemoveCardFromSideDeckView.getInstance().setCurrentDeck(deck);
        RemoveCardFromSideDeckView.getInstance().start(stage);
    }

    public void openMainDeckMenu() {

    }
}
