package view;

import controller.ProfileControllerGraphic;
import controller.ScoreBoardControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreBoardViewGraphic extends Application implements Initializable {
    private static Stage stage;
    private static ScoreBoardViewGraphic instance = null;
    @FXML
    Label label = new Label();

    public static ScoreBoardViewGraphic getInstance() {
        if (instance == null) instance = new ScoreBoardViewGraphic();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoardViewGraphic.stage = stage;
        URL url = getClass().getResource("/ScoreBoard.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon.png"))));
        stage.setTitle("YU GI OH");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        label.setText(ScoreBoardControllerGraphic.getInstance().showScoreboard());
    }

    public void goBack() throws Exception {
        ScoreBoardControllerGraphic.goBack(stage);
    }
}
