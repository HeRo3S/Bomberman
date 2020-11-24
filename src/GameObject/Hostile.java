package GameObject;

import SpriteManager.SpriteSheet;

public abstract class Hostile extends Movable {
    protected double damage;
    protected double detectionRange;
    protected double attackSpeed;
    protected int attackTimer;
    protected Entity target;
    public Hostile(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp , map);
    }

    public void moveToTarget(){
        dx = (target.x - x) / (target.x - x)/(target.y-y);
        dy = 1 - dx;
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
