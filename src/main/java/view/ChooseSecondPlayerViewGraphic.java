package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseSecondPlayerViewGraphic extends Application implements Initializable {
    private static Stage stage;
    static ChooseSecondPlayerViewGraphic instance = null;
    private static User user;
    public ComboBox<String> comboBox = new ComboBox<>();

    public static ChooseSecondPlayerViewGraphic getInstance() {
        if (instance == null) instance = new ChooseSecondPlayerViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ChooseSecondPlayerViewGraphic.user = user;
    }

    public void start(Stage stage) throws Exception {
        ChooseSecondPlayerViewGraphic.stage = stage;
        URL url = getClass().getResource("/ChooseSecondPlayer.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        fillTheComboBoxWithUsers();
        stage.show();
    }


    public void onPlayerGame(MouseEvent event) throws Exception {
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName("do");
        ChooseDuelView.getInstance().start(stage);
    }

    public void playWithAi(MouseEvent event) throws Exception {
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName("@AI@");
        ChooseDuelView.getInstance().start(stage);
    }

    public void goBack(MouseEvent event) throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }

    public void fillTheComboBoxWithUsers() {
        ArrayList<String> choices = new ArrayList<>();
        for (User user : User.getAllUsers()) {
            String username = user.getUsername();
            if (!username.equals(ChooseSecondPlayerViewGraphic.user.getUsername())) {
                choices.add(username);
            }
        }
        comboBox.getItems().addAll(choices);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTheComboBoxWithUsers();
    }
}
