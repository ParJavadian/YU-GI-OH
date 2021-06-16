package controller;

import model.Cardable;
import model.Deck;
import model.User;

import javax.smartcardio.Card;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ImportExportUserController {
    private static ImportExportUserController instance = null;

    public static ImportExportUserController getInstance() {
        if (instance == null) instance = new ImportExportUserController();
        return instance;
    }
    
    public void exportAllUsers(List<User> allUsers){
        try {
            FileWriter writer = new FileWriter("allUsers.txt");
            if (!(allUsers == null)){
                for (User user : allUsers) {
                    String username = user.getUsername();
                    writer.write(username + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportNewUser(User user1) throws IOException {
        String username = user1.getUsername();
        String password = user1.getPassword();
        String nickname = user1.getNickname();
        int highScore = user1.getScore();
        int balance = user1.getMoney();
        FileWriter writer = new FileWriter("Users/" + username + ".txt");
        writer.write(username + "\n" + password + "\n" + nickname + "\n" + highScore + "\n" + balance);
        writer.close();
    }

    public void importAllUsers(){
        String username = "";
        String password = "";
        String nickname = "";
        String highScore = "";
        String balance = "";
        File file = new File("allUsers.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                username = scanner.nextLine();
                File userFile = new File("Users/" + username + ".txt");
                Scanner userScanner = new Scanner(userFile);
                int counter = 5;
                while (userScanner.hasNext()){
                    if (counter == 5)
                        username = userScanner.nextLine();
                    if (counter == 4)
                        password = userScanner.nextLine();
                    if (counter == 3)
                        nickname = userScanner.nextLine();
                    if (counter == 2)
                        highScore = userScanner.nextLine();
                    if (counter == 1)
                        balance = userScanner.nextLine();
                    if (counter == 0)
                        break;
                    counter--;
                    User user = new User(username,nickname,password);
                    user.setMoney(Integer.parseInt(balance));
                    user.setScore(Integer.parseInt(highScore));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportAllDecks(List<Deck> allDecks,User user) throws IOException {
        String username = user.getUsername();
        FileWriter fileWriter = new FileWriter(username + "'s decks.txt");

    }

    public void exportAllCards(List<Cardable> allCards) {

    }
}
