package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Tiles implements UnFlyable, NoSeeThrough{
    private int row;
    private int column;

    public Wall(double x, double y, GameMap map, int column, int row)
    {
        super(x, y, map);
        code = SpriteSheetCode.WALL;
        setHitBox(1,1,30,30);
        this.row = row;
        this.column = column;
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(column,row), x, y);
        drawHitBox(gc);
    }
}
