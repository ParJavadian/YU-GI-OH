//import controller.ImportExportUserController;

import view.LogInView;

public class Main {

    public static void main(String[] args) {
//        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//        importExportUserController.importAllUsers();
//        importExportUserController.importAllCards();
//        importExportUserController.importAllDecks();
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
/*
user create -u hamraz -p 123 -n hamraz
user create -u parmida -p 1234 -n parmida
user login -u hamraz -p 123
edoCtaehc yenom 1000000
menu enter Shop
shop buy Yomi Ship
shop buy Yomi Ship
shop buy Yomi Ship
shop buy Pot Of Greed
shop buy Pot Of Greed
shop buy Pot Of Greed
shop buy Monster Reborn
shop buy Monster Reborn
shop buy Monster Reborn
shop buy Monster Reborn
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Dark Hole
shop buy Dark Hole
shop buy Dark Hole
shop buy Exploder Dragon
shop buy Exploder Dragon
shop buy Exploder Dragon
shop buy Command Knight
shop buy Command Knight
shop buy Command Knight
shop buy Command Knight
shop buy Curtain Of Dark Ones
shop buy Curtain Of Dark Ones
shop buy Curtain Of Dark Ones
shop buy Curtain Of Dark Ones
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Haniwa
shop buy Haniwa
shop buy Haniwa
shop buy Haniwa
shop buy Gate Guardian
shop buy Gate Guardian
shop buy Gate Guardian
shop buy Gate Guardian
shop buy Magic Cylinder
shop buy Magic Cylinder
shop buy Magic Cylinder
shop buy Magic Cylinder
shop buy Mind Crush
shop buy Mind Crush
shop buy Mind Crush
shop buy Mind Crush
shop buy Suijin
shop buy Suijin
shop buy Suijin
shop buy Suijin
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Supply Squad
shop buy Supply Squad
shop buy Supply Squad
shop buy Supply Squad
buy card Sword Of Revealing Light
shop buy Sword Of Revealing Light
shop buy Sword Of Revealing Light
shop buy Sword Of Revealing Light
shop buy Supply Squad
shop buy Supply Squad
shop buy Supply Squad
shop buy Trap Hole
shop buy Trap Hole
shop buy Trap Hole
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Leotron
shop buy Leotron
shop buy Leotron
shop buy Leotron
shop buy United We Stand
shop buy United We Stand
shop buy United We Stand
menu exit
menu enter Deck
deck create one
deck add-card -c United We Stand -d one
deck add-card -c United We Stand -d one
deck add-card -c United We Stand -d one
deck add-card -c United We Stand -d one
deck add-card -c Leotron -d one
deck add-card -c Leotron -d one
deck add-card -c Leotron -d one
deck add-card -c Leotron -d one
deck add-card -c Leotron -d one -s
deck add-card -c Silver Fang -d one -s
deck add-card -c Silver Fang -d one –s
deck add-card -c Silver Fang -d one -s
deck add-card -c Silver Fang -d one -s
deck add-card -c Silver Fang -d one -s
deck add-card -c Trap Hole -d one -s
deck add-card -c Trap Hole -d one -s
deck add-card -c Trap Hole -d one -s
deck add-card -c Trap Hole -d one -s
deck add-card -c shop buy Supply Squad
deck add-card -c Supply Squad -d one -s
deck add-card -c Supply Squad -d one -s
deck add-card -c Supply Squad -d one -s
deck add-card -c Sword Of Revealing Light -d one -s
deck add-card -c Sword Of Revealing Light -d one -s
deck add-card -c Sword Of Revealing Light -d one -s
deck add-card -c Sword Of Revealing Light -d one -s
card with name Sword Of Revealing Light does not exist
deck add-card -c Supply Squad -d one -s
deck add-card -c Supply Squad -d one
deck add-card -c Silver Fang -d one
deck add-card -c Suijin -d one
deck add-card -c Suijin -d one
deck add-card -c Suijin -d one
deck add-card -c Suijin -d one
deck add-card -c Mind Crush -d one
deck add-card -c Mind Crush -d one
deck add-card -c Mind Crush -d one
deck add-card -c Mind Crush -d one
deck add-card -c Magic Cylinder -d one
deck add-card -c Magic Cylinder -d one
deck add-card -c Magic Cylinder -d one
deck add-card -c Magic Cylinder -d one
deck show --all
deck add-card -c Gate Guardian -d one
deck add-card -c Gate Guardian -d one
deck add-card -c Gate Guardian -d one
deck add-card Haniwa -d one
deck add-card -c Haniwa -d one
deck add-card -c Haniwa -d one
deck add-card -c Haniwa -d one
deck add-card -c Haniwa -d one
deck show --all
deck add-card -c Curtain Of Dark Ones -d one
deck add-card -c Curtain Of Dark Ones -d one
deck add-card -c Curtain Of Dark Ones -d one
deck add-card -c Curtain Of Dark Ones -d one
deck add-card -c Command Knight -d one
deck add-card -c Command Knight -d one
deck add-card -c Command Knight -d one
deck add-card -c Command Knight -d one
deck add-card -c Exploder Dragon -d one
deck add-card -c Exploder Dragon -d one
deck add-card -c Exploder Dragon -d one
deck add-card -c Exploder Dragon -d one
deck add-card -c Dark Hole -d one
deck add-card -c Dark Hole -d one
deck add-card -c Dark Hole -d one
deck add-card -c Dark Hole -d one
deck add-card -c Spell Absorption -d one
deck add-card -c Spell Absorption -d one
deck add-card -c Spell Absorption -d one
deck add-card -c Spell Absorption -d one
deck add-card -c Spell Absorption -d one
deck add-card -c Monster Reborn -d one
deck add-card -c Monster Reborn -d one
deck add-card -c Monster Reborn -d one
deck add-card -c Monster Reborn -d one
deck add-card -c Pot Of Greed -d one
deck add-card -c Pot Of Greed -d one
deck add-card -c Pot Of Greed -d one
deck show --all
deck add-card -c Yomi Ship -d one
deck add-card -c Yomi Ship -d one
deck add-card -c Yomi Ship -d one
deck add-card -c Yomi Ship -d one
deck add-card -c Yami -d one
deck add-card -c Yami -d one
deck add-card -c Yami -d one
deck add-card -c Yami -d one
deck show --all
menu exit
menu enter Shop
shop buy Yami
shop buy Yami
menu exit
menu enter Deck
deck add-card -c Yami -d one
deck show –-all
deck set-activate one
menu exit
user logout
user login -u parmida -p 1234
edoCtaehc yenom 1000000
menu enter Shop
shop buy Yomi Ship
shop buy Yomi Ship
shop buy Yomi Ship
shop buy Pot Of Greed
shop buy Pot Of Greed
shop buy Pot Of Greed
shop buy Monster Reborn
shop buy Monster Reborn
shop buy Monster Reborn
shop buy Monster Reborn
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Dark Hole
shop buy Dark Hole
shop buy Dark Hole
shop buy Exploder Dragon
shop buy Exploder Dragon
shop buy Exploder Dragon
shop buy Command Knight
shop buy Command Knight
shop buy Command Knight
shop buy Command Knight
shop buy Curtain Of Dark Ones
shop buy Curtain Of Dark Ones
shop buy Curtain Of Dark Ones
shop buy Curtain Of Dark Ones
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Haniwa
shop buy Haniwa
shop buy Haniwa
shop buy Haniwa
shop buy Gate Guardian
shop buy Gate Guardian
shop buy Gate Guardian
shop buy Gate Guardian
shop buy Magic Cylinder
shop buy Magic Cylinder
shop buy Magic Cylinder
shop buy Magic Cylinder
shop buy Mind Crush
shop buy Mind Crush
shop buy Mind Crush
shop buy Mind Crush
shop buy Suijin
shop buy Suijin
shop buy Suijin
shop buy Suijin
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Silver Fang
shop buy Supply Squad
shop buy Supply Squad
shop buy Supply Squad
shop buy Supply Squad
buy card Sword Of Revealing Light
shop buy Sword Of Revealing Light
shop buy Sword Of Revealing Light
shop buy Sword Of Revealing Light
shop buy Supply Squad
shop buy Supply Squad
shop buy Supply Squad
shop buy Trap Hole
shop buy Trap Hole
shop buy Trap Hole
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Spell Absorption
shop buy Leotron
shop buy Leotron
shop buy Leotron
shop buy Leotron
shop buy United We Stand
shop buy United We Stand
shop buy United We Stand
menu exit
menu enter Deck
deck create oneParmida
deck add-card -c United We Stand -d oneParmida
deck add-card -c United We Stand -d oneParmida
deck add-card -c United We Stand -d oneParmida
deck add-card -c United We Stand -d oneParmida
deck add-card -c Leotron -d oneParmida
deck add-card -c Leotron -d oneParmida
deck add-card -c Leotron -d oneParmida
deck add-card -c Leotron -d oneParmida
deck add-card -c Leotron -d oneParmida -s
deck add-card -c Silver Fang -d oneParmida -s
deck add-card -c Silver Fang -d oneParmida –s
deck add-card -c Silver Fang -d oneParmida -s
deck add-card -c Silver Fang -d oneParmida -s
deck add-card -c Silver Fang -d oneParmida -s
deck add-card -c Trap Hole -d oneParmida -s
deck add-card -c Trap Hole -d oneParmida -s
deck add-card -c Trap Hole -d oneParmida -s
deck add-card -c Trap Hole -d oneParmida -s
deck add-card -c Supply Squad -d oneParmida -s
deck add-card -c Supply Squad -d oneParmida -s
deck add-card -c Supply Squad -d oneParmida -s
deck add-card -c Sword Of Revealing Light -d oneParmida -s
deck add-card -c Sword Of Revealing Light -d oneParmida -s
deck add-card -c Sword Of Revealing Light -d oneParmida -s
deck add-card -c Sword Of Revealing Light -d oneParmida -s
deck add-card -c Supply Squad -d oneParmida -s
deck add-card -c Supply Squad -d oneParmida
deck add-card -c Silver Fang -d oneParmida
deck add-card -c Suijin -d oneParmida
deck add-card -c Suijin -d oneParmida
deck add-card -c Suijin -d oneParmida
deck add-card -c Suijin -d oneParmida
deck add-card -c Mind Crush -d oneParmida
deck add-card -c Mind Crush -d oneParmida
deck add-card -c Mind Crush -d oneParmida
deck add-card -c Mind Crush -d oneParmida
deck add-card -c Magic Cylinder -d oneParmida
deck add-card -c Magic Cylinder -d oneParmida
deck add-card -c Magic Cylinder -d oneParmida
deck add-card -c Magic Cylinder -d oneParmida
deck show --all
deck add-card -c Gate Guardian -d oneParmida
deck add-card -c Gate Guardian -d oneParmida
deck add-card -c Gate Guardian -d oneParmida
deck add-card Haniwa -d oneParmida
deck add-card -c Haniwa -d oneParmida
deck add-card -c Haniwa -d oneParmida
deck add-card -c Haniwa -d oneParmida
deck add-card -c Haniwa -d oneParmida
deck show --all
deck add-card -c Curtain Of Dark Ones -d oneParmida
deck add-card -c Curtain Of Dark Ones -d oneParmida
deck add-card -c Curtain Of Dark Ones -d oneParmida
deck add-card -c Curtain Of Dark Ones -d oneParmida
deck add-card -c Command Knight -d oneParmida
deck add-card -c Command Knight -d oneParmida
deck add-card -c Command Knight -d oneParmida
deck add-card -c Command Knight -d oneParmida
deck add-card -c Exploder Dragon -d oneParmida
deck add-card -c Exploder Dragon -d oneParmida
deck add-card -c Exploder Dragon -d oneParmida
deck add-card -c Exploder Dragon -d oneParmida
deck add-card -c Dark Hole -d oneParmida
deck add-card -c Dark Hole -d oneParmida
deck add-card -c Dark Hole -d oneParmida
deck add-card -c Dark Hole -d oneParmida
deck add-card -c Spell Absorption -d oneParmida
deck add-card -c Spell Absorption -d oneParmida
deck add-card -c Spell Absorption -d oneParmida
deck add-card -c Spell Absorption -d oneParmida
deck add-card -c Spell Absorption -d oneParmida
deck add-card -c Monster Reborn -d oneParmida
deck add-card -c Monster Reborn -d oneParmida
deck add-card -c Monster Reborn -d oneParmida
deck add-card -c Monster Reborn -d oneParmida
deck add-card -c Pot Of Greed -d oneParmida
deck add-card -c Pot Of Greed -d oneParmida
deck add-card -c Pot Of Greed -d oneParmida
deck show --all
deck add-card -c Yomi Ship -d oneParmida
deck add-card -c Yomi Ship -d oneParmida
deck add-card -c Yomi Ship -d oneParmida
deck add-card -c Yomi Ship -d oneParmida
deck add-card -c Yami -d oneParmida
deck add-card -c Yami -d oneParmida
deck add-card -c Yami -d oneParmida
deck add-card -c Yami -d oneParmida
deck show --all
menu exit
menu enter Shop
shop buy Yami
shop buy Yami
menu exit
menu enter Deck
deck add-card -c Yami -d oneParmida
menu show-current
deck show --all
deck set-activate oneParmida
menu exit
duel -n -sp hamraz -r 3
next phase
 */