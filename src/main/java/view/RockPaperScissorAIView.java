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
    public void rock() throws Exception {
        String winnerName = findWinner();
        startNewGame(User.getUserByUsername(winnerName),findSecondPlayer(winnerName));
    }

    public void paper() throws Exception{
        String winnerName = findWinner();
        startNewGame(User.getUserByUsername(winnerName),findSecondPlayer(winnerName));
    }

    public void scissor() throws Exception{
        String winnerName = findWinner();
        startNewGame(User.getUserByUsername(winnerName),findSecondPlayer(winnerName));
    }

    public String findWinner(){
        printTextInformation(user.getUsername());
        return user.getUsername();
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
}
