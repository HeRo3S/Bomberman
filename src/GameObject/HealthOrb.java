package GameObject;


public class HealthOrb extends Orb {
    public HealthOrb(double x, double y, GameMap map) {
        super(x, y, map);
        amount = 20;
        code = SpriteSheetCode.HEALTH_ORB;
    }

    @Override
    protected void effect(Entity entity) {
        entity.health += amount;
        health = 0;
    }
}
