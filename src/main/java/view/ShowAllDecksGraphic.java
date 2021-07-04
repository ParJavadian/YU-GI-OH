/*
package view;

import controller.ScoreBoardControllerGraphic;
import controller.ShowAllDecksController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllDecksGraphic extends Application implements Initializable {
    private static Stage stage;
    private static ShowAllDecksGraphic instance = null;
    private static User user;
    @FXML
    Label label = new Label();

    public static ShowAllDecksGraphic getInstance() {
        if (instance == null) instance = new ShowAllDecksGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {ShowAllDecksGraphic.user = user;}

    @Override
    public void start(Stage stage) throws Exception {
        ShowAllDecksGraphic.stage = stage;
        URL url = getClass().getResource("/ShowAllDecks.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(user);
        ShowAllDecksController.setUser(user);
        label.setText(ShowAllDecksController.getInstance().showAllDecks());
    }

    public void goBack(MouseEvent event) throws Exception{
        DeckViewGraphic.getInstance().start(stage);
    }
}
*/
