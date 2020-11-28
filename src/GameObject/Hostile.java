package GameObject;
import static java.lang.Math.abs;
import static java.lang.Math.max;

public abstract class Hostile extends Movable {
    protected double damage;
    protected double detectionRange;
    protected double attackSpeed;
    protected double attackRange;
    protected Entity target;
    public Hostile(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp , map);
    }

    public void moveToTarget(){
        double steps = max(abs(target.x - x), abs(target.y - y));
        if (steps == 0)
        {
            dx = dy = 0;
        }
        else
        {
            dx = (target.x - x) / steps;
            dy = (target.y - y) /steps;
        }
    }
    public abstract void idle();

    /**
     * Getter/Setter
     */

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDetectionRange() {
        return detectionRange;
    }

    public void setDetectionRange(double detectionRange) {
        this.detectionRange = detectionRange;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
