package Server.controller;

import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerController {

    private static ArrayList<User> allUsers = new ArrayList<>();
    private static HashMap<String, User> loggedInUsers;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        String input = dataInputStream.readUTF();
                        process(input,dataOutputStream);
                        dataInputStream.close();
                        socket.close();
//                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    static void process(String command,DataOutputStream dataOutputStream) {
        /*if (command.startsWith("signup")) {
            String[] parts = command.split(" ");
            return String.valueOf(ServerController.register(parts[1], parts[2], parts[3]));
        } else*/
        if (command.startsWith("login")) {
            String[] parts = command.split(" ");
            ServerController.login(parts[1]);
        } else if (command.equals("NumberOfOnlinePeople")) {
            returnNumberOfOnlinePeople(dataOutputStream);
        } else if (command.startsWith("logout")) {
            logout(command);
        }
    }

    /*public static synchronized boolean register(String username, String password, String fullName) {
        for (User user: allUsers) {
            if (user.getUsername().equals(username)) return false;
        }
        allUsers.add(new User(username, password, fullName));
        return true;

    }*/

    public static void login(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                loggedInUsers.put(UUID.randomUUID().toString(), user);
                break;
            }
        }
    }

    public static void returnNumberOfOnlinePeople(DataOutputStream dataOutputStream){
        try {
            dataOutputStream.writeUTF(String.valueOf(loggedInUsers.size()));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout(String command){
        String[] parts = command.split(" ");
        loggedInUsers.values().remove(User.getUserByUsername(parts[1]));
    }



}
