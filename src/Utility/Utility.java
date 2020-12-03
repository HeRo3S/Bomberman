package Utility;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Utility {
    private static ImageView iv = new ImageView();
    public static void drawRotated(GraphicsContext gc, Image image, double x, double y, double angle){
        iv.setRotate(angle + 90);
        iv.setImage(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);
        gc.drawImage(rotatedImage, x, y);
    }
}
