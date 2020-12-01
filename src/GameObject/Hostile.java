package GameObject;


import java.awt.geom.Line2D;

import static java.lang.Math.*;

public abstract class Hostile extends Movable {
    protected double damage;
    protected double detectionRange;
    protected double attackSpeed;
    protected double attackRange;
    protected Entity target;
    protected Line2D lineOfSight = new Line2D.Double();
    public Hostile(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp , map);
    }
    public void findTarget(){
        for (Entity entity : map.getContent(x, y, detectionRange)) {
            //Find target
            if (entity instanceof Player) {
                target = entity;
                for (Entity entity1 : map.getContent(target.x, target.y, 0)) {

                    lineOfSight.setLine(target.getCenter(), getCenter());
                    if (lineOfSight.intersects(entity1.getHitBox()) && entity1 != target) {
                        target = null;
                        break;
                    }
                }
            }
            if(target != null && entity != this && entity != target) {
                lineOfSight.setLine((float)target.getCenterX(),(float)target.getCenterY(), (float)getCenterX(), (float)getCenterY());
                if (lineOfSight.intersects(entity.getHitBox())) {
                    target = null;
                }
            }
        }
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
    protected abstract void attack();

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
