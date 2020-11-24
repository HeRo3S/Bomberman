package view;

import GameObject.GameMap;
import GameObject.Green;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameViewManager {
    public AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;

    private AnimationTimer gameTimer;

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 720;

    public boolean leftIsPressed;
    public boolean rightIsPressed;
    public boolean upIsPressed;
    public boolean downIsPressed;

    private GameMap gameMap;
    private Green green;

    public GameViewManager() {
        initializeScene();
    }

    private void initializeScene() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewLevel(Stage menuStage) {
        this.menuStage = menuStage;
        menuStage.hide();
        createGameMap();
        createGameloop();
        gameStage.show();
    }

    private void createGameloop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                /**
                 * Write your logic handles here.
                 */
                testGreenMovement();
            }
        };
        gameTimer.start();
    }

    private void createGameMap() {
        gameMap = new GameMap();
        green = new Green(10, 10, 100, gameMap);
        gameMap.addContent(10 , 10, green);
        gamePane.getChildren().add(green.setUp());
    }

    public void keyboardCheck() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    upIsPressed = true;
                } else if (event.getCode() == KeyCode.A) {
                    leftIsPressed = true;
                } else if (event.getCode() == KeyCode.S) {
                    downIsPressed = true;
                } else if (event.getCode() == KeyCode.D) {
                    rightIsPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    upIsPressed = false;
                } else if (event.getCode() == KeyCode.A) {
                    leftIsPressed = false;
                } else if (event.getCode() == KeyCode.S) {
                    downIsPressed = false;
                } else if (event.getCode() == KeyCode.D) {
                    rightIsPressed = false;
                }
            }
        });
    }

    public void testGreenMovement() {
        keyboardCheck();
        if (leftIsPressed) {
            green.setUp().setLayoutX(green.getX() - 1);
            green.setX(green.getX() - 1);
        } else if (rightIsPressed) {
            green.setUp().setLayoutX(green.getX() + 1);
            green.setX(green.getX() + 1);
        }

        if (downIsPressed) {
            green.setUp().setLayoutY(green.getY() + 1);
            green.setY(green.getY() + 1);
        } else if (upIsPressed) {
            green.setUp().setLayoutY(green.getY() - 1);
            green.setY(green.getY() - 1);
        }

    }
}
