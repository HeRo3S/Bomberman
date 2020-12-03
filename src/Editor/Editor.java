package Editor;

import GameObject.*;
import SpriteManager.SpriteSheetManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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

import static GameObject.SpriteSheetCode.FLOOR;
import static GameObject.SpriteSheetCode.WALL;
import static SpriteManager.SpriteSheetManager.getSheet;


public class Editor extends Application {

    public static int status = 0;
    public Node selected_photo;
    Group group = new Group();
    HBox hBox = new HBox();


    private static final int WEIGHT = 1024;
    private static final int HEIGHT = 734;
    private static final int row = WEIGHT / 32;
    private static final int col = HEIGHT / 32;

    public static int[][] location = new int[row+1][col+1];
    public static GameMap gameMap = new GameMap();

    enum Select {GREEN, WISP,FLOOR, WALL, PHANTOM, THROWER, BRUTE}

    static Select entitySelect = Select.WISP;

    int rowSprite = 0;
    int colSprite = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(group, 1024, 900);

        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        SpriteSheetManager.initialize();

        Select select = Select.WISP;
        Button button1 = CreatButton(select, getSheet(SpriteSheetCode.WISP).getSprite(0,0));
        hBox.getChildren().add(button1);

        Select select1 = Select.GREEN;
        Button button2 = CreatButton(select1, getSheet(SpriteSheetCode.GREEN).getSprite(0,0));

        Select select2 = Select.FLOOR;
        Button button3 = CreatButton(select2, getSheet(SpriteSheetCode.FLOOR).getSprite(0,0));

        Select select3 = Select.WALL;
        Button button4 = CreatButton(select3, getSheet(SpriteSheetCode.WALL).getSprite(0,0));
        button4.setLayoutX(0);
        button4.setLayoutY(40);

        Select select4 = Select.PHANTOM;
        Button button5 = CreatButton(select4, getSheet(SpriteSheetCode.PHANTOM).getSprite(0,0));

        Select select5 = Select.THROWER;
        Button button6 = CreatButton(select5, getSheet(SpriteSheetCode.THROWER).getSprite(0,0));

        Select select6 = Select.BRUTE;
        Button button7 = CreatButton(select6, getSheet(SpriteSheetCode.BRUTE).getSprite(0,0));

        hBox.getChildren().add(button2);
        hBox.getChildren().add(button3);
        hBox.getChildren().add(button4);
        hBox.getChildren().add(button5);
        hBox.getChildren().add(button6);
        hBox.getChildren().add(button7);

        Image imageFloor = new Image("GameObject/assets/floor.png");
        Rectangle rectangle = new Rectangle();
        rectangle.setX(0);
        rectangle.setY(836);
        rectangle.setWidth(96);
        rectangle.setHeight(48);
        rectangle.setFill(new ImagePattern(imageFloor, 0, 0, 1, 1, true));
        hBox.getChildren().add(rectangle);

        Image image = new Image("GameObject/assets/wall.png");
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setX(0);
        rectangle3.setY(836);
        rectangle3.setWidth(312);
        rectangle3.setHeight(96);
        rectangle3.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        hBox.getChildren().add(rectangle3);

        hBox.setLayoutX(0);
        hBox.setLayoutY(734);
        hBox.setMaxWidth(1024);
        TextField textRow = new TextField("Row");
        textRow.setMaxSize(50,50);
        hBox.getChildren().add(textRow);

        TextField textCol = new TextField("Col");
        textCol.setMaxSize(50,50);
        hBox.getChildren().add(textCol);

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            rowSprite = Integer.parseInt(textRow.getText());
            colSprite = Integer.parseInt(textCol.getText());
        });
        hBox.getChildren().add(okButton);

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
        button.setMaxSize(40,40);
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

            if (clickX < row+1 && clickY < col+1) {
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
                                location[clickX][clickY] = 1;
                                status = 1;
                                break;
                            } else {
                                System.out.println("Chi duoc tao 1 nhan vat!");
                                break;
                            }
                        case FLOOR:
                            Floor floor = new Floor(X,Y,gameMap,rowSprite,colSprite);
                            Rectangle rectangle3 = new Rectangle();
                            rectangle3.setX(clickX * 32);
                            rectangle3.setY(clickY * 32);
                            rectangle3.setWidth(32);
                            rectangle3.setHeight(32);
                            rectangle3.setFill(new ImagePattern(getSheet(FLOOR).getSprite(rowSprite,colSprite), 0, 0, 1, 1, true));
                            group.getChildren().add(rectangle3);
                            location[clickX][clickY] = 1;
                            System.out.println("Đã tạo một floor hình:" + rowSprite + "-" + colSprite);
                            break;

                        case WALL:
                            Wall wall = new Wall(X,Y,gameMap,rowSprite,colSprite);
                            Rectangle rectangle4 = new Rectangle();
                            rectangle4.setX(clickX * 32);
                            rectangle4.setY(clickY * 32);
                            rectangle4.setWidth(32);
                            rectangle4.setHeight(32);
                            rectangle4.setFill(new ImagePattern(getSheet(WALL).getSprite(rowSprite,colSprite), 0, 0, 1, 1, true));
                            group.getChildren().add(rectangle4);
                            location[clickX][clickY] = 3;
                            System.out.println("Đã tạo một wall hình:" + rowSprite + "-" + colSprite);
                            break;

                        case PHANTOM:
                            Phantom phantom = new Phantom(X,Y,gameMap);
                            Rectangle rectangle5 = new Rectangle();
                            rectangle5.setX(clickX * 32);
                            rectangle5.setY(clickY * 32);
                            rectangle5.setWidth(32);
                            rectangle5.setHeight(32);
                            rectangle5.setFill(new ImagePattern(getSheet(SpriteSheetCode.PHANTOM).getSprite(0,0), 0, 0, 1, 1, true));
                            group.getChildren().add(rectangle5);
                            location[clickX][clickY] = 4;
                            System.out.println("Đã tạo một PhanTom");
                            break;

                        case BRUTE:
                            Brute brute = new Brute(X,Y,gameMap);
                            Rectangle rectangle6 = new Rectangle();
                            rectangle6.setX(clickX * 32);
                            rectangle6.setY(clickY * 32);
                            rectangle6.setWidth(32);
                            rectangle6.setHeight(32);
                            rectangle6.setFill(new ImagePattern(getSheet(SpriteSheetCode.BRUTE).getSprite(0,0), 0, 0, 1, 1, true));
                            group.getChildren().add(rectangle6);
                            location[clickX][clickY] = 4;
                            System.out.println("Đã tạo một Brute");
                            break;

                        case THROWER:
                            Thrower thrower = new Thrower(X,Y,gameMap);
                            Rectangle rectangle7 = new Rectangle();
                            rectangle7.setX(clickX * 32);
                            rectangle7.setY(clickY * 32);
                            rectangle7.setWidth(32);
                            rectangle7.setHeight(32);
                            rectangle7.setFill(new ImagePattern(getSheet(SpriteSheetCode.THROWER).getSprite(0,0), 0, 0, 1, 1, true));
                            group.getChildren().add(rectangle7);
                            location[clickX][clickY] = 4;
                            System.out.println("Đã tạo một Thrower");
                            break;

                        default:
//                            Tiles tiles = new Tiles(X, Y, gameMap);
//                            location[clickX][clickY] = 3;
//                            System.out.println("Da tao mot Tiles");
                            break;
                    }
                }else{
                    System.out.println("Vị trí này đã có entity");
                }
            }
        }

    };
}

