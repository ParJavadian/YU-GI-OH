package view;

import controller.ProfileControllerGraphic;
import controller.ScoreBoardControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreBoardViewGraphic extends Application implements Initializable {
    private static Stage stage;
    private static ScoreBoardViewGraphic instance = null;
    private static User user;
    @FXML
    TextFlow textFlow = new TextFlow();
//    Label label = new Label();

    public static ScoreBoardViewGraphic getInstance() {
        if (instance == null) instance = new ScoreBoardViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user){
        ScoreBoardViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardViewGraphic.stage = stage;
        URL url = getClass().getResource("/ScoreBoard.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        label.setText(ScoreBoardControllerGraphic.getInstance().showScoreboard(user));
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


//        Text redText = new Text("This is red text...");
//        redText.setFill(Color.RED);
//        Text greenText = new Text("followed by green text");
//        greenText.setFill(Color.GREEN);
//        textFlow.getChildren().addAll(redText, greenText);


        Text text1 = new Text();
        //Text text2 = new Text();




        List<User> allUsers = User.getAllUsers();
        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
//        if (allUsers != null) {
        allUsers.sort(userComparator);
        User previousUser = null;
        int rank = 1;
        //StringBuilder toPrint = new StringBuilder();
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
            text1 = new Text(rank + "-  NickName:" + eachUser.getNickname() + "      Score: " + eachUser.getScore());
            previousUser = eachUser;
            userCounter++;
            if (eachUser.equals(user)) {
                text1.setFill(Color.RED);
            } else {
                text1.setFill(Color.BLUE);
            }
            textFlow.getChildren().add(text1);
        }








//        textFlow.setTextAlignment(TextAlignment.CENTER);
//        textFlow.getChildren().add(new Text("ya hagh"));
                //ScoreBoardControllerGraphic.getInstance().showScoreboard(user);
        //label.setText(ScoreBoardControllerGraphic.getInstance().showScoreboard(user));
    }

    public void goBack() throws Exception {
        ScoreBoardControllerGraphic.goBack(stage);
    }
}
