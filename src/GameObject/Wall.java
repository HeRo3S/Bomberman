package GameObject;

import javafx.scene.canvas.GraphicsContext;

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
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(column,row), x, y);
        drawHitBox(gc);
    }

    @Override
    public int getLayer() {
        return 2;
    }
}
