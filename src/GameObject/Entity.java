package GameObject;
import SpriteManager.SpriteSheet;
import SpriteManager.SpriteSheetManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import view.GameViewManager;

import java.awt.geom.Point2D;
import java.io.Serializable;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static javafx.scene.paint.Color.GREEN;
public abstract class Entity implements Serializable, Animate {
    protected int dyingFrameCount = 48;
    protected boolean isAnimateDying = false;
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
    protected Status status = new Status();
    protected int invincibleFrame;
    protected static SoundEffect sfx;

    public Entity(double x, double y, GameMap map) {
        this.map = map;
        this.x = x;
        this.y = y;
        health = maxHp;
        map.addContent(x, y, this);
        sfx = new SoundEffect();
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
    protected Rectangle getHitBox(){
        return new Rectangle(x+offsetX,y+offsetY,width,height);
    }

    protected Rectangle getModifiedHitBox(double dx, double dy){
        return new Rectangle(x + offsetX + dx, y+ offsetY + dy, width, height);
    }

    public abstract void update();
    protected abstract void solveCollision(Entity entity);
    protected double getDistance(Entity entity){
        return (new Point2D.Double(getCenterX(),getCenterY()).distance(entity.getCenterX(), entity.getCenterY()));
    }
    protected void basicLogic() {
        invincibleFrame = max(--invincibleFrame,0);
        if (getHealth() <= 0) {
            if (--dyingFrameCount < 0) {
                map.removeContent(x, y, this);
            } else {
                isAnimateDying = true;
            }
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
        gc.strokeRect(getHitBox().getX(),
                getHitBox().getY(),
                getHitBox().getWidth(),
                getHitBox().getHeight()
        );
    }
    protected void drawHealthBar(GraphicsContext gc){
        gc.setFill(GREEN);
        gc.fillRect(x, y - 10, health/(maxHp/30), 2);
    }
    public void modifyHealth(double amount){
        if(amount < 0 && invincibleFrame > 0){
            return;
        }
        if(invincibleFrame <= 0){
            invincibleFrame = 20;
        }
        health = min(maxHp,health + amount);
    }
    /**
     * Setter/Getter
     */
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