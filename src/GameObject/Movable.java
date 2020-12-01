package GameObject;

import javafx.geometry.Point2D;

import java.lang.reflect.Type;

import static GameObject.GameMap.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Movable extends Entity {
    protected double speed;
    protected double dx;
    protected double dy;
    protected Point2D lastPos;
    public Movable(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        lastPos = new Point2D((int) (x / CHUNK_SIZE), (int) (y / CHUNK_SIZE));
    }
    public void move(){
        boolean canMoveX = true;
        boolean canMoveY = true;
        for(Entity entity : map.getContent(x,y,1)) {
            if(entity != this){
                //Calculate
                if (getModifiedHitBox(dx*speed, 0).intersects(entity.getHitBox()) && canMoveX) {
                    solveCollision(entity);
                    if (noPass(entity)) {
                        dx = 0;
                        canMoveX = false;
                    }
                }
                if (getModifiedHitBox(0, dy*speed).intersects(entity.getHitBox()) && canMoveY) {
                    solveCollision(entity);
                    if (noPass(entity)) {
                        dy = 0;
                        canMoveY = false;
                    }
                }
            }
        }
        x += dx * speed;
        y += dy * speed;
        x = max(min(x,MAP_WIDTH - 32),0);
        y = max(min(y,MAP_HEIGHT - 32),0);
        if(!(new Point2D(x/ CHUNK_SIZE, y/ CHUNK_SIZE).equals(lastPos))){
            map.removeContent(lastPos.getX() * CHUNK_SIZE,lastPos.getY() * CHUNK_SIZE, this);
            map.addContent(x ,y ,this);
            lastPos = new Point2D((int) (x / CHUNK_SIZE), (int) (y / CHUNK_SIZE));
        }
    }
    abstract boolean noPass(Entity entity);
    /**
     * Setter/Getter
     */
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
