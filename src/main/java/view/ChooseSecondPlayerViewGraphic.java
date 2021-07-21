package view;

import client.Main;
import controller.ChooseSecondPlayerControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseSecondPlayerViewGraphic extends Application implements Initializable {
    private static Stage stage;
    static ChooseSecondPlayerViewGraphic instance = null;
    private static User user;
    public ComboBox<String> comboBox = new ComboBox<>();
    AnchorPane root;


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
        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void twoPlayerGame() {
        try {
            ChooseSecondPlayerControllerGraphic.twoPlayerGame(user, stage);
        } catch (Exception e) {
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
        ArrayList<String> onlineUsers = new ArrayList<>();
        String usernames = "";
        try {
            Main.dataOutputStream.writeUTF("get online people");
            Main.dataOutputStream.flush();
            usernames = Main.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] allUserNames = usernames.split("#");
        for (String userName : allUserNames) {
            if (!userName.equals(user.getUsername()))
                onlineUsers.add(userName);
        }
        comboBox.getItems().addAll(onlineUsers);
    }

    public void sendInvitation() {
        try {
            if (User.getUserByUsername(comboBox.getValue()) != null)
                ChooseSecondPlayerControllerGraphic.sendInvitation(user, User.getUserByUsername(comboBox.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
