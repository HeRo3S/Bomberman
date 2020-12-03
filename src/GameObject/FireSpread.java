package GameObject;

import javafx.scene.canvas.GraphicsContext;
import sun.security.krb5.internal.crypto.Des;

import java.awt.geom.Point2D;

public class FireSpread extends Projectile {
    double distance;
    public FireSpread(double x, double y, GameMap map, double distance, double dx, double dy) {
        super(x, y, map);
        this.distance = distance;
        speed = 2;
        code = SpriteSheetCode.FIRE;
        this.dx = dx;
        this.dy = dy;
        dyingFrameCount = 0;
        setHitBox(0,0,32,32);
    }

    @Override
    public void update() {
        move();
        distance -= speed;

        //Spread fire
        boolean canPlace = true;
        for(Entity entity : map.getContent(x,y,1)){
            if(entity instanceof Fire && getHitBox().intersects(entity.getHitBox())){
                canPlace = false;
            }
        }
        if(canPlace){
            new Fire(x,y,map,0,0);
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
            entity.health -= 2;
        }
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(0,0),x,y);
    }
}
