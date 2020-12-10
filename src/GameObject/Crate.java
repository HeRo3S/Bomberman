package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Crate extends Tiles implements Destructible, Impassable {
    public Crate(double x, double y, GameMap map) {
        super(x, y, map);
        code = SpriteSheetCode.CRATE;
        health = 100;
        dyingFrameCount = 0;
    }

    @Override
    public void update(){
        basicLogic();
    }
    @Override
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(0,0),x,y);
    }

    @Override
    public int getLayer() {
        return 1;
    }
}
