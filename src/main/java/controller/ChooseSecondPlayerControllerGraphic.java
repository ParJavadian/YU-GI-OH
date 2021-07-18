package controller;

import client.Main;
import controller.exeption.InvalidDeck;
import controller.exeption.NoActiveDeck;
import controller.exeption.PlayerNotFound;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.User;
import view.ChooseDuelView;


import java.io.IOException;
import java.net.Socket;

public class ChooseSecondPlayerControllerGraphic {

    private static Socket socket;
    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static void twoPlayerGame(User user, Stage stage) throws Exception {
        if (user.getActiveDeck() == null)
            throw new NoActiveDeck(user.getUsername());
        if (!user.getActiveDeck().isValid())
            throw new InvalidDeck(user.getUsername());
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName("");
        ChooseDuelView.getInstance().start(stage);
    }

    public static void sendInvitation(User sender, User receiver){
        try {
            socket = new Socket("localhost", 7777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
                try {
                    Main.dataOutputStream.writeUTF("SendInvitationTo" + receiver.getUsername() + "@" + sender.getUsername());
                    Main.dataOutputStream.flush();
                    String result = Main.dataInputStream.readUTF();
                    if (result.equals("Yes")){
                        System.out.println("are");
                    }
                    else System.out.println("na");
                } catch(Exception x){
                    x.printStackTrace();
                }
        }).start();
    }


/*    private static String setOnlineNumber(){
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
                    Platform.runLater(() -> setResult(result));
                    Thread.sleep(5000);
                } catch(Exception x){
                    x.printStackTrace();
                }
            }
        }).start();
        return result;
    }*/



}
