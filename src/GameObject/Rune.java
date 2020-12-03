package GameObject;

public abstract class Rune extends Movable {
    protected double damage;
    protected int explodeTimer;
    protected int primeTime;
    protected double range;
    public Rune(double x, double y, GameMap map) {
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
        dyingFrameCount = 0;
    }
    protected abstract void explode();
}
