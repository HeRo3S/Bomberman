package GameObject;

import SpriteManager.SpriteSheet;

import java.util.List;

public class Green extends Player {

    public Green(double x, double y, double maxHp, SpriteSheet spriteSheet, GameMap map) {
        super(x, y, maxHp, spriteSheet, map);
    }

    @Override
    public void update(List<Entity> entities) {

    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate() {

    }
}
