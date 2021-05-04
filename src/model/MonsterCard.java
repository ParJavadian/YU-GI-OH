package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MonsterCard implements Card {
    COMMAND_KNIGHT(4, Attribute.FIRE, MonsterType.WARRIOR, CardType.EFFECT, 1000, 1000,
            "All Warrior-Type monsters you control gain 400 ATK. If you control another monster, monsters your " +
                    "opponent controls cannot target this card for an attack.",
            2100),

    BATTLE_OX(4, Attribute.EARTH, MonsterType.BEAST_WARRIOR, CardType.NORMAL, 1700, 1000,
            "A monster with tremendous power, it destroys enemies with a swing of its axe.", 2900),

    AXE_RAIDER(4, Attribute.EARTH, MonsterType.WARRIOR, CardType.NORMAL, 1700, 1150,
            "An axe-wielding monster of tremendous strength and agility.", 3100),

    YOMI_SHIP(3, Attribute.WATER, MonsterType.AQUA, CardType.EFFECT, 800, 1400,
            "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.",
            1700),

    HORN_IMP(4, Attribute.DARK, MonsterType.FIEND, CardType.NORMAL, 1300, 1000,
            "A small fiend that dwells in the dark, its single horn makes it a formidable opponent.", 2500),

    SILVER_FANG(3, Attribute.EARTH, MonsterType.BEAST, CardType.NORMAL, 1200, 800,
            "A snow wolf that's beautiful to the eye, but absolutely vicious in battle.", 1700),

    SUIJIN(7, Attribute.WATER, MonsterType.AQUA, CardType.EFFECT, 2500, 2400,
            "During damage calculation in your opponent's turn, if this card is being attacked: You can target " +
                    "the attacking monster; make that target's ATK 0 during damage calculation only (this is a Quick Effect). " +
                    "This effect can only be used once while this card is face-up on the field.", 8700),

    FIREYAROU(4, Attribute.FIRE, MonsterType.PYRO, CardType.NORMAL, 1300, 1000,
            "A malevolent creature wrapped in flames that attacks enemies with intense fire.", 2500),

    CURTAIN_OF_DARK_ONES(2, Attribute.DARK, MonsterType.SPELLCASTER, CardType.NORMAL, 600, 500,
            "A curtain that a spellcaster made, it is said to raise a dark power.", 700),

    FERAL_IMP(4, Attribute.DARK, MonsterType.FIEND, CardType.NORMAL, 1300, 1400,
            "A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.", 2800),

    DARK_MAGICIAN(7, Attribute.DARK, MonsterType.SPELLCASTER, CardType.NORMAL, 2500,
            2100, "The ultimate wizard in terms of attack and defense.", 8300),

    WATTKID(3, Attribute.LIGHT, MonsterType.THUNDER, CardType.NORMAL, 1000, 500,
            "A creature that electrocutes opponents with bolts of lightning.", 1300),

    BABY_DRAGON(3, Attribute.WIND, MonsterType.DRAGON, CardType.NORMAL, 1200, 700,
            "Much more than just a child, this dragon is gifted with untapped power.", 1600),

    HERO_OF_THE_EAST(3, Attribute.EARTH, MonsterType.WARRIOR, CardType.NORMAL, 1100, 1000,
            "Feel da strength ah dis sword-swinging samurai from da Far East.", 1700),

    BATTLE_WARRIOR(3, Attribute.EARTH, MonsterType.WARRIOR, CardType.NORMAL, 700, 1000,
            "A warrior that fights with his bare hands!!!", 1300),

    CRAWLING_DRAGON(5, Attribute.EARTH, MonsterType.DRAGON, CardType.NORMAL, 1600, 1400,
            "This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.", 3900),

    FLAME_MANIPULATOR(3, Attribute.FIRE, MonsterType.SPELLCASTER, CardType.NORMAL, 900, 1000,
            "This Spellcaster attacks enemies with fire-related spells such as \"Sea of Flames\" and \"Wall of Fire\".",
            1500),

    BLUE_EYES_WHITE_DRAGON(8, Attribute.LIGHT, MonsterType.DRAGON, CardType.NORMAL, 3000, 2500,
            "This legendary dragon is a powerful engine of destruction. Virtually invincible, very few have " +
                    "faced this awesome creature and lived to tell the tale.", 11300),

    CRAB_TURTLE(8, Attribute.WATER, MonsterType.AQUA, CardType.RITUAL, 2550, 2500,
            "This monster can only be Ritual Summoned with the Ritual Spell Card, \"Turtle Oath\". You must " +
                    "also offer monsters whose total Level Stars equal 8 or more as a Tribute from the field or your hand.",
            10200),

    SKULL_GUARDIAN(7, Attribute.LIGHT, MonsterType.WARRIOR, CardType.RITUAL, 2050, 2500,
            "This monster can only be Ritual Summoned with the Ritual Spell Card, \"Novox's Prayer\". You must " +
                    "also offer monsters whose total Level Stars equal 7 or more as a Tribute from the field or your hand.",
            7900),

    SLOT_MACHINE(7, Attribute.DARK, MonsterType.MACHINE, CardType.NORMAL, 2000, 2300,
            "The machine's ability is said to vary according to its slot results.", 7500),

    HANIWA(2, Attribute.EARTH, MonsterType.ROCK, CardType.NORMAL, 500, 500,
            "An earthen figure that protects the tomb of an ancient ruler.", 600),

    MAN_EATER_BUG(2, Attribute.EARTH, MonsterType.INSECT, CardType.EFFECT, 450, 600,
            "FLIP: Target 1 monster on the field; destroy that target.", 600),

    GATE_GUARDIAN(11, Attribute.DARK, MonsterType.WARRIOR, CardType.EFFECT, 3750, 3400,
            "Cannot be Normal Summoned/Set. Must first be Special Summoned (from your hand) by Tributing 1 " +
                    "\"Sanga of the Thunder\", \"Kazejin\", and \"Suijin\".", 20000),

    SCANNER(1, Attribute.LIGHT, MonsterType.MACHINE, CardType.EFFECT, 0, 0,
            "Once per turn, you can select 1 of your opponent's monsters that is removed from play. Until the" +
                    " End Phase, this card's name is treated as the selected monster's name, and this card has the same " +
                    "Attribute, Level, ATK, and DEF as the selected monster. If this card is removed from the field " +
                    "while this effect is applied, remove it from play.", 8000),

    BITRON(2, Attribute.EARTH, MonsterType.CYBERSE, CardType.NORMAL, 200, 2000,
            "A new species found in electronic space. There's not much information on it.", 1000),

    MARSHMALLON(3, Attribute.LIGHT, MonsterType.FAIRY, CardType.EFFECT, 300, 500,
            "Cannot be destroyed by battle. After damage calculation, if this card was attacked, and was " +
                    "face-down at the start of the Damage Step: The attacking player takes 1000 damage.", 700),

    BEAST_KING_BARBAROS(8, Attribute.EARTH, MonsterType.BEAST_WARRIOR, CardType.EFFECT, 3000, 1200,
            "You can Normal Summon/Set this card without Tributing, but its original ATK becomes 1900. You can " +
                    "Tribute 3 monsters to Tribute Summon (but not Set) this card. If Summoned this way: Destroy all " +
                    "cards your opponent controls.", 9200),

    TEXCHANGER(1, Attribute.DARK, MonsterType.CYBERSE, CardType.EFFECT, 100, 100,
            "Once per turn, when your monster is targeted for an attack: You can negate that attack, then " +
                    "Special Summon 1 Cyberse Normal Monster from your hand, Deck, or GY.", 200),

    LEOTRON(4, Attribute.EARTH, MonsterType.CYBERSE, CardType.NORMAL, 2000, 0,
            "A territorial electronic monster that guards its own domain.", 2500),

    THE_CALCULATOR(2, Attribute.LIGHT, MonsterType.THUNDER, CardType.EFFECT, 0, 0,
            "The ATK of this card is the combined Levels of all face-up monsters you control x 300.", 8000),

    ALEXANDRITE_DRAGON(4, Attribute.LIGHT, MonsterType.DRAGON, CardType.NORMAL, 2000, 100,
            "Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator " +
                    "remains a mystery, along with how they acquired the imperial treasures. But whosoever finds this" +
                    " dragon has hit the jackpot... whether they know it or not.", 2600),

    MIRAGE_DRAGON(4, Attribute.LIGHT, MonsterType.DRAGON, CardType.EFFECT, 1600, 600,
            "Your opponent cannot activate Trap Cards during the Battle Phase.", 2500),

    HERALD_OF_CREATION(4, Attribute.LIGHT, MonsterType.SPELLCASTER, CardType.EFFECT, 1800, 600,
            "Once per turn: You can discard 1 card, then target 1 Level 7 or higher monster in your Graveyard; " +
                    "add that target to your hand.", 2700),

    EXPLODER_DRAGON(3, Attribute.EARTH, MonsterType.DRAGON, CardType.EFFECT, 1000, 0,
            "If this card is destroyed by battle and sent to the Graveyard: Destroy the monster that destroyed" +
                    " it. Neither player takes any battle damage from attacks involving this attacking card.", 1000),

    WARRIOR_DAI_GREPHER(4, Attribute.EARTH, MonsterType.WARRIOR, CardType.NORMAL, 1700, 1600,
            "The warrior who can manipulate dragons. Nobody knows his mysterious past.", 3400),

    DARK_BLADE(4, Attribute.DARK, MonsterType.WARRIOR, CardType.NORMAL, 1800, 1500,
            "They say he is a dragon-manipulating warrior from the dark world. His attack is tremendous, using " +
                    "his great swords with vicious power.", 3500),

    WATTAILDRAGON(6, Attribute.LIGHT, MonsterType.DRAGON, CardType.NORMAL, 2500, 1000,
            "Capable of indefinite flight. Attacks by wrapping its body with electricity and ramming into opponents.\n" +
                    "IMPORTANT: Capturing the \"Wattaildragon\" is forbidden by the Ancient Rules and is a Level 6 " +
                    "offense, the minimum sentence for which is imprisonment for no less than 2500 heliocycles.", 5800),

    TERRATIGER_THE_EMPOWERED_WARRIOR(4, Attribute.EARTH, MonsterType.WARRIOR, CardType.EFFECT, 1800, 1200,
            "When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower Normal Monster from" +
                    " your hand in Defense Position.", 3200),

    THE_TRICKY(5, Attribute.WIND, MonsterType.SPELLCASTER, CardType.EFFECT, 2000, 1200,
            "You can Special Summon this card (from your hand) by discarding 1 card.", 4300),

    SPIRAL_SERPENT(8, Attribute.WATER, MonsterType.SEA_SERPENT, CardType.NORMAL, 2900, 2900,
            "When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at work. No one has" +
                    " ever escaped its dreaded Spiral Wave to accurately describe the terror they experienced.", 11700);

    private int level;
    private Attribute attribute;
    private MonsterType monsterType;
    private CardType cardType;
    private int attack;
    private int defence;
    private String description;
    private int price;

    private MonsterCard(int level, Attribute attribute, MonsterType monsterType, CardType cardType, int attack, int defence, String description, int price) {
        this.level = level;
        this.attribute = attribute;
        this.monsterType = monsterType;
        this.cardType = cardType;
        this.attack = attack;
        this.defence = defence;
        this.description = description;
        this.price = price;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getLevel() {
        return this.level;
    }

    public MonsterType getMonsterType() {
        return this.monsterType;
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
                "Level: " + this.level + "\n";
        String monsterType = this.monsterType.name().charAt(0) + this.monsterType.name().substring(1).toLowerCase();
        matcher = pattern.matcher(monsterType);
        while (matcher.find())
            monsterType = monsterType.replace(matcher.group(1), matcher.group(1).toUpperCase());
        monsterType = monsterType.replaceAll("_", " ");
        toReturn = toReturn + "Type: " + monsterType + "\n" +
                "ATK: " + this.attack + "\n" +
                "DEF: " + this.defence + "\n" +
                "Description: " + this.description;
        return toReturn;
    }
}
