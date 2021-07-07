package view;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Board;
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
    public static User player = User.getUserByUsername("parmida");
    public static User rival = User.getUserByUsername("hamraz");
    private static GameViewGraphic instance = null;
    public Label chatBox;
    public int numberOfRounds;
    private static ArrayList<Image> images = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<Boolean> conditions = new ArrayList<>();
    private static AnchorPane root;
    public static ImageView imageView1Monster1, imageView1Monster2, imageView1Monster3, imageView1Monster4, imageView1Monster5;
    public static ImageView imageView2Monster1, imageView2Monster2, imageView2Monster3, imageView2Monster4, imageView2Monster5;
    public static ImageView imageView1SpellAndTrap1, imageView1SpellAndTrap2, imageView1SpellAndTrap3, imageView1SpellAndTrap4, imageView1SpellAndTrap5;
    public static ImageView imageView2SpellAndTrap1, imageView2SpellAndTrap2, imageView2SpellAndTrap3, imageView2SpellAndTrap4, imageView2SpellAndTrap5;
    public static ImageView imageView1hand1, imageView1hand2, imageView1hand3, imageView1hand4, imageView1hand5, imageView1hand6;
    public static ImageView imageView2hand1, imageView2hand2, imageView2hand3, imageView2hand4, imageView2hand5, imageView2hand6;
    public static ImageView imageView1FieldZone, imageView2FieldZone, imageView1Graveyard, imageView2Graveyard;
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
        duelController.goNextPhase();
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
        cards = duelController.getCards();
        conditions = duelController.getConditions();
        if (images.get(0) != null) imageView1Monster1 = setImageView(images.get(0), 324, 329, conditions.get(0));
        if (images.get(1) != null) imageView1Monster2 = setImageView(images.get(1), 403, 329, conditions.get(1));
        if (images.get(2) != null) imageView1Monster3 = setImageView(images.get(2), 486, 329, conditions.get(2));
        if (images.get(3) != null) imageView1Monster4 = setImageView(images.get(3), 569, 329, conditions.get(3));
        if (images.get(4) != null) imageView1Monster5 = setImageView(images.get(4), 652, 329, conditions.get(4));
        if (images.get(5) != null) imageView2Monster1 = setImageView(images.get(5), 324, 206, conditions.get(5));
        if (images.get(6) != null) imageView2Monster2 = setImageView(images.get(6), 403, 206, conditions.get(6));
        if (images.get(7) != null) imageView2Monster3 = setImageView(images.get(7), 486, 206, conditions.get(7));
        if (images.get(8) != null) imageView2Monster4 = setImageView(images.get(8), 569, 206, conditions.get(8));
        if (images.get(9) != null) imageView2Monster5 = setImageView(images.get(9), 652, 206, conditions.get(9));
        if (images.get(10) != null)
            imageView1SpellAndTrap1 = setImageView(images.get(10), 324, 432, conditions.get(10));
        if (images.get(11) != null)
            imageView1SpellAndTrap2 = setImageView(images.get(11), 403, 432, conditions.get(11));
        if (images.get(12) != null)
            imageView1SpellAndTrap3 = setImageView(images.get(12), 486, 432, conditions.get(12));
        if (images.get(13) != null)
            imageView1SpellAndTrap4 = setImageView(images.get(13), 569, 432, conditions.get(13));
        if (images.get(14) != null)
            imageView1SpellAndTrap5 = setImageView(images.get(14), 652, 432, conditions.get(14));
        if (images.get(15) != null)
            imageView2SpellAndTrap1 = setImageView(images.get(15), 324, 104, conditions.get(15));
        if (images.get(16) != null)
            imageView2SpellAndTrap2 = setImageView(images.get(16), 403, 104, conditions.get(16));
        if (images.get(17) != null)
            imageView2SpellAndTrap3 = setImageView(images.get(17), 486, 104, conditions.get(17));
        if (images.get(18) != null)
            imageView2SpellAndTrap4 = setImageView(images.get(18), 569, 104, conditions.get(18));
        if (images.get(19) != null)
            imageView2SpellAndTrap5 = setImageView(images.get(19), 652, 104, conditions.get(19));
        if (images.get(20) != null) imageView1hand1 = setImageView(images.get(20), 331, 538, conditions.get(20));
        if (images.get(21) != null) imageView1hand2 = setImageView(images.get(21), 387, 538, conditions.get(21));
        if (images.get(22) != null) imageView1hand3 = setImageView(images.get(22), 443, 538, conditions.get(22));
        if (images.get(23) != null) imageView1hand4 = setImageView(images.get(23), 499, 538, conditions.get(23));
        if (images.get(24) != null) imageView1hand5 = setImageView(images.get(24), 555, 538, conditions.get(24));
        if (images.get(25) != null) imageView1hand6 = setImageView(images.get(25), 611, 538, conditions.get(25));
        if (images.get(26) != null) imageView2hand1 = setImageView(images.get(26), 331, -16, conditions.get(26));
        if (images.get(27) != null) imageView2hand2 = setImageView(images.get(27), 387, -16, conditions.get(27));
        if (images.get(28) != null) imageView2hand3 = setImageView(images.get(28), 443, -16, conditions.get(28));
        if (images.get(29) != null) imageView2hand4 = setImageView(images.get(29), 499, -16, conditions.get(29));
        if (images.get(30) != null) imageView2hand5 = setImageView(images.get(30), 555, -16, conditions.get(30));
        if (images.get(31) != null) imageView2hand6 = setImageView(images.get(31), 611, -16, conditions.get(31));
        if (images.get(32) != null) imageView1FieldZone = setImageView(images.get(32), 232, 324, conditions.get(32));
        if (images.get(33) != null) imageView2FieldZone = setImageView(images.get(33), 740, 205, conditions.get(33));
        if (images.get(34) != null) imageView1Graveyard = setImageView(images.get(34), 750, 328, conditions.get(34));
        if (images.get(35) != null) imageView2Graveyard = setImageView(images.get(35), 245, 213, conditions.get(35));
        if (cards.get(0) != null) card1Monster1 = cards.get(0);
        if (cards.get(1) != null) card1Monster2 = cards.get(1);
        if (cards.get(2) != null) card1Monster3 = cards.get(2);
        if (cards.get(3) != null) card1Monster4 = cards.get(3);
        if (cards.get(4) != null) card1Monster5 = cards.get(4);
        if (cards.get(5) != null) card2Monster1 = cards.get(5);
        if (cards.get(6) != null) card2Monster2 = cards.get(6);
        if (cards.get(7) != null) card2Monster3 = cards.get(7);
        if (cards.get(8) != null) card2Monster4 = cards.get(8);
        if (cards.get(9) != null) card2Monster5 = cards.get(9);
        if (cards.get(10) != null) card1SpellAndTrap1 = cards.get(10);
        if (cards.get(11) != null) card1SpellAndTrap2 = cards.get(11);
        if (cards.get(12) != null) card1SpellAndTrap3 = cards.get(12);
        if (cards.get(13) != null) card1SpellAndTrap4 = cards.get(13);
        if (cards.get(14) != null) card1SpellAndTrap5 = cards.get(14);
        if (cards.get(15) != null) card2SpellAndTrap1 = cards.get(15);
        if (cards.get(16) != null) card2SpellAndTrap2 = cards.get(16);
        if (cards.get(17) != null) card2SpellAndTrap3 = cards.get(17);
        if (cards.get(18) != null) card2SpellAndTrap4 = cards.get(18);
        if (cards.get(19) != null) card2SpellAndTrap5 = cards.get(19);
        if (cards.get(20) != null) card1Hand1 = cards.get(20);
        if (cards.get(21) != null) card1Hand2 = cards.get(21);
        if (cards.get(22) != null) card1Hand3 = cards.get(22);
        if (cards.get(23) != null) card1Hand4 = cards.get(23);
        if (cards.get(24) != null) card1Hand5 = cards.get(24);
        if (cards.get(25) != null) card1Hand6 = cards.get(25);
        if (cards.get(26) != null) card2Hand1 = cards.get(26);
        if (cards.get(27) != null) card2Hand2 = cards.get(27);
        if (cards.get(28) != null) card2Hand3 = cards.get(28);
        if (cards.get(29) != null) card2Hand4 = cards.get(29);
        if (cards.get(30) != null) card2Hand5 = cards.get(30);
        if (cards.get(31) != null) card2Hand6 = cards.get(31);
        if (cards.get(32) != null) card1FieldZone = cards.get(32);
        if (cards.get(33) != null) card2FieldZone = cards.get(33);
        if (cards.get(34) != null) card1Graveyard = cards.get(34);
        if (cards.get(35) != null) card2Graveyard = cards.get(35);
        if (images.get(0) != null) imageView1Monster1 = setImageView(images.get(0), 324, 329);
        if (images.get(1) != null) imageView1Monster2 = setImageView(images.get(1), 403, 329);
        if (images.get(2) != null) imageView1Monster3 = setImageView(images.get(2), 486, 329);
        if (images.get(3) != null) imageView1Monster4 = setImageView(images.get(3), 569, 329);
        if (images.get(4) != null) imageView1Monster5 = setImageView(images.get(4), 652, 329);
        if (images.get(5) != null) imageView2Monster1 = setImageView(images.get(5), 324, 206);
        if (images.get(6) != null) imageView2Monster2 = setImageView(images.get(6), 403, 206);
        if (images.get(7) != null) imageView2Monster3 = setImageView(images.get(7), 486, 206);
        if (images.get(8) != null) imageView2Monster4 = setImageView(images.get(8), 569, 206);
        if (images.get(9) != null) imageView2Monster5 = setImageView(images.get(9), 652, 206);
        if (images.get(10) != null) imageView1SpellAndTrap1 = setImageView(images.get(10), 324, 432);
        if (images.get(11) != null) imageView1SpellAndTrap2 = setImageView(images.get(11), 403, 432);
        if (images.get(12) != null) imageView1SpellAndTrap3 = setImageView(images.get(12), 486, 432);
        if (images.get(13) != null) imageView1SpellAndTrap4 = setImageView(images.get(13), 569, 432);
        if (images.get(14) != null) imageView1SpellAndTrap5 = setImageView(images.get(14), 652, 432);
        if (images.get(15) != null) imageView2SpellAndTrap1 = setImageView(images.get(15), 324, 104);
        if (images.get(16) != null) imageView2SpellAndTrap2 = setImageView(images.get(16), 403, 104);
        if (images.get(17) != null) imageView2SpellAndTrap3 = setImageView(images.get(17), 486, 104);
        if (images.get(18) != null) imageView2SpellAndTrap4 = setImageView(images.get(18), 569, 104);
        if (images.get(19) != null) imageView2SpellAndTrap5 = setImageView(images.get(19), 652, 104);
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
        if (images.get(32) != null) imageView1FieldZone = setImageView(images.get(32), 245, 329);
        if (images.get(33) != null) imageView2FieldZone = setImageView(images.get(33), 750, 213);
        if (images.get(34) != null) imageView1Graveyard = setImageView(images.get(34), 750, 336);
        if (images.get(35) != null) imageView2Graveyard = setImageView(images.get(35), 245, 213);
        setCardDetailsPart();
    }

    private ImageView setImageView(Image image, int x, int y, Boolean condition) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(49);
        imageView.setFitHeight(71);
        imageView.setX(x);
        imageView.setY(y);
        if (condition.equals(true))
            imageView.setRotate(90);
        else
            imageView.setRotate(0);
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
        if (imageView == null) return;
        Image unknown = new Image("images/Cards/Unknown.jpg");
        imageView.setOnMouseEntered(event -> {
            if (!imageView.getImage().equals(unknown) || owner.equals(player))
                showCardDetails(cards.get(numberForGet));
            else if (imageView.getImage().equals(unknown))
                showUnknownCard();
            if (selectedCard != null && selectedCard.getEffect() != null)
                selectedCard.setEffect(null);
            imageView.setEffect(new DropShadow());
            try {
                onMouseEnteredMethod.invoke(duelController, number1);
                startMainNoCardSelected();
                selectedCard = imageView;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        imageView.setOnMouseExited(event -> {
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
        System.out.println(card);
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

}
