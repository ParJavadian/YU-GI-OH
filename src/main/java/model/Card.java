package model;


import javafx.scene.image.Image;

public interface Card {
    String getName();
    String getNamePascalCase();
    String getDescription();
    int getPrice();
    @Override
    String toString();
    Image getImage();
}
