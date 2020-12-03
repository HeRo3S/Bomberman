package GameObject;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

import static java.lang.Math.max;

public class FireRune extends Rune {
    public FireRune(double x, double y, GameMap map) {
        super(x, y, map);
        damage = 50;
        range = 80;
        primeTime = 150;
        explodeTimer = primeTime;
        setHitBox(4,14,24,12);
        code = SpriteSheetCode.BASIC_RUNE;
    }

    @Override
    protected void explode() {
        new FireSpread(x,y,map,range,1,0);
        new FireSpread(x,y,map,range,-1,0);
        new FireSpread(x,y,map,range,0,1);
        new FireSpread(x,y,map,range,0,-1);
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
        gc.drawImage(getSpriteSheet().getSprite(max(0,3-(explodeTimer/(primeTime/4))),0), getX(), getY());
    }
}
