package view;

import controller.ChooseSecondPlayerControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseSecondPlayerViewGraphic extends Application {
    private static Stage stage;
    static ChooseSecondPlayerViewGraphic instance = null;
    private static User user;

    public static ChooseSecondPlayerViewGraphic getInstance() {
        if (instance == null) instance = new ChooseSecondPlayerViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ChooseSecondPlayerViewGraphic.user = user;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ChooseSecondPlayerViewGraphic.stage = primaryStage;
        URL url = getClass().getResource("/ChooseSecondPlayer.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void twoPlayerGame(){
        try {
            ChooseSecondPlayerControllerGraphic.twoPlayerGame(user,stage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playWithAi() throws Exception {
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName("@AI@");
        ChooseDuelView.getInstance().start(stage);
    }

    public void goBack() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }



}
