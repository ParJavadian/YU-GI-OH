/*
import controller.DeckController;
import controller.exeption.*;
import model.*;

import controller.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;


public class test {

    static User player;
    static User rival;
    static User checker;

    @BeforeAll
    static void toBeDoneBefore() throws Exception {
        player = new User("kiana_msz", "kiana", "12345");
        Deck deckOfKiana = new Deck("deck of kiana");
        Deck deckOfHamraz = new Deck("deck of hamraz");
        player.addDeck(deckOfKiana);
        player.setActiveDeck(deckOfKiana);
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
            DeckController.getInstance(player).addCardToDeck(MonsterCard.HANIWA.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
        }
        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.CURTAIN_OF_DARK_ONES.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
        }
        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.GATE_GUARDIAN.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
        }
        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.HERO_OF_THE_EAST.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.SCANNER.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
        }
        rival = new User("hamriouz", "hamraz", "12345");
        rival.addDeck(deckOfHamraz);
        rival.setActiveDeck(deckOfHamraz);
        for (int i = 0; i < 3; i++) {
            rival.addCardToUsersAllCards(MonsterCard.YOMI_SHIP);
            DeckController.getInstance(rival).addCardToDeck(MonsterCard.YOMI_SHIP.getNamePascalCase(), "deck of hamraz", false, false);
        }
        for (int i = 0; i < 3; i++) {
            rival.addCardToUsersAllCards(MonsterCard.DARK_BLADE);
            DeckController.getInstance(rival).addCardToDeck(MonsterCard.DARK_BLADE.getNamePascalCase(), "deck of hamraz", false, false);
        }
        for (int i = 0; i < 3; i++) {
            rival.addCardToUsersAllCards(MonsterCard.DARK_MAGICIAN);
            DeckController.getInstance(rival).addCardToDeck(MonsterCard.DARK_MAGICIAN.getNamePascalCase(), "deck of hamraz", false, false);
        }
        rival.addCardToUsersAllCards(MonsterCard.ALEXANDRITE_DRAGON);
        DeckController.getInstance(rival).addCardToDeck(MonsterCard.ALEXANDRITE_DRAGON.getNamePascalCase(), "deck of hamraz", false, false);
        Deck secondDeckOfKiana = new Deck("second deck");
        player.addDeck(deckOfKiana);
        Deck thirdDeckOfKiana = new Deck("third deck");
        player.addDeck(secondDeckOfKiana);
        player.addDeck(thirdDeckOfKiana);
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
                DeckController.getInstance(player).addCardToDeck(MonsterCard.ALEXANDRITE_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck("Suijin", player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck("Fireyarou", player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BATTLE_OX.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BABY_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
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
                DeckController.getInstance(player).addCardToDeck(MonsterCard.HANIWA.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.CURTAIN_OF_DARK_ONES.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.GATE_GUARDIAN.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.HERO_OF_THE_EAST.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.SCANNER.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.ADVANCED_RITUAL_ART);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.ADVANCED_RITUAL_ART.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.BLACK_PENDANT);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.BLACK_PENDANT.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.CHANGE_OF_HEART);
            }
            DeckController.getInstance(player).addCardToDeck(SpellCard.CHANGE_OF_HEART.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.CLOSED_FOREST);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.CLOSED_FOREST.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.FOREST);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.FOREST.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.DARK_HOLE);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.DARK_HOLE.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.HARPIES_FEATHER_DUST);
            }

            DeckController.getInstance(player).addCardToDeck(SpellCard.HARPIES_FEATHER_DUST.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);

            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.MONSTER_REBORN);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.MONSTER_REBORN.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.RAIGEKI);
            }


            DeckController.getInstance(player).addCardToDeck(SpellCard.RAIGEKI.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(SpellCard.MAGNUM_SHIELD);
            }

            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(SpellCard.MAGNUM_SHIELD.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }


            for (int i = 0; i < 4; i++) {
                player.addCardToUsersAllCards(TrapCard.CALL_OF_THE_HAUNTED);
            }


            for (int i = 0; i < 3; i++) {
                DeckController.getInstance(player).addCardToDeck(TrapCard.CALL_OF_THE_HAUNTED.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
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


        }
        player.setActiveDeck(deckOfKiana);
        checker = new User("checkMolayi","molayitarinCheck","bemolaKeCheck");
        checker.addDeck(player.getDeckByName("deck of kiana"));
        checker.setScore(500);
        checker.addDeck(rival.getDeckByName(rival.getActiveDeck().getDeckName()));
        checker.setActiveDeck(checker.getDeckByName("deck of kiana"));
    }


    @Test
    @DisplayName("testForMinFinder")
    public void testForMinFinder() {
        DuelController duelController = new DuelController(player,rival,1);
        Assertions.assertEquals(4,duelController.minFinder(5,4));
        Assertions.assertEquals(0,duelController.minFinder(0,5));
    }


    @Test
    @DisplayName("unselect card check")
    public void unselectCardCheck() {
        SelectedCard selectedCard = new SelectedCard(MonsterCard.BITRON,BoardZone.MONSTERZONE,3,player);
        Card beforeChanging = selectedCard.getCard();
        Assertions.assertEquals(MonsterCard.BITRON,beforeChanging);
        selectedCard.setCard(MonsterCard.AXE_RAIDER);
        Card afterChanging = selectedCard.getCard();
        Assertions.assertEquals(MonsterCard.AXE_RAIDER,afterChanging);
        BoardZone beforeChangingBoardZone = selectedCard.getBoardZone();
        Assertions.assertEquals(BoardZone.MONSTERZONE,beforeChangingBoardZone);
        selectedCard.setBoardZone(BoardZone.FIELDZONE);
        Assertions.assertEquals(BoardZone.FIELDZONE,selectedCard.getBoardZone());
        Integer number = selectedCard.getNumber();
        Assertions.assertEquals(3,selectedCard.getNumber());
        selectedCard.setNumber(4);
        Assertions.assertEquals(4,selectedCard.getNumber());
        User ownerBeforeChanging = selectedCard.getOwner();
        Assertions.assertEquals(player,ownerBeforeChanging);
        selectedCard.setOwner(rival);
        Assertions.assertEquals(rival,selectedCard.getOwner());
    }


    @Test
    @DisplayName("get player and rival test")
    public void getPlayerAndRivalTest() {
        DuelController duelController = new DuelController(player, rival, 1);
        User expectedRival = duelController.getRival();
        User expectedPlayer = duelController.getPlayer();
        Assertions.assertEquals(expectedPlayer, player);
        Assertions.assertEquals(expectedRival, rival);
    }


    @Test
    @DisplayName("set player and rival test")
    public void setPlayerAndRivalTest() {
        DuelController duelController = new DuelController(player, rival, 1);
        User naghi = new User("naghi", "naghi", "naghi");
        User taghi = new User("taghi", "taghi", "taghi");
        duelController.setPlayer(naghi);
        duelController.setRival(taghi);
        User expectedRival = duelController.getRival();
        User expectedPlayer = duelController.getPlayer();
        SelectedCard selectedCard = duelController.getSelectedCard();
        Assertions.assertEquals(expectedPlayer, naghi);
        Assertions.assertEquals(expectedRival, taghi);
        duelController.setPlayer(player);
        duelController.setRival(rival);
    }

    @Test
    @DisplayName("test for scoreboard")
    public void testForScoreBoard() throws Exception {
        LogInController.getInstance().createUser("ali", "ali", "ali");
        LogInController.getInstance().createUser("gholi", "gholi", "gholi");
        LogInController.getInstance().createUser("soli", "soli", "soli");
        LogInController.getInstance().createUser("kholi", "kholi", "kholi");
        LogInController.getInstance().createUser("goli", "goli", "goli");
        Objects.requireNonNull(User.getUserByUsername("ali")).setScore(1000);
        Objects.requireNonNull(User.getUserByUsername("goli")).setScore(1000);
        Objects.requireNonNull(User.getUserByUsername("soli")).setScore(2000);
        Objects.requireNonNull(User.getUserByUsername("kholi")).setScore(3000);
        Objects.requireNonNull(User.getUserByUsername("gholi")).setScore(3000);
        ByteArrayInputStream in = new ByteArrayInputStream("menu show-current\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        ScoreBoardController.getInstance(player).showScoreboard();
        Assertions.assertEquals("1- gholi: 3000\n" +
                "1- kholi: 3000\n" +
                "3- soli: 2000\n" +
                "4- ali: 1000\n" +
                "4- goli: 1000\n" +
                "6- molayitarinCheck: 500\n" +
                "7- hamraz: 0\n" +
                "7- kiana: 0\n".replaceAll("\n","\r\n"),outContent.toString());
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("test for show all in shopController")
    public void shopControllerShowAllTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
//        ShopController.getInstance(player).showAll();
        Assertions.assertEquals("Advanced Ritual Art:3000\n" +
                "Alexandrite Dragon:2600\n" +
                "Axe Raider:3100\n" +
                "Baby Dragon:1600\n" +
                "Battle Ox:2900\n" +
                "Battle Warrior:1300\n" +
                "Beast King Barbaros:9200\n" +
                "Bitron:1000\n" +
                "Black Pendant:4300\n" +
                "Blue Eyes White Dragon:11300\n" +
                "Call Of The Haunted:3500\n" +
                "Change Of Heart:2500\n" +
                "Closed Forest:4300\n" +
                "Command Knight:2100\n" +
                "Crab Turtle:10200\n" +
                "Crawling Dragon:3900\n" +
                "Curtain Of Dark Ones:700\n" +
                "Dark Blade:3500\n" +
                "Dark Hole:2500\n" +
                "Dark Magician:8300\n" +
                "Exploder Dragon:1000\n" +
                "Feral Imp:2800\n" +
                "Fireyarou:2500\n" +
                "Flame Manipulator:1500\n" +
                "Forest:4300\n" +
                "Gate Guardian:20000\n" +
                "Haniwa:600\n" +
                "Harpies Feather Dust:2500\n" +
                "Herald Of Creation:2700\n" +
                "Hero Of The East:1700\n" +
                "Horn Imp:2500\n" +
                "Leotron:2500\n" +
                "Magic Cylinder:2000\n" +
                "Magic Jammer:3000\n" +
                "Magnum Shield:4300\n" +
                "Man Eater Bug:600\n" +
                "Marshmallon:700\n" +
                "Messenger Of Peace:4000\n" +
                "Mind Crush:2000\n" +
                "Mirage Dragon:2500\n" +
                "Mirror Force:2000\n" +
                "Monster Reborn:2000\n" +
                "Mystical Space Typhoon:3500\n" +
                "Negate Attack:3000\n" +
                "Pot Of Greed:2500\n" +
                "Raigeki:2500\n" +
                "Ring Of Defense:3500\n" +
                "Scanner:8000\n" +
                "Silver Fang:1700\n" +
                "Skull Guardian:7900\n" +
                "Slot Machine:7500\n" +
                "Solemn Warning:3000\n" +
                "Spell Absorption:4000\n" +
                "Spiral Serpent:11700\n" +
                "Suijin:8700\n" +
                "Supply Squad:4000\n" +
                "Sword Of Dark Destruction:4300\n" +
                "Sword Of Revealing Light:2500\n" +
                "Terraforming:2500\n" +
                "Terratiger The Empowered Warrior:3200\n" +
                "Texchanger:200\n" +
                "The Calculator:8000\n" +
                "The Tricky:4300\n" +
                "Time Seal:2000\n" +
                "Torrential Tribute:2000\n" +
                "Trap Hole:2000\n" +
                "Twin Twisters:3500\n" +
                "Umiiruka:4300\n" +
                "United We Stand:4300\n" +
                "Vanitys Emptiness:3500\n" +
                "Wall Of Revealing Light:3500\n" +
                "Warrior Dai Grepher:3400\n" +
                "Wattaildragon:5800\n" +
                "Wattkid:1300\n" +
                "Yami:4300\n" +
                "Yomi Ship:1700\n".replaceAll("\n", "\r\n"), outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("buy card test")
    public void buyCardTest() throws Exception {
        Assertions.assertThrows(CardNotFoundForController.class, new Executable() {
            @Override
            public void execute() throws Throwable {
//                ShopController.getInstance(player).buyCard("card molayi");
            }
        });
        int firstSize = player.getAllCards().size();
        int firstMoney = player.getMoney();
//        ShopController.getInstance(player).buyCard(MonsterCard.SILVER_FANG.getNamePascalCase());
        int secondSize = player.getAllCards().size();
        int secondMoney = player.getMoney();
        Assertions.assertEquals(1, secondSize - firstSize);
        Assertions.assertEquals(1700, firstMoney - secondMoney);
        Assertions.assertThrows(NotEnoughMoney.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                for (int i = 0; i < 100; i++) {
//                    ShopController.getInstance(player).buyCard(MonsterCard.SILVER_FANG.getNamePascalCase());
                }
            }
        });
    }

    @Test
    @DisplayName("change password test")
    public void changePasswordTest() throws Exception {
        Assertions.assertThrows(WrongPassword.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ProfileController.getInstance(player).changePassword("123", "1234");
            }
        });
        Assertions.assertThrows(SamePassword.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ProfileController.getInstance(player).changePassword("12345", "12345");
            }
        });
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        ProfileController.getInstance(player).changePassword("12345", "1234");
        Assertions.assertEquals("password changed successfully!\r\n", outContent.toString());
        Assertions.assertEquals("1234",player.getPassword());
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("change nickname test")
    public void changeNickNameTest() throws Exception {
        Assertions.assertThrows(RepetitiveNickname.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ProfileController.getInstance(player).changeNickname("hamraz");
            }
        });
        Assertions.assertThrows(SameNewNickname.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ProfileController.getInstance(player).changeNickname("kiana");
            }
        });
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        ProfileController.getInstance(player).changeNickname("kiaanaa");
        Assertions.assertEquals("nickname changed successfully!\r\n", outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("change username test")
    public void changeUsernameTest() throws Exception {
        Assertions.assertThrows(RepetitiveUsername.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ProfileController.getInstance(player).changeUsername("hamriouz");
            }
        });
        Assertions.assertThrows(SameNewUsername.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ProfileController.getInstance(player).changeUsername("kiana_msz");
            }
        });
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        ProfileController.getInstance(player).changeUsername("kiaanaa");
        Assertions.assertEquals("username changed successfully!\r\n", outContent.toString());
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("Remove card from deck test")
    public void removeCardFromDeck() throws Exception {
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).removeCardFromDeck(MonsterCard.AXE_RAIDER.getNamePascalCase(), "deck deck", false);
            }
        });
        Assertions.assertThrows(CardNotFoundForController.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).removeCardFromDeck("molayi tarin card", "deck of kiana", false);
            }
        });
        player.addCardToUsersAllCards(MonsterCard.AXE_RAIDER);
        Assertions.assertThrows(CardNotFoundInDeck.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).removeCardFromDeck(MonsterCard.AXE_RAIDER.getNamePascalCase(), "deck of kiana", false);
            }
        });
        Assertions.assertThrows(CardNotFoundInDeck.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).removeCardFromDeck(MonsterCard.AXE_RAIDER.getNamePascalCase(), "deck of kiana", true);
            }
        });
        DeckController.getInstance(player).addCardToDeck(MonsterCard.AXE_RAIDER.getNamePascalCase(), "deck of kiana", false, false);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).removeCardFromDeck(MonsterCard.AXE_RAIDER.getNamePascalCase(), "deck of kiana", false);
        Assertions.assertEquals("card removed form deck successfully\r\n", outContent.toString());
        System.setOut(sysOutBackup);

    }


    @Test
    @DisplayName("showAllCardCheck")
    public void showAllCardsCheck() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).showAllCards();
        Assertions.assertEquals("Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Battle Warrior:A warrior that fights with his bare hands!!!\n" +
                "Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.\n" +
                "Call Of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.\n" +
                "Change Of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.\n" +
                "Change Of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.\n" +
                "Change Of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.\n" +
                "Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.\n" +
                "Curtain Of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Curtain Of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Dark Hole:Destroy all monsters on the field.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Forest:All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Harpies Feather Dust:Destroy all Spells and Traps your opponent controls.\n" +
                "Harpies Feather Dust:Destroy all Spells and Traps your opponent controls.\n" +
                "Harpies Feather Dust:Destroy all Spells and Traps your opponent controls.\n" +
                "Hero Of The East:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Hero Of The East:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.\n" +
                "Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.\n" +
                "Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.\n" +
                "Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.\n" +
                "Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.\n" +
                "Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.\n" +
                "Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.\n" +
                "Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.\n" +
                "Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
                "Attack Position: It gains ATK equal to its original DEF.\n" +
                "Defense Position: It gains DEF equal to its original ATK.\n" +
                "Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.\n" +
                "Monster Reborn:Target 1 monster in either GY; Special Summon it.\n" +
                "Raigeki:Destroy all monsters your opponent controls.\n" +
                "Raigeki:Destroy all monsters your opponent controls.\n" +
                "Raigeki:Destroy all monsters your opponent controls.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "\n".replaceAll("\n","\r\n"),outContent.toString());
        System.setOut(sysOutBackup);

    }

    @Test
    @DisplayName("showMainDeckTest")
    public void showMainDeckTest() throws Exception {
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).showDeck("deck of kasra", false);
            }
        });
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).showDeck("deck to check full main", false);
        Assertions.assertEquals("Deck: deck to check full main\n" +
                "Main deck:\n" +
                "Monsters:\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Curtain Of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Curtain Of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Curtain Of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Hero Of The East:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Hero Of The East:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Hero Of The East:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Spell and Traps:\n" +
                "Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.\n" +
                "Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.\n" +
                "Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.\n" +
                "Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.\n" +
                "Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.\n" +
                "Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.\n" +
                "Call Of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.\n" +
                "Call Of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.\n" +
                "Call Of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.\n" +
                "Change Of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.\n" +
                "Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.\n" +
                "Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.\n" +
                "Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.\n" +
                "Dark Hole:Destroy all monsters on the field.\n" +
                "Dark Hole:Destroy all monsters on the field.\n" +
                "Dark Hole:Destroy all monsters on the field.\n" +
                "Forest:All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\n" +
                "Forest:All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\n" +
                "Forest:All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\n" +
                "Harpies Feather Dust:Destroy all Spells and Traps your opponent controls.\n" +
                "Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
                "Attack Position: It gains ATK equal to its original DEF.\n" +
                "Defense Position: It gains DEF equal to its original ATK.\n" +
                "Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
                "Attack Position: It gains ATK equal to its original DEF.\n" +
                "Defense Position: It gains DEF equal to its original ATK.\n" +
                "Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
                "Attack Position: It gains ATK equal to its original DEF.\n" +
                "Defense Position: It gains DEF equal to its original ATK.\n" +
                "Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.\n" +
                "Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.\n" +
                "Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.\n" +
                "Monster Reborn:Target 1 monster in either GY; Special Summon it.\n" +
                "Monster Reborn:Target 1 monster in either GY; Special Summon it.\n" +
                "Monster Reborn:Target 1 monster in either GY; Special Summon it.\n" +
                "Raigeki:Destroy all monsters your opponent controls.\n".replaceAll("\n","\r\n"),outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("showSideDeckTest")
    public void showSideDeckTest() throws Exception {
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).showDeck("deck of kasra", true);
            }
        });
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).showDeck("deck to check full main", true);
        Assertions.assertEquals("Deck: deck to check full main\n" +
                "Side deck:\n" +
                "Monsters:\n" +
                "Spell and Traps:\n".replaceAll("\n","\r\n"),outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("add card to main deck full")
    public void addCardFullMainDeck() throws Exception {
        player.setActiveDeck(player.getDeckByName("deck to check full main"));
        Assertions.assertThrows(ThreeSameCards.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BABY_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
        });
        Assertions.assertThrows(OneCardForLimited.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(SpellCard.CHANGE_OF_HEART.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
        });
        for (int i = 0; i < 4; i++) {
            player.addCardToUsersAllCards(TrapCard.MIND_CRUSH);
        }


        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(TrapCard.MIND_CRUSH.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
        }
        Assertions.assertThrows(FullMainDeck.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(TrapCard.MAGIC_CYLINDER.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
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
            DeckController.getInstance(player).addCardToDeck("Suijin", player.getActiveDeck().getDeckName(), true, false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck("Fireyarou", player.getActiveDeck().getDeckName(), true, false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.BATTLE_OX.getNamePascalCase(), player.getActiveDeck().getDeckName(), true, false);
        }

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.BABY_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(), true, false);
        }

        Assertions.assertThrows(ThreeSameCards.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.SUIJIN.getNamePascalCase(), player.getActiveDeck().getDeckName(), false, false);
            }
        });

        for (int i = 0; i < 3; i++) {
            DeckController.getInstance(player).addCardToDeck(MonsterCard.ALEXANDRITE_DRAGON.getNamePascalCase(), player.getActiveDeck().getDeckName(), true, false);
        }

        Assertions.assertThrows(FullSideDeck.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.BATTLE_WARRIOR.getNamePascalCase(), player.getActiveDeck().getDeckName(), true, false);
            }
        });
    }

    @Test
    @DisplayName("add card to player and deck")
    public void addCardToPlayerAndDeckTest() throws Exception {
        DeckController.getInstance(player).createDeck("deck to check add card");
        player.setActiveDeck(player.getDeckByName("deck to check add card"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        player.addCardToUsersAllCards(MonsterCard.SUIJIN);
        player.addCardToUsersAllCards(MonsterCard.SUIJIN);
        player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        DeckController.getInstance(player).addCardToDeck("Suijin", player.getActiveDeck().getDeckName(), false, false);
        DeckController.getInstance(player).addCardToDeck("Fireyarou", player.getActiveDeck().getDeckName(), false, false);
        DeckController.getInstance(player).addCardToDeck("Suijin", player.getActiveDeck().getDeckName(), true, false);
        DeckController.getInstance(player).addCardToDeck("Fireyarou", player.getActiveDeck().getDeckName(), true, false);
        Assertions.assertThrows(CardNotFoundInUser.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck(MonsterCard.FLAME_MANIPULATOR.getNamePascalCase(), player.getActiveDeck().getDeckName(), true, false);
            }
        });
        player.addCardToUsersAllCards(MonsterCard.FIREYAROU);
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).addCardToDeck("Fireyarou", "best deck ever", true, false);
            }
        });
        Assertions.assertEquals(2, player.getActiveDeck().getSideDeck().size());
        Assertions.assertEquals(2, player.getActiveDeck().getMainDeck().size());
        Assertions.assertEquals("card added to deck successfully\r\ncard added to deck successfully\r\ncard added to deck successfully\r\ncard added to deck successfully\r\n", outContent.toString());
        player.setActiveDeck(player.getDeckByName("deck of kiana"));
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("delete deck")
    public void deleteDeck() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        int firstSize = player.getAllDecks().size();
        DeckController.getInstance(player).deleteDeck("third deck");
        int secondSize = player.getAllDecks().size();
        Assertions.assertEquals(1, firstSize - secondSize);
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).deleteDeck("deck number 4");
            }
        });
        int thirdSize = player.getAllDecks().size();
        Assertions.assertEquals(0, thirdSize - secondSize);
        Assertions.assertEquals("deck deleted successfully\r\n", outContent.toString());
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("activateDeck")
    public void activateDeck() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).activateDeck("second deck");
        Assertions.assertEquals("deck activated successfully\r\n", outContent.toString());
        Assertions.assertThrows(DeckNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DeckController.getInstance(player).activateDeck("deck number 4");
            }
        });
        DeckController.getInstance(player).activateDeck("deck of kiana");
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("create deck repetitive deck name")
    public void createDeckRepetitiveDeckName() throws Exception {
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
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(player).createDeck("deck molayi");
        int secondSize = player.getAllDecks().size();
        Assertions.assertEquals(1, secondSize - firstSize);
        Assertions.assertEquals("deck created successfully!\r\n", outContent.toString());
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("testSelectCardPlayerMonsterZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardPlayerMonsterZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerMonsterZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardPlayerMonsterZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardPlayerMonsterZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerMonsterZone(0);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentMonsterZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardOpponentMonsterZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentMonsterZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentMonsterZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardOpponentMonsterZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentMonsterZone(0);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardPlayerTrapAndSpellZone with input more than 5 which should throw InvalidSelection")
    public void testSelectCardPlayerTrapAndSpellZoneMoreThan6() throws Exception {
        final DuelController duelController = new DuelController(player, rival, 1);
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
        final DuelController duelController = new DuelController(player, rival, 1);
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
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentTrapAndSpellZone(6);
            }
        });
    }

    @Test
    @DisplayName("testSelectCardOpponentTrapAndSpellZone with input less than 1 which should throw InvalidSelection")
    public void testSelectCardOpponentTrapAndSpellZoneLessThan1() throws Exception {
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardOpponentTrapAndSpellZone(0);
            }
        });
    }

    @Test
    @DisplayName("createUser everything is fine")
    public void createUserFine() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        LogInController.getInstance().createUser("CHECK", "check", "ChEcK");
        Assertions.assertEquals("user created successfully!\r\n", outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("Repetitive username check")
    public void RepetitiveUsername() throws Exception {
        Assertions.assertThrows(RepetitiveUsername.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().createUser("kiana_msz", "12345", "kiana");
            }
        });
    }

    @Test
    @DisplayName("Repetitive nickname check")
    public void RepetitiveNickname() throws Exception {
        Assertions.assertThrows(RepetitiveNickname.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().createUser("kiana_m", "12345", "kiana");
            }
        });
    }

    @Test
    @DisplayName("loginUser UserNotFound")
    public void loginUserUserNotFound() throws Exception {
        Assertions.assertThrows(UsernameNotFound.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().loginUser("kasra", "123");
            }
        });
    }

    @Test
    @DisplayName("loginUser WrongPassword")
    public void loginUserWrongPassword() throws Exception {
        Assertions.assertThrows(WrongPassword.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                LogInController.getInstance().loginUser("hamriouz", "123");
            }
        });
    }



    @Test
    @DisplayName("showAllDecksTest")
    public void showAllDecksTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        DeckController.getInstance(checker).showAllDecks();
        Assertions.assertEquals("Decks:\n" +
                "Active deck:\n" +
                "deck of kiana: main deck 15, side deck 0, invalid\n" +
                "Other decks:\n" +
                "deck of hamraz: main deck 10, side deck 0, invalid\n".replaceAll("\n","\r\n"),outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestDeck() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu show-current\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        MainController.getInstance(player).goToMenu("Deck");
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestScoreBoard() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu show-current\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        MainController.getInstance(player).goToMenu("ScoreBoard");
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestProfile() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu show-current\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        MainController.getInstance(player).goToMenu("Profile");
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestShop() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu show-current\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        MainController.getInstance(player).goToMenu("Shop");
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestImportExport() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu show-current\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        MainController.getInstance(player).goToMenu("ImportExport");
        System.setOut(sysOutBackup);
    }


}
*/
