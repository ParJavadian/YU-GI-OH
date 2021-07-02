package view;


import controller.ShopController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ArrayList;

public class ShopViewGraphic extends Application {

    private static Stage stage;
    private static ShopViewGraphic instance = null;
    private static User user;
    private static ArrayList<Image> images = new ArrayList<>(4);
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;
    @FXML
    ImageView image4;
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
        stage.setScene(scene);
        stage.show();
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
        images = ShopController.getInstance(user).getImages(firstCardNumber);
        image1 = new ImageView(images.get(0));
        image2 = new ImageView(images.get(1));
        image3 = new ImageView(images.get(2));
        image4 = new ImageView(images.get(3));
    }

    public void goPreviousPage() {
        if (firstCardNumber - 4 < 0) return;
        firstCardNumber -= 4;
        images = ShopController.getInstance(user).getImages(firstCardNumber);
        image1 = new ImageView(images.get(0));
        image2 = new ImageView(images.get(1));
        image3 = new ImageView(images.get(2));
        image4 = new ImageView(images.get(3));
    }
}
