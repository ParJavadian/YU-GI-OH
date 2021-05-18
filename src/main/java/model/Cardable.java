package model;
public interface Cardable {
    public String getName();
    public String getNamePascalCase();
    public String getDescription();
    public int getPrice();
    @Override
    public String toString();
}
