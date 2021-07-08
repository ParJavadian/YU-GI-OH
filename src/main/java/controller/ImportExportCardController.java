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

    public String  importCard(String cardName) {

        String cardInfo = null;

        File monsterFile = new File("CardsIE/" + cardName + "MonsterCard.txt");
        File trapFile = new File("CardsIE/" + cardName + "TrapCard.txt");
        File spellFile = new File("CardsIE/" + cardName + "SpellCard.txt");

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

    private String  importMonsterCard(File monsterFile) throws FileNotFoundException {
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
        }return "";
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
        if (card instanceof MonsterCard)
            exportMonsterCard(card);
        if (card instanceof SpellCard)
            ExportSpellCard(card);
        if (card instanceof TrapCard)
            ExportTrapCard(card);
    }

    private void ExportTrapCard(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "TrapCard.txt");
            writer.write(((TrapCard) card).getIcon() + "\n" + ((TrapCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void ExportSpellCard(Card card) {
        try {
            FileWriter writer = new FileWriter("CardsIE/" + card.getName() + "SpellCard.txt");
            writer.write(((SpellCard) card).getIcon() + "\n" + ((SpellCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
            writer.close();
            System.out.println(card.getNamePascalCase() + "was successfully exported!");
        }catch (IOException e){
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

}
