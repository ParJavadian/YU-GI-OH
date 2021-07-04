package view;

import controller.ChangeNicknameControllerGraphic;
import controller.ChangePasswordControllerGraphic;
import controller.CreateDeckGraphicController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class CreateDeckGraphic extends Application {

    private static Stage stage;
    private static CreateDeckGraphic instance = null;
    private static User user;
    public TextField deckName;


    public static CreateDeckGraphic getInstance() {
        if (instance == null) instance = new CreateDeckGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {CreateDeckGraphic.user = user;}

    @Override
    public void start(Stage stage) throws Exception {
        CreateDeckGraphic.stage = stage;
        URL url = getClass().getResource("/CreateDeck.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void goBack() throws Exception {
        DeckViewGraphic.getInstance().start(stage);
    }

    public void createDeck(){
        try {
            CreateDeckGraphicController.createDeck(user,deckName.getText());
        } catch (Exception e){
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    public static void confirmCreatedDeck () {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Deck created successfully!");
        error.showAndWait();
    }

}
