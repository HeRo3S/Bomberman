package GameObject;

import javafx.scene.canvas.GraphicsContext;

public class Crate extends Tiles implements Destructible {

    public Crate(double x, double y, double maxHp, GameMap map) {
        super(x, y, map);
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
}
