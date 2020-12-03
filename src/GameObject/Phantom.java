package GameObject;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

import static GameObject.SpriteSheetCode.PHANTOM;
import static GameObject.Status.currentStatus.ATTACK_CD;

public class Phantom extends Hostile implements Destructible, Impassable {
    private Point2D lastPos;
    public Phantom(double x, double y, GameMap map) {
        super(x, y, map);
        lastPos = new Point2D.Double(x,y);
        attackRange = 30;
        attackSpeed = 1;
        damage = 50;
        maxHp = 100;
        health = maxHp;
        speed = 4;
        code = PHANTOM;
        detectionRange = 200;
        attackRange = 20;
    }

    @Override
    public void idle() {
        moveTo(lastPos);
    }

    @Override
    protected void attack() {
        if(status.canAttack()){
            target.health -= damage;
            status.add(ATTACK_CD,3);
        }
    }

    @Override
    protected boolean noPass(Entity entity) {
        if(entity instanceof UnFlyable){
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        hostileLogic();
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {

    }
}
