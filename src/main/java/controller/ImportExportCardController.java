package controller;

import model.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ImportExportCardController {
    private static ImportExportCardController instance = null;
    private User user;

    public static ImportExportCardController getInstance(User user) {
        if (instance == null) instance = new ImportExportCardController(user);
        else if (!instance.user.equals(user)) instance.user = user;
        return instance;
    }

    private ImportExportCardController(User user) {
        this.user = user;
    }

    public void importCard(String cardName) {
        int level = 0, attackPoint = 0, defencePoint = 0, price = 0, counter;
        String attribute = "";
        String monsterType = "";
        String cardType = "";
        String icon = "";
        String status = "";
        String description = "";
        boolean canBeNormalSummoned = false;

        File monsterFile = new File("Cards/" + cardName + "MonsterCard.txt");
        File trapFile = new File("Cards/" + cardName + "TrapCard.txt");
        File spellFile = new File("Cards/" + cardName + "SpellCard.txt");
        Scanner scanner = null;
        try {
            if (monsterFile.exists()) {
                importMonsterCard(monsterFile);
            }
            if (trapFile.exists()) {
                importTrapCard(trapFile);
            }
            if (spellFile.exists()) {
                importSpellCard(spellFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void importSpellCard(File spellFile) throws FileNotFoundException {
        String icon;
        String status;
        int price;
        String description;
        int counter = 4;
        Scanner scanner = new Scanner(spellFile);
        while (scanner.hasNextLine()) {
            if (counter == 4)
                icon = scanner.nextLine();
            if (counter == 3)
                status = scanner.nextLine();
            if (counter == 2)
                price = Integer.parseInt(scanner.nextLine());
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
            }
            if (counter == 0)
                break;
            counter--;
        }
//                SpellCard spellCard = new SpellCard(icon,description,status,price)
    }

    private void importTrapCard(File trapFile) throws FileNotFoundException {
        String icon;
        String status;
        int price;
        String description;
        int counter = 4;
        Scanner scanner = new Scanner(trapFile);
        while (scanner.hasNextLine()) {
            if (counter == 4)
                icon = scanner.nextLine();
            if (counter == 3)
                status = scanner.nextLine();
            if (counter == 2)
                price = Integer.parseInt(scanner.nextLine());
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
            }
            if (counter == 0)
                break;
            counter--;
        }
/*
                TrapCard trapCard = new TrapCard(icon,description,status,price);
*/
    }

    private void importMonsterCard(File monsterFile) throws FileNotFoundException {
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
            if (counter == 9)
                level = Integer.parseInt(scanner.nextLine());
            if (counter == 8)
                attribute = scanner.nextLine();
            if (counter == 7)
                monsterType = scanner.nextLine();
            if (counter == 6)
                cardType = scanner.nextLine();
            if (counter == 5)
                attackPoint = Integer.parseInt(scanner.nextLine());
            if (counter == 4)
                defencePoint = Integer.parseInt(scanner.nextLine());
            if (counter == 3)
                price = Integer.parseInt(scanner.nextLine());
            if (counter == 2)
                canBeNormalSummoned = Boolean.parseBoolean(scanner.nextLine());
            if (counter == 1) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                description = String.valueOf(stringBuilder);
            }
            if (counter == 0)
                break;
            counter--;
        }
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

    public void exportCard(Cardable card) {
        if (card instanceof MonsterCard)
            exportMonsterCard(card);
        if (card instanceof SpellCard)
            ExportSpellCard(card);
        if (card instanceof TrapCard)
            ExportTrapCard(card);
    }

    private void ExportTrapCard(Cardable card) {
        try {
            FileWriter writer = new FileWriter("Cards/" + card.getName() + "TrapCard.txt");
            writer.write(((TrapCard) card).getIcon() + "\n" + ((TrapCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void ExportSpellCard(Cardable card) {
        try {
            FileWriter writer = new FileWriter("Cards/" + card.getName() + "SpellCard.txt");
            writer.write(((SpellCard) card).getIcon() + "\n" + ((SpellCard) card).getStatus() + "\n"
                    + card.getPrice() + "\n" + card.getDescription());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void exportMonsterCard(Cardable card) {
        try {
            FileWriter writer = new FileWriter("Cards/" + card.getName() + "MonsterCard.txt");
            writer.write(((MonsterCard) card).getLevel() + "\n" + ((MonsterCard) card).getAttribute() + "\n" +
                    ((MonsterCard) card).getMonsterType() + "\n" + ((MonsterCard) card).getCardType() + "\n" +
                    ((MonsterCard) card).getAttack() + "\n" + ((MonsterCard) card).getDefence() + "\n" +
                    card.getPrice() + "\n" + ((MonsterCard) card).getCanBeNormalSummoned() +
                    "\n" + card.getDescription());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
