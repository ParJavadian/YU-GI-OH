package controller;

import controller.exeption.*;
import model.Deck;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import view.LogInView;

import javax.accessibility.AccessibleStateSet;
import javax.jws.soap.SOAPBinding;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class test {
    static User player;
    static User rival;

    @BeforeAll
    static void toBeDoneBefore() throws Exception {
        player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
//        player.getDeckByName("deck of kiana").addCardToMainDeck(player.getCardByName("Suijin"));
//        player.getDeckByName("deck of kiana").addCardToMainDeck(player.getCardByName("Fireyarou"));
        player.addDeck(deck);
        player.setActiveDeck(deck);
        rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
        deck = new Deck("second deck");
        player.addDeck(deck);
        deck = new Deck("third deck");
        player.addDeck(deck);
    }

    @Test
    @DisplayName("add card to deck")
    public void addCardToDeckTest(){
        player.getActiveDeck().addCardToMainDeck(player.getCardByName("Suijin"));
        player.getActiveDeck().addCardToMainDeck(player.getCardByName("Fireyarou"));
        player.getActiveDeck().addCardToSideDeck(player.getCardByName("Suijin"));
        player.getActiveDeck().addCardToSideDeck(player.getCardByName("Fireyarou"));
        Assertions.assertEquals(2,player.getActiveDeck().getSideDeck().size());
        Assertions.assertEquals(2,player.getActiveDeck().getMainDeck().size());
    }

    @Test
    @DisplayName("delete deck")
    public void deleteDeck() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int firstSize = player.getAllDecks().size();
        DeckController.getInstance(player).deleteDeck("third deck");
        int secondSize = player.getAllDecks().size();
        Assertions.assertEquals(1,firstSize-secondSize);
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).deleteDeck("deck number 4");
            }
        });
        int thirdSize = player.getAllDecks().size();
        Assertions.assertEquals(0,thirdSize-secondSize);
        Assertions.assertEquals("deck deleted successfully\r\n",outContent.toString());
    }

    @Test
    @DisplayName("activateDeck")
    public void activateDeck()throws Exception{
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).activateDeck("second deck");
        Assertions.assertEquals("deck activated successfully\r\n",outContent.toString());
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).activateDeck("deck number 4");
            }
        });
    }


    @Test
    @DisplayName("create deck repetitive deck name")
    public void createDeckRepetitiveDeckName() throws Exception{
        Assertions.assertThrows(RepetitiveDeckName.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).createDeck("deck of kiana");
            }
        });
    }

    @Test
    @DisplayName("create deck fine")
    public void createDeckFine() throws Exception {
        int firstSize = player.getAllDecks().size();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).createDeck("deck molayi");
        //player.addDeck(player.getDeckByName("deck molayi"));
        int secondSize = player.getAllDecks().size();
        Assertions.assertEquals(1,secondSize-firstSize);
        Assertions.assertEquals("deck created successfully!\r\n",outContent.toString());
    }


    @Test
    @DisplayName("testSelectCardPlayerMonsterZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardPlayerMonsterZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerMonsterZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardPlayerMonsterZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardPlayerMonsterZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerMonsterZone(0);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentMonsterZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardOpponentMonsterZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentMonsterZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentMonsterZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardOpponentMonsterZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentMonsterZone(0);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardPlayerTrapAndSpellZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardPlayerTrapAndSpellZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerTrapAndSpellZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardPlayerTrapAndSpellZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardPlayerTrapAndSpellZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerTrapAndSpellZone(0);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentTrapAndSpellZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardOpponentTrapAndSpellZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentTrapAndSpellZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentTrapAndSpellZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardOpponentTrapAndSpellZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentTrapAndSpellZone(0);
            }
        });
    }

    @Test
    @DisplayName("test menu enter")
    public void testMenuEnter(){
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        PrintStream sysOutBackup = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("menu enter \nmenu exit\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        LogInView.getInstance().getCommandForLogin();
        Assertions.assertEquals("please login first\r\n",out.toString());
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("createUser everything is fine")
    public void createUserFine() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        LogInController.getInstance().createUser("CHECK","check","ChEcK");
        Assertions.assertEquals("user created successfully!\r\n",outContent.toString());
    }

//    @Test
//    @DisplayName("loginUser fine")
//    public void loginUserFine() throws Exception {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        ByteArrayInputStream in = new ByteArrayInputStream("user login -u kiana_msz -p 12345\nuser logout\nmenu exit\n".getBytes());
//        System.setIn(in);
//        LogInView.getInstance().getCommandForLogin();
//        Assertions.assertEquals("user logged in successfully!\r\nuser logged out successfully!\r\n",outContent.toString());
//    }


    @Test
    @DisplayName("Repetitive username check")
    public void RepetitiveUsername() throws Exception {
        Assertions.assertThrows(RepetitiveUsername.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().createUser("kiana_msz","12345","kiana");
            }
        });
    }

    @Test
    @DisplayName("Repetitive nickname check")
    public void RepetitiveNickname() throws Exception {
        Assertions.assertThrows(RepetitiveNickname.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().createUser("kiana_m","12345","kiana");
            }
        });
    }

    @Test
    @DisplayName("loginUser UserNotFound")
    public void loginUserUserNotFound() throws Exception {
        Assertions.assertThrows(UsernameNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().loginUser("kasra","123");
            }
        });
    }

    @Test
    @DisplayName("loginUser WrongPassword")
    public void loginUserWrongPassword() throws Exception {
        Assertions.assertThrows(WrongPassword.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().loginUser("hamriouz","123");
            }
        });
    }

//    @Test
//    @DisplayName("")
//    public void


}
