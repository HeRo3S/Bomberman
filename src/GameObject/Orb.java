package GameObject;

import javafx.scene.canvas.GraphicsContext;

public abstract class Orb extends Drop {
    double range;
    double amount;
    public Orb(double x, double y, GameMap map) {
        super(x, y, map);
        range = 50;
        speed = 2;
        setHitBox(2,2,4,4);
    }

    @Override
    protected boolean noPass(Entity entity) {
        return false;
    }

    @Override
    public void update() {
        dropLogic();
        for(Entity entity : map.getContent(x,y,range)){
            if(entity instanceof Player){
                moveTo(entity.getCenterX(),entity.getCenterY());
                if(getDistance(entity) <= 10){
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
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(0,0),x,y);
    }
}
