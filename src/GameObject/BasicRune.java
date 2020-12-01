package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

import static java.lang.Math.*;

public class BasicRune extends Rune {
    private static SpriteSheet spriteSheet;
    public BasicRune(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        damage = 0;
        range = 150;
        primeTime = 5000;
        explodeTimer = primeTime;
        passable = true;
        setHitBox(4,14,24,12);
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/basic_rune_sheet.png",1,4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void explode() {
        for(Entity entity : map.getContent(x,y,range)){
            if(getDistance(entity) <= range){
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
        gc.drawImage(spriteSheet.getSprite(max(0,3-(explodeTimer/(primeTime/4))),0), getX(), getY());
        drawHitBox(gc);
    }
}
