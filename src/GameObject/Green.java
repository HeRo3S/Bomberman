package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.image.ImageView;
import view.GameViewManager;


import java.io.IOException;

public class Green extends Player {

    private SpriteSheet mainSprite;
    private ImageView mainImage = new ImageView();

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
        move();
        animate();
    }

    public ImageView setUp() {
        mainImage.setImage(mainSprite.getSprite(0, 0));
        mainImage.setLayoutX(this.x);
        mainImage.setLayoutY(this.y);
        return mainImage;
    }

    @Override
    protected void solveCollision(Entity entity) {

    }

    @Override
    protected void animate() {
    }
}
