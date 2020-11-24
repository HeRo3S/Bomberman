package GameObject;

import SpriteManager.SpriteSheet;
import javafx.geometry.Point2D;

import static GameObject.GameMap.CHUNK_SIZE;

public abstract class Movable extends Entity {
    protected double speed;
    protected double dx;
    protected double dy;
    protected Point2D lastPos;

    public Movable(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        lastPos = new Point2D(x/ CHUNK_SIZE, y/ GameMap.CHUNK_SIZE);
    }
    public void move(){
        boolean canMoveX = true;
        boolean canMoveY = true;
        for(Entity entity : map.getContent(x,y,1)){
            //Check and solve collision
            if (collide(entity)) {
                solveCollision(entity);
            }
            //Calculate 
            if (getModifiedHitBox(dx, 0).intersects(entity.getHitBox()) && canMoveX){
                if(!entity.isPassable()){
                    dx = 0;
                    canMoveX = false;
                }
            }
            if (getModifiedHitBox(0, dy).intersects(entity.getHitBox()) && canMoveY){
                if(!entity.isPassable()){
                    dy = 0;
                    canMoveY = false;
                }
            }
        }
        x += dx * speed;
        y += dy * speed;
        if(!(new Point2D(x/ CHUNK_SIZE, y/ GameMap.CHUNK_SIZE).equals(lastPos))){
            map.addContent(x ,y ,this);
            map.removeContent(lastPos.getX(), lastPos.getY(), this);
        }
    }
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
