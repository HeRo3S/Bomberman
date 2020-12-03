package GameObject;


import java.awt.geom.Line2D;

public abstract class Hostile extends Movable {
    protected double damage;
    protected double detectionRange;
    protected double attackSpeed;
    protected double attackRange;
    protected Entity target;
    protected Line2D lineOfSight = new Line2D.Double();
    protected int idleTimer;
    protected int idleTime;
    protected int attackFrameCount;
    protected Status status;
    public Hostile(double x, double y, GameMap map) {
        super(x, y , map);
        status = new Status();
    }
    public void findTarget(){
        for (Entity entity : map.getContent(x, y, detectionRange)) {
            //Find target
            if (entity instanceof Player) {
                target = entity;
                for (Entity entity1 : map.getContent(target.x, target.y, 0)) {

                    lineOfSight.setLine(target.getCenter(), getCenter());
                    if (collide(lineOfSight,entity1.getHitBox()) && entity1 != target) {
                        target = null;
                        break;
                    }
                }
            }
            if(target != null && entity != this && entity != target) {
                lineOfSight.setLine((float)target.getCenterX(),(float)target.getCenterY(), (float)getCenterX(), (float)getCenterY());
                if (collide(lineOfSight,entity.getHitBox())) {
                    target = null;
                }
            }
        }
    }
    public void moveToTarget(){
        moveTo(target.x,target.y);
    }
    public abstract void idle();
    protected abstract void attack();
    protected void drop(int type, int time){
        if (time >= 3){
            return;
        }
        switch (type){
            case 0:
                new HealthOrb(x,y,map);
                if((int) Math.random() * 2 == 1){
                    drop(0,time++);
                }
                break;
            case 1:
                new EnergyOrb(x,y,map);
                if((int) Math.random() * 2 == 1){
                    drop(1,time++);
                }
        }

    }
    protected void hostileLogic() {
        target = null;
        if (!status.isStunning() && !status.isChannelling()) {
            findTarget();
        }
        //Choose moving course
        if (target != null) {
            if(getDistance(target) > attackRange) {
                moveToTarget();
            }
            else{
                dx = dy = 0;
            }
            if (getDistance(target) <= attackRange) {
                //render components
                if (status.canAttack()) {
                    attack();
                }
            }
        } else {
            idle();
        }
        if (!status.isChannelling() && !status.isStunning()) {
            move();
        }
        if(dyingFrameCount <= 1){
            if(health <= 0){
                drop(0,0);
                drop(1,0);
            }
        }
        status.update();
    }
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
