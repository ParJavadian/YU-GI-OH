package view;


import client.Main;
import controller.ImportExportUserController;
import controller.ShopController;
import controller.SoundController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopViewGraphic extends Application implements Initializable {

    private static Stage stage;
    private static ShopViewGraphic instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    private static Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;
    private static AnchorPane root;
    private static Image image;
    @FXML
    private Label money, youHave1, youHave2, youHave3, youHave4, price1, price2, price3, price4;
    @FXML
    private Label currency1, currency2, currency3, currency4;
    @FXML
    private ImageView inactive1, inactive2, inactive3, inactive4;
    @FXML
    private Button buyButton1, buyButton2, buyButton3, buyButton4;

    public static ShopViewGraphic getInstance() {
        if (instance == null) instance = new ShopViewGraphic();
        return instance;
    }

    public ShopViewGraphic() {
        totalCardsNumber = ShopController.getInstance().getTotalCardsNumber();
    }

    public void setCurrentUser(User user) {
        ShopViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ShopViewGraphic.stage = stage;
        URL url = getClass().getResource("/ShopMenu.fxml");
        root = FXMLLoader.load(url);
        setImagesAndCards();
        addImages();
        dragAndDrop();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void goBack() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }

    public void buy1() {
        user.decreaseMoney(card1.getPrice());
        user.getAllCards().add(card1);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        try {
            Main.dataOutputStream.writeUTF("decreaseCurrency!@#" + card1.getName());
            Main.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printText("Card added successfully");
    }

    public void buy2() {
        user.decreaseMoney(card2.getPrice());
        user.getAllCards().add(card2);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        try {
            Main.dataOutputStream.writeUTF("decreaseCurrency!@#" + card2.getName());
            Main.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printText("Card added successfully");
    }

    public void buy3() {
        user.decreaseMoney(card3.getPrice());
        user.getAllCards().add(card3);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        try {
            Main.dataOutputStream.writeUTF("decreaseCurrency!@#" + card3.getName());
            Main.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printText("Card added successfully");
    }

    public void buy4() {
        user.decreaseMoney(card4.getPrice());
        user.getAllCards().add(card4);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        try {
            Main.dataOutputStream.writeUTF("decreaseCurrency!@#" + card4.getName());
            Main.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printText("Card added successfully");
    }

    private void startCurrencyUpdateThread() {
        new Thread(() -> {
            while (true) {
                String currencyString1 = getCurrencyTextFromServer(card1);
                String currencyString2 = getCurrencyTextFromServer(card2);
                String currencyString3 = getCurrencyTextFromServer(card3);
                String currencyString4 = getCurrencyTextFromServer(card4);
                Boolean activationState1 = getActiveStateFromServer(card1);
                Boolean activationState2 = getActiveStateFromServer(card2);
                Boolean activationState3 = getActiveStateFromServer(card3);
                Boolean activationState4 = getActiveStateFromServer(card4);
                Platform.runLater(() -> {
                    buyButton1.setDisable(card1 != null && (card1.getPrice() > user.getMoney()
                            || !activationState1
                            || Integer.parseInt(currencyString1) == 0));
                    buyButton2.setDisable(card2 != null && (card2.getPrice() > user.getMoney()
                            || !activationState2
                            || Integer.parseInt(currencyString2) == 0));
                    buyButton3.setDisable(card3 != null && (card3.getPrice() > user.getMoney()
                            || !activationState3
                            || Integer.parseInt(currencyString3) == 0));
                    buyButton4.setDisable(card4 != null && (card4.getPrice() > user.getMoney()
                            || !activationState4
                            || Integer.parseInt(currencyString4) == 0));
                    currency1.setText(currencyString1);
                    currency2.setText(currencyString2);
                    currency3.setText(currencyString3);
                    currency4.setText(currencyString4);
                    if (!activationState1)
                        inactive1.toFront();
                    else
                        inactive1.toBack();
                    if (!activationState2)
                        inactive2.toFront();
                    else
                        inactive2.toBack();
                    if (!activationState3)
                        inactive3.toFront();
                    else
                        inactive3.toBack();
                    if (!activationState4)
                        inactive4.toFront();
                    else
                        inactive4.toBack();
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void goNextPage() {
        if (firstCardNumber + 4 >= totalCardsNumber) return;
        firstCardNumber += 4;
        setOnlyImagesAndCards();
        setInStocksAndPrices();
        setAll();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        setOnlyImagesAndCards();
        setInStocksAndPrices();
        setAll();
    }

    private void setImagesAndCards() {
        images = ShopController.getInstance().getImages(firstCardNumber);
        image1 = setImageView(images.get(0), 83);
        image2 = setImageView(images.get(1), 283);
        image3 = setImageView(images.get(2), 483);
        image4 = setImageView(images.get(3), 683);
        card1 = ShopController.getInstance().getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance().getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance().getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance().getCards(firstCardNumber).get(3);

    }


    private void dragAndDrop() {
        image1.setOnDragDetected(event -> {
            Dragboard dragboard = image1.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(image1.getImage());
            dragboard.setContent(clipboardContent);
            if (image1.getImage() != null) image = image1.getImage();
            event.consume();
        });

        image2.setOnDragDetected(event -> {
            Dragboard dragboard = image2.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(image2.getImage());
            dragboard.setContent(clipboardContent);
            if (image2.getImage() != null) image = image2.getImage();
            event.consume();
        });

        image3.setOnDragDetected(event -> {
            Dragboard dragboard = image3.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(image3.getImage());
            dragboard.setContent(clipboardContent);
            if (image3.getImage() != null) image = image3.getImage();
            event.consume();
        });

        image4.setOnDragDetected(event -> {
            Dragboard dragboard = image4.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(image4.getImage());
            dragboard.setContent(clipboardContent);
            if (image4.getImage() != null) image = image4.getImage();
            event.consume();
        });

    }

    public void onDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasImage()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        }
    }

    public void onDragDropped() {
        if (image == null) {
            return;
        }
        if (image1.getImage().equals(image)) {
            buy1();
        }
        if (image2.getImage().equals(image)) {
            buy2();
        }
        if (image3.getImage().equals(image)) {
            buy3();
        }
        if (image4.getImage().equals(image)) {
            buy4();
        }
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
        images = ShopController.getInstance().getImages(firstCardNumber);
        removeImages();
        image1.setImage(images.get(0));
        image2.setImage(images.get(1));
        image3.setImage(images.get(2));
        image4.setImage(images.get(3));
        addImages();
        card1 = ShopController.getInstance().getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance().getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance().getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance().getCards(firstCardNumber).get(3);
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
        setImagesAndCards();
        setAll();
    }

    public void setAll() {
        money.setText(String.valueOf(user.getMoney()));
        setInStocksAndPrices();
        startCurrencyUpdateThread();
    }

    private void setInStocksAndPrices() {
        youHave1.setText(getTextForInStock(card1));
        youHave2.setText(getTextForInStock(card2));
        youHave3.setText(getTextForInStock(card3));
        youHave4.setText(getTextForInStock(card4));
        price1.setText(String.valueOf(card1.getPrice()));
        price2.setText(String.valueOf(card2.getPrice()));
        price3.setText(String.valueOf(card3.getPrice()));
        price4.setText(String.valueOf(card4.getPrice()));
    }

    private String getCurrencyTextFromServer(Card card) {
        try {
            Main.dataOutputStream.writeUTF("getCurrency!@#" + card.getName());
            Main.dataOutputStream.flush();
            return Main.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Boolean getActiveStateFromServer(Card card) {
        try {
            Main.dataOutputStream.writeUTF("getActiveState!@#" + card.getName());
            Main.dataOutputStream.flush();
            String result = Main.dataInputStream.readUTF();
            return Boolean.valueOf(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTextForInStock(Card card) {
        if (user.getCountOfCardInAllCards(card) > 1)
            return "You have " + user.getCountOfCardInAllCards(card) + " cards of this type";
        else if (user.getCountOfCardInAllCards(card) == 1)
            return "You have " + user.getCountOfCardInAllCards(card) + " card of this type";
        else
            return "";
    }

    public void printText(String output) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, output, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }


}