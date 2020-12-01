package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.GameViewManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Green extends Player {

    private SpriteSheet mainSprite;
    private HashSet<String> input = GameViewManager.getInput();
    private int direction;

    public Green(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        createSprite();
        setHitBox(6, 0, 20, 32);
        setSpeed(3);
        direction = 1;
    }

    @Override
    boolean noPass(Entity entity) {
        if(!entity.isPassable()){
            return true;
        }
        return false;
    }

    private void createSprite() {
        try {
            mainSprite = new SpriteSheet("GameObject/assets/mcSheet.png", 5, 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {
        inputHandle();
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
            frame = (24 - dyingFrameCount ) / 6;
        }
        if (!input.isEmpty()) {
            frame += 4;
        }
        gc.drawImage(mainSprite.getSprite(frame, direction), getX(), getY());
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
            new BasicRune(x,y,2,map);
            input.remove("K");
        }
    }

    public Image getSpritteSheet(int x, int y) {
        return mainSprite.getSprite(x,y);
    }
}
