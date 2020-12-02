package GameObject;

import javafx.scene.canvas.GraphicsContext;

public class Crate extends Tiles implements Destructible, Impassable {

    public Crate(double x, double y, double maxHp, GameMap map) {
        super(x, y, map);
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {

    }
}
