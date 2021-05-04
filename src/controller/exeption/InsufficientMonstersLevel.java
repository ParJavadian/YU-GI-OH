package controller.exeption;

public class InsufficientMonstersLevel extends Exception{
    //TODO motmaen nistam in bashe
    public InsufficientMonstersLevel(){super("selected monsters levels don’t match with ritual monster");}
}
