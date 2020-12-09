package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Crate extends Wall implements Destructible, Impassable {
    public Crate(double x, double y, GameMap map) {
        super(x, y, map,0,0);
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
}
