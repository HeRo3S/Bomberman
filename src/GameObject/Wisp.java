package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;

public class Wisp extends Hostile {

    private static SpriteSheet spriteSheet;
    private final double frameTime = 0.100;

    public static SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

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
        setHitBox(10,5,12,18);
        setSpeed(1);
        attackRange = 28;
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
            if(getDistance(target) < attackRange){
                solveCollision(target);
            }
        }
        else {
            idle();
        }
        //Finish and move to designated coordinate
        if(!status.isChannelling() && !status.isStunning()) {
            move();
        }
        status.update();
    }

    @Override
    protected void solveCollision(Entity entity) {
        if(entity instanceof Player && status.canAttack()){
            Player player = (Player) entity;
            player.health -= damage;
            status.add(Status.currentStatus.ATTACK_CD,20);
            System.out.println(entity.health);
        }
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        int frame = (int)((time % (4 * frameTime)) / frameTime);
        gc.drawImage(spriteSheet.getSprite(frame, 0), getX(), getY());
    }

    public Image getSpritteSheet(int x, int y) {
        return spriteSheet.getSprite(x,y);
    }
}
