import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

public class PopUp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        Stage stage = new Stage();
        // set title for the stage
        stage.setTitle("Creating popup");

        // create a button
        Button button = new Button("button");

        // create a tile pane
        TilePane tilepane = new TilePane();

        // create a label
        Label label = new Label("This is a Popup");

        // create a popup
        Popup popup = new Popup();

        // set background
        label.setStyle(" -fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

        // set size of label
        label.setMinWidth(80);
        label.setMinHeight(50);

        // action event
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e)
                    {
                        if (!popup.isShowing())
                            popup.show(stage);
                        else
                            popup.hide();
                    }
                };

        // when button is pressed
        button.setOnAction(event);

        // add button
        tilepane.getChildren().add(button);

        // create a scene
        Scene scene = new Scene(tilepane, 200, 200);

        // set the scene
        stage.setScene(scene);

        stage.show();





//        Alert alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
//        Stage stage = new Stage();
//        stage.show();
//        stage.setTitle("Creating popup");
//        // create a label
//        Label label = new Label("This is a Popup");
//
//        // create a popup
//        Popup popup = new Popup();
//
//        // set background
//        label.setStyle(" -fx-background-color: white;");
//
//        // add the label
//        AnchorPane anchorPane = new AnchorPane();
//        popup.getContent().add(anchorPane);
//
//        popup.show(stage);
        //popup.wait();

//        alert.setHeight(100);
//        alert.setWidth(100);
//        alert.setX(400);
//        alert.setY(600);
//        //alert.setWidth(100);
//
//        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Emojione_1F62D.svg/64px-Emojione_1F62D.svg.png");
//        ImageView imageView = new ImageView(image);
//        alert.setGraphic(imageView);
//        alert.showAndWait();




//        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ya hagh", ButtonType.OK);
//        alert.setHeaderText("");
//        alert.setTitle("");
//        alert.showAndWait();
    }
}
