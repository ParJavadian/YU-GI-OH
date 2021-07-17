package server;

import controller.DeckController;
import controller.ImportExportUserController;
import model.Deck;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ServerController {

    private static ArrayList<User> allUsers = new ArrayList<>();
    private static final HashMap<String, User> loggedInUsers = new HashMap<>();
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.importAllUsers();
        allUsers = (ArrayList<User>) User.getAllUsers();
//        System.out.println("in main: " + allUsers.size());
        /*importExportUserController.importProfileNumber();
        importExportUserController.importAllCards();
        importExportUserController.importAllDecks();
        importExportUserController.importActiveDeck();
        Deck AIDeck = Objects.requireNonNull(User.getUserByUsername("@AI@")).getDeckByName("DeckForAI");
        if (AIDeck == null) {
            Deck deck = DeckController.getInstance(User.getUserByUsername("@AI@")).createRandomDeckForAI();
            Objects.requireNonNull(User.getUserByUsername("@AI@")).setActiveDeck(deck);
        }
        else Objects.requireNonNull(User.getUserByUsername("@AI@")).setActiveDeck(AIDeck);*/
//        System.out.println("main: " + User.getAllUsers());
        try {
            serverSocket = new ServerSocket(7777);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
//                        System.out.println("main2: " + User.getAllUsers() + " " + socket.getPort());
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        while (true) {
                            String input = dataInputStream.readUTF();
                            String result = process(input, dataOutputStream);
                            if (result.equals("")) break;
                            dataOutputStream.writeUTF(result);
                            dataOutputStream.flush();
                        }
//                        dataInputStream.close();
//                        socket.close();
//                        serverSocket.close();
//                        dataInputStream.close();
//                        dataOutputStream.close();
//                        socket.close();
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

    public static void showLogins(String ok) {
        System.out.println(loggedInUsers.size() + " " + ok);
    }


    static String process(String command, DataOutputStream dataOutputStream) {
//        System.out.println("process 1: " + command);
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
            return "";
        }
        return "ok";
    }

    /*public static synchronized boolean register(String username, String password, String fullName) {
        for (User user: allUsers) {
            if (user.getUsername().equals(username)) return false;
        }
        allUsers.add(new User(username, password, fullName));
        return true;

    }*/

    public static void login(String username) {
//        System.out.println(allUsers.size() + " " + username);
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
//                System.out.println("login2: ");
                loggedInUsers.put(UUID.randomUUID().toString(), user);
                break;
            }
        }
    }

    public static void returnNumberOfOnlinePeople(DataOutputStream dataOutputStream) {
        try {
            System.out.println("logged in: "  +loggedInUsers.size());
            dataOutputStream.writeUTF(String.valueOf(loggedInUsers.size()));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout(String command) {
        String[] parts = command.split(" ");
        loggedInUsers.values().remove(User.getUserByUsername(parts[1]));
    }


}
