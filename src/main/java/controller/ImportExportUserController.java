package controller;

import controller.exeption.RepetitiveNickname;
import controller.exeption.RepetitiveUsername;
import model.Cardable;
import model.Deck;
import model.User;

import javax.smartcardio.Card;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportExportUserController {
    private static ImportExportUserController instance = null;

    public static ImportExportUserController getInstance() {
        if (instance == null) instance = new ImportExportUserController();
        return instance;
    }

    public void exportNewUser(User user) {
        if (user != null) {
            String username = user.getUsername();
            String password = user.getPassword();
            String nickname = user.getNickname();
            int highScore = user.getScore();
            int balance = user.getMoney();
            try {
                FileWriter writer = new FileWriter("Users/" + username + ".txt");
                writer.write(username + "\n" + password + "\n" + nickname + "\n" + highScore + "\n" + balance);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exportAllUsers(List<User> allUsers) {
        try {
            FileWriter writer = new FileWriter("allUsers.txt");
            for (User user : allUsers) {
                String username = user.getUsername();
                System.out.println(username);
                writer.write(username + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importAllUsers() {
        String username = "";
        String password = "";
        String nickname = "";
        String highScore = "";
        String balance = "";
        File file = new File("allUsers.txt");

        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    username = scanner.nextLine();
                    File userFile = new File("Users/" + username + ".txt");
                    Scanner userScanner = new Scanner(userFile);
                    int counter = 5;
                    while (userScanner.hasNextLine()) {
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
                    }
                    User user = new User(username, nickname, password);
                    if (balance.matches("\\d+") && highScore.matches("\\d+")) {
                        user.setMoney(Integer.parseInt(balance));
                        user.setScore(Integer.parseInt(highScore));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void exportAllDecksName(List<Deck> allDecks, User user) {
        String username = user.getUsername();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(username + "allDecks.txt");
            for (Deck deck : allDecks) {
                String deckName = deck.getDeckName();
                fileWriter.write(deckName + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportCardsInMainDeck(User user, String deckName) {
        String username = user.getUsername();
        try {
            FileWriter writer = new FileWriter("Deck/" + username + deckName + "mainDeck.txt");
            Deck toBeExportedDeck = user.getDeckByName(deckName);
            for (Cardable card : toBeExportedDeck.getMainDeck()) {
                String cardName = card.getName();
                writer.write(cardName + "\n");
            }
            FileWriter fileWriter = new FileWriter("Deck/" + username + deckName + "sideDeck.txt");
            for (Cardable card : toBeExportedDeck.getSideDeck()) {
                String cardName = card.getName();
                writer.write(cardName + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportCardsInSideDeck(User user, String deckName) {
        String username = user.getUsername();
        FileWriter writer = null;
        try {
            writer = new FileWriter("Deck/" + username + deckName + "sideDeck.txt");
            Deck toBeExportedDeck = user.getDeckByName(deckName);
            for (Cardable card : toBeExportedDeck.getSideDeck()) {
                String cardName = card.getName();
                writer.write(cardName + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importAllDecks() {
        if (User.getAllUsers() != null) {
            for (User user : User.getAllUsers()) {
                File file = new File("allDecks.txt");
                String deckName;
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        deckName = scanner.nextLine();
                        File mainDeckFile = new File("Deck" + user.getUsername() + deckName + "mainDeck.txt");
                        File sideDeckFile = new File("Deck" + user.getUsername() + deckName + "sideDeck.txt");
                        if (mainDeckFile.exists()) {
                            Scanner mainDeckScanner = new Scanner(mainDeckFile);
                            Deck deck = new Deck(deckName);
                            while (mainDeckScanner.hasNextLine()) {
                                String cardName = mainDeckScanner.nextLine();
                                deck.addCardToMainDeck(DeckController.getInstance(user).getCardByName(cardName));
                            }
                            user.addDeck(deck);
                        }
                        if (sideDeckFile.exists()) {
                            Scanner sideDeckScanner = new Scanner(sideDeckFile);
                            Deck deck = new Deck(deckName);
                            while (sideDeckScanner.hasNextLine()) {
                                String cardName = sideDeckScanner.nextLine();
                                deck.addCardToSideDeck(DeckController.getInstance(user).getCardByName(cardName));
                            }
                            user.addDeck(deck);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void exportAllCards(User user) {
        for (Cardable card : user.getAllCards()) {
            String cardName = card.getName();
            try {
                FileWriter writer = new FileWriter("allCards" + user.getUsername() + ".txt");
                writer.write(cardName + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void importAllCards() {
        if (User.getAllUsers() != null) {
            for (User user : User.getAllUsers()) {
                String username = user.getUsername();
                File file = new File("allCards" + username + ".txt");
                if (file.exists()) {
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String cardName = scanner.nextLine();
                            user.addCardToUsersAllCards(DeckController.getInstance(user).getCardByName(cardName));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
