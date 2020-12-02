package GameObject;
import SpriteManager.SpriteSheet;
import SpriteManager.SpriteSheetManager;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected SpriteSheetCode code;
    protected GameMap map;
    protected double x;
    protected double y;
    protected double offsetX = 0;
    protected double offsetY = 0;
    protected double maxHp;
    protected double health;
    protected double width = 32;
    protected double height = 32;
    protected boolean passable = false;
    protected Status status;
    public Entity(double x, double y, GameMap map) {
        this.map = map;
        this.x = x;
        this.y = y;
        health = maxHp;
        map.addContent(x, y, this);
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
        return new Rectangle2D.Double(x+offsetX,y+offsetY,width,height);
    }

    protected Rectangle2D getModifiedHitBox(double dx, double dy){
        return new Rectangle2D.Double(x + offsetX + dx, y+ offsetY + dy, width, height);
    }

    public abstract void update();
    protected abstract void solveCollision(Entity entity);
    protected double getDistance(Entity entity){
        return (new Point2D.Double(getCenterX(),getCenterY()).distance(entity.getCenterX(), entity.getCenterY()));
    }
    protected abstract void animate(GraphicsContext gc, double time);
    protected void basicLogic(){
        if (health <= 0) {
            map.removeContent(x, y, this);
        }
    }

    protected double getCenterX(){
        return (x + offsetX + width/2);
    }
    protected double getCenterY(){
        return (y + offsetY + height/2);
    }
    protected Point2D getCenter(){
        return new Point2D.Double(x + offsetX + width/2,y + offsetY + height/2);
    }
    protected void drawHitBox(GraphicsContext gc){
        gc.strokeRect(x+offsetX,y+offsetY,width,height);
    }
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

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public SpriteSheet getSpriteSheet() {
        return SpriteSheetManager.getSheet(code);
    }
}