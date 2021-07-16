package Client.view;


import Server.controller.ImportExportUserController;
import Server.controller.ShopController;
import Client.controller.SoundController;
import javafx.application.Application;
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
    private Button buyButton1, buyButton2, buyButton3, buyButton4;

    public static ShopViewGraphic getInstance() {
        if (instance == null) instance = new ShopViewGraphic();
        return instance;
    }

    public ShopViewGraphic() {
        totalCardsNumber = ShopController.getInstance(user).getTotalCardsNumber();
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
        System.out.println("buy 1");
        user.decreaseMoney(card1.getPrice());
        user.getAllCards().add(card1);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        printText("Card added successfully");
    }

    public void buy2() {
        user.decreaseMoney(card2.getPrice());
        user.getAllCards().add(card2);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        printText("Card added successfully");
    }

    public void buy3() {
        user.decreaseMoney(card3.getPrice());
        user.getAllCards().add(card3);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        printText("Card added successfully");
    }

    public void buy4() {
        user.decreaseMoney(card4.getPrice());
        user.getAllCards().add(card4);
        ImportExportUserController.getInstance().exportAllCards(ShopViewGraphic.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
        printText("Card added successfully");
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
        images = ShopController.getInstance(user).getImages(firstCardNumber);
        image1 = setImageView(images.get(0), 83);
        image2 = setImageView(images.get(1), 283);
        image3 = setImageView(images.get(2), 483);
        image4 = setImageView(images.get(3), 683);
        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);

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

    public void onDragDropped(){
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
        images = ShopController.getInstance(user).getImages(firstCardNumber);
        removeImages();
        image1.setImage(images.get(0));
        image2.setImage(images.get(1));
        image3.setImage(images.get(2));
        image4.setImage(images.get(3));
        addImages();
        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);
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
        buyButton1.setDisable(card1 != null && card1.getPrice() > user.getMoney());
        buyButton2.setDisable(card2 != null && card2.getPrice() > user.getMoney());
        buyButton3.setDisable(card3 != null && card3.getPrice() > user.getMoney());
        buyButton4.setDisable(card4 != null && card4.getPrice() > user.getMoney());
        setInStocksAndPrices();
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