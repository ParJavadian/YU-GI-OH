package view;

import controller.ProfileControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileViewForGraphic extends Application implements Initializable {
    private static Stage stage;
    private static ProfileViewForGraphic instance = null;
    private static User user;
    private static AnchorPane root;
    public static ImageView imageView;
    @FXML
    Label label = new Label();

    public static ProfileViewForGraphic getInstance() {
        if (instance == null) instance = new ProfileViewForGraphic();
        return instance;
    }

    public void setCurrentUser(User user) {
        ProfileViewForGraphic.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProfileViewForGraphic.stage = stage;
        URL url = getClass().getResource("/Profile.fxml");
        root = FXMLLoader.load(url);
        setImageView();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changePassword() throws Exception {
        ProfileControllerGraphic.changePassword(user, stage);
    }

    public void changeNickname() throws Exception {
        ProfileControllerGraphic.changeNickname(user, stage);
    }

    public void goBack() throws Exception {
        ProfileControllerGraphic.goBack(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText("Your Current Username: " + user.getUsername() + "\n Your Current Nickname: " + user.getNickname());
    }


    private void setImageView() {
        Image image;
        if (user.getProfileImage() == null) {
            URL url = getClass().getResource("/images/profiles/profile (" + user.getProfileNumber() + ").png");
            image = new Image(String.valueOf(url));
            user.setProfileImage(image);
        } else {
            image = user.getProfileImage();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(155);
        imageView.setFitHeight(155);
        imageView.setX(125);
        imageView.setY(40);
        imageView.setImage(image);
        root.getChildren().add(imageView);
    }


    public void changeProfilePhoto() throws Exception{
        ChangeProfilePhotoGraphic.getInstance().setCurrentUser(user);
        ChangeProfilePhotoGraphic.getInstance().start(stage);
    }

    public void seeAchievements() throws Exception{
        Achievements.getInstance().setCurrentUser(user);
        Achievements.getInstance().start(stage);
    }
}
