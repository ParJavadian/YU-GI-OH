import controller.ImportExportCardController;
import controller.ImportExportUserController;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.MonsterCard;
import view.LogInView;

public class Main {

    public static void main(String[] args)  {

        //System.out.println(ImportExportCardController.importCardFromCsv("COMMAND_KNIGHT"));
        ImportExportCardController.exportMonsterCardForCsv(MonsterCard.COMMAND_KNIGHT);

//        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ya hagh", ButtonType.OK);
//        alert.setHeaderText("");
//        alert.setTitle("");
//        alert.showAndWait();

//        Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
//        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Emojione_1F62D.svg/64px-Emojione_1F62D.svg.png");
//        ImageView imageView = new ImageView(image);
//        alert.setGraphic(imageView);
//        alert.showAndWait();


//        ImportExportUserController importExportUserController = ImportExportUserController.getInstance();
//        importExportUserController.importAllUsers();
//        importExportUserController.importAllCards();
//        importExportUserController.importAllDecks();
//        LogInView.getInstance().getCommandForLogin();
    }
}
/*
user login -u hamraz -p 123
menu enter Deck
deck set-activate one
menu exit
duel -n -ai -r 3
next phase
 */