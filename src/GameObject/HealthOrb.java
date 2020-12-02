package GameObject;

import SpriteManager.SpriteSheet;

import java.io.IOException;

public class HealthOrb extends Particle {
    public HealthOrb(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/healthOrb.png",1,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        amount = 20;
    }

    @Override
    protected void effect(Entity entity) {
        entity.health += amount;
        System.out.println(entity.health);
        health = 0;
    }
}
