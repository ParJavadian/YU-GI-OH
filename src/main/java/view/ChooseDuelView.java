package view;

import controller.ChooseDuelControllerGraphic;
import controller.MainControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChooseDuelView extends Application {
    private static Stage stage;
    static ChooseDuelView instance = null;
    private static User user;

    public static ChooseDuelView getInstance() {
        if (instance == null) instance = new ChooseDuelView();
        return instance;
    }

    public void setCurrentUser(User user) {
        ChooseDuelView.user = user;
    }

    public void start(Stage stage) throws Exception {
        ChooseDuelView.stage = stage;
        URL url = getClass().getResource("/NewDuelMenu.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void threeRoundsGame(MouseEvent event) throws Exception{
        RockPaperScissorView.getInstance().setCurrentUser(user);
        RockPaperScissorView.getInstance().start(stage);
    }

    public void oneRoundGame(MouseEvent event) throws Exception{
        RockPaperScissorView.getInstance().setCurrentUser(user);
        RockPaperScissorView.getInstance().start(stage);
    }

    public void goBack(MouseEvent event) throws Exception {
        ChooseDuelControllerGraphic.goBack(stage);
    }
}
