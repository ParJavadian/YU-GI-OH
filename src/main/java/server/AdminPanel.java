package server;

import controller.ShopController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminPanel extends Application implements Initializable {

    private static Stage stage;
    private static AdminPanel instance = null;
    private static ArrayList<Image> images = new ArrayList<>(4);
    private static Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;
    private static AnchorPane root;
    private static Image image;
    public Button activeButton1, activeButton2, activeButton3, activeButton4;
    public TextField textField1, textField2, textField3, textField4;
    public Label wantedNumber1, wantedNumber2, wantedNumber3, wantedNumber4;
    public Label activeDeactive1, activeDeactive2, activeDeactive3, activeDeactive4;
    public Label numberCard1, numberCard2, numberCard3, numberCard4;
    private static Label activeDeactive11, activeDeactive21, activeDeactive31, activeDeactive41, numberCard11, numberCard21, numberCard31, numberCard41;
    public static HashMap<Card, Boolean> isActives = new HashMap<>();
    public static HashMap<Card, Integer> currencies = new HashMap<>();


//
//    public AdminPanel() {
//        totalCardsNumber = AdminPanel.getInstance(user).getTotalCardsNumber();
//    }


    public static AdminPanel getInstance() {
        if (instance == null) instance = new AdminPanel();
        return instance;
    }

    public AdminPanel() {
        totalCardsNumber = ShopController.getInstance(null).getTotalCardsNumber();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AdminPanel.stage = stage;
        URL url = getClass().getResource("/AdminPanel.fxml");
        root = FXMLLoader.load(url);
        setImagesAndCards();
        addImages();
        setFXMLs();
        setActivesAndCurrencies();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void setFXMLs() {
        if (root != null) {
            for (Node child : root.getChildren()) {
                if (child.getId() == null) continue;
                if (child instanceof Label) {
                    if (child.getId().equals("activeDeactive1"))
                        activeDeactive1 = activeDeactive11;
                    else if (child.getId().equals("activeDeactive2"))
                        activeDeactive2 = activeDeactive21;
                    else if (child.getId().equals("activeDeactive3"))
                        activeDeactive3 = activeDeactive31;
                    else if (child.getId().equals("activeDeactive4"))
                        activeDeactive4 = activeDeactive41;
                    else if (child.getId().equals("numberCard1"))
                        numberCard1 = numberCard11;
                    else if (child.getId().equals("numberCard2"))
                        numberCard2 = numberCard21;
                    else if (child.getId().equals("numberCard3"))
                        numberCard3 = numberCard31;
                    else if (child.getId().equals("numberCard4"))
                        numberCard4 = numberCard41;
                }
            }
//            System.out.println("active1: " + activeDeactive11);
        }
        /*activeDeactive1 = activeDeactive11;
        activeDeactive2 = activeDeactive21;
        activeDeactive3 = activeDeactive31;
        activeDeactive4 = activeDeactive41;
        numberCard1 = numberCard11;
        numberCard2 = numberCard21;
        numberCard3 = numberCard31;
        numberCard4 = numberCard41;*/
    }


    public void active1() {
        if (isActives.get(card1))
            isActives.put(card1, false);
        else isActives.put(card1, true);
        setAll();
    }

    public void active2() {
        if (isActives.get(card2))
            isActives.put(card2, false);
        else isActives.put(card2, true);
        setAll();
    }

    public void active3() {
        if (isActives.get(card3))
            isActives.put(card3, false);
        else isActives.put(card3, true);
        setAll();
    }

    public void active4() {
        if (isActives.get(card4))
            isActives.put(card4, false);
        else isActives.put(card4, true);
        setAll();
    }

    public void goNextPage() {
        if (firstCardNumber + 4 >= totalCardsNumber) return;
        firstCardNumber += 4;
        setOnlyImagesAndCards();
//        setActivesAndCurrencies();
        setAll();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        setOnlyImagesAndCards();
//        setActivesAndCurrencies();
        setAll();
    }

    private void setImagesAndCards() {
        images = ShopController.getInstance(null).getImages(firstCardNumber);
        image1 = setImageView(images.get(0), 83);
        image2 = setImageView(images.get(1), 283);
        image3 = setImageView(images.get(2), 483);
        image4 = setImageView(images.get(3), 683);
        card1 = ShopController.getInstance(null).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(null).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(null).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(null).getCards(firstCardNumber).get(3);

    }

    private ImageView setImageView(Image image, int x) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(140);
        imageView.setFitHeight(204);
        imageView.setX(x);
        imageView.setY(64);
        return imageView;
    }

    private void setOnlyImagesAndCards() {
        images = ShopController.getInstance(null).getImages(firstCardNumber);
        removeImages();
        image1.setImage(images.get(0));
        image2.setImage(images.get(1));
        image3.setImage(images.get(2));
        image4.setImage(images.get(3));
        addImages();
        card1 = ShopController.getInstance(null).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(null).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(null).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(null).getCards(firstCardNumber).get(3);
    }

    private void removeImages() {
        root.getChildren().remove(image1);
        root.getChildren().remove(image2);
        root.getChildren().remove(image3);
        root.getChildren().remove(image4);
    }

    private void addImages() {
        root.getChildren().add(image1);
        root.getChildren().add(image2);
        root.getChildren().add(image3);
        root.getChildren().add(image4);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeActivesAndCurrencies();
        setImagesAndCards();
        setActivesAndCurrencies();
        setStatics();
    }

    private void setStatics() {
        numberCard11 = numberCard1;
        numberCard21 = numberCard2;
        numberCard31 = numberCard3;
        numberCard41 = numberCard4;
        activeDeactive11 = activeDeactive1;
        activeDeactive21 = activeDeactive2;
        activeDeactive31 = activeDeactive3;
        activeDeactive41 = activeDeactive4;
    }

    private void initializeActivesAndCurrencies() {
        ArrayList<Card> allCards = new ArrayList<>();
        Collections.addAll(allCards, MonsterCard.values());
        Collections.addAll(allCards, SpellCard.values());
        Collections.addAll(allCards, TrapCard.values());
        for (Card card : allCards) {
            isActives.put(card, true);
            currencies.put(card, 10);
        }
    }

    public void setAll() {
//        System.out.println("in set all: " + numberCard1);
//        setActivesAndCurrencies();
    }

    private void setActivesAndCurrencies() {
        new Thread(() -> {
            while (true) {
                if(numberCard1==null){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Platform.runLater(() -> {
                    numberCard1.setText(String.valueOf(currencies.get(card1)));
                    numberCard2.setText(String.valueOf(currencies.get(card2)));
                    numberCard3.setText(String.valueOf(currencies.get(card3)));
                    numberCard4.setText(String.valueOf(currencies.get(card4)));
                    if (isActives.get(card1))
                        activeDeactive1.setText("Active");
                    else
                        activeDeactive1.setText("Inactive");
                    if (isActives.get(card2))
                        activeDeactive2.setText("Active");
                    else
                        activeDeactive2.setText("Inactive");
                    if (isActives.get(card3))
                        activeDeactive3.setText("Active");
                    else
                        activeDeactive3.setText("Inactive");
                    if (isActives.get(card4))
                        activeDeactive4.setText("Active");
                    else
                        activeDeactive4.setText("Inactive");
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void set1() {
        if (textField1.getText().matches("\\d+")) {
            currencies.put(card1, Integer.parseInt(textField1.getText()));
            setAll();
        }
        textField1.setText("");
    }

    public void set2() {
        if (textField2.getText().matches("\\d+")) {
            currencies.put(card2, Integer.parseInt(textField2.getText()));
            setAll();
        }
        textField2.setText("");
    }

    public void set3() {
        if (textField3.getText().matches("\\d+")) {
            currencies.put(card3, Integer.parseInt(textField3.getText()));
            setAll();
        }
        textField3.setText("");
    }

    public void set4() {
        if (textField4.getText().matches("\\d+")) {
            currencies.put(card4, Integer.parseInt(textField4.getText()));
            setAll();
        }
        textField4.setText("");
    }

    public static int getCurrency(Card card) {
        /*if (!currencies.containsKey(card))
            System.out.println(card);*/
        return currencies.get(card);
    }

    public static Boolean getActiveState(Card card) {
        return isActives.get(card);
    }

    public void decreaseCurrency(Card card) {
//        System.out.println("numberCard1: " + numberCard1);
        int currentCurrency = currencies.get(card);
        currencies.put(card, currentCurrency - 1);
//        setActivesAndCurrencies();
    }

}
