package GameObject;

public abstract class Rune extends Entity {
    protected double damage;
    protected int explodeTimer;
    protected int primeTime;
    protected double range;
    public Rune(double x, double y, GameMap map) {
        super(x, y, map);
        maxHp = 2;
    }
    protected abstract void explode();
}
