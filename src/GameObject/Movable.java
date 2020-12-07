package GameObject;


import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.awt.geom.Point2D;

import static GameObject.GameMap.*;
import static java.lang.Math.*;
import static java.lang.Math.abs;

public abstract class Movable extends Entity {
    protected double speed;
    protected double dx;
    protected double dy;
    protected Point2D lastPos;
    //render components
    protected int frame;
    protected final double frameTime = 0.100;
    public void moveTo(double x, double y){
        double steps = max(abs(x - getCenterX()), abs(y - getCenterY()));
        if (steps == 0)
        {
            dx = dy = 0;
        }
        else
        {
            dx = (x - getCenterX()) / steps;
            dy = (y - getCenterY()) /steps;
        }
    }
    public void moveTo(Point2D point){
        double steps = max(abs(point.getX() - getCenterX()), abs(point.getY() - getCenterY()));
        if (steps == 0)
        {
            dx = dy = 0;
        }
        else
        {
            dx = (point.getX() - getCenterX()) / steps;
            dy = (point.getY() - getCenterY()) /steps;
        }
    }
    public Movable(double x, double y, GameMap map) {
        super(x, y, map);
        lastPos = new Point2D.Double((int) (x / TILE_SIZE), (int) (y / TILE_SIZE));
    }
    public void move(){
        boolean canMoveX = true;
        boolean canMoveY = true;
        for(Entity entity : map.getContent(x,y, TILE_SIZE + 1)) {
            if(entity != this){
                //Calculate
                if (((Path)Shape.intersect(getModifiedHitBox(dx*speed, 0),entity.getHitBox())).getElements().size() > 0 && canMoveX) {
                    solveCollision(entity);
                    if (noPass(entity)) {
                        dx = 0;
                        canMoveX = false;
                    }
                }
                if (((Path)Shape.intersect(getModifiedHitBox(0, dy * speed),entity.getHitBox())).getElements().size() > 0  && canMoveY) {
                    solveCollision(entity);
                    if (noPass(entity)) {
                        dy = 0;
                        canMoveY = false;
                    }
                }
            }
        }
        if(!(new Point2D.Double(x + dx*speed/ TILE_SIZE, y + dy*speed/ TILE_SIZE).equals(lastPos))){
            map.moveContent(x + dx*speed ,y + dy*speed ,this);
            lastPos = new Point2D.Double(x / TILE_SIZE, y / TILE_SIZE);
        }
        x += dx * speed;
        y += dy * speed;
        x = max(min(x,MAP_WIDTH - 32),0);
        y = max(min(y,MAP_HEIGHT - 32),0);
    }
    protected boolean noPass(Entity entity) {
        return entity instanceof Impassable;
    }
    public double getAngle(Point2D target) {
        double angle = (double) Math.toDegrees(Math.atan2( target.getY() - getCenterY(), target.getX() - getCenterX()));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }
    public double getAngle(double x, double y) {
        double angle = (double) Math.toDegrees(Math.atan2( y - getCenterY(), x - getCenterX()));

        if(angle < 0){
            angle += 360;
        }

        return angle;
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

    @Override
    public int getLayer() {
        return 2;
    }
}
