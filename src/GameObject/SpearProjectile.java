package GameObject;

import Utility.Utility;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SpearProjectile extends Projectile implements Rotatable {
    private double damage;

    public SpearProjectile(double x, double y, GameMap map, Point2D target) {
        super(x, y, map, target);
        code = SpriteSheetCode.SPEAR;
        damage = 50;
        setHitBox(6,6,6,6);
        moveTo(target);
        speed = 4;
        dyingFrameCount = 0;
    }


    @Override
    public void update() {
        move();
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {
        if (entity instanceof Player){
            entity.modifyHealth(-damage);
            health = 0;
        }
        if (entity instanceof Wall){
            health = 0;
        }

    }
    @Override
    public void animate(GraphicsContext gc, double time) {
        Utility.drawRotated(gc,getSpriteSheet().getSprite(0,0),x,y,getAngle(getCenterX() + dx, getCenterY() + dy));
    }
    @Override
    protected boolean noPass(Entity entity){
        if(entity instanceof Wall){
            return true;
        }
        return false;
    }
}
