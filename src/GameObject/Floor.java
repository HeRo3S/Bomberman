package GameObject;

import javafx.scene.canvas.GraphicsContext;


public class Floor extends Tiles implements Passable{
    private int spritePosX;
    private int spritePosY;

    public void setPassable(){
        setPassable(true);
    }

    public Floor(double x, double y, double maxHp, GameMap map) {
        super(x, y, map);
        setDestructible(false);
        setPassable();
        spritePosX = (int) (x / 32 % 4);
        spritePosY = (int) (y / 32 % 4);
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        gc.drawImage(getSpriteSheet().getSprite(spritePosX, spritePosY), x, y);
    }

}
