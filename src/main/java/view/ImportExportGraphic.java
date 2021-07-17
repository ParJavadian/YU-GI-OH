package view;

import controller.ImportExportCardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Card;
import model.User;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportExportGraphic extends Application {

    private static Stage stage;
    static ImportExportGraphic instance = null;
    private static User user;
    public static ImageView cardImage = new ImageView();
    public static Label label = new Label();
    public static AnchorPane root;
    public TextField toBeExportedCard;

    public static ImportExportGraphic getInstance() {
        if (instance == null) instance = new ImportExportGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ImportExportGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ImportExportGraphic.stage = stage;
        URL url = getClass().getResource("/ImportExport.fxml");
        root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBack() throws Exception{
        MainViewGraphic.getInstance().start(stage);
    }

    public void onDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void onDragDropped(DragEvent dragEvent) {
        try {
            String cardInfo;
            List<File> files = dragEvent.getDragboard().getFiles();
            File file = new File(files.toString());
            String fileName = file.getName();
            cardInfo = ImportExportCardController.getInstance(user).importCard(changeName(fileName));
            Card card = Card.getCardByName(changeName(fileName));
            if (card != null)
                cardImage.setImage(card.getImage());
            else {
                URL url = getClass().getResource("/images/Cards/defaultCard.JPG");
                Image image = new Image(String.valueOf(url));
                cardImage.setImage(image);
            }
            label.setText(cardInfo);
            label.setPrefHeight(328);
            label.setPrefWidth(182);
            label.setLayoutX(545);
            label.setLayoutY(250);
            label.setWrapText(true);
            label.setAlignment(Pos.TOP_LEFT);
            Font font = Font.font("Agency FB", 18);
            label.setFont(font);
            label.setTextFill(Color.BLACK);
            cardImage.setX(568);
            cardImage.setY(39);
            cardImage.setFitHeight(204);
            cardImage.setFitWidth(140);
            if (!root.getChildren().contains(cardImage))
                root.getChildren().add(cardImage);
            if (!root.getChildren().contains(label))
                root.getChildren().add(label);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exportCard() {
        if (Card.getCardByName(normalToPascalCase(toBeExportedCard.getText())) != null) {
            ImportExportCardController.getInstance(user).exportCard(Card.getCardByName(normalToPascalCase(toBeExportedCard.getText())));
            Alert information = new Alert(Alert.AlertType.INFORMATION);
            information.setHeaderText("Success");
            information.setContentText("your card was successfully exported");
            information.showAndWait();
        }else {
            Alert error = new Alert(Alert.AlertType.WARNING);
            error.setHeaderText("Error");
            error.setContentText("No card with the given name exists in the game!");
            error.showAndWait();
        }
    }

    public String normalToPascalCase(String cardName){
        String pascalName = cardName.charAt(0) + cardName.substring(1).toUpperCase();
        Pattern pattern = Pattern.compile("_([a-z])[a-z]+");
        Matcher matcher = pattern.matcher(pascalName);
        while (matcher.find())
            pascalName = pascalName.replace(" " + matcher.group(1), " " + matcher.group(1).toUpperCase());
        pascalName = pascalName.replaceAll(" ", "_");
        return pascalName;
    }

    public String changeName(String completeName){
        Pattern pattern = Pattern.compile("([\\w]+)(SpellCard.txt|MonsterCard.txt|TrapCard.txt|SpellCard.csv|MonsterCard.csv|TrapCard.csv)");
        Matcher matcher = pattern.matcher(completeName);
        if (matcher.find())
            return matcher.group(1);
        else return "";
    }

}