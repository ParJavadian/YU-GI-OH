package view;

import controller.ChooseDuelControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChooseDuelView extends Application {
    private static Stage stage;
    static ChooseDuelView instance = null;
    private static User playerOne;
    private static String playerTwoName;

    public static ChooseDuelView getInstance() {
        if (instance == null) instance = new ChooseDuelView();
        return instance;
    }

    public void setCurrentUser(User user) {

        ChooseDuelView.playerOne = user;
    }

    public void setPlayerTwoName(String playerTwoName) {

        ChooseDuelView.playerTwoName = playerTwoName;
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
        startNewGame(3);
    }

    public void oneRoundGame(MouseEvent event) throws Exception{
        startNewGame(1);
    }

    public void goBack(MouseEvent event) throws Exception {
        ChooseDuelControllerGraphic.goBack(stage);
    }


    public void startNewGame(int numberOfRounds) throws Exception{
        if (playerTwoName.equals("@AI@")){
            RockPaperScissorAIView.getInstance().setCurrentUser(playerOne);
            RockPaperScissorAIView.getInstance().setNumberOfRounds(numberOfRounds);
            RockPaperScissorAIView.getInstance().start(stage);
        }
        else {
            RockPaperScissorView.getInstance().setCurrentUser(playerOne);
            RockPaperScissorView.getInstance().setNumberOfRounds(numberOfRounds);
            RockPaperScissorView.getInstance().setPlayerTwoName(playerTwoName);
            RockPaperScissorView.getInstance().start(stage);
        }
    }


}
