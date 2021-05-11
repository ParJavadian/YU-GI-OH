package controller;

import controller.exeption.InvalidSelection;
import model.Deck;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.jws.soap.SOAPBinding;

public class test {


    @Test
    @DisplayName("testSelectCardPlayerMonsterZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardPlayerMonsterZoneMoreThan6() throws Exception {
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
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
        User player = new User("kiana_msz","kiana","12345");
        Deck deck = new Deck("deck of kiana");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        User rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentTrapAndSpellZone(0);
            }
        });
    }
}
