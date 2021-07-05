package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class RockPaperScissorPlayerTwoView extends Application {
    private static String previousHand;
    private static Stage stage;
    static RockPaperScissorPlayerTwoView instance = null;
    private static User user;

    public static RockPaperScissorPlayerTwoView getInstance() {
        if (instance == null) instance = new RockPaperScissorPlayerTwoView();
        return instance;
    }

    public void setCurrentUser(User user) {
        RockPaperScissorPlayerTwoView.user = user;
    }

    public void setPreviousHand(String previousHand) {
        RockPaperScissorPlayerTwoView.previousHand = previousHand;
    }

    public void start(Stage stage) throws Exception {
        RockPaperScissorPlayerTwoView.stage = stage;
        URL url = getClass().getResource("/RockPaperScissorPlayerTwo.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void rockTwo(MouseEvent event) throws Exception{
        findWinner(previousHand,"rock");
    }

    public void paperTwo(MouseEvent event) throws Exception{
        findWinner(previousHand,"paper");
    }

    public void scissorTwo(MouseEvent event) throws Exception{
        findWinner(previousHand,"scissor");
    }


    public void findWinner(String handPlayerOne, String handPlayerTwo) throws Exception {
        if (handPlayerOne != null) {
            if (handPlayerOne.equals(handPlayerTwo)){
                System.out.println("mosavi");
                RockPaperScissorView.getInstance().start(stage);
            }

            if (handPlayerOne.equals("rock")) {
                if (handPlayerTwo.equals("paper"))
                    System.out.println("two");
                if (handPlayerTwo.equals("scissor"))
                    System.out.println("one");
            }
            if (handPlayerOne.equals("paper")) {
                if (handPlayerTwo.equals("rock"))
                    System.out.println("one");
                if (handPlayerTwo.equals("scissor"))
                    System.out.println("two");
            }
            if (handPlayerOne.equals("scissor")) {
                if (handPlayerTwo.equals("rock"))
                    System.out.println("two");
                if (handPlayerTwo.equals("paper"))
                    System.out.println("one");
            }
        }
    }
}
