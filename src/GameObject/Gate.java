package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gate extends Tiles implements Portal {

    public Gate(double x, double y, GameMap map) {
        super(x, y, map);
        code = SpriteSheetCode.GATE;
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(0, 0), x, y);
    }

    @Override
    public int getLayer() {
        return 2;
    }
}
