package Client.view;

import Server.controller.ScoreBoardControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ScoreBoardViewGraphic extends Application implements Initializable {
    private static Stage stage;
    private static ScoreBoardViewGraphic instance = null;
    private static User user;
    @FXML
    TextFlow textFlow = new TextFlow();

    public static ScoreBoardViewGraphic getInstance() {
        if (instance == null) instance = new ScoreBoardViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ScoreBoardViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardViewGraphic.stage = stage;
        URL url = getClass().getResource("/ScoreBoard.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text text1;
        ArrayList<User> allUsers = new ArrayList<>(User.getAllUsers());
        while (allUsers.contains(User.getUserByUsername("@AI@"))) {
            allUsers.remove(User.getUserByUsername("@AI@"));
        }
        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
        allUsers.sort(userComparator);
        User previousUser = null;
        int rank = 1;
        int i = 1;
        int userCounter = 0;
        for (User eachUser : allUsers) {
            if (userCounter >= 20) {
                break;
            }
            if (previousUser != null) {
                if (previousUser.getScore() == eachUser.getScore()) {
                    i++;
                } else {
                    rank += i;
                    i = 1;
                }
            }
            text1 = new Text(rank + "-          NickName:" + eachUser.getNickname() + "          Score: " + eachUser.getScore() + "\n");
            previousUser = eachUser;
            userCounter++;

            if (eachUser.equals(user)) {
                text1.setFill(Color.PURPLE);
                Font font1 = Font.font("Agency FB", FontWeight.BOLD, 18);
                text1.setFont(font1);
            } else {
                text1.setFill(Color.BLUE);
                Font font1 = Font.font("Agency FB", FontWeight.NORMAL, 18);
                text1.setFont(font1);
            }
            textFlow.setLineSpacing(1.5);
            textFlow.getChildren().add(text1);
        }
    }

    public void goBack() throws Exception {
        ScoreBoardControllerGraphic.goBack(stage);
    }

    public void refresh() {
    }
}
