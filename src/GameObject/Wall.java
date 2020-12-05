package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static java.lang.Math.round;

public class Wall extends Tiles implements UnFlyable, NoSeeThrough{
    private int row;
    private int column;

    public Wall(double x, double y, GameMap map, int column, int row)
    {
        super(x, y, map);
        code = SpriteSheetCode.WALL;
        setHitBox(0,0,32,32);
        this.row = row;
        this.column = column;
        this.x = round(x/32)* 32;
        this.y = round(y/32) * 32;
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(column,row), x, y);
        drawHitBox(gc);
    }
}
