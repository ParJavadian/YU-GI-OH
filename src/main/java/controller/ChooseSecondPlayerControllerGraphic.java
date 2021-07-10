package controller;

import controller.exeption.InvalidDeck;
import controller.exeption.NoActiveDeck;
import controller.exeption.PlayerNotFound;
import javafx.stage.Stage;
import model.User;
import view.ChooseDuelView;

public class ChooseSecondPlayerControllerGraphic {

    public static void twoPlayerGame(String secondPlayerUsername, User user, Stage stage)throws Exception {
        if(secondPlayerUsername==null)
            throw new PlayerNotFound();
        User secondUser = User.getUserByUsername(secondPlayerUsername);
        if (secondUser.getActiveDeck() == null)
            throw new NoActiveDeck(secondPlayerUsername);
        if(!user.getActiveDeck().isValid())
            throw new InvalidDeck(secondPlayerUsername);
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName(secondPlayerUsername);
        ChooseDuelView.getInstance().start(stage);
    }
}
