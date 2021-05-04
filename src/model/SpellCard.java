package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SpellCard implements Card {


    MONSTER_REBORN(Icon.NORMAL, "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: " +
            "Target that monster; destroy that target.", Status.UNLIMITED, 2000),

    TERRAFORMING(Icon.NORMAL, "Add 1 Field Spell from your Deck to your hand.", Status.LIMITED, 2500),

    POT_OF_GREED(Icon.NORMAL, "Draw 2 cards.", Status.LIMITED, 2500),

    RAIGEKI(Icon.NORMAL, "Destroy all monsters your opponent controls.", Status.LIMITED, 2500),

    CHANGE_OF_HEART(Icon.NORMAL, "Target 1 monster your opponent controls; take control of it until the End Phase.",
            Status.LIMITED, 2500),

    HARPIES_FEATHER_DUST(Icon.NORMAL, "Destroy all Spells and Traps your opponent controls.", Status.LIMITED, 2500),

    SWORD_OF_REVEALING_LIGHT(Icon.NORMAL, "After this card's activation, it remains on the field, but destroy " +
            "it during the End Phase of your opponent's 3rd turn. When this card is activated: If your opponent controls" +
            " a face-down monster, flip all monsters they control face-up. While this card is face-up on the field, your" +
            " opponent's monsters cannot declare an attack.", Status.UNLIMITED, 2500),

    DARK_HOLE(Icon.NORMAL, "Destroy all monsters on the field.", Status.UNLIMITED, 2500),

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
            " on the field lose 200 ATK/DEF.", Status.UNLIMITED, 4300),

    FOREST(Icon.FIELD, "All Insect, Beast, Plant, and Beast-Warrior monsters on the field gain 200 ATK/DEF.",
            Status.UNLIMITED, 4300),

    CLOSED_FOREST(Icon.FIELD, "All Beast-Type monsters you control gain 100 ATK for each monster in your " +
            "Graveyard. Field Spell Cards cannot be activated. Field Spell Cards cannot be activated during the turn " +
            "this card is destroyed.", Status.UNLIMITED, 4300),

    UMIIRUKA(Icon.FIELD, "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 " +
            "points.", Status.UNLIMITED, 4300),

    SWORD_OF_DARK_DESTRUCTION(Icon.EQUIP, "A DARK monster equipped with this card increases its ATK by 400 " +
            "points and decreases its DEF by 200 points.", Status.UNLIMITED, 4300),

    BLACK_PENDANT(Icon.EQUIP, "The equipped monster gains 500 ATK. When this card is sent from the field to the" +
            " Graveyard: Inflict 500 damage to your opponent.", Status.UNLIMITED, 4300),

    UNITED_WE_STAND(Icon.EQUIP, "The equipped monster gains 800 ATK/DEF for each face-up monster you control.",
            Status.UNLIMITED, 4300),

    MAGNUM_SHIELD(Icon.EQUIP, "Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.\n" +
            "â—\u008F Attack Position: It gains ATK equal to its original DEF.\n" +
            "â—\u008F Defense Position: It gains DEF equal to its original ATK.", Status.UNLIMITED, 4300),

    ADVANCED_RITUAL_ART(Icon.RITUAL, "This card can be used to Ritual Summon any 1 Ritual Monster. You must " +
            "also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual" +
            " Monster.", Status.UNLIMITED, 3000);


    private Icon icon;
    private Status status;
    String description;
    int price;

    private SpellCard(Icon icon, String description, Status status, int price) {
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

    public String getNamePascalCase() {
        String name = this.name().charAt(0) + this.name().substring(1).toLowerCase();
        Pattern pattern = Pattern.compile("_([a-z])[a-z]+");
        Matcher matcher = pattern.matcher(name);
        while (matcher.find())
            name = name.replace(matcher.group(1), matcher.group(1).toUpperCase());
        name = name.replaceAll("_", " ");
        return name;
    }

    @Override
    public String toString() {
        String name = this.name().charAt(0) + this.name().substring(1).toLowerCase();
        Pattern pattern = Pattern.compile("_([a-z])[a-z]+");
        Matcher matcher = pattern.matcher(name);
        while (matcher.find())
            name = name.replace(matcher.group(1), matcher.group(1).toUpperCase());
        name = name.replaceAll("_", " ");
        String toReturn = "Name: " + name + "\n" +
                "Spell" + "\n";
        String type = this.icon.name().charAt(0) + this.icon.name().substring(1).toLowerCase();
        matcher = pattern.matcher(type);
        while (matcher.find())
            type = type.replace(matcher.group(1), matcher.group(1).toUpperCase());
        type = type.replaceAll("_", " ");
        toReturn = toReturn + "Type: " + type + "\n" +
                "Description: " + this.description;
        return toReturn;
    }
}
