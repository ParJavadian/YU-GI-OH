package controller;

import controller.exeption.*;
import model.*;
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
        player.addDeck(deck);
        player.setActiveDeck(deck);
        rival = new User("hamriouz","hamraz","12345");
        rival.addDeck(deck);
        rival.setActiveDeck(deck);
        rival.addCardToUsersAllCards(MonsterCard.YOMI_SHIP);
        DeckController.getInstance(rival).addCardToDeck(MonsterCard.YOMI_SHIP.getNamePascalCase(), "deck of kiana", false,false);
        deck = new Deck("second deck");
        player.addDeck(deck);
        deck = new Deck("third deck");
        player.addDeck(deck);




        {
            DeckController.getInstance(player).createDeck("deck to check full main");
            player.setActiveDeck(player.getDeckByName("deck to check full main"));




            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.SUIJIN);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.ALEXANDRITE_DRAGON);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.BABY_DRAGON);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.BATTLE_OX);
            }



            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.ALEXANDRITE_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(),false,false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck("Suijin",player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck("Fireyarou",player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BATTLE_OX.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BABY_DRAGON.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.HANIWA);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.CURTAIN_OF_DARK_ONES);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.GATE_GUARDIAN);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.HERO_OF_THE_EAST);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(MonsterCard.SCANNER);
            }



            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.HANIWA.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.CURTAIN_OF_DARK_ONES.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.GATE_GUARDIAN.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.HERO_OF_THE_EAST.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.SCANNER.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }




            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.ADVANCED_RITUAL_ART);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.ADVANCED_RITUAL_ART.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.BLACK_PENDANT);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.BLACK_PENDANT.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }



            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.CHANGE_OF_HEART);
            }
            DeckController.getInstance(player).addCardToDeck(SpellCard.CHANGE_OF_HEART.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.CLOSED_FOREST);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.CLOSED_FOREST.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.FOREST);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.FOREST.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }



            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.DARK_HOLE);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.DARK_HOLE.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.HARPIES_FEATHER_DUST);
            }

            DeckController.getInstance(player).addCardToDeck(SpellCard.HARPIES_FEATHER_DUST.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);

            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.MONSTER_REBORN);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.MONSTER_REBORN.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.RAIGEKI);
            }



            DeckController.getInstance(player).addCardToDeck(SpellCard.RAIGEKI.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.MAGNUM_SHIELD);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.MAGNUM_SHIELD.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }



            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(TrapCard.CALL_OF_THE_HAUNTED);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(TrapCard.CALL_OF_THE_HAUNTED.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }



            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(TrapCard.MIND_CRUSH);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(TrapCard.MIND_CRUSH.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }



            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(TrapCard.TRAP_HOLE);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(TrapCard.MAGIC_CYLINDER);
            }
            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(TrapCard.MAGIC_JAMMER);
            }
            player.setActiveDeck(player.getDeckByName("deck of kiana"));


        }
    }

    @Test
    @DisplayName("add card to main deck full")
    public void addCardFullMainDeck() throws Exception{

        player.setActiveDeck(player.getDeckByName("deck to check full main"));
        Assertions.assertThrows(ThreeSameCards.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BABY_DRAGON.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }
        });




        Assertions.assertThrows(OneCardForLimited.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(SpellCard.CHANGE_OF_HEART.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }
        });



        Assertions.assertThrows(FullMainDeck.class , new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(TrapCard.MAGIC_CYLINDER.getNamePascalCase(),player.getActiveDeck().getDeckName(),false,false);
            }
        });



        DeckController.getInstance(player).activateDeck("deck of kiana");


    }

    @Test
    @DisplayName("add card to side deck full ")
    public void addCardFullSideDeck() throws Exception {

        DeckController.getInstance(player).createDeck("deck to check full side");
        player.setActiveDeck(player.getDeckByName("deck to check full side"));

        for (int i = 0; i < 4; i++) {
            player.addCardToUsersAllCards(MonsterCard.SUIJIN);
        }
        for (int i = 0; i < 4; i++) {
            player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        }
        for (int i = 0; i < 4; i++) {
            player.addCardToUsersAllCards(MonsterCard.ALEXANDRITE_DRAGON);
        }
        for (int i = 0; i < 4; i++) {
            player.addCardToUsersAllCards(MonsterCard.BABY_DRAGON);
        }
        for (int i = 0; i < 4; i++) {
            player.addCardToUsersAllCards(MonsterCard.BATTLE_OX);
        }

        player.addCardToUsersAllCards(MonsterCard.BATTLE_WARRIOR);

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck("Suijin",player.getActiveDeck().getDeckName(),true,false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck("Fireyarou",player.getActiveDeck().getDeckName(),true,false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.BATTLE_OX.getNamePascalCase(),player.getActiveDeck().getDeckName(),true,false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.BABY_DRAGON.getNamePascalCase(),player.getActiveDeck().getDeckName(),true,false);
        }

        Assertions.assertThrows(ThreeSameCards.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.SUIJIN.getNamePascalCase(), player.getActiveDeck().getDeckName(),false,false);
            }
        });

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.ALEXANDRITE_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(),true,false);
        }

        Assertions.assertThrows(FullSideDeck.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BATTLE_WARRIOR.getNamePascalCase(), player.getActiveDeck().getDeckName(),true,false);
            }
        });
    }

    @Test
    @DisplayName("add card to player and deck")
    public void addCardToPlayerAndDeckTest() throws Exception {
        DeckController.getInstance(player).createDeck("deck to check add card");
        player.setActiveDeck(player.getDeckByName("deck to check add card"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        player.addCardToUsersAllCards(MonsterCard.SUIJIN);
        player.addCardToUsersAllCards(MonsterCard.SUIJIN);
        player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        DeckController.getInstance(player).addCardToDeck("Suijin",player.getActiveDeck().getDeckName(),false,false);
        DeckController.getInstance(player).addCardToDeck("Fireyarou",player.getActiveDeck().getDeckName(),false,false);
        DeckController.getInstance(player).addCardToDeck("Suijin",player.getActiveDeck().getDeckName(),true,false);
        DeckController.getInstance(player).addCardToDeck("Fireyarou",player.getActiveDeck().getDeckName(),true,false);
        Assertions.assertThrows(CardNotFoundInUser.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.FLAME_MANIPULATOR.getNamePascalCase(), player.getActiveDeck().getDeckName(),true,false);
            }
        });
        player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck("Fireyarou","best deck ever",true,false);
            }
        });
        Assertions.assertEquals(2,player.getActiveDeck().getSideDeck().size());
        Assertions.assertEquals(2,player.getActiveDeck().getMainDeck().size());
        Assertions.assertEquals("card added to deck successfully\r\ncard added to deck successfully\r\ncard added to deck successfully\r\ncard added to deck successfully\r\n",outContent.toString());
        player.setActiveDeck(player.getDeckByName("deck of kiana"));
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
        DeckController.getInstance(player).activateDeck("deck of kiana");
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
        player.setActiveDeck(player.getDeckByName("deck to check full main"));
        final DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerTrapAndSpellZone(0);
            }
        });
        player.setActiveDeck(player.getDeckByName("deck of kiana"));
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
