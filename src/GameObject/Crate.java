package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

public class Crate extends Tiles implements Destructible {
    private static SpriteSheet spriteSheet;

    public Crate(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        setPassable(false);
        setDestructible();
    }

    @Override
    public void setDestructible() {
        setDestructible(true);
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {

    }

    private void createSpriteSheet() {
    }
}
