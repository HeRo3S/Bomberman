package GameObject;

import SpriteManager.SpriteSheet;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.List;

public abstract class Entity {
    protected GameMap map;
    protected double x;
    protected double y;
    protected double offsetX = 0;
    protected double offsetY = 0;
    protected double maxHp;
    protected double health;
    protected double width = 32;
    protected double height = 32;
    protected SpriteSheet spriteSheet;
    protected boolean passable;
    protected Image display;
    protected int AnimationTimer;
    protected Status status;
    public Entity(double x, double y, double maxHp, SpriteSheet spriteSheet, GameMap map) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.maxHp = maxHp;
        this.spriteSheet = spriteSheet;
        health = maxHp;
        map.addContent((int) x/GameMap.CHUNK_SIZE, (int) y / GameMap.CHUNK_SIZE, this);
    }

    /**
     * Create a hit box for the entity
     * @param offsetX distance to sprite x 0
     * @param offsetY distance to sprite y 0
     */
    public void setHitBox(double offsetX, double offsetY, double width, double height){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }
    protected Rectangle2D getHitBox(){
        return new Rectangle2D(x+offsetX,y+offsetY,width,height);
    }

    protected Rectangle2D getModifiedHitBox(double dx, double dy){
        return new Rectangle2D(x + offsetX + dx, y+ offsetY + dy, width, height);
    }
    protected boolean collide(Entity entity){
        return getHitBox().intersects(entity.getHitBox());
    }

    public abstract void update(List<Entity> entities);
    protected abstract void solveCollision(Entity entity);
    protected double getDistance(Entity entity){
        return (new Point2D(x,y).distance(entity.x, entity.y));
    }
    protected abstract void animate();

    /**
     * Setter/Getter
     */

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}