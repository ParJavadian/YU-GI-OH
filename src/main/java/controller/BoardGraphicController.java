package controller;

import model.Card;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class BoardGraphicController {
    private User player;
    private User rival;


    public int playerLifePoint = player.getLifePoint();
    public int rivalLifePoint = rival.getLifePoint();
    public String playerUsername = player.getUsername();
    public String rivalUsername = rival.getUsername();
    public String playerNickname = player.getNickname();
    public String rivalNickname = rival.getNickname();
    public List<Card> playerGraveYard = player.getBoard().getCardsInGraveyard();
    public List<Card> rivalGraveYard = rival.getBoard().getCardsInGraveyard();
    public String phase = "";


}
