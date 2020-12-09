package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Crate extends Wall implements Destructible, Impassable {
    Image image;

    public Crate(double x, double y, GameMap map) {
        super(x, y, map,0,0);
        code = SpriteSheetCode.CRATE;
        image = getSpriteSheet().getSprite(0, 0);
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
    }
}
