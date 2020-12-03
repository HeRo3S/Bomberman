package GameObject;


import java.awt.geom.Line2D;

public abstract class Hostile extends Movable implements Destructible {
    protected double damage;
    protected double detectionRange;
    protected double attackSpeed;
    protected double attackRange;
    protected Entity target;
    protected Line2D lineOfSight = new Line2D.Double();
    protected int idleTimer;
    protected int idleTime;
    protected int attackFrameCount;
    public Hostile(double x, double y, GameMap map) {
        super(x, y , map);
    }
    public void findTarget(){
        for (Entity entity : map.getContent(x, y, detectionRange)) {
            //Find target
            if (entity instanceof Player) {
                target = entity;
                for (Entity entity1 : map.getContent(target.x, target.y, 0)) {
                    lineOfSight.setLine(target.getCenter(), getCenter());
                    if (entity1 instanceof NoSeeThrough && lineOfSight.intersects(entity1.getHitBox())) {
                        target = null;
                        break;
                    }
                }
            }
            if(entity instanceof NoSeeThrough && target != null) {
                lineOfSight.setLine(target.getCenter(),getCenter());
                if (lineOfSight.intersects(entity.getHitBox())) {
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
                if(Math.random()*2 <= 1){
                    drop(0,time++);
                }
                break;
            case 1:
                new EnergyOrb(x,y,map);
                if(Math.random() * 2 <= 1){
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
        if(status.isBurning()){
            health -= 0.5;
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
