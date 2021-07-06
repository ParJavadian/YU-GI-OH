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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class RockPaperScissorAIView extends Application implements Initializable {
    private static Stage stage;
    static RockPaperScissorAIView instance = null;
    private static User user;
    private static int numberOfRounds;
    @FXML
    private Label firstPlayerLabel,secondPlayerLabel;

    public static RockPaperScissorAIView getInstance() {
        if (instance == null) instance = new RockPaperScissorAIView();
        return instance;
    }

    public void setCurrentUser(User user) {
        RockPaperScissorAIView.user = user;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        RockPaperScissorAIView.numberOfRounds = numberOfRounds;
    }


    public void start(Stage stage) throws Exception {
        RockPaperScissorAIView.stage = stage;
        URL url = getClass().getResource("/RockPaperScissorAi.fxml");
        Parent root = FXMLLoader.load(url);
        initLabels();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    public void rock(MouseEvent event) throws Exception {
        String winnerName = findWinner("rock",randomHand());
        startNewGame(User.getUserByUsername(winnerName),findSecondPlayer(winnerName));
    }

    public void paper(MouseEvent event) throws Exception{
        String winnerName = findWinner("paper",randomHand());
        startNewGame(User.getUserByUsername(winnerName),findSecondPlayer(winnerName));
    }

    public void scissor(MouseEvent event) throws Exception{
        String winnerName = findWinner("scissor",randomHand());
        startNewGame(User.getUserByUsername(winnerName),findSecondPlayer(winnerName));
    }


    public String randomHand(){
        Random random = new Random();
        int randomNumber =  ((random.nextInt(3) + 1));

        switch (randomNumber) {
            case 1 : return "rock";
            case 2 : return "paper";
            case 3 : return "scissor";
            default: return "";
        }
    }

    public String findWinner(String handPlayerOne, String handPlayerTwo) throws Exception {
        if (handPlayerOne != null) {
            if (handPlayerOne.equals(handPlayerTwo)) {
                PrintTextEqualRPS();
            }

            if (handPlayerOne.equals("rock")) {
                if (handPlayerTwo.equals("paper")) {
                    printTextInformation("Computer");
                    return "@AI@";
                }
                if (handPlayerTwo.equals("scissor")) {
                    printTextInformation(user.getUsername());
                    return user.getUsername();
                }
            }
            if (handPlayerOne.equals("paper")) {
                if (handPlayerTwo.equals("rock")) {
                    printTextInformation(user.getUsername());
                    return user.getUsername();
                }
                if (handPlayerTwo.equals("scissor")) {
                    printTextInformation("Computer");
                    return "@AI@";
                }
            }
            if (handPlayerOne.equals("scissor")) {
                if (handPlayerTwo.equals("rock")) {
                    printTextInformation("Computer");
                    return "@AI@";
                }
                if (handPlayerTwo.equals("paper")) {
                    printTextInformation(user.getUsername());
                    return user.getUsername();
                }
            }
        }
        return "";
    }

    public void startNewGame(User firstPlayer, User secondPlayer) throws Exception{
        if (firstPlayer != null) {
            GameViewGraphic gameViewGraphic = new GameViewGraphic();
//        gameViewGraphic.start(stage);
            gameViewGraphic.setPlayer(firstPlayer);
            gameViewGraphic.setRival(secondPlayer);
            gameViewGraphic.setNumberOfRounds(numberOfRounds);
            gameViewGraphic.setDuelController();
            gameViewGraphic.start(stage);
        }
        else
            RockPaperScissorAIView.getInstance().start(stage);

    }

    public User findSecondPlayer(String firstPlayer){
        if (firstPlayer.equals("@AI@"))
            return user;
        else return User.getUserByUsername("@AI@");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabels();
    }

    private void initLabels(){
        if(firstPlayerLabel!=null && secondPlayerLabel!=null && user!=null) {
            firstPlayerLabel.setText(user.getUsername());
            secondPlayerLabel.setText("computer");
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
