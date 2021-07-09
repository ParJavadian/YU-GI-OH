package controller;

import model.*;

import java.io.*;
import java.util.Scanner;

public class ImportExportCardController {
    private static ImportExportCardController instance = null;
    private User user;

    public static ImportExportCardController getInstance(User user) {
        if (instance == null) instance = new ImportExportCardController(user);
        else if (instance.user == null || !instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ImportExportCardController(User user) {
        this.user = user;
    }

    public String importCard(String cardName) {

        String cardInfo = null;

        File monsterFile = new File("CardsIE/" + cardName + "MonsterCard.txt");
        File trapFile = new File("CardsIE/" + cardName + "TrapCard.txt");
        File spellFile = new File("CardsIE/" + cardName + "SpellCard.txt");
        File monsterFileForCsv = new File("CardsIE/" + cardName + "MonsterCard.csv");
        File trapFileForCsv = new File("CardsIE/" + cardName + "TrapCard.csv");
        File spellFileForCsv = new File("CardsIE/" + cardName + "SpellCard.csv");

        try {
            if (monsterFile.exists()) {
                cardInfo = importMonsterCard(monsterFile);
            }
            if (trapFile.exists()) {
                cardInfo = importTrapCard(trapFile);
            }
            if (spellFile.exists()) {
                cardInfo = importSpellCard(spellFile);
            }
            if (monsterFileForCsv.exists()) {
                cardInfo = importMonsterCardForCsv(monsterFileForCsv);
            }
            if (trapFileForCsv.exists()) {
                cardInfo = importTrapCardForCsv(trapFileForCsv);
            }
            if (spellFileForCsv.exists()) {
                cardInfo = importSpellCardForCsv(spellFileForCsv);
            }
            return cardInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String importSpellCard(File spellFile) throws FileNotFoundException {
        StringBuilder cardInfo = new StringBuilder();
        String icon;
        String status;
        int price;
        String description;
        int counter = 4;
        Scanner scanner = new Scanner(spellFile);
        while (scanner.hasNextLine()) {
            if (counter == 4) {
                icon = scanner.nextLine();
                cardInfo.append("icon = ").append(icon).append("\n");
            }
            if (counter == 3) {
                status = scanner.nextLine();
                cardInfo.append("status = ").append(status).append("\n");
            }
            if (counter == 2) {
                price = Integer.parseInt(scanner.nextLine());
                cardInfo.append("price = ").append(price).append("\n");
            }
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
                cardInfo.append("card description = ").append(description);
                return String.valueOf(cardInfo);

            }
            if (counter == 0) {
                break;
            }
            counter--;
        }
//        SpellCard spellCard = new SpellCard(icon,description,status,price)
        return "";
    }

    private String importTrapCard(File trapFile) throws FileNotFoundException {
        StringBuilder cardInfo = new StringBuilder();
        String icon;
        String status;
        int price;
        String description;
        int counter = 4;
        Scanner scanner = new Scanner(trapFile);
        while (scanner.hasNextLine()) {
            if (counter == 4) {
                icon = scanner.nextLine();
                cardInfo.append("icon = ").append(icon).append("\n");
            }
            if (counter == 3) {
                status = scanner.nextLine();
                cardInfo.append("status = ").append(status).append("\n");
            }
            if (counter == 2) {
                price = Integer.parseInt(scanner.nextLine());
                cardInfo.append("price = ").append(price).append("\n");
            }
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
                cardInfo.append("card description = ").append(description);
                return String.valueOf(cardInfo);
            }
            if (counter == 0) {
                break;
            }
            counter--;
        }
//                TrapCard trapCard = new TrapCard(icon,description,status,price);
        return "";
    }

    private String importMonsterCard(File monsterFile) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        int counter;
        String monsterType;
        String cardType;
        int defencePoint;
        Scanner scanner;
        int price;
        int level;
        boolean canBeNormalSummoned;
        String attribute;
        int attackPoint;
        String description;
        counter = 9;
        scanner = new Scanner(monsterFile);
        while (scanner.hasNextLine()) {
            if (counter == 9) {
                level = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("level = ").append(level).append("\n");
            }
            if (counter == 8) {
                attribute = scanner.nextLine();
                stringBuilder.append("attribute = ").append(attribute).append("\n");
            }
            if (counter == 7) {
                monsterType = scanner.nextLine();
                stringBuilder.append("monster type = ").append(monsterType).append("\n");
            }
            if (counter == 6) {
                cardType = scanner.nextLine();
                stringBuilder.append("card type = ").append(cardType).append("\n");
            }
            if (counter == 5) {
                attackPoint = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("attack point = ").append(attackPoint).append("\n");
            }
            if (counter == 4) {
                defencePoint = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("defence point = ").append(defencePoint).append("\n");
            }
            if (counter == 3) {
                price = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("price = ").append(price).append("\n");
            }
            if (counter == 2) {
                canBeNormalSummoned = Boolean.parseBoolean(scanner.nextLine());
                stringBuilder.append("can be normal summoned = ").append(canBeNormalSummoned).append("\n");
            }
            if (counter == 1) {
                StringBuilder stringBuilder2 = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder2.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder2);
                stringBuilder.append("card description = ").append(description);
                return String.valueOf(stringBuilder);
            }
            if (counter == 0) {
                break;
            }
            counter--;
        }
        return "";
/*                MonsterCard monsterCard = new MonsterCard(level, attribute, monsterType, cardType, attackPoint, defencePoint, description, price, canBeNormalSummoned) {
                    @Override
                    public void takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {

                    }

                    @Override
                    public boolean canBeAttacked(DuelController duelController, int monsterNumber) {
                        return false;
                    }
                };*/
    }

    public void exportCard(Card card) {
        if (card instanceof MonsterCard) {
            exportMonsterCard(card);
            exportMonsterCardForCsv(card);

        }
        if (card instanceof SpellCard) {
            exportSpellCard(card);
            exportSpellCardForCsv(card);
        }
        if (card instanceof TrapCard) {
            exportTrapCard(card);
            exportTrapCardForCsv(card);
        }
    }

    private void exportTrapCard(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "TrapCard.txt");
            writer.write(((TrapCard) card).getIcon() + "\n" + ((TrapCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportSpellCard(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "SpellCard.txt");
            writer.write(((SpellCard) card).getIcon() + "\n" + ((SpellCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exportMonsterCard(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "MonsterCard.txt");
            writer.write(((MonsterCard) card).getLevel() + "\n" + ((MonsterCard) card).getAttribute() + "\n" +
                    ((MonsterCard) card).getMonsterType() + "\n" + ((MonsterCard) card).getCardType() + "\n" +
                    ((MonsterCard) card).getAttack() + "\n" + ((MonsterCard) card).getDefence() + "\n" +
                    card.getPrice() + "\n" + ((MonsterCard) card).getCanBeNormalSummoned() +
                    "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String importCardFromCsv(String cardName) {

        String cardInfo = null;

        File monsterFile = new File("CardsIE/" + cardName + "MonsterCard.csv");
        File trapFile = new File("CardsIE/" + cardName + "TrapCard.csv");
        File spellFile = new File("CardsIE/" + cardName + "SpellCard.csv");
        StringBuilder stringBuilder = new StringBuilder();
        if (monsterFile.exists()) {
            try {
                String row;

                String monsterType;
                String cardType;
                int defencePoint;
                int price;
                int level;
                boolean canBeNormalSummoned;
                String attribute;
                int attackPoint;
                String description;


                BufferedReader csvReader = new BufferedReader(new FileReader("CardsIE/" + cardName + "MonsterCard.csv"));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if (data.length > 0) {
                        level = Integer.parseInt(data[0]);
                        stringBuilder.append("level = ").append(level).append("\n");
                    }
                    if (data.length > 1) {
                        attribute = data[1];
                        stringBuilder.append("attribute = ").append(attribute).append("\n");
                    }
                    if (data.length > 2) {
                        monsterType = data[2];
                        stringBuilder.append("monster type = ").append(monsterType).append("\n");
                    }
                    if (data.length > 3) {
                        cardType = data[3];
                        stringBuilder.append("card type = ").append(cardType).append("\n");
                    }
                    if (data.length > 4) {
                        attackPoint = Integer.parseInt(data[4]);
                        stringBuilder.append("attack point = ").append(attackPoint).append("\n");
                    }
                    if (data.length > 5) {
                        defencePoint = Integer.parseInt(data[5]);
                        stringBuilder.append("defence point = ").append(defencePoint).append("\n");
                    }
                    if (data.length > 6) {
                        price = Integer.parseInt(data[6]);
                        stringBuilder.append("price = ").append(price).append("\n");
                    }

                    if (data.length > 7) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        //while (scanner.hasNextLine()) {
                        stringBuilder2.append(data[7]);
                        //}
                        description = String.valueOf(stringBuilder2);
                        stringBuilder.append("card description = ").append(description);
//                        return String.valueOf(stringBuilder);
                    }
                    return String.valueOf(stringBuilder);
//                    if (counter == 0) {
//                        break;
//                    }
//                    counter--;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (spellFile.exists()) {
            try {
                String row;
                StringBuilder stringbuilder = new StringBuilder();
                String icon;
                String status;
                int price;
                int counter = 4;


                BufferedReader csvReader = new BufferedReader(new FileReader("CardsIE/" + cardName + "SpellCard.csv"));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");

                    if (data.length > 0) {
                        icon = data[0];
                        stringbuilder.append("icon = ").append(icon).append("\n");
                    }
                    if (data.length > 1) {
                        status = data[1];
                        stringbuilder.append("status = ").append(status).append("\n");
                    }
                    if (data.length > 2) {
                        price = Integer.parseInt(data[2]);
                        stringbuilder.append("price = ").append(price).append("\n");
                    }
                    if (data.length > 3) {

                        String spellDescription = data[3];
                        stringbuilder.append("card description = ").append(spellDescription);
                        return String.valueOf(stringBuilder);

                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return String.valueOf(stringBuilder);
        }


        if (trapFile.exists()) {
            try {
                String row;
                StringBuilder stringbuilder = new StringBuilder();
                String icon;
                String status;
                int price;
                int counter = 4;


                BufferedReader csvReader = new BufferedReader(new FileReader("CardsIE/" + cardName + "TrapCard.csv"));
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");

                    if (data.length > 0) {
                        icon = data[0];
                        stringbuilder.append("icon = ").append(icon).append("\n");
                    }
                    if (data.length > 1) {
                        status = data[1];
                        stringbuilder.append("status = ").append(status).append("\n");
                    }
                    if (data.length > 2) {
                        price = Integer.parseInt(data[2]);
                        stringbuilder.append("price = ").append(price).append("\n");
                    }
                    if (data.length > 3) {

                        String spellDescription = data[3];
                        stringbuilder.append("card description = ").append(spellDescription);
                        return String.valueOf(stringBuilder);

                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    } //todo fixme


    public static void exportMonsterCardForCsv(Card card) {
        System.out.println("salam");
//        PrintWriter pw = null;
//        try {
//            pw = new PrintWriter(new File("CardsIE/" + card.getName() + "MonsterCard.csv"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        StringBuilder builder = new StringBuilder();
//        //String columnNamesList = "Id,Name";
//        // No need give the headers Like: id, Name on builder.append
//        builder.append(columnNamesList +"\n");
//        builder.append("1"+",");
//        builder.append("Chola");
//        builder.append('\n')


        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "MonsterCard.csv");
            writer.write(((MonsterCard) card).getLevel() + "\n" + ((MonsterCard) card).getAttribute() + "\n" +
                    ((MonsterCard) card).getMonsterType() + "\n" + ((MonsterCard) card).getCardType() + "\n" +
                    ((MonsterCard) card).getAttack() + "\n" + ((MonsterCard) card).getDefence() + "\n" +
                    card.getPrice() + "\n" + ((MonsterCard) card).getCanBeNormalSummoned() +
                    "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportSpellCardForCsv(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "SpellCard.csv");
            writer.write(((SpellCard) card).getIcon() + "\n" + ((SpellCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportTrapCardForCsv(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "TrapCard.csv");
            writer.write(((TrapCard) card).getIcon() + "\n" + ((TrapCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static String importCardForCsv(String cardName) {
//
//        String cardInfo = null;
//
//        File monsterFile = new File("CardsIE/" + cardName + "MonsterCard.csv");
//        File trapFile = new File("CardsIE/" + cardName + "TrapCard.csv");
//        File spellFile = new File("CardsIE/" + cardName + "SpellCard.csv");
//
//        try {
//            if (monsterFile.exists()) {
//                cardInfo = importMonsterCardForCsv(monsterFile);
//            }
//            if (trapFile.exists()) {
//                cardInfo = importTrapCardForCsv(trapFile);
//            }
//            if (spellFile.exists()) {
//                cardInfo = importSpellCardForCsv(spellFile);
//            }
//            return cardInfo;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


    private String importSpellCardForCsv(File spellFile) throws FileNotFoundException {
        StringBuilder cardInfo = new StringBuilder();
        String icon;
        String status;
        int price;
        String description;
        int counter = 4;
        Scanner scanner = new Scanner(spellFile);
        while (scanner.hasNextLine()) {
            if (counter == 4) {
                icon = scanner.nextLine();
                cardInfo.append("icon = ").append(icon).append("\n");
            }
            if (counter == 3) {
                status = scanner.nextLine();
                cardInfo.append("status = ").append(status).append("\n");
            }
            if (counter == 2) {
                price = Integer.parseInt(scanner.nextLine());
                cardInfo.append("price = ").append(price).append("\n");
            }
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
                cardInfo.append("card description = ").append(description);
                return String.valueOf(cardInfo);
            }
            if (counter == 0) {
                break;
            }
            counter--;
        }
//        SpellCard spellCard = new SpellCard(icon,description,status,price)
        return "";
    }

    private String importTrapCardForCsv(File trapFile) throws FileNotFoundException {
        StringBuilder cardInfo = new StringBuilder();
        String icon;
        String status;
        int price;
        String description;
        int counter = 4;
        Scanner scanner = new Scanner(trapFile);
        while (scanner.hasNextLine()) {
            if (counter == 4) {
                icon = scanner.nextLine();
                cardInfo.append("icon = ").append(icon).append("\n");
            }
            if (counter == 3) {
                status = scanner.nextLine();
                cardInfo.append("status = ").append(status).append("\n");
            }
            if (counter == 2) {
                price = Integer.parseInt(scanner.nextLine());
                cardInfo.append("price = ").append(price).append("\n");
            }
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
                cardInfo.append("card description = ").append(description);
                return String.valueOf(cardInfo);
            }
            if (counter == 0) {
                break;
            }
            counter--;
        }
//                TrapCard trapCard = new TrapCard(icon,description,status,price);
        return "";
    }

    private String importMonsterCardForCsv(File monsterFile) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        int counter;
        String monsterType;
        String cardType;
        int defencePoint;
        Scanner scanner;
        int price;
        int level;
        boolean canBeNormalSummoned;
        String attribute;
        int attackPoint;
        String description;
        counter = 9;
        scanner = new Scanner(monsterFile);
        while (scanner.hasNextLine()) {
            if (counter == 9) {
                level = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("level = ").append(level).append("\n");
            }
            if (counter == 8) {
                attribute = scanner.nextLine();
                stringBuilder.append("attribute = ").append(attribute).append("\n");
            }
            if (counter == 7) {
                monsterType = scanner.nextLine();
                stringBuilder.append("monster type = ").append(monsterType).append("\n");
            }
            if (counter == 6) {
                cardType = scanner.nextLine();
                stringBuilder.append("card type = ").append(cardType).append("\n");
            }
            if (counter == 5) {
                attackPoint = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("attack point = ").append(attackPoint).append("\n");
            }
            if (counter == 4) {
                defencePoint = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("defence point = ").append(defencePoint).append("\n");
            }
            if (counter == 3) {
                price = Integer.parseInt(scanner.nextLine());
                stringBuilder.append("price = ").append(price).append("\n");
            }
            if (counter == 2) {
                canBeNormalSummoned = Boolean.parseBoolean(scanner.nextLine());
                stringBuilder.append("can be normal summoned = ").append(canBeNormalSummoned).append("\n");
            }
            if (counter == 1) {
                StringBuilder stringBuilder2 = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder2.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder2);
                stringBuilder.append("card description = ").append(description);
                return String.valueOf(stringBuilder);
            }
            if (counter == 0) {
                break;
            }
            counter--;
        }
        return "";
/*                MonsterCard monsterCard = new MonsterCard(level, attribute, monsterType, cardType, attackPoint, defencePoint, description, price, canBeNormalSummoned) {
                    @Override
                    public void takeAction(DuelController duelController, TakeActionCase takeActionCase, User owner, int targetNumber) {

                    }

                    @Override
                    public boolean canBeAttacked(DuelController duelController, int monsterNumber) {
                        return false;
                    }
                };*/
    }







//    public StringBuilder importSpellCardFromCsv(String cardName){
////        StringBuilder stringbuilder = new StringBuilder();
//        StringBuilder stringBuilder = null;
//        try {
//            String row;
//
//            String icon;
//            String status;
//            int price;
//            int counter = 4;
//
//
//            BufferedReader csvReader = new BufferedReader(new FileReader("CardsIE/" + cardName + "SpellCard.csv"));
//            while ((row = csvReader.readLine()) != null) {
//                String[] data = row.split(",");
//
//                StringBuilder stringbuilder = new StringBuilder();
//                if (counter > 0) {
//                    icon = data[0];
//                    stringbuilder.append("icon = ").append(icon).append("\n");
//                }
//                if (counter > 1) {
//                    status = data[1];
//                    stringbuilder.append("status = ").append(status).append("\n");
//                }
//                if (counter > 2) {
//                    price = Integer.parseInt(data[2]);
//                    stringbuilder.append("price = ").append(price).append("\n");
//                }
//                if (counter > 3) {
//
//                    String spellDescription = data[3];
//                    stringbuilder.append("card description = ").append(spellDescription);
//                    return stringBuilder;
//
//                }
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return String.valueOf(stringBuilder);
//    }

}
