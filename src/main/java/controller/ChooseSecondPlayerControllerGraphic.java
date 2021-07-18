package controller;

import controller.exeption.InvalidDeck;
import controller.exeption.NoActiveDeck;
import controller.exeption.PlayerNotFound;
import javafx.stage.Stage;
import model.User;
import view.ChooseDuelView;

public class ChooseSecondPlayerControllerGraphic {

    public static void twoPlayerGame(User user, Stage stage) throws Exception {
        if (user.getActiveDeck() == null)
            throw new NoActiveDeck(user.getUsername());
        if (!user.getActiveDeck().isValid())
            throw new InvalidDeck(user.getUsername());
        ChooseDuelView.getInstance().setCurrentUser(user);
        ChooseDuelView.getInstance().setPlayerTwoName("");
        ChooseDuelView.getInstance().start(stage);
    }
}
