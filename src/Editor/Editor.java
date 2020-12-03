package Editor;

import GameObject.*;
import SpriteManager.SpriteSheetManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static GameObject.SpriteSheetCode.WALL;


public class Editor extends Application {

    public static int status = 0;
    public Node selected_photo;
    Group group = new Group();
    HBox hBox = new HBox();


    private static final int WEIGHT = 1024;
    private static final int HEIGHT = 734;
    private static final int row = WEIGHT / 32;
    private static final int col = HEIGHT / 32;

    public static int[][] location = new int[row][col];
    public static GameMap gameMap = new GameMap();

    enum Select {GREEN, WISP}

    static Select entitySelect = Select.WISP;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(group, 1024, 900);

        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        SpriteSheetManager.initialize();

        Select select = Select.WISP;
        Button button1 = CreatButton(select, SpriteSheetManager.getSheet(SpriteSheetCode.WISP).getSprite(0,0));
        hBox.getChildren().add(button1);

        Select select1 = Select.GREEN;
        Button button2 = CreatButton(select1, SpriteSheetManager.getSheet(SpriteSheetCode.GREEN).getSprite(0,0));
        hBox.getChildren().add(button2);
        hBox.setLayoutX(0);
        hBox.setLayoutY(734);

        Button save = new Button("Save");
        save.setOnAction(event -> {
            try {
                FileOutputStream file = new FileOutputStream("GameMap.dat");
                ObjectOutputStream os = new ObjectOutputStream(file);
                os.writeObject(gameMap);
                System.out.println("Đã lưu Map thành công ");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        hBox.getChildren().add(save);

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
                if (location[clickX][clickY] == 0){
                    switch (entitySelect) {
                        case WISP:
                            Wisp wisp = new Wisp(X, Y, gameMap);
                            System.out.println("Da tao mot Wisp");
                            Image imageWisp = wisp.getSpriteSheet().getSprite(0,0);
                            Rectangle rectangle = new Rectangle();
                            rectangle.setX(clickX * 32);
                            rectangle.setY(clickY * 32);
                            rectangle.setWidth(32);
                            rectangle.setHeight(32);
                            rectangle.setFill(new ImagePattern(imageWisp, 0, 0, 1, 1, true));
                            group.getChildren().add(rectangle);
                            location[clickX][clickY] = 2;
                            break;
                        case GREEN:
                            if (status == 0) {
                                System.out.println("Da tao mot Green");
                                Green green1 = new Green(X, Y, gameMap);
                                Image imageGreen = green1.getSpriteSheet(0, 0);
                                Rectangle rectangle1 = new Rectangle();
                                rectangle1.setX(clickX * 32);
                                rectangle1.setY(clickY * 32);
                                rectangle1.setWidth(32);
                                rectangle1.setHeight(32);
                                rectangle1.setFill(new ImagePattern(imageGreen, 0, 0, 1, 1, true));
                                group.getChildren().add(rectangle1);
                                status = 1;
                                break;
                            } else {
                                System.out.println("Chi duoc tao 1 nhan vat!");
                                break;
                            }
                        default:
                            Tiles tiles = new Tiles(X, Y, gameMap);
                            location[clickX][clickY] = 3;
                            System.out.println("Da tao mot Tiles");
                    }
                }else{
                    System.out.println("Vị trí này đã có Entity! Không tạo ");
                }
            }
        }

    };

}

