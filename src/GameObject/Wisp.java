package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

public class Wisp extends Hostile {

    private static SpriteSheet spriteSheet;
    private final double frameTime = 0.100;

    private void createSprite() {
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/wisp.png", 1, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Wisp(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        detectionRange = 120;
        damage = 20;
        attackSpeed = 1;
        status = new Status();
        createSprite();
    }

    @Override
    public void idle() {

    }

    @Override
    public void update() {
        //Reset
        dx = 0;
        dy = 0;
        target = null;
        if(!status.isStunning() && !status.isChannelling()) {
            for (Entity entity : map.getContent(x, y, detectionRange)) {
                //Find target
                if (entity instanceof Player) {
                    target = entity;
                }
            }
        }
        //Choose moving course
        if(target != null){
            moveToTarget();
        }
        else {
            idle();
        }
        //Finish and move to designated coordinate
        if(!status.isChannelling() && !status.isStunning())
        move();
        status.update();
    }

    @Override
    protected void solveCollision(Entity entity) {
        if(entity instanceof Player){
            Player player = (Player) entity;
            player.health -= damage;
            status.add("channel",1 / attackSpeed);
        }
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        int frame = (int)((time % (4 * frameTime)) / frameTime);
        gc.drawImage(spriteSheet.getSprite(frame, 0), getX(), getY());
    }
}
