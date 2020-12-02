package Editor;

import GameObject.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.paint.ImagePattern;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Editor extends Application implements Initializable {
    @FXML
    public HBox hbox;

    public int status = 0;
    public Node selected_photo;

    private final int WEIGHT = 1024;
    private final int HEIGHT = 734;
    private int row = WEIGHT / 32;
    private int col = HEIGHT / 32;

    GameMap gameMap = new GameMap();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tiles tiles = new Tiles(0, 0, 50, gameMap);
        Wisp wisp = new Wisp(0, 32, 50, gameMap);
        Green green = new Green(0, 64, 50, gameMap);

        Select select = Select.WISP;
        Button button1 = CreatButton(select, wisp.getSpriteSheet(0, 0));
        hbox.getChildren().add(button1);

        Select select1 = Select.GREEN;
        Button button2 = CreatButton(select1, green.getSpriteSheet(0, 0));
        hbox.getChildren().add(button2);

    }

    enum Select {PLAYER, GREEN, WISP}

    static Select entitySelect = Select.GREEN;

    public static ArrayList<ArrayList<Rectangle>> rectangles = new ArrayList<ArrayList<Rectangle>>();

    public void DrawRectangle(int widthMap, int heightMap) {
        for (int i = 0; i < widthMap; i += 32) {
            ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
            for (int j = 0; j < heightMap; j += 32) {
                Rectangle rectangle = new Rectangle();
                rectangle.setX(j * 32);
                rectangle.setY(i * 32);
                rectangle.setWidth(32);
                rectangle.setHeight(32);
                rectangleArrayList.add(rectangle);
            }
            rectangles.add(rectangleArrayList);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Editor/MapEditor.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(root, 1024, 900);
        DrawRectangle(WEIGHT, HEIGHT);


        scene.setOnMouseClicked(mouseHandler);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button CreatButton(Select select, Image image) {
        Button button = new Button();
        button.setGraphic(new ImageView(image));
        button.setOnAction(event -> {
            selected_photo = button.getGraphic();
            entitySelect = select;
            System.out.println(entitySelect);
        });
        return button;
    }

    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println(mouseEvent.getEventType() + "\n"
                    + "X : Y - " + mouseEvent.getX() + " : " + mouseEvent.getY() + "\n"
                    + "SceneX : SceneY - " + mouseEvent.getSceneX() + " : " + mouseEvent.getSceneY() + "\n"
                    + "ScreenX : ScreenY - " + mouseEvent.getScreenX() + " : " + mouseEvent.getScreenY());

            double X = mouseEvent.getSceneX();
            double Y = mouseEvent.getSceneY();

            int clickX = (int) (X / 32);
            int clickY = (int) (Y / 32);

            if (clickX <= row && clickY <= col) {
                switch (entitySelect) {
                    case WISP:
                        Wisp wisp = new Wisp(X, Y, 50, gameMap);
                        System.out.println("Da tao mot Wisp");
                        Image imageWisp = wisp.getSpriteSheet(0,0);
                        rectangles.get(clickX).get(clickY).setFill(new ImagePattern(imageWisp, 0, 0, 1, 1, true));
                        break;
                    case GREEN:
                        Green green = new Green(X, Y, 50, gameMap);
                        System.out.println("Da tao mot Green");
                        break;
                    default:
                        Tiles tiles = new Tiles(X, Y, 50, gameMap);
                        System.out.println("Da tao mot Tiles");
                }
            }
        }

    };

    public GameMap getGameMap() {
        return gameMap;
    }
}

