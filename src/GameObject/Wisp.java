package GameObject;

import SpriteManager.SpriteSheet;

import java.util.List;

public class Wisp extends Hostile {

    public Wisp(double x, double y, double maxHp, SpriteSheet spriteSheet, GameMap map) {
        super(x, y, maxHp, spriteSheet, map);
        detectionRange = 120;
        damage = 20;
    }

    @Override
    public void idle() {

    }

    @Override
    public void update(List<Entity> entities) {
        //Reset
        dx = 0;
        dy = 0;
        target = null;
        if(!status.isStunning()) {
            for (Entity entity : map.getContent(x, y, detectionRange)) {
                //Find target
                if (entity instanceof Player) {
                    target = entity;
                }
                if (collide(entity)) {
                    solveCollision(entity);
                }
            }
        }
        //Choose moving course
        if(target != null){
            moveToTarget();
        }
        else {
            idle();
        }
        //Finish and move to designated coordinate
        if(!status.isChannelling() && !status.isStunning())
        move();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate() {

    }
}
