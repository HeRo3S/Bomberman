package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Crate extends Tiles implements Destructible, Impassable {
    Image image;

    public Crate(double x, double y, double maxHp, GameMap map) {
        super(x, y, map);
        code = SpriteSheetCode.CRATE;
        image = getSpriteSheet().getSprite(0, 0);
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {

    }
}
