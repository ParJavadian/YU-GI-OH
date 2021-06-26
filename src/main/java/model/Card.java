package model;
public interface Card {
    String getName();
    String getNamePascalCase();
    String getDescription();
    int getPrice();
    @Override
    String toString();
}
