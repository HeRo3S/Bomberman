package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;


import static java.lang.Math.*;

public class BasicRune extends Rune {
    public BasicRune(double x, double y, GameMap map) {
        super(x, y, map);
        damage = 100;
        range = 80;
        primeTime = 150;
        explodeTimer = primeTime;
        code = SpriteSheetCode.BASIC_RUNE;
    }

    @Override
    protected void explode() {
        for(Entity entity : map.getContent(x,y,range)){
            if(getDistance(entity) <= range && (entity instanceof Destructible)){
                entity.health -= damage;
            }
        }
        health = 0;
    }

    @Override
    public void update() {
        if(explodeTimer-- <= 0){
            explode();
        }
        basicLogic();

    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.strokeOval(x+16-range,y+16-range,range*2,range*2);
        gc.drawImage(getSpriteSheet().getSprite(max(0,3-(explodeTimer/(primeTime/4))),0), getX(), getY());
    }
}
