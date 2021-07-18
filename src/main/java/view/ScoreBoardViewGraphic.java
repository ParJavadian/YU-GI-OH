package view;

import client.Main;
import controller.ScoreBoardControllerGraphic;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.User;
import server.ServerController;

import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Text text1;
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
        }*/
        ArrayList<Text> texts = new ArrayList<>();
//        final Text[] text1 = new Text[1];
        ArrayList<User> allUsers = new ArrayList<>();
        String usernames = "";
        try {
            Main.dataOutputStream.writeUTF("get people");
            Main.dataOutputStream.flush();
            usernames = Main.dataInputStream.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile("#([^#]+)");
        Matcher matcher = pattern.matcher(usernames);
        while (matcher.find()) {
            User toBeAdded = User.getUserByUsername(matcher.group(1));
            allUsers.add(toBeAdded);
        }
        while (allUsers.contains(User.getUserByUsername("@AI@"))) {
            allUsers.remove(User.getUserByUsername("@AI@"));
        }
        Comparator<User> userComparator = Comparator.comparing(User::getScore, Comparator.reverseOrder()).thenComparing(User::getNickname);
        new Thread(() -> {
            while (true) {
                ArrayList<User> onlineUsers = new ArrayList<>();
                String usernames2 = "";
                try {
                    Main.dataOutputStream.writeUTF("get online people");
                    Main.dataOutputStream.flush();
                    usernames2 = Main.dataInputStream.readUTF();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Pattern pattern2 = Pattern.compile("#([^#]+)");
                Matcher matcher2 = pattern2.matcher(usernames2);
                while (matcher2.find()) {
                    User toBeAdded = User.getUserByUsername(matcher2.group(1));
                    onlineUsers.add(toBeAdded);
                }
                allUsers.sort(userComparator);
                User previousUser = null;
                int rank = 1;
                int i = 1;
                int userCounter = 0;
                Platform.runLater(() -> textFlow.getChildren().clear());
                for (int j=0;j<allUsers.size();j++) {
                    User eachUser = allUsers.get(j);
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
                    texts.add(new Text(rank + "-          NickName:" + eachUser.getNickname() + "          Score: " + eachUser.getScore() + "\n"));
                    previousUser = eachUser;
                    userCounter++;
                    if (eachUser.equals(user)) {
                        texts.get(j).setFill(Color.PURPLE);
                        Font font1 = Font.font("Agency FB", FontWeight.BOLD, 18);
                        texts.get(j).setFont(font1);
                    } else if (onlineUsers.contains(eachUser)) {
                        texts.get(j).setFill(Color.PURPLE);
                        Font font1 = Font.font("Agency FB", FontWeight.NORMAL, 18);
                        texts.get(j).setFont(font1);
                    } else {
                        texts.get(j).setFill(Color.BLUE);
                        Font font1 = Font.font("Agency FB", FontWeight.NORMAL, 18);
                        texts.get(j).setFont(font1);
                    }
                    int finalJ = j;
                    Platform.runLater(() -> {
                        textFlow.setLineSpacing(1.5);
                        textFlow.getChildren().add(texts.get(finalJ));
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void goBack() throws Exception {
        ScoreBoardControllerGraphic.goBack(stage);
    }

    public void refresh() {
    }
}
