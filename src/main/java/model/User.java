package model;

import controller.ImportExportUserController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {

    private static List<User> allUsers;
    private String username;
    private String password;
    private String nickname;
    private int score;
    private List<Card> allCards;
    private List<Deck> allDecks;
    private Deck currentActiveDeck;
    private Deck currentGameDeck;
    private int lifePoint;
    private Board board;
    private int money;
    private int profileNumber;

    public User(String username, String nickname, String password) {
        if (allUsers == null)
            allUsers = new ArrayList<>();
        this.allCards = new ArrayList<>();
        this.allDecks = new ArrayList<>();
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.currentActiveDeck = null;
        this.profileNumber = getRandomProfileNumber();
        setScore(0);
        setMoney(100000);
        allUsers.add(this);
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(User.getUserByUsername(username));
        importExportUserController.exportAllUsers(User.getAllUsers());
    }

    public static User getUserByUsername(String username) {
        if (allUsers != null) {
            for (User user : allUsers) {
                if (user.getUsername().equals(username))
                    return user;
            }
        }
        return null;
    }

    public static User getUserByNickname(String nickname) {
        for (User allUser : allUsers) {
            if (allUser.getNickname().equals(nickname))
                return allUser;
        }
        return null;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public List<Deck> getAllDecks() {
        return this.allDecks;
    }

    public List<Card> getAllCards() {
        return this.allCards;
    }

    public Deck getDeckByName(String name) {
        for (Deck deck : allDecks) {
            if (deck.getDeckName().equals(name)) {
                return deck;
            }
        }
        return null;
    }

    public Deck getGameDeck() {
        return this.currentGameDeck;
    }

    public Card getCardByName(String name) {
        for (Card card : allCards) {
            if (card.getNamePascalCase().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public void setGameDeck(Deck currentGameDeck) {
        this.currentGameDeck = currentGameDeck;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getProfileNumber() {
        return this.profileNumber;
    }

    public int getScore() {
        return this.score;
    }

    public Deck getActiveDeck() {
        return this.currentActiveDeck;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setNewBoard() {
        this.board = new Board();
    }

    public int getLifePoint() {
        return this.lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void setPassword(String password) {
        this.password = password;
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(User.getUserByUsername(this.getUsername()));
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(User.getUserByNickname(nickname));
    }

    public void setUsername(String username) {
        this.username = username;
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(User.getUserByUsername(username));
        importExportUserController.exportAllUsers(User.getAllUsers());
    }

    public void setActiveDeck(Deck deck) {
        this.currentActiveDeck = deck;
    }

    public int getCountOfCardInAllCards(Card card) {
        int counter = 0;
        for (Card eachCard : allCards) {
            if (card.getName().equals(eachCard.getName()))
                counter++;
        }
        return counter;
    }

    public void deleteDeck(String name) {
        this.allDecks.remove(getDeckByName(name));
    }

    public void deleteCard(String name) {
        this.allCards.remove(getCardByName(name));
    }

    public void addDeck(Deck deck) {
        this.allDecks.add(deck);
    }

    public void addCardToUsersAllCards(Card card) {
        this.allCards.add(card);
    }

    public void removeDeck(Deck deck) {
        this.allDecks.remove(deck);
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }

    public void decreaseScore(int amount) {
        this.score -= amount;
    }

    public void increaseLifePoint(int amount) {
        this.lifePoint += amount;
    }

    public void decreaseLifePoint(int amount) {
        this.lifePoint -= amount;
    }

    public void increaseMoney(int amount) {
        this.money += amount;
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(User.getUserByUsername(this.getUsername()));
    }

    public void decreaseMoney(int amount) {
        this.money -= amount;
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportNewUser(User.getUserByUsername(this.getUsername()));
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return (this.username.equals(user.username));
    }

    public int getRandomProfileNumber() {
        Random random = new Random();
        return ((random.nextInt(36) + 1));
    }

    public void removeCard(Card card) {
        Card cardToBeDeleted = null;
        for (Card eachCard : this.allCards) {
            if (eachCard.getName().equals(card.getName())) {
                cardToBeDeleted = eachCard;
                break;
            }
        }
        this.allCards.remove(cardToBeDeleted);
    }

}
