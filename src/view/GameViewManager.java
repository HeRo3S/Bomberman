package view;

import GameObject.*;
import SpriteManager.SpriteSheet;
import SpriteManager.SpriteSheetManager;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashSet;

public class GameViewManager {
    public AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private StackPane gameStackPane;

    private Stage menuStage;

    private AnimationTimer gameTimer;
    private long startTime = System.nanoTime();

    private Canvas canvas;
    private GraphicsContext gc;

    private SpriteSheet background;

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 720;
    private static HashSet<String> input = new HashSet<>();
    public static HashSet<String> getInput() {
        return input;
    }

    private GameMap gameMap;
    private Green green;
    private Wisp wisp;
    private Phantom phantom;

    public GameViewManager() {
        initializeScene();
    }

    private void initializeScene() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStackPane = new StackPane();

        gameStage.setScene(gameScene);
        gameStage.getIcons().add(new Image("view/resources/icon.jpg"));
        gameStage.setTitle("Bomberman - RE:write Edition");

        canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.scale(1.25, 1.25);
        gameStackPane.setStyle("-fx-background-color: #DEB887");
        gamePane.getChildren().add(gameStackPane);
        gameStackPane.getChildren().add(canvas);
        SpriteSheetManager.initialize();
    }

    public void createNewLevel(Stage menuStage) {
        this.menuStage = menuStage;
        menuStage.hide();
        createGameMap();
        keyboardCheck();
        createGameLoop();
        gameStage.show();
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double t = (now - startTime) / 1000000000.0;
                gameMap.updateContent();
                canvas.getGraphicsContext2D().clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                createGameBackground();
                gameMap.render(gc, t);
            }
        };
        gameTimer.start();
    }

    private void createGameBackground() {
            background = new SpriteSheet("view/resources/gameBackground.png", 1, 9);

        for (int i = 1; i < gameMap.getMapHeight() / 64; i++) {
            for (int k = 1; k < gameMap.getMapWidth() / 64 - 1; k++) {
                gc.drawImage(background.getSprite(4, 0), k * 64, i * 64);
            }
        }
        for (int i = 1; i < gameMap.getMapHeight() / 64; i++) {
            gc.drawImage(background.getSprite(3, 0), 0, i * 64);
            gc.drawImage(background.getSprite(5, 0), gameMap.getMapWidth() - 64, i * 64);
        }
        for (int k = 1; k < gameMap.getMapWidth() / 64 - 1; k++) {
            gc.drawImage(background.getSprite(1, 0), k * 64, 0);
            gc.drawImage(background.getSprite(7, 0), k * 64, gameMap.getMapHeight() - 64);
        }
        gc.drawImage(background.getSprite(0, 0), 0, 0);
        gc.drawImage(background.getSprite(2, 0), gameMap.getMapWidth() - 64, 0);
        gc.drawImage(background.getSprite(6, 0), 0, gameMap.getMapHeight() - 64);
        gc.drawImage(background.getSprite(8, 0), gameMap.getMapWidth() - 64, gameMap.getMapHeight() - 64);
    }

    private void createGameMap() {
        gameMap = new GameMap();
        green = new Green(10, 10, gameMap);
        wisp = new Wisp(400, 300, gameMap);
        phantom = new Phantom(350, 200, gameMap);
        new Wall(200,100,gameMap,0,0);
    }

    public void keyboardCheck() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                    input.add(code);

            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                input.remove(code);
            }
        });
    }
}
