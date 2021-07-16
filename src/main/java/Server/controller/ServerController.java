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
                        while (true) {
                            String input = dataInputStream.readUTF();
                            String result = process(input);
                            if (result.equals("")) break;
                            dataOutputStream.writeUTF(result);
                            dataOutputStream.flush();
                        }
                        dataInputStream.close();
                        socket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String process(String command) {
        /*if (command.startsWith("signup")) {
            String[] parts = command.split(" ");
            return String.valueOf(ServerController.register(parts[1], parts[2], parts[3]));
        } else*/ if (command.startsWith("login")) {
            String[] parts = command.split(" ");
            return String.valueOf(ServerController.login(parts[1], parts[2]));
        }
        return "";
    }

    /*public static synchronized boolean register(String username, String password, String fullName) {
        for (User user: allUsers) {
            if (user.getUsername().equals(username)) return false;
        }
        allUsers.add(new User(username, password, fullName));
        return true;

    }*/

    public static boolean login(String username, String password) {
        for (User user: allUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUsers.put(UUID.randomUUID().toString(), user);
                return true;
            }
        }
        return false;
    }





}
