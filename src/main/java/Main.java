import Client.Controller.SoundController;
import javafx.application.Application;
import javafx.stage.Stage;
import Client.view.SignUpViewGraphic;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        SoundController.getInstance().playWhenStart();
        SignUpViewGraphic.getInstance().start(primaryStage);
    }
}