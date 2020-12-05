package GameObject;


import java.awt.geom.Point2D;

public abstract class Projectile extends Movable {
    protected Point2D target;
    public Projectile(double x, double y, GameMap map) {
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
        dyingFrameCount = 0;
    }
    public Projectile(double x, double y, GameMap map, Point2D target){
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
        this.target = target;
        moveTo(target);
    }
}
