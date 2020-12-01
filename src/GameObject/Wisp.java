package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

import static java.lang.Math.random;

public class Wisp extends Hostile {

    private static SpriteSheet spriteSheet;
    private final double frameTime = 0.100;
    private int directionSprite;
    private int statusSprite;
    private int attackFrameCount;
    private Entity testing;
    private void createSprite() {
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/wispSheet.png", 3, 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Wisp(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        detectionRange = 100;
        damage = 20;
        attackSpeed = 1;
        status = new Status();
        createSprite();
        setHitBox(10, 5, 12, 18);
        setSpeed(1);
        attackRange = 28;
        idleTime = 60;
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
    public void update() {
        //Reset
        target = null;
        if (!status.isStunning() && !status.isChannelling()) {
            findTarget();
            if(testing == null){
                testing = target;
            }
        }
        //Choose moving course
        if (target != null) {
            moveToTarget();
            //render components
            if (getDx() < 0) {
                directionSprite = 0;
            } else {
                directionSprite = 1;
            }
            //end of render components
            if (getDistance(target) < attackRange) {
                //render components
                if (status.canAttack()) {
                    attackFrameCount = 60;
                    attack();
                }
                //end of render components
            }
        } else {
            idle();
        }
        //Finish and move to designated coordinate
        if (!status.isChannelling() && !status.isStunning()) {
            move();
        }
        basicLogic();
        status.update();
    }

    @Override
    protected void solveCollision(Entity entity) {
    }
    protected void attack(){
        if (target instanceof Player && status.canAttack()) {
            Player player = (Player) target;
            player.health -= damage;
            status.add(Status.currentStatus.ATTACK_CD, 5);
            System.out.println(target.health);
        }
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        if (isAnimateDying) {
            directionSprite= 2;
            statusSprite = 0;
            frame =  (24 - dyingFrameCount ) / 6;
        } else {
            if (attackFrameCount != 0) {
                statusSprite = 4;
                attackFrameCount--;
            } else {
                statusSprite = 0;
            }
            frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        }
        gc.drawImage(spriteSheet.getSprite(frame, directionSprite), getX(), getY());
        if(testing != null) {
            gc.strokeLine(testing.getCenterX(), testing.getCenterY(), getCenterX(), getCenterY());
        }
        drawHitBox(gc);
    }
}
