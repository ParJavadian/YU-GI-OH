/*
package view;
import controller.ImportExportUserController;
import controller.ShopController;
import controller.SoundController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminPanel extends Application implements Initializable{

    //TODO chon az jayi call nashode in stage esh set nashode
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

    @FXML
    private Label money, price1, price2, price3, price4;


//
//    public AdminPanel() {
//        totalCardsNumber = AdminPanel.getInstance(user).getTotalCardsNumber();
//    }


    public static AdminPanel getInstance() {
        if (instance == null) instance = new AdminPanel();
        return instance;
    }

    public AdminPanel() {
        totalCardsNumber = ShopController.getInstance(user).getTotalCardsNumber();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AdminPanel.stage = stage;
        URL url = getClass().getResource("/AdminPanel.fxml");
        root = FXMLLoader.load(url);
        setImagesAndCards();
        addImages();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void goBack() throws Exception {
        //TODO
    }

    public void active1() {
//        user.decreaseMoney(card1.getPrice());
//        user.getAllCards().add(card1);
//        ImportExportUserController.getInstance().exportAllCards(AdminPanel.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
//        printText("Card added successfully");
    }

    public void active2() {
//        user.decreaseMoney(card2.getPrice());
//        user.getAllCards().add(card2);
//        ImportExportUserController.getInstance().exportAllCards(AdminPanel.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
//        printText("Card added successfully");
    }

    public void active3() {
//        user.decreaseMoney(card3.getPrice());
//        user.getAllCards().add(card3);
//        ImportExportUserController.getInstance().exportAllCards(AdminPanel.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
//        printText("Card added successfully");
    }

    public void active4() {
//        user.decreaseMoney(card4.getPrice());
//        user.getAllCards().add(card4);
//        ImportExportUserController.getInstance().exportAllCards(AdminPanel.user);
        setAll();
        SoundController.getInstance().playWhenBuys();
//        printText("Card added successfully");
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
*/
/*        money.setText(String.valueOf(user.getMoney()));
        activeButton1.setDisable(card1 != null && card1.getPrice() > user.getMoney());
        activeButton2.setDisable(card2 != null && card2.getPrice() > user.getMoney());
        activeButton3.setDisable(card3 != null && card3.getPrice() > user.getMoney());
        activeButton4.setDisable(card4 != null && card4.getPrice() > user.getMoney());*//*

        setInStocksAndPrices();
    }

    private void setInStocksAndPrices() {
        wantedNumber1.setText("number of cards");
        wantedNumber2.setText("number of cards");
        wantedNumber3.setText("number of cards");
        wantedNumber4.setText("number of cards");

    }

}
*/
