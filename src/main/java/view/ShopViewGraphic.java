package view;


import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopViewGraphic extends Application {

    private static Stage stage;
    private static ShopViewGraphic instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    Card card1, card2, card3, card4;
    public static ImageView image1, image2, image3, image4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;
    private static AnchorPane root;

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
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void goBack() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }

    public void buy1() {

    }

    public void buy2() {

    }

    public void buy3() {

    }

    public void buy4() {

    }

    public void goNextPage() {
        if (firstCardNumber + 4 >= totalCardsNumber) return;
        firstCardNumber += 4;
        setOnlyImagesAndCards();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        setOnlyImagesAndCards();
    }

    private void setImagesAndCards() {
        images = ShopController.getInstance(user).getImages(firstCardNumber);
//        image1.setImage(images.get(0));
        image1 = new ImageView(images.get(0));
        image1.setFitWidth(140);
        image1.setFitHeight(204);
        image1.setX(83);
        image1.setY(64);
        image2 = new ImageView(images.get(1));
        image2.setFitWidth(140);
        image2.setFitHeight(204);
        image2.setX(283);
        image2.setY(64);
        image3 = new ImageView(images.get(2));
        image3.setFitWidth(140);
        image3.setFitHeight(204);
        image3.setX(483);
        image3.setY(64);
        image4 = new ImageView(images.get(3));
        image4.setFitWidth(140);
        image4.setFitHeight(204);
        image4.setX(683);
        image4.setY(64);
        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);
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
}
