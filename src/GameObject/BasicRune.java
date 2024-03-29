package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;


import static java.lang.Math.*;
import static javafx.scene.paint.Color.*;

public class BasicRune extends Rune {
    private String explosionSFX = "src/GameObject/sfx/bomb_explosion.wav";
    public BasicRune(double x, double y, GameMap map, Player player) {
        super(x, y, map, player);
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
                entity.modifyHealth(-damage);
                entity.invincibleFrame = 0;
            }
        }
        new Effect(getCenterX() - range, getCenterY() - range, map);
        sfx.playWithoutFlag(explosionSFX);
        player.runes.remove(this);
        health = 0;
    }

    @Override
    public void update() {
        if(dx == 0 && dy == 0){
            explodeTimer-= 0.5;
        }
        if(explodeTimer <= 0){
            explode();
        }
        move();
        basicLogic();

    }


    @Override
    public void animate(GraphicsContext gc, double time) {
        if(dx == 0 && dy == 0) {
        gc.setFill(ORANGE);
        gc.fillOval(x+16-(primeTime - explodeTimer)* range/primeTime,
                y+16-(primeTime - explodeTimer)* range/primeTime,
                (primeTime - explodeTimer)* range*2/primeTime,
                (primeTime - explodeTimer)* range*2/primeTime);
            gc.setStroke(RED);
            gc.strokeOval(x + 16 - range, y + 16 - range, range * 2, range * 2);
            gc.setStroke(BLACK);
        }
        gc.drawImage(getSpriteSheet().getSprite(max(0, 3 - (explodeTimer / (primeTime / 4))), 0), getX(), getY());
    }


}
