package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import view.GameViewManager;


import java.io.IOException;
import java.util.ArrayList;

public class Green extends Player {

    private SpriteSheet mainSprite;
    private ArrayList<String> input = GameViewManager.getInput();
    private final double frameTime = 0.100;
    private int direction;

    public Green(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        createSprite();
        setHitBox(2, 0, 20, 32);
        setSpeed(5);
        direction = 1;
    }

    private void createSprite() {
        try {
            mainSprite = new SpriteSheet("GameObject/assets/mcSpriteSheet.png", 4, 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {
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
        move();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        int frame = (int) ((time % (4 * frameTime)) / frameTime);
        if (!input.isEmpty()) {
            frame += 4;
        }
        gc.drawImage(mainSprite.getSprite(frame, direction), getX(), getY());
    }
}
