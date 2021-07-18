package server;

import controller.ImportExportUserController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Message;
import model.User;
//import server.AdminPanel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerController extends Application {

    private static ArrayList<User> allUsers = new ArrayList<>();
    public static final HashMap<String, User> loggedInUsers = new HashMap<>();
    private static ServerSocket serverSocket;
    private static ArrayList<User> peopleWaitingFor3RoundGame = new ArrayList<>();
    private static ArrayList<User> peopleWaitingFor1RoundGame = new ArrayList<>();
    private static ArrayList<Message> messages = new ArrayList<>();
    private static String pinText = "";
    static User finalSecondUser;
    static User finalFirstUser;

    public static void main(String[] args) {
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.importAllUsers();
        allUsers = (ArrayList<User>) User.getAllUsers();
        importExportUserController.importProfileNumber();
//        System.out.println("in main: " + allUsers.size());
        importExportUserController.importProfileNumber();
        importExportUserController.importAllCards();
        importExportUserController.importAllDecks();
        importExportUserController.importActiveDeck();
        /*Deck AIDeck = Objects.requireNonNull(User.getUserByUsername("@AI@")).getDeckByName("DeckForAI");
        if (AIDeck == null) {
            Deck deck = DeckController.getInstance(User.getUserByUsername("@AI@")).createRandomDeckForAI();
            Objects.requireNonNull(User.getUserByUsername("@AI@")).setActiveDeck(deck);
        }
        else Objects.requireNonNull(User.getUserByUsername("@AI@")).setActiveDeck(AIDeck);*/
//        System.out.println("main: " + User.getAllUsers());
        try {
            serverSocket = new ServerSocket(7777);
            new Thread(() -> launch(args)).start();
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
//                        System.out.println("main2: " + User.getAllUsers() + " " + socket.getPort());
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        while (true) {
                            String input = dataInputStream.readUTF();
                            process(input, dataOutputStream, objectOutputStream);
//                            if (result.equals("")) break;
//                            dataOutputStream.writeUTF(result);
//                            dataOutputStream.flush();
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


    public static HashMap<String, User> getLoggedInUsers() {
        System.out.println("az tu server = " + loggedInUsers);
        return loggedInUsers;
    }

    public static void showLogins(String ok) {
        System.out.println(loggedInUsers.size() + " " + ok);
    }

    static void process(String command, DataOutputStream dataOutputStream, ObjectOutputStream objectOutputStream) {
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
        } else if (command.startsWith("request for game: ")) {
            handleRequestForGame(command);
        } else if (command.equals("all messages")) {
            handleRequestGetAllMessages(dataOutputStream);
        } else if (command.startsWith("addchat!@#")) {
            addMessage(command);
        } else if (command.startsWith("setPin!@#")) {
            setPin(command);
        } else if (command.equals("get pin")) {
            handleRequestGetPin(dataOutputStream);
        } else if (command.startsWith("deletechat!@#")) {
            deleteMessage(command);
        } else if (command.equals("get online people")) {
            getOnlinePeople(dataOutputStream);
        } else if (command.startsWith("stop waiting ")) {
            removeUserFromWaitingList(command);
        } else if (command.startsWith("get people")) {
            getPeople(dataOutputStream);
        } else if (command.startsWith("SendInvitationTo")) {
            sendInvitation(dataOutputStream);
        } else if (command.startsWith("get online people")) {
            getOnlinePeople(dataOutputStream);
        }
    }

    private static void getOnlinePeople(DataOutputStream dataOutputStream) {
        StringBuilder toWrite = new StringBuilder("#");
        for (User user : loggedInUsers.values()) {
            toWrite.append(user.getUsername()).append("#");
        }
        try {
            dataOutputStream.writeUTF(toWrite.toString());
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteMessage(String command) {
        String[] parts = command.split("!@#");
        String toDelete = parts[1];
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            String toCompare = message.sender.getNickname() + ":    " + message.text;
            if (toCompare.equals(toDelete)) {
                messages.remove(message);
                return;
            }
        }
    }

    public static void getPeople(DataOutputStream dataOutputStream) {
        String toWrite = "#";
        for (User user : allUsers) {
            toWrite += user.getUsername() + "#";
        }
        try {
            dataOutputStream.writeUTF(toWrite);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequestGetPin(DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeUTF(pinText);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setPin(String command) {
        String[] parts = command.split("!@#");
        pinText = parts[1];
    }

    private static void addMessage(String command) {
        String[] parts = command.split("!@#");
        String text = parts[1];
        User sender = User.getUserByUsername(parts[2]);
        int Id = Integer.parseInt(parts[3]);
        messages.add(new Message(text, sender));
    }

    private synchronized static void handleRequestGetAllMessages(DataOutputStream dataOutputStream) {
        try {
            String toWrite = "";
            for (Message message : messages) {
                toWrite += message.sender.getProfileNumber() + "!@#";
                toWrite += message.sender.getNickname() + "!@#";
                toWrite += message.text + "#@!";
            }
            dataOutputStream.writeUTF(toWrite);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static synchronized void returnNumberOfOnlinePeople(DataOutputStream dataOutputStream) {
        try {
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

    public static void handleRequestForGame(String command) {
        String[] parts = command.split(" ");
        String username = parts[3];
        String numberOfRounds = parts[4];
        if (numberOfRounds.equals("1")) {
            peopleWaitingFor1RoundGame.add(User.getUserByUsername(username));
        } else if (numberOfRounds.equals("3")) {
            peopleWaitingFor3RoundGame.add(User.getUserByUsername(username));
        }
        if (peopleWaitingFor1RoundGame.size() > 1) {
            matchUsersForGame(peopleWaitingFor1RoundGame);
            //todo startGame
        }
        if (peopleWaitingFor3RoundGame.size() > 1) {
            matchUsersForGame(peopleWaitingFor3RoundGame);
            //todo startGame
        }
    }

    public static void matchUsersForGame(ArrayList<User> waitingUsers) {
        new Thread(() -> {
            for (User firstUser : waitingUsers) {
                for (User secondUser : waitingUsers) {
                    if (/*firstUser!=null && secondUser!=null && */(Math.abs(firstUser.getScore() - secondUser.getScore()) < 2000) && (waitingUsers.indexOf(firstUser) != waitingUsers.indexOf(secondUser))) {
                        finalFirstUser = firstUser;
                        finalSecondUser = secondUser;
                        System.out.println("first user: " + finalFirstUser.getUsername() + "," + finalFirstUser.getScore());
                        System.out.println("second user: " + finalSecondUser.getUsername() + "," + finalSecondUser.getScore());
                        return;
                    }
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (User firstUser : waitingUsers) {
                for (User secondUser : waitingUsers) {
                    if (/*firstUser!=null && secondUser!=null && */(Math.abs(firstUser.getScore() - secondUser.getScore()) < 2000) && (waitingUsers.indexOf(firstUser) != waitingUsers.indexOf(secondUser))) {
                        finalFirstUser = firstUser;
                        finalSecondUser = secondUser;
                        System.out.println("first user: " + finalFirstUser.getUsername() + "," + finalFirstUser.getScore());
                        System.out.println("second user: " + finalSecondUser.getUsername() + "," + finalSecondUser.getScore());
                        return;
                    }
                }
            }
            for (User firstUser : waitingUsers) {
                for (User secondUser : waitingUsers) {
                    if (waitingUsers.indexOf(firstUser) != waitingUsers.indexOf(secondUser) && waitingUsers.size() > 1) {
                        finalFirstUser = waitingUsers.get(0);
                        finalSecondUser = waitingUsers.get(1);
                        System.out.println("first user: " + finalFirstUser.getUsername() + "," + finalFirstUser.getScore());
                        System.out.println("second user: " + finalSecondUser.getUsername() + "," + finalSecondUser.getScore());
                        return;
                    }
                }
            }

        }).start();
    }

    static void removeUserFromWaitingList(String command) {
        String[] parts = command.split(" ");
        String username = parts[2];
        User toBeDeleted = null;
        for (User user : peopleWaitingFor1RoundGame) {
            if (user.getUsername().equals(username)) {
                toBeDeleted = user;
                break;
            }
        }
        peopleWaitingFor1RoundGame.remove(toBeDeleted);
        for (User user : peopleWaitingFor3RoundGame) {
            if (user.getUsername().equals(username)) {
                toBeDeleted = user;
                break;
            }
        }
        peopleWaitingFor3RoundGame.remove(toBeDeleted);
    }

    static void sendInvitation(DataOutputStream dataOutputStream) {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION, "You have just been invited to a play a game with " /* + esme yaru */ + "\n" + "want to join?", ButtonType.YES, ButtonType.NO);
        info.setHeaderText("Invitation");
        info.showAndWait();
        String result;
        if (info.getResult().equals(ButtonType.NO))
            result = "No";
        else result = "Yes";
        try {
            dataOutputStream.writeUTF(result);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        AdminPanel.getInstance().start(primaryStage);
    }


}
