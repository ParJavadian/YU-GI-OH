package view;

import client.Main;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatView extends Application implements Initializable {
    private static Stage stage;
    private static ChatView instance = null;
    private static User user;
    @FXML
    public TextField textField;
    @FXML
    private VBox vBox;
    @FXML
    private Label pin, onlineNumber;
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
        setOnlineNumber();
        setMessages();
    }

    private void setMessages() {
        new Thread(() -> {
            while (true) {
                try {
                    Main.dataOutputStream.writeUTF("all messages");
                    Main.dataOutputStream.flush();
                    String answer = Main.dataInputStream.readUTF();
                    Platform.runLater(() -> vBox.getChildren().clear());
                    if (!answer.equals("")) {
                        String[] messages = answer.split("#@!");
                        for (String message : messages) {
                            String[] parts = message.split("!@#");
                            int profileNumber = Integer.parseInt(parts[0]);
                            String nickName = parts[1];
                            String text = parts[2];
                            Image profileImage = new Image("/images/profiles/profile (" + profileNumber + ").png");
                            Platform.runLater(() -> addMessage(profileImage, nickName, text));
                        }
                    }
                    Main.dataOutputStream.writeUTF("get pin");
                    Main.dataOutputStream.flush();
                    String pinText = Main.dataInputStream.readUTF();
                    Platform.runLater(() -> pin.setText(pinText));
                    Thread.sleep(2000);
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }).start();
    }

    private void setOnlineNumber() {
        try {
            socket = new Socket("localhost", 7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Main.dataOutputStream.writeUTF("NumberOfOnlinePeople");
                    Main.dataOutputStream.flush();
                    String result = Main.dataInputStream.readUTF();
                    Platform.runLater(() -> setLabel(result));
                    Thread.sleep(5000);
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }).start();
    }

    private void setLabel(String text) {
        onlineNumber.setText(text);
    }

    private void addMessage(Image profileImage, String nickName, String text) {
        Label label1 = new Label();
        label1.setPrefWidth(452);
        label1.setText(nickName + ":    " + text);
        ImageView imageView = new ImageView(profileImage);
        imageView.mouseTransparentProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal) {
                imageView.setMouseTransparent(false);
            }
        });
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setX(0);
        imageView.setY(0);
        label1.setWrapText(true);
        label1.setGraphic(imageView);
        label1.getGraphic().setOnMouseClicked(event -> {
            Popup popup = new Popup();
            popup.setWidth(400);
            popup.setHeight(200);
            AnchorPane anchorPane = new AnchorPane();
            ImageView profileImageView = new ImageView(profileImage);
            profileImageView.setFitHeight(60);
            profileImageView.setFitWidth(60);
            profileImageView.setX(10);
            profileImageView.setY(10);
            anchorPane.getChildren().add(profileImageView);
            User user = User.getUserByNickname(nickName);
            Label label = new Label();
            label.setText("Username: " + user.getUsername() + "\nNickname: " + user.getNickname() +
                    "\nScore: " + user.getScore());
            label.setWrapText(true);
            label.setPrefWidth(150);
            label.setPrefHeight(120);
            label.setLayoutX(80);
            label.setLayoutY(0);
            label.setStyle("-fx-text-fill: #84e1e1");
            label.setFont(Font.font("Agency FB", 20));
            anchorPane.getChildren().add(label);
            Button button = new Button("OK");
            button.setDefaultButton(true);
            button.setFont(Font.font("Agency FB", 15));
            button.setLayoutX(200);
            button.setLayoutY(100);
            button.setOnAction(event1 -> popup.hide());
            anchorPane.getChildren().add(button);
            anchorPane.setStyle("-fx-background-color: #196666;");
            popup.getContent().add(anchorPane);
            popup.setX(400);
            popup.setY(200);
            if (!popup.isShowing()) {
                popup.show(stage);
            }
        });
        label1.setFont(new Font("Agency FB", 16.5));
        label1.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                try {
                    Main.dataOutputStream.writeUTF("setPin!@#" + label1.getText());
                    Main.dataOutputStream.flush();
                    Main.dataOutputStream.writeUTF("all messages");
                    Main.dataOutputStream.flush();
                    String pinText = Main.dataInputStream.readUTF();
                    pin.setText(pinText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                try {
                    Main.dataOutputStream.writeUTF("deletechat!@#" + label1.getText());
                    Main.dataOutputStream.flush();
                    Main.dataOutputStream.writeUTF("all messages");
                    Main.dataOutputStream.flush();
                    String answer = Main.dataInputStream.readUTF();
                    vBox.getChildren().clear();
                    if (!answer.equals("")) {
                        String[] messages = answer.split("#@!");
                        for (String message : messages) {
                            String[] parts = message.split("!@#");
                            int profileNumber = Integer.parseInt(parts[0]);
                            String nickName2 = parts[1];
                            String text2 = parts[2];
                            Image profileImage2 = new Image("/images/profiles/profile (" + profileNumber + ").png");
                            addMessage(profileImage2, nickName2, text2);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        vBox.getChildren().add(label1);
    }

    public void back() throws Exception {
        MainViewGraphic.getInstance().start(stage);
    }

    public void send() {
        try {
            Main.dataOutputStream.writeUTF("addchat!@#" + textField.getText() + "!@#" + user.getUsername());
            Main.dataOutputStream.flush();
            Main.dataOutputStream.writeUTF("all messages");
            Main.dataOutputStream.flush();
            String answer = Main.dataInputStream.readUTF();
            vBox.getChildren().clear();
            textField.setText("");
            String[] messages = answer.split("#@!");
            for (String message : messages) {
                String[] parts = message.split("!@#");
                int profileNumber = Integer.parseInt(parts[0]);
                String nickName = parts[1];
                String text = parts[2];
                Image profileImage = new Image("/images/profiles/profile (" + profileNumber + ").png");
                addMessage(profileImage, nickName, text);
            }
            Main.dataOutputStream.writeUTF("get pin");
            Main.dataOutputStream.flush();
            String pinText = Main.dataInputStream.readUTF();
            pin.setText(pinText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
