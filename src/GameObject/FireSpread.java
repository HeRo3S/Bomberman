package GameObject;

import javafx.scene.canvas.GraphicsContext;
import sun.security.krb5.internal.crypto.Des;

import java.awt.geom.Point2D;

import static GameObject.GameMap.CHUNK_SIZE;
import static java.lang.Math.round;

public class FireSpread extends Projectile {
    private double distance;
    private Fire lastFire;
    public FireSpread(double x, double y, GameMap map, double distance, double dx, double dy) {
        super(x, y, map);
        this.distance = distance;
        speed = 2;
        code = SpriteSheetCode.FIRE;
        this.dx = dx;
        this.dy = dy;
        dyingFrameCount = 0;
        setHitBox(8,8,16,16);
    }

    @Override
    public void update() {
        move();
        distance -= speed;

        //Spread fire
        boolean canPlace = true;
        for(Entity entity : map.getContent(x,y,1)){
            if(entity instanceof Fire && getHitBox().intersects(entity.getHitBox())){
                if(entity == lastFire) {
                    canPlace = false;
                }
            }
        }
        if(canPlace){
            lastFire = new Fire(round(x/CHUNK_SIZE) * CHUNK_SIZE,round(y/CHUNK_SIZE) * CHUNK_SIZE,map,0,0);

        }
        if(distance <= 0 || (x == 0 && y == 0)){
            health = 0;
        }
        basicLogic();
    }

    @Override
    protected boolean noPass(Entity entity){
        if(entity instanceof Wall){
            return true;
        }
        return false;
    }

    @Override
    protected void solveCollision(Entity entity) {
        if(entity instanceof Destructible){
            entity.health -= 5;
        }
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(0,0),x,y);
    }
}