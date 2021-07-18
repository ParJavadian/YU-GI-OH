package view;

import client.Main;
import controller.ChooseDuelControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseDuelView extends Application implements Initializable {
    private static Stage stage;
    static ChooseDuelView instance = null;
    private static User playerOne;
    private static String playerTwoName;
    public Label chooseAndWaiting;

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
        stage.setScene(scene);
        stage.show();
    }

    public void threeRoundsGame(){
        chooseAndWaiting.setText("We Are Trying To Find a Rival For You! Please Wait!");
        sendGameRequestToServer(playerOne,3);
        //startNewGame(3); //todo
    }

    public void oneRoundGame(){
        chooseAndWaiting.setText("We Are Trying To Find a Rival For You! Please Wait!");
        sendGameRequestToServer(playerOne,1);
        //startNewGame(1); //todo
    }

    public static synchronized void sendGameRequestToServer(User user,int numberOfRounds) {
        try {
            Main.dataOutputStream.writeUTF("request for game: " + user.getUsername() + " " + numberOfRounds);
            Main.dataOutputStream.flush();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void goBack() throws Exception {
        cancelGameRequestInServer();
        ChooseDuelControllerGraphic.goBack(stage);
        cancelGameRequestInServer();
    }

    public static synchronized void cancelGameRequestInServer() {
        try {
            Main.dataOutputStream.writeUTF("stop waiting " + playerOne.getUsername());
            Main.dataOutputStream.flush();
        } catch (Exception x) {
            x.printStackTrace();
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseAndWaiting.setText("Choose Your Game Round Number");
    }
}
