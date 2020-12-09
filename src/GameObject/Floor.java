package GameObject;

import javafx.scene.canvas.GraphicsContext;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Floor extends Tiles{
    int row;
    int column;

    public Floor(double x, double y, GameMap map, int column, int row) {
        super(x, y, map);
        code = SpriteSheetCode.FLOOR;
        this.row = min(row,3);
        this.column = min(column,1);
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(row,column), x, y);
    }

    @Override
    public int getLayer() {
        return 0;
    }
}
