package view;

import controller.DuelController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.*;
import model.Card;
import model.MonsterCard;
import model.User;

import java.awt.image.ImageFilter;
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
    private Phase phase;
    private static ArrayList<Image> images = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<Boolean> conditions = new ArrayList<>();
    private static AnchorPane root;
    public static ImageView imageView1Monster1 = new ImageView(), imageView1Monster2 = new ImageView(), imageView1Monster3 = new ImageView(), imageView1Monster4 = new ImageView(), imageView1Monster5 = new ImageView();
    public static ImageView imageView2Monster1 = new ImageView(), imageView2Monster2 = new ImageView(), imageView2Monster3 = new ImageView(), imageView2Monster4 = new ImageView(), imageView2Monster5 = new ImageView();
    public static ImageView imageView1SpellAndTrap1 = new ImageView(), imageView1SpellAndTrap2 = new ImageView(), imageView1SpellAndTrap3 = new ImageView(), imageView1SpellAndTrap4 = new ImageView(), imageView1SpellAndTrap5 = new ImageView();
    public static ImageView imageView2SpellAndTrap1 = new ImageView(), imageView2SpellAndTrap2 = new ImageView(), imageView2SpellAndTrap3 = new ImageView(), imageView2SpellAndTrap4 = new ImageView(), imageView2SpellAndTrap5 = new ImageView();
    public static ImageView imageView1hand1 = new ImageView(), imageView1hand2 = new ImageView(), imageView1hand3 = new ImageView(), imageView1hand4 = new ImageView(), imageView1hand5 = new ImageView(), imageView1hand6 = new ImageView();
    public static ImageView imageView2hand1 = new ImageView(), imageView2hand2 = new ImageView(), imageView2hand3 = new ImageView(), imageView2hand4 = new ImageView(), imageView2hand5 = new ImageView(), imageView2hand6 = new ImageView();
    public static ImageView imageView1FieldZone = new ImageView(), imageView2FieldZone = new ImageView(), imageView1Graveyard = new ImageView(), imageView2Graveyard = new ImageView();
    private static Card card1Monster1, card1Monster2, card1Monster3, card1Monster4, card1Monster5;
    private static Card card2Monster1, card2Monster2, card2Monster3, card2Monster4, card2Monster5;
    private static Card card1SpellAndTrap1, card1SpellAndTrap2, card1SpellAndTrap3, card1SpellAndTrap4, card1SpellAndTrap5;
    private static Card card2SpellAndTrap1, card2SpellAndTrap2, card2SpellAndTrap3, card2SpellAndTrap4, card2SpellAndTrap5;
    public static Card card1Hand1, card1Hand2, card1Hand3, card1Hand4, card1Hand5, card1Hand6;
    public static Card card2Hand1, card2Hand2, card2Hand3, card2Hand4, card2Hand5, card2Hand6;
    public static Card card1FieldZone, card2FieldZone, card1Graveyard, card2Graveyard;
    public static ImageView rivalProfile = new ImageView();
    public static ImageView playerProfile = new ImageView();
    public ImageView selectedCard;
    @FXML
    public Label playerUsername, playerNickname, playerLifePoint, rivalUsername, rivalNickname, rivalLifePoint;
    @FXML
    private ProgressBar rivalProgressBar, playerProgressBar;
    private static Label description, attack, defence;
    private static ImageView selectedCardImageView;
    public Popup popUpPlayerGraveyard, popUpRivalGraveyard;


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

//        setSelectedCard();
        duelController.goNextPhase();
        startMainNoCardSelected();
        //showPlayerPopUp();
        stage.show();
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
        cards = duelController.getCards();
        conditions = duelController.getConditions();
        setImageView(imageView1Monster1, images.get(0), 324, 329, conditions.get(0));
        setImageView(imageView1Monster2, images.get(1), 403, 329, conditions.get(1));
        setImageView(imageView1Monster3, images.get(2), 486, 329, conditions.get(2));
        setImageView(imageView1Monster4, images.get(3), 569, 329, conditions.get(3));
        setImageView(imageView1Monster5, images.get(4), 652, 329, conditions.get(4));
        setImageView(imageView2Monster1, images.get(5), 324, 206, conditions.get(5));
        setImageView(imageView2Monster2, images.get(6), 403, 206, conditions.get(6));
        setImageView(imageView2Monster3, images.get(7), 486, 206, conditions.get(7));
        setImageView(imageView2Monster4, images.get(8), 569, 206, conditions.get(8));
        setImageView(imageView2Monster5, images.get(9), 652, 206, conditions.get(9));
        setImageView(imageView1SpellAndTrap1, images.get(10), 324, 432, conditions.get(10));
        setImageView(imageView1SpellAndTrap2, images.get(11), 403, 432, conditions.get(11));
        setImageView(imageView1SpellAndTrap3, images.get(12), 486, 432, conditions.get(12));
        setImageView(imageView1SpellAndTrap4, images.get(13), 569, 432, conditions.get(13));
        setImageView(imageView1SpellAndTrap5, images.get(14), 652, 432, conditions.get(14));
        setImageView(imageView2SpellAndTrap1, images.get(15), 324, 104, conditions.get(15));
        setImageView(imageView2SpellAndTrap2, images.get(16), 403, 104, conditions.get(16));
        setImageView(imageView2SpellAndTrap3, images.get(17), 486, 104, conditions.get(17));
        setImageView(imageView2SpellAndTrap4, images.get(18), 569, 104, conditions.get(18));
        setImageView(imageView2SpellAndTrap5, images.get(19), 652, 104, conditions.get(19));
        setImageView(imageView1hand1, images.get(20), 331, 538, conditions.get(20));
        setImageView(imageView1hand2, images.get(21), 387, 538, conditions.get(21));
        setImageView(imageView1hand3, images.get(22), 443, 538, conditions.get(22));
        setImageView(imageView1hand4, images.get(23), 499, 538, conditions.get(23));
        setImageView(imageView1hand5, images.get(24), 555, 538, conditions.get(24));
        setImageView(imageView1hand6, images.get(25), 611, 538, conditions.get(25));
        setImageView(imageView2hand1, images.get(26), 331, -16, conditions.get(26));
        setImageView(imageView2hand2, images.get(27), 387, -16, conditions.get(27));
        setImageView(imageView2hand3, images.get(28), 443, -16, conditions.get(28));
        setImageView(imageView2hand4, images.get(29), 499, -16, conditions.get(29));
        setImageView(imageView2hand5, images.get(30), 555, -16, conditions.get(30));
        setImageView(imageView2hand6, images.get(31), 611, -16, conditions.get(31));
        setImageView(imageView1FieldZone, images.get(32), 232, 324, conditions.get(32));
        setImageView(imageView2FieldZone, images.get(33), 740, 205, conditions.get(33));
        setImageView(imageView1Graveyard, images.get(34), 750, 328, conditions.get(34));
        setImageView(imageView2Graveyard, images.get(35), 245, 213, conditions.get(35));
        card1Monster1 = cards.get(0);
        card1Monster2 = cards.get(1);
        card1Monster3 = cards.get(2);
        card1Monster4 = cards.get(3);
        card1Monster5 = cards.get(4);
        card2Monster1 = cards.get(5);
        card2Monster2 = cards.get(6);
        card2Monster3 = cards.get(7);
        card2Monster4 = cards.get(8);
        card2Monster5 = cards.get(9);
        card1SpellAndTrap1 = cards.get(10);
        card1SpellAndTrap2 = cards.get(11);
        card1SpellAndTrap3 = cards.get(12);
        card1SpellAndTrap4 = cards.get(13);
        card1SpellAndTrap5 = cards.get(14);
        card2SpellAndTrap1 = cards.get(15);
        card2SpellAndTrap2 = cards.get(16);
        card2SpellAndTrap3 = cards.get(17);
        card2SpellAndTrap4 = cards.get(18);
        card2SpellAndTrap5 = cards.get(19);
        card1Hand1 = cards.get(20);
        card1Hand2 = cards.get(21);
        card1Hand3 = cards.get(22);
        card1Hand4 = cards.get(23);
        card1Hand5 = cards.get(24);
        card1Hand6 = cards.get(25);
        card2Hand1 = cards.get(26);
        card2Hand2 = cards.get(27);
        card2Hand3 = cards.get(28);
        card2Hand4 = cards.get(29);
        card2Hand5 = cards.get(30);
        card2Hand6 = cards.get(31);
        card1FieldZone = cards.get(32);
        card2FieldZone = cards.get(33);
        card1Graveyard = cards.get(34);
        card2Graveyard = cards.get(35);
        setCardDetailsPart();
    }

    private void setImageView(ImageView imageView, Image image, int x, int y, Boolean condition) {
        imageView.setImage(image);
        imageView.setFitWidth(49);
        imageView.setFitHeight(71);
        imageView.setX(x);
        imageView.setY(y);
        if (condition != null && condition.equals(true))
            imageView.setRotate(90);
        else
            imageView.setRotate(0);
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

        System.out.println(popUpPlayerGraveyard);

        //ArrayList<Card> playerGraveyard =
        ArrayList<Image> images = duelController.getBoard();
        ArrayList<Card> playerGraveyard = duelController.getCards();
//        ArrayList<Card> playerGraveyard = (ArrayList<Card>) player.getBoard().getCardsInGraveyard();
        //todo image haye card haro az parmida begir
//        ScrollPane scrollPane = new ScrollPane();

        //scrollPane.set(100);
        //scrollPane.setLayoutX(500);
        //scrollPane.setLayoutX(200);
        popUpPlayerGraveyard = new Popup();
        System.out.println(popUpPlayerGraveyard);
        AnchorPane anchorPane = new AnchorPane();
        popUpPlayerGraveyard.setX(10);
        popUpPlayerGraveyard.setY(10);
        popUpPlayerGraveyard.setWidth(300);
        popUpPlayerGraveyard.setHeight(300);
        anchorPane.setPrefWidth(200);
        anchorPane.setPrefHeight(200);
        anchorPane.setLayoutX(50);
        anchorPane.setLayoutY(50);
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(images.get(i));
            imageView.setFitWidth(70);
            imageView.setFitHeight(102);
            imageView.setX(220);
            imageView.setY(20 * (i + 1) + 102 * (i));
            anchorPane.getChildren().add(imageView);
        }
        popUpPlayerGraveyard.getContent().add(anchorPane);
        popUpPlayerGraveyard.requestFocus();
        System.out.println(anchorPane.getChildren());
        System.out.println(popUpPlayerGraveyard.getContent());
        ;
        popUpPlayerGraveyard.show(stage);
        popUpPlayerGraveyard.requestFocus();
        System.out.println(popUpPlayerGraveyard.isShowing());


    }

    public void updateBoard() {
        setImagesAndCards();
        addAllImages();
    }

    public void startMainNoCardSelected() throws Exception {
        /*setOnClickSelected(imageView1Monster1, 5, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class), DuelController.class.getMethod("summonMonster"));
        setOnClickSelected(imageView1Monster2, 3, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster3, 1, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster4, 2, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));
        setOnClickSelected(imageView1Monster5, 4, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainAPlayerMonsterSelected", ImageView.class));

        setOnClickSelected(imageView2Monster1, 4, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster2, 2, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster3, 1, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster4, 3, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2Monster5, 5, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));

        setOnClickSelected(imageView1SpellAndTrap1, 5, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap2, 3, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap3, 1, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap4, 2, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));
        setOnClickSelected(imageView1SpellAndTrap5, 4, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainAPlayerSpellOrTrapSelected", ImageView.class));

        setOnClickSelected(imageView2SpellAndTrap1, 4, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap2, 2, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap3, 1, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap4, 3, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
        setOnClickSelected(imageView2SpellAndTrap5, 5, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("doNothing", ImageView.class));
*/
        setOnClickSelected(imageView1hand1, player, 1, 20, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("summonMonster"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("preSet"));
        setOnClickSelected(imageView1hand2, player, 2, 21, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("summonMonster"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("preSet"));
        setOnClickSelected(imageView1hand3, player, 3, 22, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("summonMonster"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("preSet"));
        setOnClickSelected(imageView1hand4, player, 4, 23, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("summonMonster"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("preSet"));
        setOnClickSelected(imageView1hand5, player, 5, 24, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("summonMonster"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("preSet"));
        setOnClickSelected(imageView1hand6, player, 6, 25, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("summonMonster"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("preSet"));
        setOnClickSelected(imageView1Monster1, player, 5, 0, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("flipSummon"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("changePosition"));
        setOnClickSelected(imageView1Monster2, player, 3, 1, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("flipSummon"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("changePosition"));
        setOnClickSelected(imageView1Monster3, player, 1, 2, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("flipSummon"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("changePosition"));
        setOnClickSelected(imageView1Monster4, player, 2, 3, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("flipSummon"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("changePosition"));
        setOnClickSelected(imageView1Monster5, player, 4, 4, DuelController.class.getMethod("selectCardPlayerMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("flipSummon"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("changePosition"));
        setOnClickSelected(imageView2Monster1, rival, 4, 5, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2Monster1, rival, 2, 6, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2Monster1, rival, 1, 7, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2Monster1, rival, 3, 8, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2Monster1, rival, 5, 9, DuelController.class.getMethod("selectCardOpponentMonsterZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1SpellAndTrap1, player, 5, 10, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1SpellAndTrap2, player, 3, 11, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1SpellAndTrap3, player, 1, 12, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1SpellAndTrap4, player, 2, 13, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1SpellAndTrap5, player, 4, 14, DuelController.class.getMethod("selectCardPlayerTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("activateSpell"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2SpellAndTrap1, rival, 4, 15, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2SpellAndTrap2, rival, 2, 16, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2SpellAndTrap3, rival, 1, 17, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2SpellAndTrap4, rival, 3, 18, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2SpellAndTrap5, rival, 5, 19, DuelController.class.getMethod("selectCardOpponentTrapAndSpellZone", int.class), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1FieldZone, player, 1, 32, DuelController.class.getMethod("selectCardPlayerFieldZone"), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2FieldZone, rival, 1, 33, DuelController.class.getMethod("selectCardOpponentFieldZone"), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView1Graveyard, player, 1, 34, DuelController.class.getMethod("showGraveyard"), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));
        setOnClickSelected(imageView2Graveyard, rival, 1, 35, DuelController.class.getMethod("showGraveyard"), this.getClass().getMethod("startMainNoCardSelected"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"), DuelController.class.getMethod("doNothing"));

/*setOnClickSelected(imageView1hand2, 2, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand3, 3, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand4, 4, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand5, 5, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
        setOnClickSelected(imageView1hand6, 6, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
*/
//        setOnClickSelected(imageView1FieldZone, 1, DuelController.class.getMethod("selectCardPlayerFieldZone"), this.getClass().getMethod("startMainPlayerFieldZoneSelected", ImageView.class));
//        setOnClickSelected(imageView2FieldZone, 1, DuelController.class.getMethod("selectCardOpponentFieldZone"), this.getClass().getMethod("startMainOpponentFieldZoneSelected", ImageView.class));

//        //todo number ro gozashtam 1. chi kar konam? va show graveyard darim na select graveyard. bezanam?
//        setOnClickSelected(imageView1Graveyard, 1, DuelController.class.getMethod("selectPlayerGraveyard"), this.getClass().getMethod("startMainAHandSelected", ImageView.class));
//        setOnClickSelected(imageView1Graveyard, 1, DuelController.class.getMethod("selectRivalGraveyard"), this.getClass().getMethod("startMainAHandSelected", ImageView.class));


    }


    private void setOnClickSelected(ImageView imageView, User owner, int number1, int numberForGet, Method onMouseEnteredMethod, Method nextMethod, Method onMouseClickedMethodMonster, Method onMouseClickedMethodSpell, Method onMouseRightClickedMethod) {
        Image unknown = new Image("images/Cards/Unknown.jpg");
        imageView.setOnMouseEntered(event -> {
            if (imageView.getImage() == null) return;
            if (!imageView.getImage().equals(unknown) || owner.equals(player))
                showCardDetails(cards.get(numberForGet));
            else if (imageView.getImage().equals(unknown))
                showUnknownCard();
            if (selectedCard != null && selectedCard.getEffect() != null)
                selectedCard.setEffect(null);
            imageView.setEffect(new DropShadow());
            try {
                onMouseEnteredMethod.invoke(duelController, number1);
                selectedCard = imageView;
                startMainNoCardSelected();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        imageView.setOnMouseExited(event -> {
            if (imageView.getImage() == null) return;
            if (!imageView.getImage().equals(unknown) || owner.equals(player))
                clearCardDetails();
            else if (imageView.getImage().equals(unknown))
                clearCardDetails();
            imageView.setEffect(null);
            try {
                DuelController.class.getMethod("unselectCard").invoke(duelController);
                startMainNoCardSelected();
                selectedCard = null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        imageView.setOnMouseClicked(event -> {
            if (imageView.getImage() == null) return;
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                try {
                    if (cards.get(numberForGet) instanceof MonsterCard)
                        onMouseClickedMethodMonster.invoke(duelController);
                    else
                        onMouseClickedMethodSpell.invoke(duelController);
                    if (!imageView.getImage().equals(unknown)) {
                        clearCardDetails();
                    }
                    imageView.setEffect(null);
                    selectedCard = null;
                    updateBoard();
                    startMainNoCardSelected();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    onMouseRightClickedMethod.invoke(duelController);
                    if (!imageView.getImage().equals(unknown)) {
                        clearCardDetails();
                    }
                    imageView.setEffect(null);
                    selectedCard = null;
                    updateBoard();
                    startMainNoCardSelected();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
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

    public void showUnknownCard() {
        Image unknown = new Image("images/Cards/Unknown.jpg");
        selectedCardImageView.setImage(unknown);
    }

    public void startMainAHandSelected(ImageView imageView) throws Exception {
//        setOnClickSelected(imageView1hand2, 1, DuelController.class.getMethod("selectCardPlayerHand", int.class), this.getClass().getMethod("startMainAHandSelected", ImageView.class));

    }

    public void startMainAPlayerMonsterSelected(ImageView imageView) {

    }

    public void doNothing(ImageView imageView) {

    }

    public void startMainAPlayerSpellOrTrapSelected(ImageView imageView) {

    }

    public void startMainPlayerFieldZoneSelected(ImageView imageView) {

    }

    public void startMainOpponentFieldZoneSelected(ImageView imageView) {

    }

    public void setBar() { //todo harja lifepoint taghyir kard bayad update beshe
        if (rivalProgressBar != null && rival != null)
            rivalProgressBar.setProgress((double) player.getLifePoint() / 8000);
        if (playerProgressBar != null && rival != null)
            playerProgressBar.setProgress((double) rival.getLifePoint() / 8000);
    }

    private void setCardDetailsPart() {
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

    private void clearCardDetails() {
        selectedCardImageView.setImage(null);
        description.setText("");
        attack.setText("");
        defence.setText("");
    }

    //TODO RANGE MOSTATILE PPHASE AVAZ SHE VAGHTI TUSHE
    public void goToBattlePhase(MouseEvent event) {
        if (this.phase.equals(Phase.MAIN_PHASE1)) {
            this.phase = Phase.BATTLE_PHASE;

        } else showErrorNotProperPhaseNavigation("BATTLE_PHASE");
    }

    public void goToMainTwo(MouseEvent event) {
        if (this.phase.equals(Phase.BATTLE_PHASE)) {
            this.phase = Phase.MAIN_PHASE2;
        } else showErrorNotProperPhaseNavigation("Main_Phase2");
    }

    public void goToEndPhase(MouseEvent event) {
        if (this.phase.equals(Phase.MAIN_PHASE1) || this.phase.equals(Phase.BATTLE_PHASE) || this.phase.equals(Phase.MAIN_PHASE2))
            this.phase = Phase.END_PHASE;
    }

    public void showErrorNotProperPhaseNavigation(String phaseNameToEnter) {
        Alert error = new Alert(Alert.AlertType.WARNING);
        error.setHeaderText("Error");
        error.setContentText("You cant go to " + phaseNameToEnter + "from " + this.phase);
        error.showAndWait();
    }

    private void showPlayerPopUp() {
//        System.out.println(popUpPlayerGraveyard);
//
//        //ArrayList<Card> playerGraveyard =
//        ArrayList<Image> images = duelController.getBoard();
//        ArrayList<Card> playerGraveyard = duelController.getCards();
////        ArrayList<Card> playerGraveyard = (ArrayList<Card>) player.getBoard().getCardsInGraveyard();
//        //todo image haye card haro az parmida begir
////        ScrollPane scrollPane = new ScrollPane();
//
//        //scrollPane.set(100);
//        //scrollPane.setLayoutX(500);
//        //scrollPane.setLayoutX(200);
//        popUpPlayerGraveyard = new Popup();
//        System.out.println(popUpPlayerGraveyard);
//        AnchorPane anchorPane = new AnchorPane();
//        popUpPlayerGraveyard.setX(500);
//        popUpPlayerGraveyard.setY(200);
//        popUpPlayerGraveyard.setWidth(300);
//        popUpPlayerGraveyard.setHeight(300);
//        for (int i = 0; i < 6; i++) {
//            ImageView imageView = new ImageView(images.get(i));
//            imageView.setFitWidth(70);
//            imageView.setFitHeight(102);
//            imageView.setX(220);
//            imageView.setY(20 * i + 102 * (i - 1));
//            anchorPane.getChildren().add(imageView);
//        }
//        popUpPlayerGraveyard.getContent().add(anchorPane);
//        System.out.println(anchorPane.getChildren());
//        System.out.println(popUpPlayerGraveyard.getContent());;
//
//
//        popUpPlayerGraveyard.show(stage);
//        System.out.println(popUpPlayerGraveyard.isShowing());


//        for (Card card : playerGraveyard) {
//            ImageView imageView = new ImageView();
//            imageView.setFitWidth(49);
//            imageView.setFitHeight(71);
//            imageView.setX(x);
//            imageView.setY(y);
//            scrollPane.getChildrenUnmodifiable().add(imageView);
//        }


    }

    private void showRivalPopUp() {
        popUpRivalGraveyard = new Popup();
        ArrayList<Card> rivalGraveyard = (ArrayList<Card>) rival.getBoard().getCardsInGraveyard();

    }

}
