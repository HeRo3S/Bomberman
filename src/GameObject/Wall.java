package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

public class Wall extends Tiles{
    private Position position;
    private static SpriteSheet spriteSheet;

    public Wall(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        setDestructible(false);
        createSpriteSheet();
    }

    public Wall(double x, double y, double maxHp, GameMap map, Position pos)
    {
        super(x, y, maxHp, map);
        setPassable(false);
        setDestructible(false);
        position = pos;
        createSpriteSheet();
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {
        switch (position)
        {
            case HORIZONTAL:
                //gc.drawImage(spriteSheet.getSprite(?,?), x, y);
            case VERTICAL:
                //gc.drawImage(spriteSheet.getSprite(?,?), x, y);
            case LEFT_UP_CORNER:
                //gc.drawImage(spriteSheet.getSprite(?,?), x, y);
            case RIGHT_UP_CORNER:
                //gc.drawImage(spriteSheet.getSprite(?,?), x, y);
            case LEFT_DOWN_CORNER:
                //gc.drawImage(spriteSheet.getSprite(?,?), x, y);
            case RIGHT_DOWN_CORNER:
                //gc.drawImage(spriteSheet.getSprite(?,?), x, y);
            default:
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private void createSpriteSheet() {
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/wall.png", 13, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
