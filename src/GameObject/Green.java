package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import view.GameViewManager;


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
        ArrayList<String> input = GameViewManager.getInput();
        if (input.contains("W")) {
            setDy(-10);
        }
        if(input.contains("A")){
            setDx(-10);
        }
        if (input.contains("S")) {
                setDy(10);
            }
        if(input.contains("D")){
            setDx(10);
        }
            move();
        }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate(GraphicsContext gc) {
        gc.drawImage(mainSprite.getSprite(0, 1), getX(), getY());
    }
}
