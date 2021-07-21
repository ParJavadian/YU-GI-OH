package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class RockPaperScissorPlayerTwoView extends Application implements Initializable {
    private static String previousHand;
    private static Stage stage;
    static RockPaperScissorPlayerTwoView instance = null;
    @FXML
    private Label firstPlayerLabel,secondPlayerLabel;
    private static User loginOne;
    private static User loginTwo;
    private static int numberOfRounds;

    public static RockPaperScissorPlayerTwoView getInstance() {
        if (instance == null) instance = new RockPaperScissorPlayerTwoView();
        return instance;
    }

    public void setCurrentUser(User user) {
        RockPaperScissorPlayerTwoView.loginOne = user;
    }

    public void setLoginTwo(User user){RockPaperScissorPlayerTwoView.loginTwo = user; }

    public void setPreviousHand(String previousHand) {
        RockPaperScissorPlayerTwoView.previousHand = previousHand;
    }

    public void setNumberOfRounds(int numberOfRounds) {RockPaperScissorPlayerTwoView.numberOfRounds = numberOfRounds; }

    public void start(Stage stage) throws Exception {
        RockPaperScissorPlayerTwoView.stage = stage;
        URL url = getClass().getResource("/RockPaperScissorPlayerTwo.fxml");
        Parent root = FXMLLoader.load(url);
        initLabels();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void rockTwo() throws Exception{
        findWinner(previousHand,"rock");
    }

    public void paperTwo() throws Exception{
        findWinner(previousHand,"paper");
    }

    public void scissorTwo() throws Exception{
        findWinner(previousHand,"scissor");
    }


    public void findWinner(String handPlayerOne, String handPlayerTwo) throws Exception {
        if (handPlayerOne != null) {
            if (handPlayerOne.equals(handPlayerTwo)){
                PrintTextEqualRPS();
                RockPaperScissorView.getInstance().start(stage);
            }

            if (handPlayerOne.equals("rock")) {
                if (handPlayerTwo.equals("paper")) {
                    printTextInformation(loginTwo.getUsername());
                    startNewGame(loginTwo, loginOne);
                }
                if (handPlayerTwo.equals("scissor")) {
                    printTextInformation(loginOne.getUsername());
                    startNewGame(loginOne, loginTwo);
                }
            }
            if (handPlayerOne.equals("paper")) {
                if (handPlayerTwo.equals("rock")) {
                    printTextInformation(loginOne.getUsername());
                    startNewGame(loginOne, loginTwo);
                }
                if (handPlayerTwo.equals("scissor")) {
                    printTextInformation(loginTwo.getUsername());
                    startNewGame(loginTwo, loginOne);
                }
            }
            if (handPlayerOne.equals("scissor")) {
                if (handPlayerTwo.equals("rock")) {
                    printTextInformation(loginTwo.getUsername());
                    startNewGame(loginTwo, loginOne);
                }
                if (handPlayerTwo.equals("paper")) {
                    printTextInformation(loginOne.getUsername());
                    startNewGame(loginOne, loginTwo);
                }
            }
        }
    }

    public void startNewGame(User firstPlayer, User secondPlayer) throws Exception{
        GameViewGraphic gameViewGraphic = new GameViewGraphic();
        gameViewGraphic.setPlayer(firstPlayer);
        gameViewGraphic.setRival(secondPlayer);
        gameViewGraphic.setNumberOfRounds(numberOfRounds);
        gameViewGraphic.setDuelController();
        gameViewGraphic.start(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabels();
    }

    private void initLabels(){
        if(firstPlayerLabel!=null && secondPlayerLabel!=null && loginOne!=null && loginTwo!=null) {
            firstPlayerLabel.setText(loginOne.getUsername());
            secondPlayerLabel.setText(loginTwo.getUsername());
        }
    }

    public void printTextInformation(String winnerName) {
        String winner = winnerName + " won the Rock paper scissor match and will start the game!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, winner, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    public void PrintTextEqualRPS(){
        String message = "both of you chose the same hand! play again!";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }
}
