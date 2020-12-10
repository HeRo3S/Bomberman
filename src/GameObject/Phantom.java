package GameObject;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

import static GameObject.SpriteSheetCode.PHANTOM;
import static GameObject.Status.currentStatus.ATTACK_CD;
import static GameObject.Status.currentStatus.STUN;

public class Phantom extends Hostile implements Destructible {
    private Point2D spawnPos;
    private int directionSprite;
    private int statusSprite;

    private final String PHANTOM_ATTACK_SFX = "src/GameObject/sfx/phantom_attack.wav";

    public Phantom(double x, double y, GameMap map) {
        super(x, y, map);
        spawnPos = new Point2D.Double(getCenterX(),getCenterY());
        attackRange = 30;
        attackSpeed = 1;
        damage = 50;
        maxHp = 100;
        health = maxHp;
        speed = 3;
        code = PHANTOM;
        detectionRange = 200;
        attackRange = 30;
        setHitBox(8, 4, 16, 24);
    }

    @Override
    public void idle() {
        moveTo(spawnPos);
    }

    @Override
    protected void attack() {
        if(status.canAttack()){
            target.modifyHealth(-damage);
            status.add(ATTACK_CD,3);
            status.add(Status.currentStatus.STUN,1);
            sfx.playWithFlag(PHANTOM_ATTACK_SFX);
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
        speed = 3;
        for(Entity entity : map.getContent(x,y,detectionRange)){
            if (entity instanceof Rune){
                if(getDistance(entity) <= ((Rune) entity).range) {
                    speed = 5;
                    break;
                }
            }
        }
        hostileLogic();
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
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected boolean checkSeeThrough(Entity entity){
        if(entity instanceof  Crate){
            return false;
        }
        return entity instanceof NoSeeThrough;
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        frame = (int) ((time % (4 * frameTime)) / frameTime);
        if (isAnimateDying) {
            directionSprite = 0;
            statusSprite = 4;
            frame = (48 - dyingFrameCount ) / 12  + 3;
        }  else {
            frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        }

        gc.drawImage(getSpriteSheet().getSprite(frame, directionSprite), getX(), getY());
        drawHealthBar(gc);
    }
}
