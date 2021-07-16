package Client.view;

import Server.controller.DeckController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Deck;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllDecksViewGraphic extends Application implements Initializable {
    private static final ArrayList<AnchorPane> decks = new ArrayList<>();
    private static final ToggleGroup toggleGroup = new ToggleGroup();
    private static Stage stage;
    private static AllDecksViewGraphic instance = null;
    private static User user;
    @FXML
    public TextField deckName;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;

    public static AllDecksViewGraphic getInstance() {
        if (instance == null) instance = new AllDecksViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        AllDecksViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        AllDecksViewGraphic.stage = stage;
        URL url = getClass().getResource("/AllDecksMenu.fxml");
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Deck> userAllDecks = (ArrayList<Deck>) user.getAllDecks();
        for (Deck deck : userAllDecks) {
            addDeck(deck);
            if (user.getActiveDeck() != null && deck.getDeckName().equals(user.getActiveDeck().getDeckName())) {
                setActiveDeckInitialize(deck);
            }
        }
    }

    private void addDeck(Deck deck) {
        AnchorPane anchorPane1 = new AnchorPane();
        RadioButton radioButton = new RadioButton(deck.getDeckName());
        radioButton.setLayoutX(8);
        radioButton.setTextFill(Color.WHITE);
        radioButton.setOnAction(this::setActiveDeck);
        radioButton.setToggleGroup(toggleGroup);
        radioButton.setMaxWidth(180);
        anchorPane1.getChildren().add(radioButton);
        Label label = new Label();
        label.setText(String.valueOf(deck.getMainSize() + deck.getSideSize()));
        label.setLayoutX(184);
        label.setTextFill(Color.WHITE);
        anchorPane1.getChildren().add(label);
        Button editButton = new Button("Edit Deck");
        editButton.setLayoutX(240);
        editButton.setOnAction(this::editDeck);
        anchorPane1.getChildren().add(editButton);
        Button deleteButton = new Button("Delete Deck");
        deleteButton.setCancelButton(true);
        deleteButton.setLayoutX(310);
        deleteButton.setOnAction(this::deleteDeck);
        deleteButton.setStyle("-fx-background-color: #ffb3b3;");
        anchorPane1.getChildren().add(deleteButton);
        vBox.setSpacing(10);
        decks.add(anchorPane1);
        vBox.getChildren().add(anchorPane1);
        scrollPane.setMaxHeight(400);
        vBox.setPrefHeight(36 * decks.size());
        scrollPane.setPrefHeight(36 * decks.size());
    }


    public void goBack() throws Exception {
        MainViewGraphic.getInstance().setCurrentUser(user);
        MainViewGraphic.getInstance().start(stage);
    }

    public void editDeck(ActionEvent actionEvent) {
        for (AnchorPane anchorPane : decks) {
            Button button = (Button) anchorPane.getChildren().get(2);
            if (button.equals(actionEvent.getSource())) {
                RadioButton radioButton = (RadioButton) anchorPane.getChildren().get(0);
                Deck deck = user.getDeckByName(radioButton.getText());
                RemoveCardFromDeckView.getInstance().setCurrentUser(user);
                RemoveCardFromDeckView.getInstance().setCurrentDeck(deck);
                try {
                    RemoveCardFromDeckView.getInstance().start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteDeck(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to permanently delete this deck?");
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.CANCEL))
            return;
        for (int i = 0; i < decks.size(); i++) {
            AnchorPane anchorPane = decks.get(i);
            Button button = (Button) anchorPane.getChildren().get(3);
            if (button.equals(actionEvent.getSource())) {
                RadioButton radioButton = (RadioButton) anchorPane.getChildren().get(0);
                try {
                    DeckController.getInstance(user).deleteDeck(radioButton.getText());
                } catch (Exception ignored) {

                }
                decks.remove(anchorPane);
                for (int j = 0; j < vBox.getChildren().size(); j++) {
                    AnchorPane anchorPane1 = (AnchorPane) vBox.getChildren().get(j);
                    if (((RadioButton) anchorPane1.getChildren().get(0)).getText().equals(((RadioButton) anchorPane.getChildren().get(0)).getText())) {
                        vBox.getChildren().remove(j);
                        return;
                    }
                }
            }
        }
    }

    public void setActiveDeck(ActionEvent actionEvent) {
        for (AnchorPane anchorPane : decks) {
            RadioButton radioButton = (RadioButton) anchorPane.getChildren().get(0);
            if (radioButton.equals(actionEvent.getSource())) {
                Deck deck = user.getDeckByName(radioButton.getText());
                user.setActiveDeck(deck);
                return;
            }
        }
    }

    private void setActiveDeckInitialize(Deck deck) {
        for (int i = 0; i < decks.size(); i++) {
            AnchorPane anchorPane = (AnchorPane) vBox.getChildren().get(i);
            RadioButton radioButton = (RadioButton) anchorPane.getChildren().get(0);
            if (radioButton.getText().equals(deck.getDeckName())) {
                toggleGroup.getToggles().get(i).setSelected(true);
                toggleGroup.selectToggle(radioButton);
                return;
            }
        }
    }

    public void createNewDeck() {
        if (user.getDeckByName(deckName.getText()) != null)
            printError("deck with name " + deckName.getText() + " already exists");
        else if (deckName.getText().equals(""))
            printError("fill in the box");
        else {
            Deck deck = new Deck(deckName.getText());
            user.addDeck(deck);
            addDeck(deck);
            deckName.clear();
        }
    }

    public void printError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR, text);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.showAndWait();
    }
}
