package GameObject;

import SpriteManager.SpriteSheet;

import java.util.List;

public class Tiles extends Entity {
    private boolean destructible;

    public Tiles(double x, double y, double maxHp, SpriteSheet spriteSheet, GameMap map) {
        super(x, y, maxHp, spriteSheet, map);
    }

    @Override
    public void update(List<Entity> entities) {

    }

    @Override
    public void solveCollision(Entity entity) {

    }

    @Override
    protected void animate() {

    }

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }
}
