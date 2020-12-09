package GameObject;




import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import static java.lang.Math.random;

public abstract class Hostile extends Movable implements Destructible {
    protected double damage;
    protected double detectionRange;
    protected double attackSpeed;
    protected double attackRange;
    protected Entity target;
    protected Line lineOfSight;
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
                lineOfSight= new Line(target.getCenterX(), target.getCenterY(),getCenterX(),getCenterY());
                for (Entity entity1 : map.getContent(target.x, target.y, GameMap.CHUNK_SIZE + 1)) {
                    if (checkSeeThrough(entity1) &&
                            ((Path)Shape.intersect(lineOfSight, entity1.getHitBox())).getElements().size() > 0) {
                        target = null;
                        break;
                    }
                }
            }
            if(checkSeeThrough(entity) && target != null) {
                if (((Path)Shape.intersect(lineOfSight, entity.getHitBox())).getElements().size() > 0) {
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
        time++;
        switch (type){
            case 0:
                HealthOrb healthOrb = new HealthOrb(x + random() * width,y + random() * height,map);
                if(random()*2 <= 1){
                    drop(0,time);
                }
                break;
            case 1:
                new EnergyOrb(x + random() * width,y + random() * height,map);
                if(random() * 2 <= 1){
                    drop(1,time);
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
            modifyHealth(-10);
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
    protected boolean checkSeeThrough(Entity entity){
        return entity instanceof NoSeeThrough;
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
