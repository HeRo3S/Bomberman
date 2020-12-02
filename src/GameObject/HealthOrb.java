package GameObject;

import SpriteManager.SpriteSheet;

import java.io.IOException;

public class HealthOrb extends Particle {
    public HealthOrb(double x, double y, GameMap map) {
        super(x, y, map);
        amount = 20;
        code = SpriteSheetCode.HEALTH_ORB;
    }

    @Override
    protected void effect(Entity entity) {
        entity.health += amount;
        System.out.println(entity.health);
        health = 0;
    }
}
