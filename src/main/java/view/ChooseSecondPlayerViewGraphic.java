package view;

import com.sun.security.ntlm.Server;
import controller.ChooseSecondPlayerControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import server.ServerController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseSecondPlayerViewGraphic extends Application implements Initializable{
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

    public void initialize(URL location, ResourceBundle resources) {
        fillTheComboBoxWithUsers();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ChooseSecondPlayerViewGraphic.stage = primaryStage;
        URL url = getClass().getResource("/ChooseSecondPlayer.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void twoPlayerGame(){
        try {
            ChooseSecondPlayerControllerGraphic.twoPlayerGame(user,stage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playWithAi() throws Exception {
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName("@AI@");
        ChooseDuelView.getInstance().start(stage);
    }

    public void goBack() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }


    public void fillTheComboBoxWithUsers() {
        ArrayList<String> choices = new ArrayList<>();
        System.out.println("all usera = " + ServerController.getLoggedInUsers());
        for (User user : ServerController.loggedInUsers.values()) {
            String username = user.getUsername();
            if (!username.equals(ChooseSecondPlayerViewGraphic.user.getUsername()) && !username.equals("@AI@")) {
                choices.add(username);
            }
        }
        comboBox.getItems().addAll(choices);
    }

    public void sendInvitation(MouseEvent event) {
        try {
            if (User.getUserByUsername(comboBox.getValue()) != null)
        ChooseSecondPlayerControllerGraphic.sendInvitation(user, User.getUserByUsername(comboBox.getValue()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
