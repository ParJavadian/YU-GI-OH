package view;

import controller.DuelController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Card;
import model.MonsterCard;
import model.User;

import java.lang.reflect.Method;
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
    private static ArrayList<Image> images = new ArrayList<>();
    private static AnchorPane root;
    public static ImageView imageView1Monster1, imageView1Monster2, imageView1Monster3, imageView1Monster4, imageView1Monster5;
    public static ImageView imageView2Monster1, imageView2Monster2, imageView2Monster3, imageView2Monster4, imageView2Monster5;
    public static ImageView imageView1SpellAndTrap1, imageView1SpellAndTrap2, imageView1SpellAndTrap3, imageView1SpellAndTrap4, imageView1SpellAndTrap5;
    public static ImageView imageView2SpellAndTrap1, imageView2SpellAndTrap2, imageView2SpellAndTrap3, imageView2SpellAndTrap4, imageView2SpellAndTrap5;
    public static ImageView imageView1hand1, imageView1hand2, imageView1hand3, imageView1hand4, imageView1hand5, imageView1hand6;
    public static ImageView imageView2hand1, imageView2hand2, imageView2hand3, imageView2hand4, imageView2hand5, imageView2hand6;
    public static ImageView imageView1FieldZone, imageView2FieldZone, imageView1Graveyard, imageView2Graveyard;
    public static ImageView rivalProfile = new ImageView();
    public static ImageView playerProfile = new ImageView();
    public ImageView selectedCard;
    @FXML
    public Label playerUsername, playerNickname, playerLifePoint, rivalUsername, rivalNickname, rivalLifePoint;
    @FXML
    private ProgressBar rivalProgressBar, playerProgressBar;
    private static Label description, attack, defence;
    private static ImageView selectedCardImageView;

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
        URL url = getClass().getResource("/GameMenu.fxml");
        root = FXMLLoader.load(url);
        setImagesAndCards();
        setBar();
        setImageViewForProfile();
        addAllImages();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//        setSelectedCard();
        startMainNoCardSelected();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBar();
        setImageViewForProfile();
        if (player != null && rival != null) {
            if (playerLifePoint != null) playerLifePoint.setText(String.valueOf(player.getLifePoint()));
            if (rivalLifePoint != null) rivalLifePoint.setText(String.valueOf(rival.getLifePoint()));
            if (playerNickname != null) playerNickname.setText(player.getNickname());
            if (rivalNickname != null) rivalNickname.setText(rival.getNickname());
            if (playerUsername != null) playerUsername.setText(player.getUsername());
            if (rivalUsername != null) rivalUsername.setText(rival.getUsername());
        }
        if (duelController != null) {
            setImagesAndCards();
            addAllImages();
        }
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
        setCard();
    }

    private ImageView setImageView(Image image, int x, int y) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(49);
        imageView.setFitHeight(71);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    private void setImageViewForProfile() {
        URL url = getClass().getResource("/images/profiles/profile (" + player.getProfileNumber() + ").png");
        Image image = new Image(String.valueOf(url));
        playerProfile = new ImageView(image);
        playerProfile.setFitWidth(50);
        playerProfile.setFitHeight(50);
        playerProfile.setX(228);
        playerProfile.setY(442);
        playerProfile.setImage(image);
        if (root != null && !root.getChildren().contains(playerProfile))
            root.getChildren().add(playerProfile);
        url = getClass().getResource("/images/profiles/profile (" + rival.getProfileNumber() + ").png");
        image = new Image(String.valueOf(url));
        rivalProfile = new ImageView(image);
        rivalProfile.setFitWidth(50);
        rivalProfile.setFitHeight(50);
        rivalProfile.setX(737);
        rivalProfile.setY(112);
        rivalProfile.setImage(image);
        if (root != null && !root.getChildren().contains(rivalProfile))
            root.getChildren().add(rivalProfile);
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
        addLabel(description);
        addLabel(attack);
        addLabel(defence);
        addImagesView(selectedCardImageView);
    }

    private void addImagesView(ImageView imageView) {
        if (root != null) {
            if (imageView != null && !root.getChildren().contains(imageView))
                root.getChildren().add(imageView);
        }
    }

    private void addLabel(Label label) {
        if (root != null) {
            if (label != null && !root.getChildren().contains(label))
                root.getChildren().add(label);
        }
    }


    public void pauseMenu() {

    }

    public void updateBoard() {
        setImagesAndCards();
        addAllImages();
    }

    public void startMainNoCardSelected() throws Exception {
        setOnClickSelected(imageView1Monster1, 5, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster2, 3, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster3, 1, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster4, 2, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster5, 4, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));

        setOnClickSelected(imageView2Monster1, 4, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster2, 2, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster3, 1, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster4, 3, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster5, 5, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));

        setOnClickSelected(imageView1SpellAndTrap1,5, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap2,3, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap3,1, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap4,2, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap5,4, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));

        setOnClickSelected(imageView2SpellAndTrap1,4, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainARivalSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap2,2, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainARivalSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap3,1, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainARivalSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap4,3, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainARivalSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap5,5, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainARivalSpellOrTrapSelected", ImageView.class));

        setOnClickSelected(imageView1hand1, 1, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand2, 2, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand3, 3, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand4, 4, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand5, 5, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand6, 6, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));

        //todo select card opponent hand nadarim. benevisam? ya kolan nemikhad?
        setOnClickSelected(imageView2hand1, 1, DuelController.class.getMethod("selectCardOpponentHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView2hand2, 2, DuelController.class.getMethod("selectCardOpponentHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView2hand3, 3, DuelController.class.getMethod("selectCardOpponentHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView2hand4, 4, DuelController.class.getMethod("selectCardOpponentHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView2hand5, 5, DuelController.class.getMethod("selectCardOpponentHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView2hand6, 6, DuelController.class.getMethod("selectCardOpponentHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));

        //todo number ro gozashtam 1. chi kar konam?
        setOnClickSelected(imageView1FieldZone, 1, DuelController.class.getMethod("selectCardPlayerFieldZone"), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView2FieldZone, 1, DuelController.class.getMethod("selectCardOpponentFieldZone"), this.getClass().getMethod("startMainAHandSelected", ImageView.class));

        //todo number ro gozashtam 1. chi kar konam? va show graveyard darim na select graveyard. bezanam?
        setOnClickSelected(imageView1Graveyard, 1, DuelController.class.getMethod("selectPlayerGraveyard"), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1Graveyard, 1, DuelController.class.getMethod("selectRivalGraveyard"), this.getClass().getMethod("startMainAHandSelected", ImageView.class));

        //        setOnClickSelected(imageView1hand1,duelController.selectCardPlayerHand(1));

        /*
        try {
            setOnClickSelected(imageView1hand1, duelController.selectCardPlayerHand(1));
            setOnClickSelected(imageView1hand2);
            setOnClickSelected(imageView1hand3);
            setOnClickSelected(imageView1hand4);
            setOnClickSelected(imageView1hand5);
            setOnClickSelected(imageView1hand6);

            setOnClickSelected(imageView1Monster1);
            setOnClickSelected(imageView1Monster2);
            setOnClickSelected(imageView1Monster3);
            setOnClickSelected(imageView1Monster4);
            setOnClickSelected(imageView1Monster5);
            setOnClickSelected(imageView2Monster1);
            setOnClickSelected(imageView2Monster2);
            setOnClickSelected(imageView2Monster3);
            setOnClickSelected(imageView2Monster4);
            setOnClickSelected(imageView2Monster5);

            setOnClickSelected(imageView1SpellAndTrap1);
            setOnClickSelected(imageView1SpellAndTrap2);
            setOnClickSelected(imageView1SpellAndTrap3);
            setOnClickSelected(imageView1SpellAndTrap4);
            setOnClickSelected(imageView1SpellAndTrap5);
            setOnClickSelected(imageView2SpellAndTrap1);
            setOnClickSelected(imageView2SpellAndTrap2);
            setOnClickSelected(imageView2SpellAndTrap3);
            setOnClickSelected(imageView2SpellAndTrap4);
            setOnClickSelected(imageView2SpellAndTrap5);

            setOnClickSelected(imageView1FieldZone);
            setOnClickSelected(imageView2FieldZone);*/
    }


    private void setOnClickSelected(ImageView imageView, int number, Method duelControllerMethod, Method thisMethod) {
        if (imageView == null) return;
        Image unknown = new Image("images/Cards/Unknown.jpg");
        imageView.setOnMouseClicked(event -> {
            if (!imageView.getImage().equals(unknown))
                showCardDetails(Card.getCardByImage(imageView.getImage()));
            if (selectedCard != null && selectedCard.getEffect() != null)
                selectedCard.setEffect(null);
            imageView.setEffect(new DropShadow());
            try {
                duelControllerMethod.invoke(duelController, number);
                thisMethod.invoke(this, imageView);
                selectedCard = imageView;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void showCardDetails(Card card) {
        selectedCardImageView.setImage(card.getImage());
        description.setText("Description:\n" + card.getDescription());
        if (card instanceof MonsterCard) {
            MonsterCard monsterCard = (MonsterCard) card;
            attack.setText("Attack: " + monsterCard.getAttack());
            defence.setText("Defence: " + monsterCard.getDefence());
        }
    }

    public void startMainAHandSelected(ImageView imageView) throws Exception {
        setOnClickSelected(imageView1hand2, 1, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));

    }

    public void startMainAPlayerMonsterSelected(ImageView imageView) {

    }

    public void doNothing(ImageView imageView) {

    }

    public void startMainAPlayerSpellOrTrapSelected(ImageView imageView){

    }

    public void startMainARivalSpellOrTrapSelected(ImageView imageView){

    }

    public void setBar() { //todo harja lifepoint taghyir kard bayad update beshe
        if (rivalProgressBar != null && rival != null)
            rivalProgressBar.setProgress((double) player.getLifePoint() / 8000);
        if (playerProgressBar != null && rival != null)
            playerProgressBar.setProgress((double) rival.getLifePoint() / 8000);
    }

    private void setCard() {
        description = new Label();
        description.setLayoutX(12);
        description.setLayoutY(338);
        description.setPrefWidth(167);
        description.setPrefHeight(120);
        description.setTextFill(Color.WHITE);
        description.setAlignment(Pos.TOP_LEFT);
        description.setFont(Font.font("Agency FB", 14));
        description.setWrapText(true);
        attack = new Label();
        attack.setLayoutX(12);
        attack.setLayoutY(457);
        attack.setPrefWidth(149);
        attack.setPrefHeight(15);
        attack.setTextFill(Color.WHITE);
        attack.setFont(Font.font("Agency FB", 14));
        defence = new Label();
        defence.setLayoutX(12);
        defence.setLayoutY(471);
        defence.setPrefWidth(149);
        defence.setPrefHeight(15);
        defence.setTextFill(Color.WHITE);
        defence.setFont(Font.font("Agency FB", 14));
        selectedCardImageView = new ImageView();
        selectedCardImageView.setY(128);
        selectedCardImageView.setX(29);
        selectedCardImageView.setFitHeight(202);
        selectedCardImageView.setFitWidth(184);
        selectedCardImageView.setPickOnBounds(true);
        selectedCardImageView.setPreserveRatio(true);
    }

}
