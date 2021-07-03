package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class DeckViewGraphic extends Application {
    private static Stage stage;
    private static DeckViewGraphic instance = null;
    private static User user;

    public static DeckViewGraphic getInstance() {
        if (instance == null) instance = new DeckViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        DeckViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        DeckViewGraphic.stage = stage;
        URL url = getClass().getResource("/DeckView.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }


    public void showAllDecksAndSetActiveDeck(MouseEvent event) throws Exception {
        ShowAllDecksGraphic.getInstance().setCurrentUser(user);
        ShowAllDecksGraphic.getInstance().start(stage);
    }

    public void editAndDeleteDeck(MouseEvent event) {
    }

    public void createDeck(MouseEvent event) throws Exception {

        CreateNewDeckView.getInstance().setCurrentUser(user);//todo befrest to controller
        CreateNewDeckView.getInstance().setCurrentDeck(user.getActiveDeck());
        CreateNewDeckView.getInstance().start(stage);

    }

    public void goBack(MouseEvent event) throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }


}
