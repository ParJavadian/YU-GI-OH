package model;

import controller.ImportExportUserController;
import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private Image profileImage;
    private int profileNumber;
    private int wonGamesInARow = 0;
    private int lostGamesInARow = 0;
    private int numberOfWonGamesWithoutMonster = 0;
    private int numberOfBronze = 0;
    private int numberOfSilver = 0;
    private int numberOfGold = 0;
    private int numberOfTrophy = 0;
    private int numberOfGifts = 0;
    private int numberOfThreeLosesInARow = 0;
    private int numberOfSevenLosesInARow = 0;
    private int numberOfTenLosesInARow = 0;


    public User(String username, String nickname, String password,boolean isCalledFromSignUpMenu) {
        if (allUsers == null)
            allUsers = new ArrayList<>();
        this.allCards = new ArrayList<>();
        this.allDecks = new ArrayList<>();
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.currentActiveDeck = null;
        if (isCalledFromSignUpMenu) {
            setScore(0);
            setMoney(100000);
        }
        this.profileImage = null;
        allUsers.add(this);
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

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public Image getProfileImage() {
        return this.profileImage;
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

    public void setProfileNumber(int profileNumber) {
        this.profileNumber = profileNumber;
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
        ImportExportUserController.getInstance().exportActiveDeck(Objects.requireNonNull(getUserByUsername(username)),deck.getDeckName());
    }

    public void addWonGamesInARow() {
        this.wonGamesInARow += 1;
    }

    public void setWonGamesInARowToZero(){
        this.wonGamesInARow = 0;
    }

    public void setNumberOfWonGamesWithoutMonsterToZero(){
        this.numberOfWonGamesWithoutMonster = 0;
    }

    public int getNumberOfGifts() {
        return numberOfGifts;
    }

    public void addGift(){
        this.numberOfGifts += 1;
    }

    public void setWonGamesInARow(int wonGamesInARow) {
        this.wonGamesInARow = wonGamesInARow;
    }

    public void setNumberOfWonGamesWithoutMonster(int numberOfWonGamesWithoutMonster) {
        this.numberOfWonGamesWithoutMonster = numberOfWonGamesWithoutMonster;
    }

    public void setNumberOfBronze(int numberOfBronze) {
        this.numberOfBronze = numberOfBronze;
    }

    public void setNumberOfSilver(int numberOfSilver) {
        this.numberOfSilver = numberOfSilver;
    }

    public void setNumberOfGold(int numberOfGold) {
        this.numberOfGold = numberOfGold;
    }

    public void setNumberOfTrophy(int numberOfTrophy) {
        this.numberOfTrophy = numberOfTrophy;
    }

    public void setLostGamesInARow(int lostGamesInARow) {
        this.lostGamesInARow = lostGamesInARow;
    }

    public void addThreeLosesInARow(){
        this.numberOfThreeLosesInARow += 1;
    }

    public void addSevenLosesInARow(){
        this.numberOfSevenLosesInARow += 1;
    }

    public void addTenLosesInARow(){
        this.numberOfTenLosesInARow += 1;
    }

    public void addToLostGames(){
        this.lostGamesInARow += 1;
    }

    public int getLostGamesInARow() {
        return lostGamesInARow;
    }

    public int getNumberOfThreeLosesInARow() {
        return numberOfThreeLosesInARow;
    }

    public int getNumberOfSevenLosesInARow() {
        return numberOfSevenLosesInARow;
    }

    public int getNumberOfTenLosesInARow() {
        return numberOfTenLosesInARow;
    }

    public int getNumberOfWonGamesWithoutMonster() {
        return numberOfWonGamesWithoutMonster;
    }

    public int getWonGamesInARow() {
        return wonGamesInARow;
    }

    public int getNumberOfBronze() {
        return numberOfBronze;
    }

    public int getNumberOfSilver() {
        return numberOfSilver;
    }

    public int getNumberOfGold() {
        return numberOfGold;
    }

    public int getNumberOfTrophy() {
        return numberOfTrophy;
    }

    public void addNumberOfWonGamesWithoutMonster(){
        this.numberOfWonGamesWithoutMonster += 1;
    }

    public void addNumberOfTrophy(){
        this.numberOfTrophy += 1;
    }

    public void addNumberOfBronze() {
        this.numberOfBronze += 1;
    }

    public void addNumberOfSilver() {
        this.numberOfSilver += 1;
    }

    public void addNumberOfGold() {
        this.numberOfGold += 1;
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
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportAllDecksName(this.getAllDecks(), Objects.requireNonNull(User.getUserByUsername(username)));
    }

    public void addCardToUsersAllCards(Card card) {
        this.allCards.add(card);
    }

    public void removeDeck(Deck deck) {
        this.allDecks.remove(deck);
        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
        importExportUserController.exportAllDecksName(this.getAllDecks(), Objects.requireNonNull(User.getUserByUsername(username)));
    }

    public void increaseScore(int amount) {
        this.score += amount;
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

    public void addDeckCardsToUserAllCards(String name) {
        Deck deck = this.getDeckByName(name);
        allCards.addAll(deck.getMainDeck());
        allCards.addAll(deck.getSideDeck());
    }
}
