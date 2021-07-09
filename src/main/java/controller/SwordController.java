package controller;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SwordController extends Transition {
    private ImageView imageView;

    public SwordController(ImageView imageView) {
        this.imageView = imageView;
        setCycleDuration(Duration.millis(500));
    }

    @Override
    protected void interpolate(double v) {
        System.out.println("1");
        int frame = (int) Math.floor(v * 5);
        imageView.setImage( new Image("/images/sword/" + frame + ".png"));
    }
}
