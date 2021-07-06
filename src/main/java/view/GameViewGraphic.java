package view;

import controller.DuelController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Card;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameViewGraphic extends Application implements Initializable {
    private static DuelController duelController;
    private static Stage stage;
    public static User player;
    public static User rival;
    private static GameViewGraphic instance = null;
    public Label chatBox;
    public int numberOfRounds;
    //    private static ArrayList<Image> imageOf1Monsters = new ArrayList<>(4);
//    private static ArrayList<Image> imageOf2Monsters = new ArrayList<>(4);
    private static ArrayList<Image> images = new ArrayList<>();
    private static AnchorPane root;
    public static ImageView imageView1Monster1, imageView1Monster2, imageView1Monster3, imageView1Monster4, imageView1Monster5;
    public static ImageView imageView2Monster1, imageView2Monster2, imageView2Monster3, imageView2Monster4, imageView2Monster5;
    public static ImageView imageView1SpellAndTrap1, imageView1SpellAndTrap2, imageView1SpellAndTrap3, imageView1SpellAndTrap4, imageView1SpellAndTrap5;
    public static ImageView imageView2SpellAndTrap1, imageView2SpellAndTrap2, imageView2SpellAndTrap3, imageView2SpellAndTrap4, imageView2SpellAndTrap5;
    public static ImageView imageView1hand1, imageView1hand2, imageView1hand3, imageView1hand4, imageView1hand5, imageView1hand6;
    public static ImageView imageView2hand1, imageView2hand2, imageView2hand3, imageView2hand4, imageView2hand5, imageView2hand6;
    public static ImageView imageView1FieldZone, imageView2FieldZone, imageView1Graveyard, imageView2Graveyard;
    @FXML
    public Label playerUsername, playerNickname, playerLifePoint, rivalUsername, rivalNickname, rivalLifePoint;

    /*public GameViewGraphic(User player,User rival,int numberOfRounds){
        GameViewGraphic.player = player;
        GameViewGraphic.rival = rival;
        this.numberOfRounds = numberOfRounds;
        duelController = new DuelController(player,rival,numberOfRounds);
    }*/
    public void setPlayer(User player) {
        GameViewGraphic.player = player;
    }

    public void setRival(User rival) {
        GameViewGraphic.rival = rival;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public void setDuelController() {
        duelController = new DuelController(player, rival, numberOfRounds);
    }

    /*public static GameViewGraphic getInstance() {
        if (instance == null) instance = new GameViewGraphic();
        return instance;
    }*/

    @Override
    public void start(Stage stage) throws Exception {
        GameViewGraphic.stage = stage;
        URL url = getClass().getResource("/BoardGamePlayerOne.fxml");
        root = FXMLLoader.load(url);
        addAllImages();
        setImagesAndCards();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        startMainNoCardSelected();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (player != null && rival != null) {
            if (playerLifePoint != null) playerLifePoint.setText(String.valueOf(player.getLifePoint()));
            if (rivalLifePoint != null) rivalLifePoint.setText(String.valueOf(rival.getLifePoint()));
            if (playerNickname != null) playerNickname.setText(player.getNickname());
            if (rivalNickname != null) rivalNickname.setText(rival.getNickname());
            if (playerUsername != null) playerUsername.setText(player.getUsername());
            if (rivalUsername != null) rivalUsername.setText(rival.getUsername());
        }
        addAllImages();
        if (duelController != null)
            setImagesAndCards();
    }

    public void setImagesAndCards() {
        images = duelController.getBoard();
        if (images.get(0) != null) imageView1Monster1 = setImageView(images.get(0), 312, 321);
        if (images.get(1) != null) imageView1Monster2 = setImageView(images.get(1), 391, 321);
        if (images.get(2) != null) imageView1Monster3 = setImageView(images.get(2), 474, 321);
        if (images.get(3) != null) imageView1Monster4 = setImageView(images.get(3), 557, 321);
        if (images.get(4) != null) imageView1Monster5 = setImageView(images.get(4), 640, 321);
        if (images.get(5) != null) imageView2Monster1 = setImageView(images.get(5), 312, 198);
        if (images.get(6) != null) imageView2Monster2 = setImageView(images.get(6), 391, 198);
        if (images.get(7) != null) imageView2Monster3 = setImageView(images.get(7), 474, 198);
        if (images.get(8) != null) imageView2Monster4 = setImageView(images.get(8), 557, 198);
        if (images.get(9) != null) imageView2Monster5 = setImageView(images.get(9), 640, 198);
        if (images.get(10) != null) imageView1SpellAndTrap1 = setImageView(images.get(10), 312, 424);
        if (images.get(11) != null) imageView1SpellAndTrap2 = setImageView(images.get(11), 391, 424);
        if (images.get(12) != null) imageView1SpellAndTrap3 = setImageView(images.get(12), 474, 424);
        if (images.get(13) != null) imageView1SpellAndTrap4 = setImageView(images.get(13), 557, 424);
        if (images.get(14) != null) imageView1SpellAndTrap5 = setImageView(images.get(14), 640, 424);
        if (images.get(15) != null) imageView2SpellAndTrap1 = setImageView(images.get(15), 312, 96);
        if (images.get(16) != null) imageView2SpellAndTrap2 = setImageView(images.get(16), 391, 96);
        if (images.get(17) != null) imageView2SpellAndTrap3 = setImageView(images.get(17), 474, 96);
        if (images.get(18) != null) imageView2SpellAndTrap4 = setImageView(images.get(18), 557, 96);
        if (images.get(19) != null) imageView2SpellAndTrap5 = setImageView(images.get(19), 640, 96);
        if (images.get(20) != null) imageView1hand1 = setImageView(images.get(20), 331, 538);
        if (images.get(21) != null) imageView1hand2 = setImageView(images.get(21), 387, 538);
        if (images.get(22) != null) imageView1hand3 = setImageView(images.get(22), 443, 538);
        if (images.get(23) != null) imageView1hand4 = setImageView(images.get(23), 499, 538);
        if (images.get(24) != null) imageView1hand5 = setImageView(images.get(24), 555, 538);
        if (images.get(25) != null) imageView1hand6 = setImageView(images.get(25), 611, 538);
        if (images.get(26) != null) imageView2hand1 = setImageView(images.get(26), 331, -16);
        if (images.get(27) != null) imageView2hand2 = setImageView(images.get(27), 387, -16);
        if (images.get(28) != null) imageView2hand3 = setImageView(images.get(28), 443, -16);
        if (images.get(29) != null) imageView2hand4 = setImageView(images.get(29), 499, -16);
        if (images.get(30) != null) imageView2hand5 = setImageView(images.get(30), 555, -16);
        if (images.get(31) != null) imageView2hand6 = setImageView(images.get(31), 611, -16);
        if (images.get(32) != null) imageView1FieldZone = setImageView(images.get(32), 233, 321);
        if (images.get(33) != null) imageView2FieldZone = setImageView(images.get(33), 738, 205);
        if (images.get(34) != null) imageView1Graveyard = setImageView(images.get(34), 738, 328);
        if (images.get(35) != null) imageView2Graveyard = setImageView(images.get(35), 233, 205);
    }

    private ImageView setImageView(Image image, int x, int y) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(49);
        imageView.setFitHeight(71);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    private void addAllImages() {
        addImagesView(imageView1hand1);
        addImagesView(imageView1hand2);
        addImagesView(imageView1hand3);
        addImagesView(imageView1hand4);
        addImagesView(imageView1hand5);
        addImagesView(imageView1hand6);
        addImagesView(imageView2hand1);
        addImagesView(imageView2hand2);
        addImagesView(imageView2hand3);
        addImagesView(imageView2hand4);
        addImagesView(imageView2hand5);
        addImagesView(imageView2hand6);
        addImagesView(imageView1Monster1);
        addImagesView(imageView1Monster2);
        addImagesView(imageView1Monster3);
        addImagesView(imageView1Monster4);
        addImagesView(imageView1Monster5);
        addImagesView(imageView2Monster1);
        addImagesView(imageView2Monster2);
        addImagesView(imageView2Monster3);
        addImagesView(imageView2Monster4);
        addImagesView(imageView2Monster5);
        addImagesView(imageView1SpellAndTrap1);
        addImagesView(imageView1SpellAndTrap2);
        addImagesView(imageView1SpellAndTrap3);
        addImagesView(imageView1SpellAndTrap4);
        addImagesView(imageView1SpellAndTrap5);
        addImagesView(imageView2SpellAndTrap1);
        addImagesView(imageView2SpellAndTrap2);
        addImagesView(imageView2SpellAndTrap3);
        addImagesView(imageView2SpellAndTrap4);
        addImagesView(imageView2SpellAndTrap5);
        addImagesView(imageView1FieldZone);
        addImagesView(imageView2FieldZone);
        addImagesView(imageView1Graveyard);
        addImagesView(imageView2Graveyard);
    }

    private void addImagesView(ImageView imageView) {
        if (root != null) {
            if (imageView != null && !root.getChildren().contains(imageView))
                root.getChildren().add(imageView);
        }
    }


    public void pauseMenu() {

    }

    public void updateBoard() {
        addAllImages();
        setImagesAndCards();
    }

    public void startMainNoCardSelected() {
        System.out.println(Card.getCardByImage(imageView1hand1.getImage()));
        imageView1hand1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     System.out.println("1");
                                                     showCardDetails(Card.getCardByImage(imageView1hand1.getImage()));
                                                     imageView1hand1.setEffect(new DropShadow());
                                                     startMainAHandSelected(imageView1hand1);
                                                 }
                                             });
        for (Node child : root.getChildren()) {
            System.out.println(child.getId());
            if(child.getId()!=null && child instanceof ImageView && child.getId().equals("imageView1hand1"))
                System.out.println(child.getOnMouseClicked());
        }
    }

    public void showCardDetails(Card card) {

    }

    public void startMainAHandSelected(ImageView imageView){

    }
}
