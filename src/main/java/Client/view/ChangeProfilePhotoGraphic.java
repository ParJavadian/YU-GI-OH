package Client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ChangeProfilePhotoGraphic extends Application {

    private static Stage stage;
    static ChangeProfilePhotoGraphic instance = null;
    private static User user;
    public ImageView destination;

    public static ChangeProfilePhotoGraphic getInstance() {
        if (instance == null) instance = new ChangeProfilePhotoGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ChangeProfilePhotoGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ChangeProfilePhotoGraphic.stage = stage;
        URL url = getClass().getResource("/ChangeProfilePhoto.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goBack() throws Exception {
        ProfileViewForGraphic.getInstance().start(stage);
    }

    public void handleDragOver(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void onDragDropped(DragEvent dragEvent) throws IOException {
        List<File> files = dragEvent.getDragboard().getFiles();
        FileInputStream fileInputStream = new FileInputStream(files.get(0));
        File file = new File(files.toString());
        String path = file.getPath();
        System.out.println("path = " + path);
        Image image = new Image(fileInputStream);
        user.setProfileImage(image);
    }
}
