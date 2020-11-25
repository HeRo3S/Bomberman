package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;


import java.io.IOException;
import java.util.ArrayList;

public class Green extends Player {

    private SpriteSheet mainSprite;

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
        /**if (input.isEmpty())
        {
            return;
        } else {
            if (input.contains("S")) {
                setDy(10);
            } else {
                setDy(0);
            }
            move();
        }**/
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc) {
        gc.drawImage(mainSprite.getSprite(0, 1), getX(), getY());
    }
}
