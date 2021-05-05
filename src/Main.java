import view.LogInView;
public class Main {

    public static void main(String[] args) {
        LogInView.getInstance().getCommandForLogin();
        /*ArrayList<MonsterTypes> types = new ArrayList<>();
        types.add(MonsterTypes.BEAST);
        types.add(MonsterTypes.CYBERSE);
        types.add(MonsterTypes.BEAST_WARRIOR);
        String typesForPrint;
        for(MonsterTypes eachType : types){
            typesForPrint += eachType.toString().
        }
        String typesForPrint = types.toString().replaceAll("\\[|\\]","").replaceAll(", ","/").replaceAll("_","-");
        System.out.println(typesForPrint);
        System.out.println(MonsterType.AQUA);
        String test = "HELLO_WELCOME_TO_HERE";
        test = test.charAt(0) + test.substring(1).toLowerCase();
        Pattern pattern = Pattern.compile("_([a-z])[a-z]+");
        Matcher matcher = pattern.matcher(test);
        while (matcher.find())
            test = test.replace(matcher.group(1), matcher.group(1).toUpperCase());
        test = test.replaceAll("_"," ");
        System.out.println(test);
        System.out.println(Model.Icon.EQUIP);
        System.out.println(MonsterCard.BATTLE_OX.getAttribute());
        Deck deck = new Deck("me");
        Deck deck1 = new Deck("ey");
        Deck deck2 = new Deck("mo");
        ArrayList<Deck> allDecks = new ArrayList<>();
        allDecks.add(deck);
        allDecks.add(deck1);
        allDecks.add(deck2);
        Comparator<Deck> deckComparator = Comparator.comparing(Deck::getDeckName);
        allDecks.sort(deckComparator);
        System.out.println(allDecks);*/
    }
}
