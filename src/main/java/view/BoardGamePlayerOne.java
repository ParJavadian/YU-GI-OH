package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardGamePlayerOne extends Application implements Initializable {
    private static Stage stage;
    public static User player;
    public static  User rival;
    private static BoardGamePlayerOne instance = null;
    public Label chatBox;
    public Label rivalLifePoint;
    public Label playerLifePoint;
    public int numberOfRounds;
//    private static ArrayList<Image> imageOf1Monsters = new ArrayList<>(4);
//    private static ArrayList<Image> imageOf2Monsters = new ArrayList<>(4);
    private static ArrayList<Image> images = new ArrayList<>();
    private static AnchorPane root;
    public static ImageView imageView1Monster1, imageView1Monster2, imageView1Monster3, imageView1Monster4,imageView1Monster5;
    public static ImageView imageView2Monster1, imageView2Monster2, imageView2Monster3, imageView2Monster4,imageView2Monster5;
    public static ImageView imageView1SpellAndTrap1, imageView1SpellAndTrap2, imageView1SpellAndTrap3, imageView1SpellAndTrap4,imageView1SpellAndTrap5;
    public static ImageView imageView2SpellAndTrap1, imageView2SpellAndTrap2, imageView2SpellAndTrap3, imageView2SpellAndTrap4,imageView2SpellAndTrap5;
    public static ImageView imageView1hand1,imageView1hand2,imageView1hand3,imageView1hand4,imageView1hand5,imageView1hand6;
    public static ImageView imageView2hand1,imageView2hand2,imageView2hand3,imageView2hand4,imageView2hand5,imageView2hand6;

    public void setPlayer(User player) {
        BoardGamePlayerOne.player = player;
    }

    public void setRival(User rival) {
        BoardGamePlayerOne.rival = rival;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public static BoardGamePlayerOne getInstance() {
        if (instance == null) instance = new BoardGamePlayerOne();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BoardGamePlayerOne.stage = stage;
        URL url = getClass().getResource("/BoardGamePlayerOne.fxml");
        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (playerLifePoint != null && rivalLifePoint != null && player != null && rival != null) {
            playerLifePoint.setText(String.valueOf(player.getLifePoint()));
            rivalLifePoint.setText(String.valueOf(rival.getLifePoint()));
        }
    }

    private void setImagesAndCards() {
//        images = ShopController.getInstance(user).getImages(firstCardNumber);
        if (images.get(0) != null) imageView1Monster1 = setImageViewMonsterAndSpellAndTrap(images.get(0), 312,321);
        if (images.get(1) != null) imageView1Monster2 = setImageViewMonsterAndSpellAndTrap(images.get(1), 391,321);
        if (images.get(2) != null) imageView1Monster3 = setImageViewMonsterAndSpellAndTrap(images.get(2), 474 ,321);
        if (images.get(3) != null) imageView1Monster4 = setImageViewMonsterAndSpellAndTrap(images.get(3), 557,321);
        if (images.get(4) != null) imageView1Monster5 = setImageViewMonsterAndSpellAndTrap(images.get(4), 640,321);
        if (images.get(5) != null) imageView2Monster1 = setImageViewMonsterAndSpellAndTrap(images.get(5), 312,198);
        if (images.get(6) != null) imageView2Monster2 = setImageViewMonsterAndSpellAndTrap(images.get(6), 391,198);
        if (images.get(7) != null) imageView2Monster3 = setImageViewMonsterAndSpellAndTrap(images.get(7), 474,198);
        if (images.get(8) != null) imageView2Monster4 = setImageViewMonsterAndSpellAndTrap(images.get(8), 557,198);
        if (images.get(9) != null) imageView2Monster5 = setImageViewMonsterAndSpellAndTrap(images.get(9), 640,198);
        if (images.get(10) != null) imageView1SpellAndTrap1 = setImageViewMonsterAndSpellAndTrap(images.get(10), 312,424);
        if (images.get(11) != null) imageView1SpellAndTrap2 = setImageViewMonsterAndSpellAndTrap(images.get(11), 391,424);
        if (images.get(12) != null) imageView1SpellAndTrap3 = setImageViewMonsterAndSpellAndTrap(images.get(12), 474,424);
        if (images.get(13) != null) imageView1SpellAndTrap4 = setImageViewMonsterAndSpellAndTrap(images.get(13), 557,424);
        if (images.get(14) != null) imageView1SpellAndTrap5 = setImageViewMonsterAndSpellAndTrap(images.get(14), 640,424);
        if (images.get(15) != null) imageView2SpellAndTrap1 = setImageViewMonsterAndSpellAndTrap(images.get(15), 312,96);
        if (images.get(16) != null) imageView2SpellAndTrap2 = setImageViewMonsterAndSpellAndTrap(images.get(16), 391,96);
        if (images.get(17) != null) imageView2SpellAndTrap3 = setImageViewMonsterAndSpellAndTrap(images.get(17), 474,96);
        if (images.get(18) != null) imageView2SpellAndTrap4 = setImageViewMonsterAndSpellAndTrap(images.get(18), 557,96);
        if (images.get(19) != null) imageView2SpellAndTrap5 = setImageViewMonsterAndSpellAndTrap(images.get(19), 640,96);
        if (images.get(20) != null) imageView1hand1 = setImageViewHand(images.get(20),331,-16);
        if (images.get(21) != null) imageView1hand2 = setImageViewHand(images.get(21),387,-16);
        if (images.get(22) != null) imageView1hand3 = setImageViewHand(images.get(22),443,-16);
        if (images.get(23) != null) imageView1hand4 = setImageViewHand(images.get(23),499,-16);
        if (images.get(24) != null) imageView1hand5 = setImageViewHand(images.get(24),555,-16);
        if (images.get(25) != null) imageView1hand6 = setImageViewHand(images.get(25),611,-16);
        if (images.get(26) != null) imageView2hand1 = setImageViewHand(images.get(26),331,-16);
        if (images.get(27) != null) imageView2hand2 = setImageViewHand(images.get(27),387,-16);
        if (images.get(28) != null) imageView2hand3 = setImageViewHand(images.get(28),443,-16);
        if (images.get(29) != null) imageView2hand4 = setImageViewHand(images.get(29),499,-16);
        if (images.get(30) != null) imageView2hand5 = setImageViewHand(images.get(30),555,-16);
        if (images.get(31) != null) imageView2hand6 = setImageViewHand(images.get(31),611,-16);

//        card1 = ShopController.getInstance(user).getCards(firstCardNumber).get(0);
//        card2 = ShopController.getInstance(user).getCards(firstCardNumber).get(1);
//        card3 = ShopController.getInstance(user).getCards(firstCardNumber).get(2);
//        card4 = ShopController.getInstance(user).getCards(firstCardNumber).get(3);
    }

    private ImageView setImageViewMonsterAndSpellAndTrap(Image image, int x, int y) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(72);
        imageView.setFitHeight(88);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    private ImageView setImageViewHand(Image image, int x, int y) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(49);
        imageView.setFitHeight(71);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    private void addImagesView(ImageView imageView) {
        root.getChildren().add(imageView); //todo check shavad
//        root.getChildren().add(image2);
//        root.getChildren().add(image3);
//        root.getChildren().add(image4);
    }


    public void pauseMenu(MouseEvent event) {

    }
}
