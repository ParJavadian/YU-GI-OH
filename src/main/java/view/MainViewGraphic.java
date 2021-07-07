package view;

import controller.MainControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;

import java.net.URL;

public class MainViewGraphic extends Application {

    private static Stage stage;
    static MainViewGraphic instance = null;
    private static User user;

    public static MainViewGraphic getInstance() {
        if (instance == null) instance = new MainViewGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        MainViewGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainViewGraphic.stage = stage;
        URL url = getClass().getResource("/MainMenu.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    public void logout() throws Exception {
        MainControllerGraphic.logout(stage);
    }

    public void showScoreboard() throws Exception{
        MainControllerGraphic.showScoreBoard(user,stage);
    }

    public void profileMenu() throws Exception {
        MainControllerGraphic.showProfileMenu(user,stage);
    }

    public void shopMenu() throws Exception {
        MainControllerGraphic.showShopMenu(user,stage);
    }

    public void goToDeckMenu() throws Exception {
        MainControllerGraphic.showDeckMenu(user,stage);
    }

    public void importExport(MouseEvent event) throws Exception{
        ImportExportGraphic.getInstance().start(stage);
    }

    public void newDuel(){
        try {
            MainControllerGraphic.showNewDuelMenu(user,stage);
        }
        catch (Exception e){
            printError(e.getMessage());
        }
    }

    private void printError(String command){
        Alert alert = new Alert(Alert.AlertType.ERROR,command);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }
}
