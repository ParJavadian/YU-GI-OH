package view;

import controller.DeckController;
import controller.SoundController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCardToDeckView extends Application implements Initializable {

    private static Deck deck;
    private static Stage stage;
    private static AddCardToDeckView instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    private static Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int firstCardNumber = 0;
    private static boolean hasComeFromSide;
    public static Label label = new Label();
    public static Button button1 = new Button();
    public static Button button2 = new Button();
    public static Button button3 = new Button();
    public static Button button4 = new Button();
    public static Button button5 = new Button();
    public static Button button6 = new Button();
    public static Button button7 = new Button();
    public static Button button8 = new Button();
    private static AnchorPane root;
    public boolean isCTRLPressed = false;
    public boolean isSHIFTPressed = false;
    public boolean isDPressed = false;

    public static AddCardToDeckView getInstance() {
        if (instance == null) instance = new AddCardToDeckView();
        return instance;
    }

    public void setCurrentUser(User user) {
        AddCardToDeckView.user = user;
    }

    public void setCurrentDeck(Deck deck) {
        AddCardToDeckView.deck = deck;
    }

    public void setHasComeFromSide(boolean hasComeFromSide) {
        AddCardToDeckView.hasComeFromSide = hasComeFromSide;
    }

    @Override
    public void start(Stage stage) throws Exception {
        AddCardToDeckView.stage = stage;
        URL url = getClass().getResource("/CreateNewDeck.fxml");
        root = FXMLLoader.load(url);
        DeckController.getInstance(user).setUsersCards();
        DeckController.initImages();
        setImagesAndCards();
        addImages();
        removeBadAnchorPanes();
        Scene scene = new Scene(root);
        preCheat();
        stage.setScene(scene);
    }

    public void addCardToDeck(Card card, boolean isSide) {
        try {
            DeckController.getInstance(user).addCardToDeck(card.getNamePascalCase(), deck.getDeckName(), isSide, false);
            resetImagesAndCards();
            SoundController.getInstance().playWhenMovesCard();
        } catch (Exception e) {
            printTextError(e.getMessage());
        }
    }

    public void addCardToDeckByCheating(Card card, boolean isSide){
        try {
            DeckController.getInstance(user).addCardToDeck(card.getNamePascalCase(), deck.getDeckName(), isSide, true);
            SoundController.getInstance().playWhenMovesCard();
            resetImagesAndCards();
        } catch (Exception e) {
            printTextError(e.getMessage());
        }
    }

    public void goNextPage() {
        if (firstCardNumber + 4 >= user.getAllCards().size()) return;
        firstCardNumber += 4;
        resetImagesAndCards();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        resetImagesAndCards();
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

    private void resetImagesAndCards() {
        DeckController.getInstance(user).setUsersCards();
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

    private void removeImages() {
        if (root != null) {
            if (image1 != null) root.getChildren().remove(image1);
            if (image2 != null) root.getChildren().remove(image2);
            if (image3 != null) root.getChildren().remove(image3);
            if (image4 != null) root.getChildren().remove(image4);
        }
    }

    private void addImages() {
        if (root != null) {
            if (image1 != null && !root.getChildren().contains(image1)) root.getChildren().add(image1);
            if (image2 != null && !root.getChildren().contains(image2)) root.getChildren().add(image2);
            if (image3 != null && !root.getChildren().contains(image3)) root.getChildren().add(image3);
            if (image4 != null && !root.getChildren().contains(image4)) root.getChildren().add(image4);
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
        DeckController.getInstance(user).setUsersCards();
        DeckController.initImages();
        setImagesAndCards();
        addImages();
        removeBadAnchorPanes();
    }

    public void addToMainDeck1() {
        addCardToDeck(card1, false);
    }

    public void addToMainDeck2() {
        addCardToDeck(card2, false);
    }

    public void addToMainDeck3() {
        addCardToDeck(card3, false);
    }

    public void addToMainDeck4() {
        addCardToDeck(card4, false);
    }

    public void addToSideDeck1() {
        addCardToDeck(card1, true);
    }

    public void addToSideDeck2() {
        addCardToDeck(card2, true);
    }

    public void addToSideDeck3() {
        addCardToDeck(card3, true);
    }

    public void addToSideDeck4() {
        addCardToDeck(card4, true);
    }

    public void addToMainDeckCheat1() {
        addCardToDeckByCheating(card1, false);
    }

    public void addToMainDeckCheat2() {
        addCardToDeckByCheating(card2, false);
    }

    public void addToMainDeckCheat3() {
        addCardToDeckByCheating(card3, false);
    }

    public void addToMainDeckCheat4() {
        addCardToDeckByCheating(card4, false);
    }

    public void addToSideDeckCheat1() {
        addCardToDeckByCheating(card1, true);
    }

    public void addToSideDeckCheat2() {
        addCardToDeckByCheating(card2, true);
    }

    public void addToSideDeckCheat3() {
        addCardToDeckByCheating(card3, true);
    }

    public void addToSideDeckCheat4() {
        addCardToDeckByCheating(card4, true);
    }


    private void preCheat(){

        adjustButtonsOnScene();
        root.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().getName().equals("D"))
                    isDPressed = true;
                if (event.getCode().getName().equals("Ctrl"))
                    isCTRLPressed = true;
                if (event.getCode().getName().equals("Shift"))
                    isSHIFTPressed = true;
                if (isSHIFTPressed && isCTRLPressed && isDPressed){
                    setButtonsOnScene();
                    button1.setOnAction(this::createCheatEnvironment1);
                    button2.setOnAction(this::createCheatEnvironment2);
                    button3.setOnAction(this::createCheatEnvironment3);
                    button4.setOnAction(this::createCheatEnvironment4);
                    button5.setOnAction(this::createCheatEnvironment5);
                    button6.setOnAction(this::createCheatEnvironment6);
                    button7.setOnAction(this::createCheatEnvironment7);
                    button8.setOnAction(this::createCheatEnvironment8);

                }
            }

            private void createCheatEnvironment1(ActionEvent actionEvent) {
                addToMainDeckCheat1();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment2(ActionEvent actionEvent) {
                addToSideDeckCheat1();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment3(ActionEvent actionEvent) {
                addToMainDeckCheat2();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment4(ActionEvent actionEvent) {
                addToSideDeckCheat2();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment5(ActionEvent actionEvent) {
                addToMainDeckCheat3();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment6(ActionEvent actionEvent) {
                addToSideDeckCheat3();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment7(ActionEvent actionEvent) {
                addToMainDeckCheat4();
                removeButtons();
                alertForCheat();
            }

            private void createCheatEnvironment8(ActionEvent actionEvent) {
                addToSideDeckCheat4();
                removeButtons();
                alertForCheat();
            }
        });
        root.getScene().setOnKeyReleased(event -> {
            if (event.getCode().getName().equals("D"))
                isDPressed = false;
            if (event.getCode().getName().equals("Ctrl"))
                isCTRLPressed = false;
            if (event.getCode().getName().equals("Shift"))
                isSHIFTPressed = false;
        });
    }

    public void adjustButtonsOnScene(){
        button1.setLayoutX(100);
        button1.setLayoutY(438);
        button1.setText("AMC!");

        button2.setLayoutX(170);
        button2.setLayoutY(438);
        button2.setText("ASC!");

        button3.setLayoutX(300);
        button3.setLayoutY(438);
        button3.setText("AMC");

        button4.setLayoutX(370);
        button4.setLayoutY(438);
        button4.setText("ASC!");

        button5.setLayoutX(500);
        button5.setLayoutY(438);
        button5.setText("AMC!");

        button6.setLayoutX(570);
        button6.setLayoutY(438);
        button6.setText("ASC!");

        button7.setLayoutX(705);
        button7.setLayoutY(438);
        button7.setText("AMC!");

        button8.setLayoutX(773);
        button8.setLayoutY(438);
        button8.setText("ASC!");
    }

    public void setButtonsOnScene(){
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        root.getChildren().add(button3);
        root.getChildren().add(button4);
        root.getChildren().add(button5);
        root.getChildren().add(button6);
        root.getChildren().add(button7);
        root.getChildren().add(button8);
    }

    private void removeButtons(){
        root.getChildren().remove(button1);
        root.getChildren().remove(button2);
        root.getChildren().remove(button3);
        root.getChildren().remove(button4);
        root.getChildren().remove(button5);
        root.getChildren().remove(button6);
        root.getChildren().remove(button7);
        root.getChildren().remove(button8);
    }

    public void alertForCheat(){
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("shame on you!");
        error.setContentText("you successfully cheated!");
        error.showAndWait();
    }

    public void printTextError(String output) {
        Alert alert = new Alert(Alert.AlertType.ERROR, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    public void back() throws Exception {
        if (hasComeFromSide) {
            RemoveCardFromSideDeckView.getInstance().setCurrentUser(user);
            RemoveCardFromSideDeckView.getInstance().setCurrentDeck(deck);
            RemoveCardFromSideDeckView.getInstance().start(stage);
        } else {
            RemoveCardFromDeckView.getInstance().setCurrentUser(user);
            RemoveCardFromDeckView.getInstance().setCurrentDeck(deck);
            RemoveCardFromDeckView.getInstance().start(stage);
        }
    }
}
