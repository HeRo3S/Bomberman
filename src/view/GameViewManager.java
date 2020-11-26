package view;

import GameObject.GameMap;
import GameObject.Green;
import GameObject.Wisp;
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
    private long startTime = System.nanoTime();

    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 720;
    private static ArrayList<String> input = new ArrayList<>();
    public static ArrayList<String> getInput(){
        return input;
    }
    private GameMap gameMap;
    private Green green;
    private Wisp wisp;

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
                double t = (now - startTime) / 1000000000.0;
                gameMap.updateContent();
                gameMap.getCanvas().getGraphicsContext2D().clearRect(
                        0,0,gameMap.getCanvas().getWidth(),gameMap.getCanvas().getHeight());
                gameMap.render(t);
            }
        };
        gameTimer.start();
    }

    private void createGameMap() {
        gameMap = new GameMap();
        green = new Green(10, 10, 100, gameMap);
        wisp = new Wisp(400, 300, 100, gameMap);
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
