package view;

import controller.ChangeNicknameControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

public class ImportExportGraphic extends Application {

    private static Stage stage;
    static ImportExportGraphic instance = null;
    private static User user;

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
        Parent root = FXMLLoader.load(url);
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

    public void onDragDropped(DragEvent dragEvent) throws FileNotFoundException {

/*
        List<File> files = dragEvent.getDragboard().getFiles();
        Text textDocument = new Text(new FileInputStream(files.get(0)));
        Text
*/
    }
}
