package GameObject;

public abstract class Drop extends Movable {
    public Drop(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
    }
    protected abstract void effect(Entity entity);
}
