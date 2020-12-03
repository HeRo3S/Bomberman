package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Floor extends Tiles{
    Image image;

    public Image getImage() {
        return image;
    }

    public Floor(double x, double y, GameMap map, int row, int column) {
        super(x, y, map);
        code = SpriteSheetCode.FLOOR;
        image = getSpriteSheet().getSprite(row,column);
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(image, x, y);
    }

}
