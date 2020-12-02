package GameObject;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

import static GameObject.SpriteSheetCode.PHANTOM;

public class Phantom extends Hostile {
    private Point2D lastPos;
    private int directionSprite;
    private int statusSprite;
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

    }

    @Override
    protected void drop() {

    }

    @Override
    boolean noPass(Entity entity) {
        if(entity instanceof UnFlyable){
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        if (getDx() == 0) {
          directionSprite = 0;
          statusSprite = 0;
        } else if (getDx() < 0) {
            directionSprite = 1;
            statusSprite = 0;
        } else {
            directionSprite = 1;
            statusSprite = 4;
        }
        hostileLogic();
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        frame = (int) ((time % (4 * frameTime)) / frameTime);
        if (isAnimateDying) {
            directionSprite = 0;
            statusSprite = 4;
            frame = (48 - dyingFrameCount ) / 12  + 4;
        }  else {
            frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        }

        gc.drawImage(getSpriteSheet().getSprite(frame, directionSprite), getX(), getY());
    }
}
