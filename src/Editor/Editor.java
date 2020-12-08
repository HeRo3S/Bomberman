package Editor;

import GameObject.*;
import SpriteManager.SpriteSheetManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

import static SpriteManager.SpriteSheetManager.getSheet;


public class Editor extends Application {

    public static int status = 0;
    public Node selected_photo;
    Group group = new Group();
    HBox hBox = new HBox();

    private static String nameFile = "GameMap.dat";
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 576;
    private static final int row = WIDTH / 32;
    private static final int col = HEIGHT / 32;
    private GraphicsContext gc;
    private Canvas canvas;
    private final int floorRow = 4;
    private final int floorCol = 2;
    private final int wallRow = 13;
    private final int wallCol = 4;
    public static int[][] location = new int[row + 1][col + 1];
    public static GameMap gameMap = new GameMap();
//    private Image image;
    private static ImageView imageView = new ImageView();

    HBox hbox = new HBox();
    enum Select {GREEN, WISP, FLOOR, WALL, PHANTOM, THROWER, BRUTE}

    static Select entitySelect = Select.WISP;


    static int rowSprite;
    static int colSprite;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(group, 1024, 900);

        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        SpriteSheetManager.initialize();

        Select select = Select.WISP;
        Button button1 = CreatButton(select, getSheet(SpriteSheetCode.WISP).getSprite(0, 0));
        hBox.getChildren().add(button1);

        Select select1 = Select.GREEN;
        Button button2 = CreatButton(select1, getSheet(SpriteSheetCode.GREEN).getSprite(0, 0));

        Select select2 = Select.FLOOR;
        Button button3 = CreatButton(select2, getSheet(SpriteSheetCode.FLOOR).getSprite(0, 0));
        button3.setOnMouseClicked(event -> {
            Image image = new Image("GameObject/assets/floor.png");
            imageView.setImage(image);
        });

        Select select3 = Select.WALL;
        Button button4 = CreatButton(select3, getSheet(SpriteSheetCode.WALL).getSprite(0, 0));
        button4.setOnMouseClicked(event -> {
            Image image = new Image("GameObject/assets/wall.png");
            imageView.setImage(image);
        });
        button4.setLayoutX(0);
        button4.setLayoutY(40);

        Select select4 = Select.PHANTOM;
        Button button5 = CreatButton(select4, getSheet(SpriteSheetCode.PHANTOM).getSprite(0, 0));

        Select select5 = Select.THROWER;
        Button button6 = CreatButton(select5, getSheet(SpriteSheetCode.THROWER).getSprite(0, 0));

        Select select6 = Select.BRUTE;
        Button button7 = CreatButton(select6, getSheet(SpriteSheetCode.BRUTE).getSprite(0, 0));

        Button load = new Button("Load");
        load.setOnMouseClicked(event -> {
            try {
                FileInputStream inputStream = new FileInputStream(nameFile);
                ObjectInputStream os = new ObjectInputStream(inputStream);
                Object o1 = os.readObject();
                gameMap = (GameMap) o1;
                os.close();
                canvas = new Canvas(1024, 576);
                gc = canvas.getGraphicsContext2D();
                gameMap.render(gc, 0);
                group.getChildren().add(canvas);
                System.out.println("Đã load lại Map");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        hBox.getChildren().add(button2);
        hBox.getChildren().add(button3);
        hBox.getChildren().add(button4);
        hBox.getChildren().add(button5);
        hBox.getChildren().add(button6);
        hBox.getChildren().add(button7);

        hBox.setLayoutX(0);
        hBox.setLayoutY(576);
        hBox.setMaxWidth(1024);
//        TextField textRow = new TextField("Row");
//        textRow.setMaxSize(50, 50);
//        hBox.getChildren().add(textRow);

        TextField textField = new TextField("GameMap.dat");


//        TextField textCol = new TextField("Col");
//        textCol.setMaxSize(50, 50);
//        hBox.getChildren().add(textCol);
//
//        Button okButton = new Button("OK");
//        okButton.setOnAction(event -> {
//            rowSprite = Integer.parseInt(textRow.getText());
//            colSprite = Integer.parseInt(textCol.getText());
//        });
//        hBox.getChildren().add(okButton);

        Button save = new Button("Save");
        save.setOnAction(event -> {
            try {
                nameFile = textField.getText();
                FileOutputStream file = new FileOutputStream(nameFile);
                ObjectOutputStream os = new ObjectOutputStream(file);
                os.writeObject(gameMap);
                System.out.println("Đã lưu Map thành công ");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        hBox.getChildren().add(textField);
        hBox.getChildren().add(load);
        hBox.getChildren().add(save);

        canvas = new Canvas(1024, 576);
        gc = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);

        hbox.getChildren().add(imageView);
        hbox.setLayoutX(0);
        hbox.setLayoutY(650);

        group.getChildren().add(hBox);
        group.getChildren().add(hbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button CreatButton(Select select, Image image) {
        Button button = new Button();
        button.setMaxSize(40, 40);
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

            int toadoX = clickX * 32;
            int toadoY = clickY * 32;

            if (entitySelect == Select.FLOOR) {
                if (clickY > 19 && clickY < 19 + floorRow ) {
                    rowSprite = clickX ;
                    colSprite = clickY - 20;
                    System.out.println("rowSprite = " + rowSprite + " colSprite = " + colSprite);
                }
            }

            if (entitySelect == Select.WALL) {
                if (clickY > 19 && clickY < 19 + wallRow){
                    rowSprite = clickX;
                    colSprite = clickY - 20;
                    System.out.println("rowSprite = " + rowSprite + " colSprite = " + colSprite);
                }
            }

            if (clickX < row + 1 && clickY < col + 1) {
                if (location[clickX][clickY] == 0) {
                    switch (entitySelect) {
                        case WISP:
                            Wisp wisp = new Wisp(toadoX, toadoY, gameMap);
                            System.out.println("Da tao mot Wisp");
                            gameMap.render(gc, 0);
                            location[clickX][clickY] = 2;
                            break;
                        case GREEN:
                            if (status == 0) {
                                System.out.println("Da tao mot Green");
                                Green green1 = new Green(toadoX, toadoY, gameMap);
                                gameMap.render(gc, 0);
                                location[clickX][clickY] = 1;
                                status = 1;
                                break;
                            } else {
                                System.out.println("Chi duoc tao 1 nhan vat!");
                                break;
                            }
                        case FLOOR:
                            Floor floor = new Floor(toadoX, toadoY, gameMap, colSprite, rowSprite);
                            gameMap.render(gc, 0);
                            location[clickX][clickY] = 0;
                            System.out.println("Đã tạo một floor hình:" + rowSprite + "-" + colSprite);
                            break;

                        case WALL:
                            Wall wall = new Wall(toadoX, toadoY, gameMap, colSprite, rowSprite);
                            gameMap.render(gc, 0);
                            location[clickX][clickY] = 3;
                            System.out.println("Đã tạo một wall hình:" + rowSprite + "-" + colSprite);
                            break;

                        case PHANTOM:
                            Phantom phantom = new Phantom(toadoX, toadoY, gameMap);
                            gameMap.render(gc, 0);
                            location[clickX][clickY] = 4;
                            System.out.println("Đã tạo một PhanTom");
                            break;

                        case BRUTE:
                            Brute brute = new Brute(toadoX, toadoY, gameMap);
                            gameMap.render(gc, 0);
                            location[clickX][clickY] = 4;
                            System.out.println("Đã tạo một Brute");
                            break;

                        case THROWER:
                            Thrower thrower = new Thrower(toadoX, toadoY, gameMap);
                            gameMap.render(gc, 0);
                            location[clickX][clickY] = 4;
                            System.out.println("Đã tạo một Thrower");
                            break;

                        default:
                            break;
                    }
                } else {
                    if (entitySelect == Select.FLOOR) {
                        Floor floor = new Floor(toadoX, toadoY, gameMap, rowSprite, colSprite);
                        gameMap.render(gc, 0);
                        System.out.println("Đã tạo một floor hình:" + rowSprite + "-" + colSprite);
                    } else System.out.println("Ở đây đã có entity");
                }
            }
        }

    };
}

