package GameObject;

import SpriteManager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;

public class EnergyOrb extends Particle {

    public EnergyOrb(double x, double y, double maxHp, GameMap map) {
        super(x, y, maxHp, map);
        amount = 20;
        try {
            spriteSheet = new SpriteSheet("GameObject/assets/energyOrb.png",1,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void animate(GraphicsContext gc, double time) {

    }

    @Override
    protected void effect(Entity entity) {
        Player player = (Player) entity;
        player.energy += amount;
        health = 0;
    }
}