package GameObject;

public class EnergyOrb extends Orb {

    public EnergyOrb(double x, double y, GameMap map) {
        super(x, y, map);
        amount = 20;
        code = SpriteSheetCode.ENERGY_ORB;
    }


    @Override
    protected void effect(Entity entity) {
        Player player = (Player) entity;
        player.energy += amount;
        health = 0;
    }
}
