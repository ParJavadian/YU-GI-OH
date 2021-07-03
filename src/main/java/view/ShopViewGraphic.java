package view;


import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;

public class ShopViewGraphic extends Application {

    private static Stage stage;
    private static ShopViewGraphic instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    @FXML
    ImageView image1, image2, image3, image4;
    Card card1, card2, card3, card4;
    private static int totalCardsNumber;
    private static int firstCardNumber = 0;

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
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        setImagesAndCards();
        stage.setScene(scene);
        System.out.println(image1.getImage() + "\t5");
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
        setImagesAndCards();
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        setImagesAndCards();
    }

    private void setImagesAndCards() {
        images = ShopController.getInstance(user).getImages(firstCardNumber);
//        image1.setImage(images.get(0));
        image1 = new ImageView(images.get(0));
        image2 = new ImageView(images.get(1));
        image3 = new ImageView(images.get(2));
        image4 = new ImageView(images.get(3));
        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);
        System.out.println(image1.getImage());
    }
}
