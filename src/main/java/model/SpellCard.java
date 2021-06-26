package model;

import controller.DuelController;
import view.DuelView;

import java.util.ArrayList;
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
                        for (int i = 0; i < duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
                            Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                hasMonster = true;
                                break;
                            }
                        }
                        if (!hasMonster) {
                            DuelView.printText("Graveyard is empty!");
//                            duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                            return false;
                        }
                        DuelView.printText("select one these cards by number:");
                        int monsterCounter = 1;
                        for (int i = 0; i < duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
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
                        for (i = 0; i < duelController.getPlayer().getBoard().getCardsInGraveyard().size(); i++) {
                            Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                if (monsterCounter == Integer.parseInt(cardNumber))
                                    break;
                                else
                                    monsterCounter++;
                            }
                        }
                        Cardable card = duelController.getPlayer().getBoard().getCardsInGraveyard().get(i);
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
                        ((MonsterCard) card).takeAction(duelController, TakeActionCase.SUMMONED, duelController.getPlayer(), targetPlace);
                        duelController.getPlayer().getBoard().getCardsInGraveyard().remove(i);
                        DuelView.printText("special summoned successfully");
                        SpellAction.getInstance().enableSpellAbsorptions(duelController);
                        duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                        return true;
                    case "Rival":
                        hasMonster = false;
                        for (i = 0; i < duelController.getRival().getBoard().getCardsInGraveyard().size(); i++) {
                            card = duelController.getRival().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                hasMonster = true;
                                break;
                            }
                        }
                        if (!hasMonster) {
                            DuelView.printText("Graveyard is empty!");
//                            duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                            return false;
                        }
                        DuelView.printText("select one these cards by number:");
                        monsterCounter = 1;
                        for (i = 0; i < duelController.getRival().getBoard().getCardsInGraveyard().size(); i++) {
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
                        for (i = 0; i < duelController.getRival().getBoard().getCardsInGraveyard().size(); i++) {
                            card = duelController.getRival().getBoard().getCardsInGraveyard().get(i);
                            if (card instanceof MonsterCard) {
                                if (monsterCounter == Integer.parseInt(cardNumber))
                                    break;
                                else
                                    monsterCounter++;
                            }
                        }
                        card = duelController.getRival().getBoard().getCardsInGraveyard().get(i);
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
                        targetPlace = duelController.getPlayer().getBoard().putMonster((MonsterCard) card, position);
                        ((MonsterCard) card).takeAction(duelController, TakeActionCase.SUMMONED, duelController.getPlayer(), targetPlace);
                        duelController.getRival().getBoard().getCardsInGraveyard().remove(i);
                        DuelView.printText("special summoned successfully");
                        SpellAction.getInstance().enableSpellAbsorptions(duelController);
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
                    DuelView.printText("You have no field spell card in your deck!!");
//                            duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                    return false;
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
                DuelView.printText("card added to hand successfully");
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
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
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
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
                        duelController.getPlayer().getBoard().removeMonster(i, duelController, duelController.getPlayer());
                        duelController.setMonsterAttackRival(i, null);
                    }
                }
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    //FIXME
    CHANGE_OF_HEART(Icon.NORMAL, "Target 1 monster your opponent controls; take control of it until the End Phase.",
            Status.LIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    HARPIES_FEATHER_DUST(Icon.NORMAL, "Destroy all Spells and Traps your opponent controls.", Status.LIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                for (int i = 0; i < 5; i++) {
                    if (duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i) != null) {
                        if (duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i) instanceof SpellCard)
                            ((SpellCard) duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i)).takeAction(duelController, TakeActionCase.REMOVE_FROM_SPELLTRAPZONE, duelController.getRival(), i);
                        duelController.getPlayer().getBoard().removeSpellOrTrap(i);
                    }
                }
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    //FIXME
    SWORD_OF_REVEALING_LIGHT(Icon.NORMAL, "After this card's activation, it remains on the field, but destroy " +
            "it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls" +
            " a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, your" +
            " opponent's monsters cannot declare an attack.", Status.UNLIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    DARK_HOLE(Icon.NORMAL, "Destroy all monsters on the field.", Status.UNLIMITED, 2500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                for (int i = 0; i < 5; i++) {
                    if (duelController.getPlayer().getBoard().getMonsterByNumber(i) != null) {
                        duelController.getPlayer().getBoard().getMonsterByNumber(i).takeAction(duelController, TakeActionCase.REMOVE_FROM_MONSTERZONE, duelController.getRival(), i);
                        duelController.getPlayer().getBoard().removeMonster(i, duelController, duelController.getPlayer());
                        System.out.println("player :" + i);
//                        duelController.setMonsterAttackRival(i, null);
                    }
                    if (duelController.getRival().getBoard().getMonsterByNumber(i) != null) {
                        duelController.getRival().getBoard().getMonsterByNumber(i).takeAction(duelController, TakeActionCase.REMOVE_FROM_MONSTERZONE, duelController.getRival(), i);
                        duelController.getRival().getBoard().removeMonster(i, duelController, duelController.getRival());
                        System.out.println("rival :" + i);
//                        duelController.setMonsterAttackPlayer(i, null);
                    }
                }
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    //FIXME
    SUPPLY_SQUAD(Icon.CONTINUOUS, "Once per turn, if a monster(s) you control is destroyed by battle or card" +
            " effect: Draw 1 card.", Status.UNLIMITED, 4000) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    SPELL_ABSORPTION(Icon.CONTINUOUS, "Each time a Spell Card is activated, gain 500 Life Points immediately " +
            "after it resolves.", Status.UNLIMITED, 4000) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.ANY_SPELL_ACTIVATED)) {
                duelController.getPlayer().increaseLifePoint(500);
            }
            return true;
        }

    },

    //FIXME
    MESSENGER_OF_PEACE(Icon.CONTINUOUS, "Monsters with 1500 or more ATK cannot declare an attack. Once per turn," +
            " during your Standby Phase, pay 100 LP or destroy this card.", Status.UNLIMITED, 4000) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    TWIN_TWISTERS(Icon.QUICK_PLAY, "Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them."
            , Status.UNLIMITED, 3500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                int numberOfCardsInHand = duelController.getPlayer().getBoard().getCardsInHand().size();
                if (numberOfCardsInHand == 0) {
                    DuelView.printText("you don't have enough card in your hand to use this spell");
                    return false;
                } else {
                    numberOfCardsInHand -= 1;
                    DuelView.printText("please enter a number between 1 and " + (numberOfCardsInHand + 1) + " to choose" +
                            " a card from your hand to be removed.");
                    String cardNumber = DuelView.scan();
                    if (cardNumber.equals("cancel")) return false;
                    while (!cardNumber.matches("\\d+") || Integer.parseInt(cardNumber) < 0
                            || Integer.parseInt(cardNumber) > numberOfCardsInHand + 1) {
                        DuelView.printText("please Enter a valid Number!");
                        cardNumber = DuelView.scan();
                        if (cardNumber.equals("cancel")) return false;
                    }
                    int address = Integer.parseInt(cardNumber) - 1;
                    Cardable toBeRemoved = duelController.getPlayer().getBoard().getCardInHandByNumber(address);
                    duelController.getPlayer().getBoard().removeCardFromHand(toBeRemoved);
                    DuelView.printText("the card was successfully removed from your hand. Now please enter \"My\" or \"Rival\" to select field");
                    String field = DuelView.scan();
                    while (!field.equals("cancel") && !field.equals("My") && !field.equals("Rival")) {
                        DuelView.printText("enter \"My\" or \"Rival\" to select field");
                        field = DuelView.scan();
                    }
                    if (field.equals("cancel")) return false;
                    destroySpellOrTrap(duelController, field);
                    DuelView.printText("now, if you want to destroy another spell or trap, type the word \"My\" or \"Rival\", otherwise type the word continue");
                    field = DuelView.scan();
                    while (!field.equals("continue") && !field.equals("My") && !field.equals("Rival")) {
                        DuelView.printText("enter \"My\" or \"Rival\" to select field, or enter continue");
                        field = DuelView.scan();
                    }
                    if (field.equals("My") || field.equals("Rival")) {
                        destroySpellOrTrap(duelController, field);
                    }
                    SpellAction.getInstance().enableSpellAbsorptions(duelController);
                    duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
                }
            }
            return true;
        }
    },

    MYSTICAL_SPACE_TYPHOON(Icon.QUICK_PLAY, "Target 1 Spell/Trap on the field; destroy that target.",
            Status.UNLIMITED, 3500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
                DuelView.printText("please type the word \"My\" or \"Rival\" to choose a field to destroy the trap or spell");
                String field = DuelView.scan();
                while (!field.equals("cancel") && !field.equals("My") && !field.equals("Rival")) {
                    DuelView.printText("enter \"My\" or \"Rival\" to select field");
                    field = DuelView.scan();
                }
                if (field.equals("cancel")) return false;
                destroySpellOrTrap(duelController, field);
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
                duelController.getPlayer().getBoard().removeSpellOrTrap(targetNumber);
            }
            return true;
        }
    },

    //FIXME
    RING_OF_DEFENSE(Icon.QUICK_PLAY, "When a Trap effect that inflicts damage is activated: Make that effect " +
            "damage 0.", Status.UNLIMITED, 3500) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

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
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
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
            } else if (takeActionCase.equals(TakeActionCase.ANY_MONSTER_PUT_IN_MONSTERZONE)) {
                if (owner.getUsername().equals(duelController.getPlayer().getUsername())) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(targetNumber);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.FIEND) || playerMonster.getMonsterType().equals(MonsterType.SPELLCASTER)) {
                            duelController.changePlayerAttackPoint(targetNumber, 200);
                            duelController.changePlayerDefencePoint(targetNumber, 200);
                        } else if (playerMonster.getMonsterType().equals(MonsterType.FAIRY)) {
                            duelController.changePlayerAttackPoint(targetNumber, -200);
                            duelController.changePlayerDefencePoint(targetNumber, -200);
                        }
                    }
                } else {
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(targetNumber);
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.FIEND) || rivalMonster.getMonsterType().equals(MonsterType.SPELLCASTER)) {
                            duelController.changeRivalAttackPoint(targetNumber, 200);
                            duelController.changeRivalDefencePoint(targetNumber, 200);
                        } else if (rivalMonster.getMonsterType().equals(MonsterType.FAIRY)) {
                            duelController.changeRivalAttackPoint(targetNumber, -200);
                            duelController.changeRivalDefencePoint(targetNumber, -200);
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
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
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
            } else if (takeActionCase.equals(TakeActionCase.ANY_MONSTER_PUT_IN_MONSTERZONE)) {
                if (owner.getUsername().equals(duelController.getPlayer().getUsername())) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(targetNumber);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.INSECT) || playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(targetNumber, 200);
                            duelController.changePlayerDefencePoint(targetNumber, 200);
                        }
                    }
                } else {
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(targetNumber);
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.INSECT) || rivalMonster.getMonsterType().equals(MonsterType.BEAST) || rivalMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changeRivalAttackPoint(targetNumber, 200);
                            duelController.changeRivalDefencePoint(targetNumber, 200);
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
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
            } else if (takeActionCase.equals(TakeActionCase.REMOVE_FROM_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(i, -1 * amount);
                        }
                    }
                }
            } else if (takeActionCase.equals(TakeActionCase.ANY_MONSTER_PUT_IN_MONSTERZONE)) {
                if (owner.getUsername().equals(duelController.getPlayer().getUsername())) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(targetNumber);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.BEAST) || playerMonster.getMonsterType().equals(MonsterType.BEAST_WARRIOR)) {
                            duelController.changePlayerAttackPoint(targetNumber, amount);
                        }
                    }
                }
            }
            return true;
        }

    },

    UMIIRUKA(Icon.FIELD, "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 " +
            "points.", Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.AQUA)) {
                            duelController.changePlayerAttackPoint(i, 500);
                            duelController.changePlayerDefencePoint(i, -400);
                        }
                    }
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.AQUA)) {
                            duelController.changeRivalAttackPoint(i, 500);
                            duelController.changeRivalDefencePoint(i, -400);
                        }
                    }
                }
                SpellAction.getInstance().enableSpellAbsorptions(duelController);
            } else if (takeActionCase.equals(TakeActionCase.REMOVE_FROM_FIELDZONE_FACE_UP)) {
                for (int i = 0; i < 5; i++) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(i);
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(i);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.AQUA)) {
                            duelController.changePlayerAttackPoint(i, -500);
                            duelController.changePlayerDefencePoint(i, 400);
                        }
                    }
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.AQUA)) {
                            duelController.changeRivalAttackPoint(i, -500);
                            duelController.changeRivalDefencePoint(i, 400);
                        }
                    }
                }
            } else if (takeActionCase.equals(TakeActionCase.ANY_MONSTER_PUT_IN_MONSTERZONE)) {
                if (owner.getUsername().equals(duelController.getPlayer().getUsername())) {
                    MonsterCard playerMonster = duelController.getPlayer().getBoard().getMonsterByNumber(targetNumber);
                    if (playerMonster != null) {
                        if (playerMonster.getMonsterType().equals(MonsterType.AQUA)) {
                            duelController.changePlayerAttackPoint(targetNumber, 500);
                            duelController.changePlayerDefencePoint(targetNumber, -400);
                        }
                    }
                } else {
                    MonsterCard rivalMonster = duelController.getRival().getBoard().getMonsterByNumber(targetNumber);
                    if (rivalMonster != null) {
                        if (rivalMonster.getMonsterType().equals(MonsterType.AQUA)) {
                            duelController.changeRivalAttackPoint(targetNumber, 500);
                            duelController.changeRivalDefencePoint(targetNumber, -400);
                        }
                    }
                }
            }
            return true;
        }

    },

    //FIXME
    SWORD_OF_DARK_DESTRUCTION(Icon.EQUIP, "A DARK monster equipped with this card increases its ATK by 400 " +
            "points and decreases its DEF by 200 points.", Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    //FIXME
    BLACK_PENDANT(Icon.EQUIP, "The equipped monster gains 500 ATK. When this card is sent from the field to the" +
            " Graveyard: Inflict 500 damage to your opponent.", Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    //FIXME
    UNITED_WE_STAND(Icon.EQUIP, "The equipped monster gains 800 ATK/DEF for each face-up monster you control.",
            Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_FIELDZONE_FACE_UP)) {

            }
            return true;
        }
    },

    //FIXME
    MAGNUM_SHIELD(Icon.EQUIP, "Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
            "Attack Position: It gains ATK equal to its original DEF.\n" +
            "Defense Position: It gains DEF equal to its original ATK.", Status.UNLIMITED, 4300) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    },

    //FIXME
    ADVANCED_RITUAL_ART(Icon.RITUAL, "This card can be used to Ritual Summon any 1 Ritual Monster. You must " +
            "also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual" +
            " Monster.", Status.UNLIMITED, 3000) {
        public boolean takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {
            if (takeActionCase.equals(TakeActionCase.PUT_IN_SPELLTRAPZONE)) {
            }
            return true;
        }
    };


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

    private static void destroySpellOrTrap(DuelController duelController, String field) {
        ArrayList<Integer> updatedOnBoardSpellOrTrap = new ArrayList<>();
        if (field.equals("My")) {
            for (int i = 1; i <= 5; i++) {
                Cardable notNullSpellOrTrap = duelController.getPlayer().getBoard().getSpellAndTrapByNumber(i - 1);
                if (notNullSpellOrTrap != null)
                    updatedOnBoardSpellOrTrap.add(i);
            }
            DuelView.printText("please type the spell or trap's address from your spell zone to be destroyed");
            String input = DuelView.scan();
            while (!input.matches("\\d+") || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                DuelView.printText("please type the spell or trap's address from your spell zone to be destroyed, between 1 and 5");
                input = DuelView.scan();
            }
            int myAddress = Integer.parseInt(input);
            while (!(updatedOnBoardSpellOrTrap.contains(myAddress))) {
                DuelView.printText("the chosen address is empty");
                input = DuelView.scan();
                while (!input.matches("\\d+") || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                    DuelView.printText("please type the spell or trap's address from your spell zone to be destroyed");
                    input = DuelView.scan();
                }
                myAddress = Integer.parseInt(input);
            }
//            Cardable numberOne = duelController.getPlayer().getBoard().getSpellAndTrapByNumber(myAddress);
            duelController.getPlayer().getBoard().removeSpellOrTrap(myAddress - 1);
        } else {
            for (int i = 1; i <= 5; i++) {
                Cardable notNullSpellOrTrap = duelController.getRival().getBoard().getSpellAndTrapByNumber(i - 1);
                if (notNullSpellOrTrap != null)
                    updatedOnBoardSpellOrTrap.add(i);
            }
            DuelView.printText("please type the spell or trap's address from your spell zone to be destroyed");
            String input = DuelView.scan();
            while (!input.matches("\\d+") || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                DuelView.printText("please type the spell or trap's address from your spell zone to be destroyed, between 1 and 5");
                input = DuelView.scan();
            }
            int myAddress = Integer.parseInt(input);
            while (!(updatedOnBoardSpellOrTrap.contains(myAddress))) {
                DuelView.printText("the chosen address is empty");
                input = DuelView.scan();
                while (!input.matches("\\d+") || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                    DuelView.printText("please type the spell or trap's address from your spell zone to be destroyed");
                    input = DuelView.scan();
                }
                myAddress = Integer.parseInt(input);
            }
//            Cardable numberOne = duelController.getPlayer().getBoard().getSpellAndTrapByNumber(myAddress);
            duelController.getRival().getBoard().removeSpellOrTrap(myAddress - 1);
        }
        DuelView.printText("the chosen spell or trap was destroyed");
    }
}
