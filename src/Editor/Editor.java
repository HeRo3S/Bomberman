package Editor;

import GameObject.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Editor extends Application {
    @FXML
    public HBox hbox;

    public static Rectangle[][] rectangles = new Rectangle[1024 / 32][734 /32];

    public void DrawRectangle(int widthMap, int heightMap){
        for (int i = 0; i < widthMap; i += 32){
            for (int j = 0; j < heightMap; j+= 32){
                Rectangle rectangle = new Rectangle();
                rectangle.setX(j * 32);
                rectangle.setY(i * 32);
                rectangle.setWidth(32);
                rectangle.setHeight(32);
                rectangles[i][j] = rectangle;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapEditor.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(root, 1024, 900);


        GameMap gameMap = new GameMap();
        Tiles tiles = new Tiles(0,0,50,gameMap);
        Wisp wisp = new Wisp(0,32,50,gameMap);
        Green green = new Green(0, 64 , 50,gameMap);
//        ObservableList<Entity> objectList = FXCollections.observableArrayList();
//        //objectList.addAll({tiles,green,wisp});
//        objectList.add(tiles);
//        objectList.add(wisp);
//        objectList.add(green);

        //ListView<Entity> listView = new ListView<>((ObservableList<Entity>) objectList);


        scene.setOnMouseClicked(mouseHandler);
//        scene.setOnMouseDragged(mouseHandler);
//        scene.setOnMouseEntered(mouseHandler);
//        scene.setOnMouseExited(mouseHandler);
//        scene.setOnMouseMoved(mouseHandler);
//        scene.setOnMousePressed(mouseHandler);
//        scene.setOnMouseReleased(mouseHandler);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    EventHandler<MouseEvent>mouseEventEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            
        }
    };

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println(mouseEvent.getEventType() + "\n"
                    + "X : Y - " + mouseEvent.getX() + " : " + mouseEvent.getY() + "\n"
                    + "SceneX : SceneY - " + mouseEvent.getSceneX() + " : " + mouseEvent.getSceneY() + "\n"
                    + "ScreenX : ScreenY - " + mouseEvent.getScreenX() + " : " + mouseEvent.getScreenY());

            double X = mouseEvent.getSceneX();
            double Y = mouseEvent.getSceneY();

            int i = (int) (X / 32);
            int j = (int) (Y / 32);
        }

    };
}

