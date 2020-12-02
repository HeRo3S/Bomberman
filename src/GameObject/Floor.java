package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

public class Floor extends Tiles implements Passable{
    private int spritePosX;
    private int spritePosY;

    public void setPassable(){
        setPassable(true);
    }

    public Floor(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        setDestructible(false);
        setPassable();
        createSpriteSheet();
        spritePosX = (int) (x / 32 % 4);
        spritePosY = (int) (y / 32 % 4);
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(spriteSheet.getSprite(spritePosX, spritePosY), x, y);
    }


    private void createSpriteSheet() {
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/floor.png", 2, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
