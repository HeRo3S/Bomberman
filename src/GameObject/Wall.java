package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Tiles implements UnFlyable, NoSeeThrough{
    private int row;
    private int column;

    public Wall(double x, double y, GameMap map, int row, int column)
    {
        super(x, y, map);
        code = SpriteSheetCode.WALL;
        setHitBox(0,0,32,32);
        this.row = row;
        this.column = column;
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(row,column), x, y);
        drawHitBox(gc);
    }
}
