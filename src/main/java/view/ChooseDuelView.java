package view;

import controller.ChooseDuelControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class ChooseDuelView extends Application {
    private static Stage stage;
    static ChooseDuelView instance = null;
    private static User playerOne;
    private static String playerTwoName;
    public Label chooseAndWaiting;
//    @FXML
//    Label chooseAndWaiting;

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        ChooseDuelView.stage = primaryStage;
        URL url = getClass().getResource("/NewDuelMenu.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        chooseAndWaiting.setText("Choose Your Game Round Number");
        stage.setScene(scene);
        stage.show();
    }

    public void threeRoundsGame() throws Exception{
        chooseAndWaiting.setText("We Are Trying To Find a Rival For You! Please Wait!");
        startNewGame(3); //todo
    }

    public void oneRoundGame() throws Exception{
        chooseAndWaiting.setText("We Are Trying To Find a Rival For You! Please Wait!");
        startNewGame(1); //todo
    }

    public void goBack() throws Exception {
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
