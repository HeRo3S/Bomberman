package GameObject;

import static GameObject.GameMap.TILE_SIZE;
import static java.lang.Math.round;

public abstract class Rune extends Movable {
    protected double damage;
    protected int explodeTimer;
    protected int primeTime;
    protected double range;
    protected Player player;
    public Rune(double x, double y, GameMap map, Player player) {
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
        dyingFrameCount = 0;
        map.removeContent(x,y,this);
        this.x = round(x/TILE_SIZE) * TILE_SIZE;
        this.y = round(y/TILE_SIZE) * TILE_SIZE;
        map.addContent(this.x,this.y,this);
        setHitBox(4,14,24,12);
        this.player = player;
        this.player.runes.add(this);
        speed = 5;
    }

    @Override
    protected void solveCollision(Entity entity) {
        if(dx != 0 || dy != 0){
            if(!(entity instanceof Player) && entity instanceof Impassable || entity instanceof Hostile){
                explode();
            }
        }
    }
    protected abstract void explode();

    @Override
    public int getLayer() {
        return 1;
    }
    protected boolean noPass(Entity entity){
        if(entity instanceof Player){
            return false;
        }
        return entity instanceof Impassable;
    }
}
