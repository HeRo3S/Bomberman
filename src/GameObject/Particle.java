package GameObject;

import javafx.scene.canvas.GraphicsContext;

public abstract class Particle extends Drop {
    double range;
    double amount;
    public Particle(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        passable = true;
        range = 50;
        speed = 1;
        setHitBox(2,2,4,4);
    }

    @Override
    boolean noPass(Entity entity) {
        return false;
    }

    @Override
    public void update() {
        dx = dy = 0;
        for(Entity entity : map.getContent(x,y,range)){
            if(entity instanceof Player){
                moveTo(entity.getCenterX(),entity.getCenterY());
                if(getDistance(entity) <= 2){
                    effect(entity);
                }
            }
        }
        move();
        if(health <= 0){
            map.removeContent(x,y,this);
        }
    }

    @Override
    protected void solveCollision(Entity entity) {
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(spriteSheet.getSprite(0,0),x,y);
    }
}
