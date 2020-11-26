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
        setSpeed(1);
        createSprite();
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
        if (input.isEmpty())
        {
            setDx(0);
            setDy(0);
            setSpeed(0);
        }
        else {
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
            setSpeed(5);
            move();
        }
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        int frame = (int)((time % (4 * frameTime)) / frameTime);
        gc.drawImage(mainSprite.getSprite(frame, direction), getX(), getY());
    }
}
