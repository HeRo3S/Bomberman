package Editor;

import GameObject.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class Editor extends Application {
    @FXML
    public HBox hbox;

    public int status = 0;
    public Node selected_photo;

    enum Select{PLAYER, GRASS, WISP}
    Select entitySelect;

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

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Editor/MapEditor.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Map Editor");
        Scene scene = new Scene(root, 1024, 900);


        GameMap gameMap = new GameMap();
        Tiles tiles = new Tiles(0,0,50,gameMap);
        Wisp wisp = new Wisp(0,32,50,gameMap);
        Green green = new Green(0, 64 , 50,gameMap);

        Select select = Select.WISP;
        //Button button = CreatButton(select, wisp.getSpritteSheet(0,0));
        Button button = new Button("WISP");
        hbox.getChildren().add(button);

        scene.setOnMouseClicked(mouseHandler);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button CreatButton (Select select, Image image){
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

            int i = (int) (X / 32);
            int j = (int) (Y / 32);
        }

    };
}

