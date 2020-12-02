package Editor;

import GameObject.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Editor extends Application {
    @FXML
    public HBox hbox;

    @FXML
    private AnchorPane anchorPane;


    public int status = 0;
    public Node selected_photo;
    Group group = new Group();
    HBox hBox = new HBox();


    private final int WEIGHT = 1024;
    private final int HEIGHT = 734;
    private int row = WEIGHT / 32;
    private int col = HEIGHT / 32;

    public static GameMap gameMap = new GameMap();

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        Tiles tiles = new Tiles(0, 0, 50, gameMap);
//        Wisp wisp = new Wisp(0, 32, 50, gameMap);
//        Green green = new Green(0, 64, 50, gameMap);
//
//        Select select = Select.WISP;
//        Button button1 = CreatButton(select, wisp.getSpritteSheet(0, 0));
//        hBox.getChildren().add(button1);
//
//        Select select1 = Select.GREEN;
//        Button button2 = CreatButton(select1, green.getSpritteSheet(0, 0));
//        hBox.getChildren().add(button2);
//        hBox.setLayoutX(0);
//        hBox.setLayoutY(734);
//
//        group.getChildren().add(hBox);
//    }

    enum Select {PLAYER, GREEN, WISP}

    static Select entitySelect = Select.WISP;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(group,1024,900);

        scene.setOnMouseClicked(mouseHandler);
        Tiles tiles = new Tiles(0, 0, 50, gameMap);
        Wisp wisp = new Wisp(0, 32, 50, gameMap);
        Green green = new Green(0, 64, 50, gameMap);
        Select select = Select.WISP;
        Button button1 = CreatButton(select, wisp.getSpritteSheet(0, 0));
        hBox.getChildren().add(button1);

        Select select1 = Select.GREEN;
        Button button2 = CreatButton(select1, green.getSpritteSheet(0, 0));
        hBox.getChildren().add(button2);
        hBox.setLayoutX(0);
        hBox.setLayoutY(734);

        group.getChildren().add(hBox);

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
                        Image imageWisp = wisp.getSpritteSheet(0,0);
                        Rectangle rectangle = new Rectangle();
                        rectangle.setX(clickX * 32);
                        rectangle.setY(clickY * 32);
                        rectangle.setWidth(32);
                        rectangle.setHeight(32);
                        rectangle.setFill(new ImagePattern(imageWisp, 0, 0, 1, 1, true));
                        group.getChildren().add(rectangle);
                        break;
                    case GREEN:
                        Green green = new Green(X, Y, 50, gameMap);
                        System.out.println("Da tao mot Green");
                        Green green1 = new Green(X,Y,50,gameMap);
                        Image imageGreen = green1.getSpritteSheet(0,0);
                        Rectangle rectangle1 = new Rectangle();
                        rectangle1.setX(clickX * 32);
                        rectangle1.setY(clickY * 32);
                        rectangle1.setWidth(32);
                        rectangle1.setHeight(32);
                        rectangle1.setFill(new ImagePattern(imageGreen, 0, 0, 1, 1, true));
                        group.getChildren().add(rectangle1);
                        break;
                    case PLAYER:
                        Player player = new Player(X, Y, 50, gameMap) {
                            @Override
                            public void update() {

                            }

                            @Override
                            protected void solveCollision(Entity entity) {

                            }

                            @Override
                            protected void animate(GraphicsContext gc, double time) {

                            }
                        };
                        System.out.println("Da tao mot Player");
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

