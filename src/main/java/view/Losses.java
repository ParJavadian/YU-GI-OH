package view;

import javafx.application.Application;
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

public class Losses extends Application implements Initializable {

    private static Stage stage;
    static Losses instance = null;
    private static User user;

    public Label threeLossesIR, sevenLossesIR, tenLossesIR;


    public static Losses getInstance() {
        if (instance == null) instance = new Losses();
        return instance;
    }

    public void setCurrentUser(User user) {
        Losses.user = user;
    }


    @Override
    public void start(Stage stage) throws Exception {
        Losses.stage = stage;
        URL url = getClass().getResource("/LossesGift.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void goBack() throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }

    public void goToAchievements(MouseEvent event) throws Exception{
        Achievements.getInstance().setCurrentUser(user);
        Achievements.getInstance().start(stage);
    }

    public void setNumbers(){
        threeLossesIR.setText(String.valueOf(user.getNumberOfThreeLosesInARow()));
        sevenLossesIR.setText(String.valueOf(user.getNumberOfSevenLosesInARow()));
        tenLossesIR.setText(String.valueOf(user.getNumberOfTenLosesInARow()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNumbers();
    }


}
