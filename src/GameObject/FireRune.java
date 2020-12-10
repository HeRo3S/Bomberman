package GameObject;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Point2D;

import static java.lang.Math.max;

public class FireRune extends Rune {
    private String explosionSFX = "src/GameObject/sfx/bomb_explosion.wav";

    public FireRune(double x, double y, GameMap map, Player player) {
        super(x, y, map, player);
        damage = 50;
        range = 80;
        primeTime = 150;
        explodeTimer = primeTime;
        code = SpriteSheetCode.BASIC_RUNE;

    }

    @Override
    protected void explode() {
        new FireSpread(x,y,map,range,1,0);
        new FireSpread(x,y,map,range,-1,0);
        new FireSpread(x,y,map,range,0,1);
        new FireSpread(x,y,map,range,0,-1);
        sfx.playWithoutFlag(explosionSFX);
        player.runes.remove(this);
        health = 0;
    }

    @Override
    public void update() {
        explodeTimer--;
        if(explodeTimer <= 0){
            explode();
        }
        move();
        basicLogic();

    }


    @Override
    public void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(max(0,3-(explodeTimer/(primeTime/4))),0), getX(), getY());
    }
}
