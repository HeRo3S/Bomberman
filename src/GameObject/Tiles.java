package GameObject;

import javafx.scene.canvas.GraphicsContext;

public abstract class Tiles extends Entity {
    private boolean destructible;

    public Tiles(double x, double y, GameMap map) {
        super(x, y, map);
    }

    @Override
    public void update() {

    }

    @Override
    public void solveCollision(Entity entity) {

    }

    @Override
    public void animate(GraphicsContext gc, double time) {

    }

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }
}
