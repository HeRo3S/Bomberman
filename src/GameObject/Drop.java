package GameObject;

public abstract class Drop extends Movable {
    public Drop(double x, double y, GameMap map) {
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
    }
    protected abstract void effect(Entity entity);
}
