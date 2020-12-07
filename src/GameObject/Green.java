package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import view.GameViewManager;
import java.util.HashSet;

import static java.lang.Math.*;
import static javafx.scene.paint.Color.BLUE;

public class Green extends Player implements Destructible, Impassable {
    private HashSet<String> input = GameViewManager.getInput();
    private int direction;

    private final String bombPlantedSFX = "src/GameObject/sfx/bomb_planted.mp3";

    public Green(double x, double y, GameMap map) {
        super(x, y, map);
        setHitBox(6, 1, 20, 30);
        speed = 3;
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
            modifyHealth(-10);
        }
        basicLogic();
        move();
        status.update();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    public void animate(GraphicsContext gc, double time) {
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
                for(Entity entity : map.getContent(x,y,GameMap.CHUNK_SIZE + 1)){
                    if(!(entity instanceof Floor) && entity != this &&(((Path) Shape.intersect(getHitBox(),entity.getHitBox())).getElements().size() > 0)){
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
                for(Entity entity : map.getContent(x,y,GameMap.CHUNK_SIZE + 1)){
                    if(!(entity instanceof Floor) && entity != this &&(((Path) Shape.intersect(getHitBox(),entity.getHitBox())).getElements().size() > 0)){
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
            new Wall(x+ 32,y + 32, map,0, 0);
            input.remove("L");
        }
    }

    public Image getSpriteSheet(int x, int y) {
        return getSpriteSheet().getSprite(x,y);
    }
}
