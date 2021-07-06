package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.Random;

public class RockPaperScissorAIView extends Application {
    private static Stage stage;
    static RockPaperScissorAIView instance = null;
    private static User user;
    private static int numberOfRounds;

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
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void rock(MouseEvent event) throws Exception {
        /*String winnerName = */findWinner("rock",randomHand());
    }

    public void paper(MouseEvent event) throws Exception{
        /*String winnerName = */findWinner("paper",randomHand());
    }

    public void scissor(MouseEvent event) throws Exception{
        /*String winnerName = */findWinner("scissor",randomHand());
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

    public void findWinner(String handPlayerOne, String handPlayerTwo) throws Exception {
        if (handPlayerOne != null) {
            if (handPlayerOne.equals(handPlayerTwo)){
                System.out.println("mosavi");
                RockPaperScissorAIView.getInstance().start(stage);
            }

            if (handPlayerOne.equals("rock")) {
                if (handPlayerTwo.equals("paper")) {
                    System.out.println("ai");
//                    return "@AI@";
                }
                if (handPlayerTwo.equals("scissor")) {
                    System.out.println("yaru");
//                    return user.getUsername();
                }
            }
            if (handPlayerOne.equals("paper")) {
                if (handPlayerTwo.equals("rock"))
                    System.out.println("yaru");
//                    return user.getUsername();
                if (handPlayerTwo.equals("scissor"))
                    System.out.println("ai");
//                    return "@AI@";
            }
            if (handPlayerOne.equals("scissor")) {
                if (handPlayerTwo.equals("rock"))
                    System.out.println("ai");
//                    return "@AI@";
                if (handPlayerTwo.equals("paper"))
                    System.out.println("uaru");
//                    return user.getUsername();
            }
        }
//        return "";
    }
}