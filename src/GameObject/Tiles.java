package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

public class Tiles extends Entity {
    private boolean destructible;

    public Tiles(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
    }

    @Override
    public void update() {

    }

    @Override
    public void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc) {

    }

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }
}
