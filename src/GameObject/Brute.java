package GameObject;

import javafx.scene.canvas.GraphicsContext;

import static GameObject.SpriteSheetCode.BRUTE;
import static GameObject.SpriteSheetCode.THROWER;
import static java.lang.Math.random;

public class Brute extends Hostile {
    private int directionSprite;
    private int statusSprite;

    public Brute(double x, double y, GameMap map) {
        super(x, y, map);
        attackRange = 30;
        attackSpeed = 1;
        damage = 100;
        maxHp = 500;
        health = maxHp;
        speed = 2;
        code = BRUTE;
        detectionRange = 150;
        attackRange = 30;
        idleTime = 60;
    }

    @Override
    protected void basicLogic() {
        if (getHealth() <= 0) {
            map.removeContent(x, y, this);
        }
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

    }

    @Override
    protected void drop() {

    }

    @Override
    boolean noPass(Entity entity) {
        return false;
    }

    @Override
    public void update() {
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
        hostileLogic();
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        frame = (int) ((time % (4 * frameTime)) / frameTime) + statusSprite;
        gc.drawImage(getSpriteSheet().getSprite(frame, directionSprite), getX(), getY());
    }
}
