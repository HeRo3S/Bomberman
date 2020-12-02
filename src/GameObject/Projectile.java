package GameObject;

public abstract class Projectile extends Movable implements Impassable {
    public Projectile(double x, double y, GameMap map) {
        super(x, y, map);
    }
}
