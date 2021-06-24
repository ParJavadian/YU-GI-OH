//package controller;

import controller.DeckController;
import controller.exeption.*;
import model.*;
import controller.*;
import view.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import view.DeckView;
import view.LogInView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;


public class test {

    static User player;
    static User rival;

    @BeforeAll
    static void toBeDoneBefore() throws Exception {
        player = new User("kiana_msz", "kiana", "12345");
        Deck deck = new Deck("deck of kiana");
        Deck deck1 = new Deck("deck of hamraz");
        player.addDeck(deck);
        player.setActiveDeck(deck);
        rival = new User("hamriouz", "hamraz", "12345");
        rival.addDeck(deck1);
        rival.setActiveDeck(deck1);
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
        player.setActiveDeck(player.getDeckByName("deck of kiana"));
    }

//    @Test
//    @DisplayName("game")
//    public void gameTest(){
//        DuelController duelController = new DuelController(player,rival,3);
//        //duelController.startNewGame(null);
//        //duelController.startNewGame(player);
////        duelController.startNewGame(rival);
//    }

//    @Test
//    @DisplayName("startDrawPhaseTest")
//    public void startDrawPhaseTest(){
//        DuelController duelController = new DuelController(player,rival,3);
//        duelController.startDrawPhase(false);
//    }

    @Test
    @DisplayName("testForMinFinder")
    public void testForMinFinder() {
        DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertEquals(4, duelController.minFinder(5, 4));
        Assertions.assertEquals(0, duelController.minFinder(0, 5));
    }


    @Test
    @DisplayName("unselect card check")
    public void unselectCardCheck() {
        //BoardZone boardZone = new BoardZone();
        SelectedCard selectedCard = new SelectedCard(MonsterCard.BITRON, BoardZone.MONSTERZONE, 3, player);
        Cardable beforeChanging = selectedCard.getCard();
        Assertions.assertEquals(MonsterCard.BITRON, beforeChanging);
        selectedCard.setCard(MonsterCard.AXE_RAIDER);
        Cardable afterChanging = selectedCard.getCard();
        Assertions.assertEquals(MonsterCard.AXE_RAIDER, afterChanging);
        BoardZone beforeChangingBoardZone = selectedCard.getBoardZone();
        Assertions.assertEquals(BoardZone.MONSTERZONE, beforeChangingBoardZone);
        selectedCard.setBoardZone(BoardZone.FIELDZONE);
        Assertions.assertEquals(BoardZone.FIELDZONE, selectedCard.getBoardZone());
        Integer number = selectedCard.getNumber();
        Assertions.assertEquals(3, selectedCard.getNumber());
        selectedCard.setNumber(4);
        Assertions.assertEquals(4, selectedCard.getNumber());
        User ownerBeforeChanging = selectedCard.getOwner();
        Assertions.assertEquals(player, ownerBeforeChanging);
        selectedCard.setOwner(rival);
        Assertions.assertEquals(rival, selectedCard.getOwner());


    }


//    @Test
//    @DisplayName("test for exchange card between main and side")
//    public void testExchangeCardBetweenMainAndSide() throws Exception {
//        player.addCardToUsersAllCards(MonsterCard.CRAWLING_DRAGON);
//        DeckController.getInstance(player).addCardToDeck(MonsterCard.CRAWLING_DRAGON.getNamePascalCase(),"deck to check full main",true, false );
//        DeckController.getInstance(player).showDeck("deck to check full main",false);
//        DeckController.getInstance(player).showDeck(player.getActiveDeck().getDeckName(),true);
//        DuelController duelController = new DuelController(player,rival,3);
//        ByteArrayInputStream in = new ByteArrayInputStream("YES\nMonster Reborn\nCrawling Dragon".getBytes());
//        System.setIn(in);
//        rival.setLifePoint(0);
//
//        duelController.manageEndGame();
//
//
//
//
//    }

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

    /*@Test
    @DisplayName("manage end game test")
    public void manageEndGameTest(){
        User hossein = new User("hossein","hossein","hossein");
        User hasan = new User("hasan","hasan","hasan");
        //Deck deckMolayi = new Deck("deck molayi");
        hasan.addDeck(player.getDeckByName("deck to check full main"));
        hossein.addDeck(player.getDeckByName("deck to check full main"));
        hasan.setActiveDeck(hasan.getDeckByName("deck to check full main"));
        hossein.setActiveDeck(hossein.getDeckByName("deck to check full main"));
        ByteArrayInputStream in = new ByteArrayInputStream("no\nno\nno\n".getBytes());
        System.setIn(in);
        DuelController duelController = new DuelController(hasan,hossein,3);
        hasan.setLifePoint(0);
        duelController.manageEndGame();
        hasan.setLifePoint(0);
        duelController.manageEndGame();
//        hasan.setLifePoint(0);
//        duelController.manageEndGame();
    }*/


//    @Test
//    @DisplayName("getShouldEndGameTes")

    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestDeck() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu enter Deck\nmenu exit\nuser logout\n".getBytes());
        System.setIn(in);
        MainView.getInstance(player).getCommandForMain();
        //MainController.getInstance(player).goToMenu("Deck");
    }

    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestScoreBoard() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nuser logout\n".getBytes());
        System.setIn(in);
        MainController.getInstance(player).goToMenu("ScoreBoard");
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestProfile() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nuser logout\n".getBytes());
        System.setIn(in);
        MainController.getInstance(player).goToMenu("Profile");
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestShop() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nuser logout\n".getBytes());
        System.setIn(in);
        MainController.getInstance(player).goToMenu("Shop");
    }


    @Test
    @DisplayName("go to menu test")
    public void goToMenuTestImportExport() {
        ByteArrayInputStream in = new ByteArrayInputStream("menu exit\nuser logout\n".getBytes());
        System.setIn(in);
        MainController.getInstance(player).goToMenu("ImportExport");
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
        ScoreBoardController.getInstance(player).showScoreboard();
        //todo Assertions.assertEquals("");

    }

    @Test
    @DisplayName("test for show all in shopController")
    public void shopControllerShowAllTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        ShopController.getInstance(player).showAll();
        Assertions.assertEquals("Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.\n" +
                "Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this dragon has hit the jackpot... whether they know it or not.\n" +
                "Axe Raider:An axe-wielding monster of tremendous strength and agility.\n" +
                "Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.\n" +
                "Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.\n" +
                "Battle Warrior:A warrior that fights with his bare hands!!!\n" +
                "Beast King Barbaros:You can Normal Summon/Set this card without Tributing, but its original ATK becomes 1900. You can Tribute 3 monsters to Tribute Summon (but not Set) this card. If Summoned this way: Destroy all cards your opponent controls.\n" +
                "Bitron:A new species found in electronic space. There's not much information on it.\n" +
                "Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.\n" +
                "Blue Eyes White Dragon:This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have faced this awesome creature and lived to tell the tale.\n" +
                "Call Of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position. When this card leaves the field, destroy that monster. When that monster is destroyed, destroy this card.\n" +
                "Change Of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.\n" +
                "Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn this card is destroyed.\n" +
                "Command Knight:All Warrior-Type monsters you control gain 400 ATK. If you control another monster, monsters your opponent controls cannot target this card for an attack.\n" +
                "Crab Turtle:This monster can only be Ritual Summoned with the Ritual Spell Card, \"Turtle Oath\". You must also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.\n" +
                "Crawling Dragon:This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.\n" +
                "Curtain Of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.\n" +
                "Dark Blade:They say he is a dragon-manipulating warrior from the dark world. His attack is tremendous, using his great swords with vicious power.\n" +
                "Dark Hole:Destroy all monsters on the field.\n" +
                "Dark Magician:The ultimate wizard in terms of attack and defense.\n" +
                "Exploder Dragon:If this card is destroyed by battle and sent to the Graveyard: Destroy the monster that destroyed it. Neither player takes any battle damage from attacks involving this attacking card.\n" +
                "Feral Imp:A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.\n" +
                "Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.\n" +
                "Flame Manipulator:This Spellcaster attacks enemies with fire-related spells such as \"Sea of Flames\" and \"Wall of Fire\".\n" +
                "Forest:All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.\n" +
                "Gate Guardian:Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 \"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".\n" +
                "Haniwa:An earthen figure that protects the tomb of an ancient ruler.\n" +
                "Harpies Feather Dust:Destroy all Spells and Traps your opponent controls.\n" +
                "Herald Of Creation:Once per turn: You can discard 1 card, then target 1 Level 7 or higher monster in your Graveyard; add that target to your hand.\n" +
                "Hero Of The East:Feel da strength ah dis sword-swinging samurai from da Far East.\n" +
                "Horn Imp:A small fiend that dwells in the dark, its single horn makes it a formidable opponent.\n" +
                "Leotron:A territorial electronic monster that guards its own domain.\n" +
                "Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.\n" +
                "Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.\n" +
                "Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
                "Attack Position: It gains ATK equal to its original DEF.\n" +
                "Defense Position: It gains DEF equal to its original ATK.\n" +
                "Man Eater Bug:FLIP: Target 1 monster on the field; destroy that target.\n" +
                "Marshmallon:Cannot be destroyed by battle. After damage calculation, if this card was attacked, and was face-down at the start of the Damage Step: The attacking player takes 1000 damage.\n" +
                "Messenger Of Peace:Monsters with 1500 or more ATK cannot declare an attack. Once per turn, during your Standby Phase, pay 100 LP or destroy this card.\n" +
                "Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card.\n" +
                "Mirage Dragon:Your opponent cannot activate Trap Cards during the Battle Phase.\n" +
                "Mirror Force:When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.\n" +
                "Monster Reborn:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "Mystical Spcae Typhoon:Target 1 Spell/Trap on the field; destroy that target.\n" +
                "Negate Attack:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, then end the Battle Phase\n" +
                "Pot Of Greed:Draw 2 cards.\n" +
                "Raigeki:Destroy all monsters your opponent controls.\n" +
                "Ring Of Defense:When a Trap effect that inflicts damage is activated: Make that effect damage 0.\n" +
                "Scanner:Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the End Phase, this card's name is treated as the selected monster's name, and this card has the same Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field while this effect is applied, remove it from play.\n" +
                "Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.\n" +
                "Skull Guardian:This monster can only be Ritual Summoned with the Ritual Spell Card, \"Novox's Prayer\". You must also offer monsters whose total Level Stars equal 7 or more as a Tribute from the field or your hand.\n" +
                "Slot Machine:The machine's ability is said to vary according to its slot results.\n" +
                "Solemn Warning:When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster effect, is activated that includes an effect that Special Summons a monster(s): Pay 2000 LP; negate the Summon or activation, and if you do, destroy it.\n" +
                "Spell Absorption:Each time a Spell Card is activated, gain 500 Life Points immediately after it resolves.\n" +
                "Spiral Serpent:When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at work. No one has ever escaped its dreaded Spiral Wave to accurately describe the terror they experienced.\n" +
                "Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). This effect can only be used once while this card is face-up on the field.\n" +
                "Supply Squad:Once per turn, if a monster(s) you control is destroyed by battle or card effect: Draw 1 card.\n" +
                "Sword Of Dark Destruction:A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.\n" +
                "Sword Of Revealing Light:After this card's activation, it remains on the field, but destroy it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, your opponent's monsters cannot declare an attack.\n" +
                "Terraforming:Add 1 Field Spell from your Deck to your hand.\n" +
                "Terratiger The Empowered Warrior:When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower Normal Monster from your hand in Defense Position.\n" +
                "Texchanger:Once per turn, when your monster is targeted for an attack: You can negate that attack, then Special Summon 1 Cyberse Normal Monster from your hand, Deck, or GY.\n" +
                "The Calculator:The ATK of this card is the combined Levels of all face-up monsters you control x 300.\n" +
                "The Tricky:You can Special Summon this card (from your hand) by discarding 1 card.\n" +
                "Time Seal:Skip the Draw Phase of your opponent's next turn.\n" +
                "Torrential Tribute:When a monster(s) is Summoned: Destroy all monsters on the field.\n" +
                "Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.\n" +
                "Twin Twister:Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them.\n" +
                "Umiiruka:Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.\n" +
                "United We Stand:The equipped monster gains 800 ATK/DEF for each face-up monster you control.\n" +
                "Vanitys Emptiness:Neither player can Special Summon monsters. If a card is sent from the Deck or the field to your Graveyard: Destroy this card.\n" +
                "Wall Of Revealing Light:Neither player can Special Summon monsters. If a card is sent from the Deck or the field to your Graveyard: Destroy this card.\n" +
                "Warrior Dai Grepher:The warrior who can manipulate dragons. Nobody knows his mysterious past.\n" +
                "Wattaildragon:Capable of indefinite flight. Attacks by wrapping its body with electricity and ramming into opponents.\n" +
                "IMPORTANT: Capturing the \"Wattaildragon\" is forbidden by the Ancient Rules and is a Level 6 offense, the minimum sentence for which is imprisonment for no less than 2500 heliocycles.\n" +
                "Wattkid:A creature that electrocutes opponents with bolts of lightning.\n" +
                "Yami:All Fiend and Spellcaster monsters on the field gain 200 ATK/DEF, also all Fairy monsters on the field lose 200 ATK/DEF.\n" +
                "Yomi Ship:If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.\n".replaceAll("\n", "\r\n"), outContent.toString());
        System.setOut(sysOutBackup);
    }


    @Test
    @DisplayName("buy card test")
    public void buyCardTest() throws Exception {
        Assertions.assertThrows(CardNotFoundForController.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ShopController.getInstance(player).buyCard("card molayi");
            }
        });
        int firstSize = player.getAllCards().size();
        int firstMoney = player.getMoney();
        ShopController.getInstance(player).buyCard(MonsterCard.SILVER_FANG.getNamePascalCase());
        int secondSize = player.getAllCards().size();
        int secondMoney = player.getMoney();
        Assertions.assertEquals(1, secondSize - firstSize);
        Assertions.assertEquals(1700, firstMoney - secondMoney);
        Assertions.assertThrows(NotEnoughMoney.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                for (int i = 0; i < 100; i++) {
                    ShopController.getInstance(player).buyCard(MonsterCard.SILVER_FANG.getNamePascalCase());
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
        Assertions.assertEquals("1234", player.getPassword());
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
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream sysOutBackup = System.out;
        System.setOut(new PrintStream(outContent));
        ProfileController.getInstance(player).changeNickname("kiaanaa");
        Assertions.assertEquals("nickname changed successfully!\r\n", outContent.toString());
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
//        DeckController.getInstance(player).
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
    @DisplayName("showAllDecksCheck")
    public void showAllDecksCheck() {
        DeckController.getInstance(player).showAllDecks();
    }


    @Test
    @DisplayName("showCardTest")
    public void showCardTest() {
        DeckController.getInstance(player).showAllCards();
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
        DeckController.getInstance(player).showDeck("deck to check full main", false);
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
        DeckController.getInstance(player).showDeck("deck to check full main", true);
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
        /*ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.out.println(player.getActiveDeck().getMainSize() + " " + player.getUsername());
        Assertions.assertEquals("60 kiana_msz\r\n", outContent.toString());*/
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
        //player.addDeck(player.getDeckByName("deck molayi"));
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
        /*ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.out.println(player.getActiveDeck().getMainSize() + " " + player.getUsername());
        Assertions.assertEquals("60 kiana_msz\r\n", outContent.toString());*/
        final DuelController duelController = new DuelController(player, rival, 1);
        Assertions.assertThrows(InvalidSelection.class, new Executable() {
            public void execute() throws Throwable {
                duelController.selectCardPlayerTrapAndSpellZone(0);
            }
        });
        /*System.setOut(new PrintStream(outContent));
        System.out.println(player.getActiveDeck().getMainSize() + " " + player.getUsername());
        Assertions.assertEquals("teste mozakhraf\r\n", outContent.toString());*/
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

    /*@Test
    @DisplayName("test menu enter")
    public void testMenuEnter() {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        PrintStream sysOutBackup = System.out;
        ByteArrayInputStream in = new ByteArrayInputStream("\nmenu enter Deck\nmenu exit\n".getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        LogInView.getInstance().getCommandForLogin();
        Assertions.assertEquals("please login first\r\n", out.toString());
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
        System.out.println("5");
    }*/

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

//    @Test
//    @DisplayName("")
//    public void


}
