package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class RockPaperScissorView extends Application implements Initializable {
    private static Stage stage;
    static RockPaperScissorView instance = null;
    private static User playerOne;
    private static String playerTwoName;
    private static int numberOfRounds;
    @FXML
    private Label firstPlayerLabel,secondPlayerLabel;

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

    public void setNumberOfRounds(int numberOfRounds){RockPaperScissorView.numberOfRounds = numberOfRounds; }

    public void start(Stage stage) throws Exception {
        RockPaperScissorView.stage = stage;
        URL url = getClass().getResource("/RockPaperScissor.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        initLabels();
        stage.setScene(scene);
        stage.show();
    }


    public void scissorOne(MouseEvent event) throws Exception{
        nextHand("scissor");
    }

    public void paperOne(MouseEvent event) throws Exception{
        nextHand("paper");
    }

    public void rockOne(MouseEvent event) throws Exception{
        nextHand("rock");
    }

    public void nextHand(String thisHand) throws Exception{
        RockPaperScissorPlayerTwoView.getInstance().setCurrentUser(playerOne);
        RockPaperScissorPlayerTwoView.getInstance().setLoginTwo(User.getUserByUsername(playerTwoName));
        RockPaperScissorPlayerTwoView.getInstance().setPreviousHand(thisHand);
        RockPaperScissorPlayerTwoView.getInstance().setNumberOfRounds(numberOfRounds);
        RockPaperScissorPlayerTwoView.getInstance().start(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLabels();
    }

    private void initLabels(){
        if(firstPlayerLabel!=null && secondPlayerLabel!=null && playerOne!=null && playerTwoName!=null) {
            firstPlayerLabel.setText(playerOne.getUsername());
            secondPlayerLabel.setText(playerTwoName);
        }
    }
}
