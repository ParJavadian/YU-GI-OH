//package view;
//
//import controller.DeckController;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import model.Card;
//import model.Deck;
//import model.User;
//
//import java.net.URL;
//import java.util.ArrayList;
//
//public class RemoveCardFromDeckView {
//    private static Stage stage;
//    private static Deck deck;
//    private static RemoveCardFromDeckView instance = null;
//    private static User user;
//    private static ArrayList<Image> images = new ArrayList<>(4);
//    private static Card card1, card2, card3, card4;
//    public static ImageView image1, image2, image3, image4;
//    private static int totalCardsNumber;
//    private static int firstCardNumber = 0;
//    private static AnchorPane root;
//
//    public static RemoveCardFromDeckView getInstance() {
//        if (instance == null) instance = new RemoveCardFromDeckView();
//        return instance;
//    }
//
//    public void setCurrentUser(User user) {
//        RemoveCardFromDeckView.user = user;
//    }
//
//    public void setCurrentDeck(Deck deck) {
//        RemoveCardFromDeckView.deck = deck;
//    }
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        DeckController.getInstance(user).getUsersCards(deck);
//        AddCardToDeckView.stage = stage;
//        URL url = getClass().getResource("/CreateNewDeck.fxml");
//        root = FXMLLoader.load(url);
//        setImagesAndCards();
//        addImages();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//    }
//}
