package model;

import controller.DuelController;
import view.DuelView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SpellCard implements Cardable {


    MONSTER_REBORN(Icon.NORMAL, "Target 1 monster in either GY; Special Summon it.", Status.UNLIMITED, 2000) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                DuelView.printText("enter \"My\" or \"Rival\" to select graveyard");
                String graveyard = DuelView.scan();
                while (!graveyard.equals("cancel") && !graveyard.equals("My") && !graveyard.equals("Rival")) {
                    DuelView.printText("enter \"My\" or \"Rival\" to select graveyard");
                    graveyard = DuelView.scan();
                }
                switch (graveyard) {
                    case "My":
                        boolean hasMonster = false;
                        for (int i = 1; i <= duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
                            Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                hasMonster = true;
                                break;
                            }
                        }
                        if (!hasMonster) {
                            duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                            return true;
                        }
                        DuelView.printText("select one these cards by number:");
                        int monsterCounter = 1;
                        for (int i = 1; i <= duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
                            Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                DuelView.printText(monsterCounter + ": " + card.getNamePascalCase() + ": " + card.getDescription());
                                monsterCounter++;
                                break;
                            }
                        }
                        String cardNumber = DuelView.scan();
                        if (cardNumber.equals("cancel")) return false;
                        while (!cardNumber.matches("\\d+") || Integer.parseInt(cardNumber) < 1
                                || Integer.parseInt(cardNumber) > monsterCounter - 1) {
                            DuelView.printText("please Enter a valid Number!");
                            cardNumber = DuelView.scan();
                            if (cardNumber.equals("cancel")) return false;
                        }
                        monsterCounter = 1;
                        int i;
                        for (i = 1; i <= duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
                            Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                if (monsterCounter == Integer.parseInt(cardNumber))
                                    break;
                                else
                                    monsterCounter++;
                            }
                        }
                        Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i - 1);
                        DuelView.printText("enter the position you want to summon monster in(attack or defence)");
                        String position = DuelView.scan();
                        if (position.equals("cancel")) return false;
                        while (!(position.equals("attack") || position.equals("defence"))) {
                            DuelView.printText("please enter a valid position!");
                            position = DuelView.scan();
                            if (position.equals("cancel")) return false;
                        }
                        switch (position) {
                            case "attack":
                                position = "OO";
                                break;
                            case "defence":
                                position = "DO";
                                break;
                        }
                        int targetPlace = duelController.getPlayer().getBoard().putMonster((MonsterCard) card, position);
                        ((MonsterCard) card).takeAction(duelController, TakeActionCase.PUT_IN_MONSTERZONE, duelController.getPlayer(), targetPlace);
                        duelController.getPlayer().getBoard().getCardsInGraveyard().remove(i - 1);
                        duelController.setSelectedCard(null);
                        DuelView.printText("special summoned successfully");
                        duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                        return true;
                    case "Rival":
                        hasMonster = false;
                        for (i = 1; i <= duelController.getRival().getBoard().getCardsInGraveyard().size(); i++) {
                            card = duelController.getRival().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                hasMonster = true;
                                break;
                            }
                        }
                        if (!hasMonster) {
                            duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                            return true;
                        }
                        DuelView.printText("select one these cards by number:");
                        monsterCounter = 1;
                        for (i = 1; i <= duelController.getRival().getBoard().getCardsInGraveyard().size(); i++) {
                            card = duelController.getRival().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                DuelView.printText(monsterCounter + ": " + card.getNamePascalCase() + ": " + card.getDescription());
                                monsterCounter++;
                                break;
                            }
                        }
                        cardNumber = DuelView.scan();
                        if (cardNumber.equals("cancel")) return false;
                        while (!cardNumber.matches("\\d+") || Integer.parseInt(cardNumber) < 1
                                || Integer.parseInt(cardNumber) > monsterCounter - 1) {
                            DuelView.printText("please Enter a valid Number!");
                            cardNumber = DuelView.scan();
                            if (cardNumber.equals("cancel")) return false;
                        }
                        monsterCounter = 1;
                        for (i = 1; i <= duelController.getRival().getBoard().getCardsInGraveyard().size(); i++) {
                            card = duelController.getRival().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                if (monsterCounter == Integer.parseInt(cardNumber))
                                    break;
                                else
                                    monsterCounter++;
                            }
                        }
                        card = duelController.getRival().getBoard().getCardsInGraveyard().get(i - 1);
                        DuelView.printText("enter the position you want to summon monster in(attack or defence)");
                        position = DuelView.scan();
                        if (position.equals("cancel")) return false;
                        while (!(position.equals("attack") || position.equals("defence"))) {
                            DuelView.printText("please enter a valid position!");
                            position = DuelView.scan();
                            if (position.equals("cancel")) return false;
                        }
                        switch (position) {
                            case "attack":
                                position = "OO";
                                break;
                            case "defence":
                                position = "DO";
                                break;
                        }
                        targetPlace = duelController.getRival().getBoard().putMonster((MonsterCard) card, position);
                        ((MonsterCard) card).takeAction(duelController, TakeActionCase.PUT_IN_MONSTERZONE, duelController.getPlayer(), targetPlace);
                        duelController.getRival().getBoard().getCardsInGraveyard().remove(i - 1);
                        duelController.setSelectedCard(null);
                        DuelView.printText("special summoned successfully");
                        duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                        return true;
                    default:
                        return false;
                }
            } else return true;
        }
    },

    TERRAFORMING(Icon.NORMAL, "Add 1 Field Spell from your Deck to your hand.", Status.LIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                boolean hasFieldSpell = false;
                for (Cardable card : duelController.getPlayer().getGameDeck().getMainDeck()) {
                    if (card instanceof SpellCard && ((SpellCard) card).getIcon().equals(Icon.FIELD)) {
                        hasFieldSpell = true;
                        break;
                    }
                }
                if (!hasFieldSpell) {
                    duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                    return true;
                }
                DuelView.printText("select one of these Field Spell cards from your deck to add to your hand:");
                int cardCounter = 1;
                for (Cardable card : duelController.getPlayer().getGameDeck().getMainDeck()) {
                    if (card instanceof SpellCard && ((SpellCard) card).getIcon().equals(Icon.FIELD)) {
                        DuelView.printText(cardCounter + ": " + card.getName() + ": " + card.getDescription());
                        cardCounter++;
                    }
                }
                String choice = DuelView.scan();
                if (choice.equals("cancel")) return false;
                while (!choice.matches("\\d+") || Integer.parseInt(choice) < 1
                        || Integer.parseInt(choice) > cardCounter - 1) {
                    DuelView.printText("please Enter a valid Number!");
                    choice = DuelView.scan();
                    if (choice.equals("cancel")) return false;
                }
                cardCounter = 1;
                int i;
                for (i = 1; i <= duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
                    Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
                    if (card instanceof SpellCard && ((SpellCard) card).getIcon().equals(Icon.FIELD)) {
                        if (cardCounter == Integer.parseInt(choice))
                            break;
                        else
                            cardCounter++;
                    }
                }
                Cardable card = duelController.getPlayer().getGameDeck().getMainDeck().get(i - 1);
                duelController.getPlayer().getBoard().getCardsInHand().add(card);
                duelController.getPlayer().getGameDeck().getMainDeck().remove(i - 1);
                duelController.setSelectedCard(null);
                DuelView.printText("card added to hand successfully");
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    POT_OF_GREED(Icon.NORMAL, "Draw 2 cards.", Status.LIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                List<Cardable> deck = duelController.getPlayer().getGameDeck().getMainDeck();
                Cardable card1 = deck.get(deck.size() - 1);
                Cardable card2 = deck.get(deck.size() - 2);
                duelController.getPlayer().getBoard().getCardsInHand().add(card1);
                duelController.getPlayer().getBoard().getCardsInHand().add(card2);
                deck.remove(card1);
                deck.remove(card2);
                DuelView.printText("Spell activated successfully");
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    RAIGEKI(Icon.NORMAL, "Destroy all monsters your opponent controls.", Status.LIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                for (int i = 0; i < 5; i++) {
                    if (duelController.getPlayer().getBoard().getMonsterByNumber(i) != null) {
                        duelController.getPlayer().getBoard().getMonsterByNumber(i).takeAction(duelController, TakeActionCase.REMOVE_FROM_MONSTERZONE, duelController.getRival(), i);
                        duelController.getPlayer().getBoard().removeMonster(i);
                        duelController.setMonsterAttackRival(i, null);
                    }
                }
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    CHANGE_OF_HEART(Icon.NORMAL, "Target 1 monster your opponent controls; take control of it until the End Phase.",
            Status.LIMITED, 2500),

    HARPIES_FEATHER_DUST(Icon.NORMAL, "Destroy all Spells and Traps your opponent controls.", Status.LIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                for (int i = 0; i < 5; i++) {
                    if (duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i) != null) {
                        duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i).takeAction(duelController, TakeActionCase.REMOVE_FROM_SPELLTRAPZONE, duelController.getRival(), i);
                        duelController.getPlayer().getBoard().removeSpellOrTrap(i);
                    }
                }
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    SWORD_OF_REVEALING_LIGHT(Icon.NORMAL, "After this card's activation, it remains on the field, but destroy " +
            "it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls" +
            " a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, your" +
            " opponent's monsters cannot declare an attack.", Status.UNLIMITED, 2500),

    DARK_HOLE(Icon.NORMAL, "Destroy all monsters on the field.", Status.UNLIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                for (int i = 0; i < 5; i++) {
                    if (duelController.getPlayer().getBoard().getMonsterByNumber(i) != null) {
                        duelController.getPlayer().getBoard().getMonsterByNumber(i).takeAction(duelController, TakeActionCase.REMOVE_FROM_MONSTERZONE, duelController.getRival(), i);
                        duelController.getPlayer().getBoard().removeMonster(i);
                        duelController.setMonsterAttackRival(i, null);
                    }
                    if (duelController.getRival().getBoard().getMonsterByNumber(i) != null) {
                        duelController.getRival().getBoard().getMonsterByNumber(i).takeAction(duelController, TakeActionCase.REMOVE_FROM_MONSTERZONE, duelController.getRival(), i);
                        duelController.getRival().getBoard().removeMonster(i);
                        duelController.setMonsterAttackPlayer(i, null);
                    }
                }
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    SUPPLY_SQUAD(Icon.CONTINUOUS, "Once per turn, if a monster(s) you control is destroyed by battle or card" +
            " effect: Draw 1 card.", Status.UNLIMITED, 4000),

    SPELL_ABSORPTION(Icon.CONTINUOUS, "Each time a Spell Card is activated, gain 500 Life Points immediately " +
            "after it resolves.", Status.UNLIMITED, 4000),

    MESSENGER_OF_PEACE(Icon.CONTINUOUS, "Monsters with 1500 or more ATK cannot declare an attack. Once per turn," +
            " during your Standby Phase, pay 100 LP or destroy this card.", Status.UNLIMITED, 4000),

    TWIN_TWISTER(Icon.QUICK_PLAY, "Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them."
            , Status.UNLIMITED, 3500),

    MYSTICAL_SPCAE_TYPHOON(Icon.QUICK_PLAY, "Target 1 Spell/Trap on the field; destroy that target.",
            Status.UNLIMITED, 3500),

    RING_OF_DEFENSE(Icon.QUICK_PLAY, "When a Trap effect that inflicts damage is activated: Make that effect " +
            "damage 0.", Status.UNLIMITED, 3500),

    YAMI(Icon.FIELD, "All Fiend and Spellcaster monsters on the field gain 200 ATK/DEF, also all Fairy monsters" +
            " on the field lose 200 ATK/DEF.", Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.FIEND) || playerMonster.getMonsterType().equals(MonsterType.SPELLCASTER)) {
                            duelController.changePlayerAttackPoint(i, 200);
                            duelController.changePlayerDefencePoint(i, 200);
                        } else if (playerMonster.getMonsterType().equals(MonsterType.FAIRY)) {
                            duelController.changePlayerAttackPoint(i, -200);
                            duelController.changePlayerDefencePoint(i, -200);
                        }
                    }
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.FIEND) || rivalMonster.getMonsterType().equals(MonsterType.SPELLCASTER)) {
                            duelController.changeRivalAttackPoint(i, 200);
                            duelController.changeRivalDefencePoint(i, 200);
                        } else if (rivalMonster.getMonsterType().equals(MonsterType.FAIRY)) {
                            duelController.changeRivalAttackPoint(i, -200);
                            duelController.changeRivalDefencePoint(i, -200);
                        }
                    }
                }
            } else if (takeActionCase.equals(TakeActionCase.REMOVE_FROM_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.FIEND) || playerMonster.getMonsterType().equals(MonsterType.SPELLCASTER)) {
                            duelController.changePlayerAttackPoint(i, -200);
                            duelController.changePlayerDefencePoint(i, -200);
                        } else if (playerMonster.getMonsterType().equals(MonsterType.FAIRY)) {
                            duelController.changePlayerAttackPoint(i, 200);
                            duelController.changePlayerDefencePoint(i, 200);
                        }
                    }
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.FIEND) || rivalMonster.getMonsterType().equals(MonsterType.SPELLCASTER)) {
                            duelController.changeRivalAttackPoint(i, -200);
                            duelController.changeRivalDefencePoint(i, -200);
                        } else if (rivalMonster.getMonsterType().equals(MonsterType.FAIRY)) {
                            duelController.changeRivalAttackPoint(i, 200);
                            duelController.changeRivalDefencePoint(i, 200);
                        }
                    }
                }
            }
            return true;
        }
    },

    FOREST(Icon.FIELD, "All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.",
            Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.INSECT) || playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(i, 200);
                            duelController.changePlayerDefencePoint(i, 200);
                        }
                    }
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.INSECT) || rivalMonster.getMonsterType().equals(MonsterType.BEAST) || rivalMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changeRivalAttackPoint(i, 200);
                            duelController.changeRivalDefencePoint(i, 200);
                        }
                    }
                }
            } else if (takeActionCase.equals(TakeActionCase.REMOVE_FROM_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.INSECT) || playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(i, -200);
                            duelController.changePlayerDefencePoint(i, -200);
                        }
                    }
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.INSECT) || rivalMonster.getMonsterType().equals(MonsterType.BEAST) || rivalMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changeRivalAttackPoint(i, -200);
                            duelController.changeRivalDefencePoint(i, -200);
                        }
                    }
                }
            }
            return true;
        }

    },

    CLOSED_FOREST(Icon.FIELD, "All Beast-Type monsters you control gain 100 ATK for each monster in your " +
            "Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn " +
            "this card is destroyed.", Status.UNLIMITED, 4300) {
        //TODO change graveyard if necessary
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            int amount = 0;
            for (Cardable card : duelController.getPlayer().getBoard().getCardsInGraveyard()) {
                if (card instanceof MonsterCard) {
                    amount++;
                }
            }
            amount = amount * 100;
            if (takeActionCase.equals(TakeActionCase.PUT_IN_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(i, amount);
                        }
                    }
                }
            } else if (takeActionCase.equals(TakeActionCase.REMOVE_FROM_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(i, -1 * amount);
                        }
                    }
                }
            }
            return true;
        }

    },

    UMIIRUKA(Icon.FIELD, "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 " +
            "points.", Status.UNLIMITED, 4300),

    SWORD_OF_DARK_DESTRUCTION(Icon.EQUIP, "A DARK monster equipped with this card increases its ATK by 400 " +
            "points and decreases its DEF by 200 points.", Status.UNLIMITED, 4300),

    BLACK_PENDANT(Icon.EQUIP, "The equipped monster gains 500 ATK. When this card is sent from the field to the" +
            " Graveyard: Inflict 500 damage to your opponent.", Status.UNLIMITED, 4300),

    UNITED_WE_STAND(Icon.EQUIP, "The equipped monster gains 800 ATK/DEF for each face-up monster you control.",
            Status.UNLIMITED, 4300),

    MAGNUM_SHIELD(Icon.EQUIP, "Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
            "Attack Position: It gains ATK equal to its original DEF.\n" +
            "Defense Position: It gains DEF equal to its original ATK.", Status.UNLIMITED, 4300),

    ADVANCED_RITUAL_ART(Icon.RITUAL, "This card can be used to Ritual Summon any 1 Ritual Monster. You must " +
            "also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual" +
            " Monster.", Status.UNLIMITED, 3000);


    private final Icon icon;
    private final Status status;
    private final String description;
    private final int price;

    SpellCard(Icon icon, String description, Status status, int price) {
        this.icon = icon;
        this.status = status;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name();
    }

    public abstract boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber);

    public String getNamePascalCase() {
        String name = this.name().charAt(0) + this.name().substring(1).toLowerCase();
        Pattern pattern = Pattern.compile("_([a-z])[a-z]+");
        Matcher matcher = pattern.matcher(name);
        while (matcher.find())
            name = name.replace("_" + matcher.group(1), "_" + matcher.group(1).toUpperCase());
        name = name.replaceAll("_", " ");
        return name;
    }

    @Override
    public String toString() {
        String name = this.getNamePascalCase();
        String toReturn = "Name: " + name + "\n" +
                "Spell" + "\n";
        String type = this.icon.getNamePascalCase();
        toReturn = toReturn + "Type: " + type + "\n" +
                "Description: " + this.description;
        return toReturn;
    }
}
