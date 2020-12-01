package GameObject;

public abstract class Rune extends Entity {
    protected double damage;
    protected int explodeTimer;
    protected int primeTime;
    protected double range;
    public Rune(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
    }
    protected abstract void explode();
}
