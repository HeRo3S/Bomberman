package GameObject;

import javafx.scene.canvas.GraphicsContext;

import static GameObject.SpriteSheetCode.THROWER;
import static java.lang.Math.random;

public class Thrower extends Hostile implements Destructible, Impassable {
    public Thrower(double x, double y, GameMap map) {
        super(x, y, map);
        attackRange = 30;
        attackSpeed = 1;
        damage = 30;
        maxHp = 200;
        health = maxHp;
        speed = 3;
        code = THROWER;
        detectionRange = 200;
        attackRange = 120;
        idleTime = 60;
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

    }

    @Override
    protected void drop() {

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
