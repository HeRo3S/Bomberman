package GameObject;

import javafx.scene.canvas.GraphicsContext;

import static GameObject.SpriteSheetCode.THROWER;
import static java.lang.Math.random;

public class Thrower extends Hostile implements Destructible,Impassable {
    private int directionSprite;
    private int statusSprite;

    private final String THROWER_ATTACK_SFX = "src/GameObject/sfx/bt_attack.wav";

    public Thrower(double x, double y, GameMap map) {
        super(x, y, map);
        attackRange = 30;
        attackSpeed = 1;
        maxHp = 200;
        health = maxHp;
        speed = 2.5;
        code = THROWER;
        detectionRange = 200;
        attackRange = 120;
        idleTime = 60;
        dyingFrameCount = 0;
        setHitBox(8,8,16,26);
    }

    @Override
    public void idle() {
        if (--idleTimer <= 0) {
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
    protected void attack() {
        if(status.canAttack()) {
            new SpearProjectile(getCenterX(), getCenterY(), map, target.getCenter());
            status.add(Status.currentStatus.ATTACK_CD, 2);
            sfx.playWithFlag(THROWER_ATTACK_SFX);
        }
    }


    @Override
    public void update() {
        hostileLogic();
        if (getDx() == 0) {
            statusSprite = 0;
        } else {
            statusSprite = 4;
            if (getDx() < 0) {
                directionSprite = 0;
            } else if (getDx() > 0) {
                directionSprite = 1;
            }
        }
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        gc.drawImage(getSpriteSheet().getSprite(frame, directionSprite), getX(), getY());
        drawHealthBar(gc);
    }

}
