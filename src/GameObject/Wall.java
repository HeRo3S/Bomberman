package GameObject;

import javafx.scene.canvas.GraphicsContext;

import static java.lang.Math.min;

public class Wall extends Tiles implements UnFlyable, NoSeeThrough{
    private int row;
    private int column;

    public Wall(double x, double y, GameMap map, int column, int row)
    {
        super(x, y, map);
        code = SpriteSheetCode.WALL;
        setHitBox(1,1,30,30);
        this.row = min(row,12);
        this.column = min(column,3);
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(row,column), x, y);
    }

    @Override
    public int getLayer() {
        return 2;
    }
}
