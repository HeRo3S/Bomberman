package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.geom.Point2D;
import java.io.IOException;

import static GameObject.GameMap.*;
import static GameObject.GameMap.CHUNK_SIZE;
import static java.lang.Math.*;

public class Wisp extends Hostile implements Impassable, Destructible {
    private final double frameTime = 0.100;
    private int directionSprite;
    private int statusSprite;
    public Wisp(double x, double y, GameMap map) {
        super(x, y, map);
        detectionRange = 50;
        maxHp = 100;
        damage = 20;
        attackSpeed = 1;
        setHitBox(10, 5, 12, 18);
        setSpeed(1);
        attackRange = 28;
        idleTime = 60;
        code = SpriteSheetCode.WISP;
        health = maxHp;
    }



    @Override
    public void idle() {
        if(--idleTimer <= 0) {
            int rand = (int) (random() * 10);
            switch (rand) {
                case 0:
                    dx = 0;
                    dy = 1;
                    idleTimer = idleTime;
                    break;
                case 1:
                    dx = 0;
                    dy = -1;
                    idleTimer = idleTime;
                    break;
                case 2:
                    dx = 1;
                    dy = 0;
                    idleTimer = idleTime;
                    break;
                case 3:
                    dx = -1;
                    dy = 0;
                    idleTimer = idleTime;
                    break;
                default:
                    dx = 0;
                    dy = 0;
                    idleTimer = idleTime;
            }
        }
    }

    @Override
    public void update() {
        hostileLogic();
        if (getDx() < 0) {
            directionSprite = 0;
        } else {
            directionSprite = 1;
        }
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {
    }
    protected void attack(){
            target.modifyHealth(-damage);
            status.add(Status.currentStatus.ATTACK_CD, 5);
    }


    @Override
    protected void animate(GraphicsContext gc, double time) {
        if (isAnimateDying) {
            directionSprite= 2;
            statusSprite = 0;
            frame =  (48 - dyingFrameCount ) / 12;
        } else {
            if (attackFrameCount != 0) {
                statusSprite = 4;
                attackFrameCount--;
            } else {
                statusSprite = 0;
            }
            frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        }
        gc.drawImage(getSpriteSheet().getSprite(frame, directionSprite), getX(), getY());
        drawHealthBar(gc);
    }
}
