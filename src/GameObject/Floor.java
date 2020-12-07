package GameObject;

import javafx.scene.canvas.GraphicsContext;


public class Floor extends Tiles{
    int row;
    int column;

    public Floor(double x, double y, GameMap map, int column, int row) {
        super(x, y, map);
        code = SpriteSheetCode.FLOOR;
        this.row = row;
        this.column = column;
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
