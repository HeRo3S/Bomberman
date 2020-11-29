package GameObject;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

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
    protected boolean passable = false;
    protected Status status;
    public Entity(double x, double y, double maxHp, GameMap map) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.maxHp = maxHp;
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
        return new Rectangle2D(x+offsetX,y+offsetY,width,height);
    }

    protected Rectangle2D getModifiedHitBox(double dx, double dy){
        return new Rectangle2D(x + offsetX + dx, y+ offsetY + dy, width, height);
    }
    protected boolean collide(Entity entity){
        return getHitBox().intersects(entity.getHitBox());
    }

    public abstract void update();
    protected abstract void solveCollision(Entity entity);
    protected double getDistance(Entity entity){
        return (new Point2D(x,y).distance(entity.x, entity.y));
    }
    protected abstract void animate(GraphicsContext gc, double time);
    protected void basicLogic(){
        if(health <= 0){
            map.removeContent(x,y,this);
        }
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
}