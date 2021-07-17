package Server.controller;

import Client.controller.SoundController;
import javafx.application.Application;
import javafx.stage.Stage;
import Client.view.SignUpViewGraphic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    public static Socket socket;
    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    public static void main(String[] args) {
        initializeNetwork();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SoundController.getInstance().playWhenStart();
        SignUpViewGraphic.getInstance().start(primaryStage);
    }

    public static void initializeNetwork() {
        try {
            socket = new Socket("localhost", 7777);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

}