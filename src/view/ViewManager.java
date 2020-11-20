package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BombermanButton;
import model.BombermanSubScene;
import model.infoLabel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;

    private MediaPlayer mediaPlayer;
    private final static String TITLE_THEME_PATH = "src/view/resources/titleTheme.mp3";

    private final static int WIDTH = 1280;
    private final static int HEIGHT = 720;

    private final static int MENU_BUTTONS_INITIALISE_X = 440;
    private final static int MENU_BUTTONS_INITIALISE_Y = 650;

    private BombermanSubScene creditsSubScene;
    private BombermanSubScene levelSubScene;
    private BombermanSubScene helpSubScene;
    private BombermanSubScene sceneIsShowed;

    List<BombermanButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        mainStage.getIcons().add(new Image("view/resources/icon.jpg"));
        mainStage.setTitle("Bomberman - RE:write Edition");

        createBackground();
        createLogo();
        createMenuButtons();
        createSubScenes();
        createBGM();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuButtons(BombermanButton button) {
        button.setLayoutX(MENU_BUTTONS_INITIALISE_X + menuButtons.size()* 210);
        button.setLayoutY(MENU_BUTTONS_INITIALISE_Y);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createMenuButtons() {
        createStartButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    public void createSubScenes() {
        levelSubScene = new BombermanSubScene();
        mainPane.getChildren().add(levelSubScene);

        helpSubScene = new BombermanSubScene();
        mainPane.getChildren().add(helpSubScene);

        creditsSubScene = new BombermanSubScene();
        infoLabel creditsLabel = new infoLabel("Credits: \n");
        creditsLabel.setTextFromFile("src/model/resources/Credits.txt");
        creditsSubScene.getSubScene().getChildren().add(creditsLabel);
        mainPane.getChildren().add(creditsSubScene);
    }

    private void handleSubScenes(BombermanSubScene subScene) {
        if (sceneIsShowed == subScene) {
            subScene.transitionSubScene();
            sceneIsShowed = null;
            return;
        } else if (sceneIsShowed != null) {
            sceneIsShowed.transitionSubScene();
        }
        subScene.transitionSubScene();
        sceneIsShowed = subScene;
        return;
    }

    private void createStartButton() {
        BombermanButton button = new BombermanButton("Start");
        addMenuButtons(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.stop();
                GameViewManager gameViewManager = new GameViewManager();
                gameViewManager.createNewLevel(mainStage);
            }
        });
    }

    private void createHelpButton() {
        BombermanButton button = new BombermanButton("Help");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSubScenes(helpSubScene);
            }
        });
    }

    private void createCreditsButton() {
        BombermanButton button = new BombermanButton("Credits");
        addMenuButtons(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSubScenes(creditsSubScene);
            }
        });
    }

    private void createExitButton() {
        BombermanButton button = new BombermanButton("Exit");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("view/resources/background.jpg",
                1280,720,true,true);
        BackgroundImage background = new BackgroundImage(backgroundImage
                , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null );
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("view/resources/logo.png");
        logo.setLayoutX(0);
        logo.setLayoutY(-50);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);
    }

    private void createBGM() {
        Media media = new Media(new File(TITLE_THEME_PATH).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }
}
