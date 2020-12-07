package GameObject;

import static GameObject.GameMap.CHUNK_SIZE;
import static java.lang.Math.round;

public abstract class Rune extends Movable {
    protected double damage;
    protected int explodeTimer;
    protected int primeTime;
    protected double range;
    public Rune(double x, double y, GameMap map) {
        super(x, y, map);
        maxHp = 2;
        health = maxHp;
        dyingFrameCount = 0;
        map.removeContent(x,y,this);
        this.x = round(x/CHUNK_SIZE) * CHUNK_SIZE;
        this.y = round(y/CHUNK_SIZE) * CHUNK_SIZE;
        map.addContent(this.x,this.y,this);
        setHitBox(1,1,30,30);
    }
    protected abstract void explode();

    @Override
    public int getLayer() {
        return 1;
    }
}
