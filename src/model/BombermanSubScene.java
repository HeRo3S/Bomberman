package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;


public class BombermanSubScene extends SubScene {
    private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
    private final String BACKGROUND_PATH = "model/resources/yellow_panel.png";

    private boolean isHidden;

    public BombermanSubScene() {
        super(new AnchorPane(), 853, 480);
        prefWidth(853);
        prefHeight(480);

        Image backgroundImage = new Image(BACKGROUND_PATH,
                853, 480, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage
                , BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, null, null);

        AnchorPane anchorPane = (AnchorPane) this.getRoot();
        anchorPane.setBackground(new Background(background));

        isHidden = true;

        setLayoutX(420 + 1000);
        setLayoutY(120);
    }

    public AnchorPane getSubScene() {
        AnchorPane anchorPane = (AnchorPane) this.getRoot();
        return anchorPane;
    }

    public void transitionSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setByX(-1000);
            transition.play();
            isHidden = false;
        } else {
            transition.setByX(+1000);
            transition.play();
            isHidden = true;
        }

        transition.play();
    }
}
