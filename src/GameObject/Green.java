package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GameViewManager;


import java.io.IOException;
import java.util.HashSet;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Green extends Player implements Destructible, Impassable {
    private HashSet<String> input = GameViewManager.getInput();
    private int direction;

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
    }

    private void inputHandle() {
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
            new FireRune(x,y,map);
            input.remove("K");
        }
    }

    public Image getSpriteSheet(int x, int y) {
        return getSpriteSheet().getSprite(x,y);
    }
}
