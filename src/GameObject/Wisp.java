package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.geom.Point2D;
import java.io.IOException;

import static GameObject.GameMap.*;
import static GameObject.GameMap.CHUNK_SIZE;
import static java.lang.Math.*;

public class Wisp extends Hostile {
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
    boolean noPass(Entity entity) {
        if(!entity.isPassable()){
            return true;
        }
        return false;
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
    protected void hostileLogic() {
        boolean canMoveX = true;
        boolean canMoveY = true;
        for(Entity entity : map.getContent(x,y,1)) {
            if(entity != this){
                //Calculate
                if (getModifiedHitBox(dx*speed, 0).intersects(entity.getHitBox()) && canMoveX) {
                    solveCollision(entity);
                    if (noPass(entity)) {
                        dx = 0;
                        canMoveX = false;
                    }
                }
                if (getModifiedHitBox(0, dy*speed).intersects(entity.getHitBox()) && canMoveY) {
                    solveCollision(entity);
                    if (noPass(entity)) {
                        dy = 0;
                        canMoveY = false;
                    }
                }
            }
        }
        x += dx * speed;
        y += dy * speed;
        x = max(min(x,MAP_WIDTH - 32),0);
        y = max(min(y,MAP_HEIGHT - 32),0);
        if(!(new Point2D.Double(x/ CHUNK_SIZE, y/ CHUNK_SIZE).equals(lastPos))){
            map.removeContent(lastPos.getX() * CHUNK_SIZE,lastPos.getY() * CHUNK_SIZE, this);
            map.addContent(x ,y ,this);
            lastPos = new Point2D.Double(x / CHUNK_SIZE, y / CHUNK_SIZE);
        }
    }

    @Override
    public void update() {
        if (getDx() < 0) {
            directionSprite = 0;
        } else {
            directionSprite = 1;
        }
        hostileLogic();
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {
    }
    protected void attack(){
        if (target instanceof Player) {
            Player player = (Player) target;
            player.health -= damage;
            status.add(Status.currentStatus.ATTACK_CD, 5);
        }
    }

    @Override
    protected void drop() {
        new HealthOrb(getCenterX(),getCenterY(),map);
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
    }
}
