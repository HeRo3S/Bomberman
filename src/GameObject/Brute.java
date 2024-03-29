package GameObject;

import javafx.scene.canvas.GraphicsContext;

import static GameObject.SpriteSheetCode.BRUTE;
import static java.lang.Math.*;

public class Brute extends Hostile implements Impassable, Destructible, Regen {
    double healthRegen = 20;
    double regenTimer = 20;
    private int directionSprite;
    private int statusSprite;

    private final String BRUTE_ATTACK_SFX = "src/GameObject/sfx/bt_attack.wav";

    public Brute(double x, double y, GameMap map) {
        super(x, y, map);
        attackSpeed = 1;
        damage = 100;
        maxHp = 400;
        health = maxHp;
        speed = 1.5;
        code = BRUTE;
        detectionRange = 150;
        attackRange = 60;
        idleTime = 60;
        dyingFrameCount = 0;
        setHitBox(9,9,29,39);
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
    protected void attack() {
            target.modifyHealth(-damage);
            status.add(Status.currentStatus.ATTACK_CD,5);
            status.add(Status.currentStatus.STUN,1);
            sfx.playWithFlag(BRUTE_ATTACK_SFX);
    }



    @Override
    public void update() {
        regen();
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
        if(entity instanceof Crate && target != null){
            entity.modifyHealth(-damage);
        }
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
        frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        gc.drawImage(getSpriteSheet().getSprite(frame, directionSprite), getX(), getY());
        drawHealthBar(gc);
    }

    @Override
    public void regen() {
        regenTimer --;
        if(!status.isBurning() && regenTimer <= 0){
            health = min(health += healthRegen,maxHp);
            regenTimer = regenDelay;
        }
    }
}
