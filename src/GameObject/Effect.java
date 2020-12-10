package GameObject;

import javafx.scene.canvas.GraphicsContext;

public class Effect extends Entity {
    int spriteRenderPositionX = 0;
    int spriteRenderPositionY = 0;

    public Effect(double x, double y, GameMap map) {
        super(x, y, map);
        dyingFrameCount = 70;
        code = SpriteSheetCode.EFFECT;
    }


    @Override
    public void update() {
        basicLogic();
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        spriteRenderPositionY = (70 - dyingFrameCount) / 10;
        spriteRenderPositionX = (70 - dyingFrameCount) % 10;
        gc.drawImage(getSpriteSheet().getSprite(spriteRenderPositionX, spriteRenderPositionY), x, y,160,160);
    }

    @Override
    public int getLayer() {
        return 2;
    }
}
