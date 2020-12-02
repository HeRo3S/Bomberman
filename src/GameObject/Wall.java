package GameObject;

import javafx.scene.canvas.GraphicsContext;

public class Wall extends Tiles{
    private Position position;

    public Wall(double x, double y, double maxHp, GameMap map) {
        super(x, y, map);
        setDestructible(false);
    }

    public Wall(double x, double y, double maxHp, GameMap map, Position pos)
    {
        super(x, y, map);
        setPassable(false);
        setDestructible(false);
        position = pos;
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
}
