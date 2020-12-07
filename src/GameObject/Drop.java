package GameObject;

public abstract class Drop extends Movable {
    int delay = 20;
    public Drop(double x, double y, GameMap map) {
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
        dx = Math.random();
        dy = 1 -dx;
    }
    protected abstract void effect(Entity entity);
    protected void dropLogic(){
        if(--delay <= 0){
            dx = dy = 0;
        }
    }
}
