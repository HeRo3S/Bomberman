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

import java.util.ArrayList;

public class GameViewManager {
    public AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;

    private AnimationTimer gameTimer;

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 720;
    private static ArrayList<String> input = new ArrayList<>();
    public static ArrayList<String> getInput(){
        return input;
    }
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
        keyboardCheck();
        createGameloop();
        gameStage.show();
    }

    private void createGameloop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameMap.updateContent();
                gameMap.render();
            }
        };
        gameTimer.start();
    }

    private void createGameMap() {
        gameMap = new GameMap();
        green = new Green(10, 10, 100, gameMap);
        gamePane.getChildren().add(gameMap.getCanvas());
    }

    public void keyboardCheck() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                if ( !input.contains(code) )
                    input.add( code );

            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                input.remove( code );
            }
        });
    }
}
