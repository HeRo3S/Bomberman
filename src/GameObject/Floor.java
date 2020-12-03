package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Floor extends Tiles{
    int row;
    int column;

    public Floor(double x, double y, GameMap map, int row, int column) {
        super(x, y, map);
        code = SpriteSheetCode.FLOOR;
        this.row = row;
        this.column = column;
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(row,column), x, y);
    }

}
