package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gate extends Tiles implements Portal {
    private final double frameTime = 0.125;
    private int frame;

    public Gate(double x, double y, GameMap map) {
        super(x, y, map);
        code = SpriteSheetCode.GATE;
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        frame = (int) ((time % (8 * frameTime)) / frameTime);
        gc.drawImage(getSpriteSheet().getSprite(frame, 0), x, y);
    }

    @Override
    public int getLayer() {
        return 2;
    }
}
