/*
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class RockPaperScissorView extends Application {
    private static Stage stage;
    static RockPaperScissorView instance = null;
    private static User playerOne;
    private static String playerTwoName;

    public static RockPaperScissorView getInstance() {
        if (instance == null) instance = new RockPaperScissorView();
        return instance;
    }

    public void setCurrentUser(User user) {
        RockPaperScissorView.playerOne = user;
    }

    public void setPlayerTwoName(String playerTwoName) {
        RockPaperScissorView.playerTwoName = playerTwoName;
    }

    public void start(Stage stage) throws Exception {
        RockPaperScissorView.stage = stage;
        URL url = getClass().getResource("/RockPaperScissor.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void scissorOne(MouseEvent event) throws Exception{
        RockPaperScissorPlayerTwoView.getInstance().setCurrentUser(playerOne);
        RockPaperScissorPlayerTwoView.getInstance().setPreviousHand("scissor");
        RockPaperScissorPlayerTwoView.getInstance().start(stage);
    }

    public void paperOne(MouseEvent event) throws Exception{
        RockPaperScissorPlayerTwoView.getInstance().setCurrentUser(playerOne);
        RockPaperScissorPlayerTwoView.getInstance().setPreviousHand("paper");
        RockPaperScissorPlayerTwoView.getInstance().start(stage);
    }

    public void rockOne(MouseEvent event) throws Exception{
        RockPaperScissorPlayerTwoView.getInstance().setCurrentUser(playerOne);
        RockPaperScissorPlayerTwoView.getInstance().setPreviousHand("rock");
        RockPaperScissorPlayerTwoView.getInstance().start(stage);
    }

}
*/
