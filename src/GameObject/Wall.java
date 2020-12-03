package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Tiles implements UnFlyable{
    private Image image;

    public Image getImage() {
        return image;
    }

    public Wall(double x, double y, GameMap map, int row, int column)
    {
        super(x, y, map);
        code = SpriteSheetCode.WALL;
        image = getSpriteSheet().getSprite(row,column);
        setHitBox(0,0,32,32);

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(image, x, y);
        drawHitBox(gc);
    }
}
