package view;

import client.Main;
import server.ServerController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Message;
import model.User;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatView extends Application implements Initializable {
    private static final ArrayList<Label> messages = new ArrayList<>();
    private static Stage stage;
    private static ChatView instance = null;
    private static User user;
    @FXML
    public TextField textField;
    @FXML
    private VBox vBox;
    @FXML
    private Label pin,onlineNumber;
    Socket socket;

    public static ChatView getInstance() {
        if (instance == null) instance = new ChatView();
        return instance;
    }

    public void setCurrentUser(User user) {
        ChatView.user = user;
    }
    @Override
    public void start(Stage stage) throws Exception {
        ChatView.stage = stage;
        URL url = getClass().getResource("/Chat.fxml");
        AnchorPane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*User user1 = new User("man","man","123",true);
        int profileNumber = user1.getRandomProfileNumber();
        user1.setProfileNumber(profileNumber);
        if(user1.getProfileImage()==null){
            URL url = getClass().getResource("/images/profiles/profile (" + profileNumber + ").png");
            user1.setProfileImage(new Image(String.valueOf(url)));
        }
        User user2 = new User("mann","mann","123",true);
        int profileNumber2 = user2.getRandomProfileNumber();
        user2.setProfileNumber(profileNumber2);
        if(user2.getProfileImage()==null){
            URL url = getClass().getResource("/images/profiles/profile (" + profileNumber2 + ").png");
            user2.setProfileImage(new Image(String.valueOf(url)));
        }
        User user3 = new User("mannn","mannn","123",true);
        int profileNumber3 = user3.getRandomProfileNumber();
        user3.setProfileNumber(profileNumber3);
        if(user3.getProfileImage()==null){
            URL url = getClass().getResource("/images/profiles/profile (" + profileNumber3 + ").png");
            user3.setProfileImage(new Image(String.valueOf(url)));
        }
        Message message1 = new Message("hi from user 1",user1);
        Message message2 = new Message("hi from user 2",user2);
        Message message3 = new Message("hi from user 3",user3);
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);*/
        try {
            socket = new Socket("localhost",7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ServerController.showLogins("1");
        new Thread(() -> {
            try {
                Main.dataOutputStream.writeUTF("NumberOfOnlinePeople");
                Main.dataOutputStream.flush();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String result = dataInputStream.readUTF();
                System.out.println("result: " + result);
                onlineNumber.setText(result);
//                ServerController.showLogins("2");
            } catch (IOException x) {
                System.out.println("1");
                x.printStackTrace();
            }
        }).start();
        //TODO get messages from server
        ArrayList<Message> messages = new ArrayList<>();
        for (Message message : messages) {
            addMessage(message);
        }
    }

    private void addMessage(Message message) {
        Label label1 = new Label();
        label1.setPrefWidth(452);
        label1.setText(message.sender.getNickname() + ":    " + message.text);
        ImageView imageView = new ImageView(message.sender.getProfileImage());
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setX(0);
        imageView.setY(0);
        label1.setWrapText(true);
        label1.setGraphic(imageView);
        label1.setFont(new Font("Agency FB",16.5));
        messages.add(label1);
        vBox.getChildren().add(label1);
        /*vBox.setPrefHeight(36 * decks.size());
        scrollPane.setPrefHeight(36 * decks.size());*/
    }

    public void back() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }
}
