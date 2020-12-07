package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gate extends Tiles implements Portal {
    Image image;

    public Gate(double x, double y, GameMap map) {
        super(x, y, map);
        code = SpriteSheetCode.GATE;
        image = getSpriteSheet().getSprite(0, 0);
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(image, x, y);
    }

    @Override
    public int getLayer() {
        return 2;
    }
}
