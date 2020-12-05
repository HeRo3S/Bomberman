package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GameViewManager;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static javafx.scene.paint.Color.BLUE;

public class Green extends Player implements Destructible, Impassable {
    private HashSet<String> input = GameViewManager.getInput();
    private int direction;

    private final String bombPlantedSFX = "src/GameObject/sfx/bomb_planted.mp3";

    public Green(double x, double y, GameMap map) {
        super(x, y, map);
        setHitBox(6, 0, 20, 32);
        setSpeed(3);
        direction = 1;
        code = SpriteSheetCode.GREEN;
        maxHp = 400;
        health = maxHp;
        maxEnergy = 400;
        energy = maxEnergy;
    }



    @Override
    public void update() {
        health = min(health,maxHp);
        energy = min(++energy,maxEnergy);
        inputHandle();
        if(status.isBurning()){
            health -= 0.5;
        }
        basicLogic();
        move();
        status.update();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        frame = (int) ((time % (4 * frameTime)) / frameTime);
        if (isAnimateDying) {
            direction = 4;
            frame = (48 - dyingFrameCount ) / 12;
        }
        if (!input.isEmpty()) {
            frame += 4;
        }
        gc.drawImage(getSpriteSheet().getSprite(frame, direction), getX(), getY());
        drawHitBox(gc);
        drawHealthBar(gc);
        gc.setFill(BLUE);
        gc.fillRect(x,y - 7, energy / (maxEnergy/30), 2);
    }

    private void inputHandle() {
        input = GameViewManager.getInput();
        dx = 0;
        dy = 0;
        if (input.contains("W")) {
            setDy(-1);
            direction = 0;
        }

        if (input.contains("A")) {
            setDx(-1);
            direction = 3;
        }

        if (input.contains("S")) {
            setDy(1);
            direction = 1;
        }

        if (input.contains("D")) {
            setDx(1);
            direction = 2;
        }
        if(input.contains("K")){
            if(energy >= 100) {
                boolean canPlace = true;
                for(Entity entity : map.getContent(x,y,1)){
                    if(entity != this &&!(entity instanceof Floor) && getHitBox().intersects(entity.getHitBox())){
                        canPlace = false;
                    }
                }
                if(canPlace) {
                    new FireRune(x, y, map);
                    energy -= 100;
                    sfx.playWithLoop(bombPlantedSFX);
                }
            }
            input.remove("K");
        }
        if(input.contains("J")){
            if(energy >= 50) {
                boolean canPlace = true;
                for(Entity entity : map.getContent(x,y,1)){
                    if(entity != this &&!(entity instanceof Floor) && getHitBox().intersects(entity.getHitBox())){
                        canPlace = false;
                    }
                }
                if(canPlace) {
                    new BasicRune(x, y, map);
                    energy -= 50;
                    sfx.playWithLoop(bombPlantedSFX);
                }
            }
            input.remove("J");
        }
        if(input.contains("L")){
            new Wall(x - 32, y - 32, map,0, 0);
            input.remove("L");
        }
    }

    public Image getSpriteSheet(int x, int y) {
        return getSpriteSheet().getSprite(x,y);
    }
}
