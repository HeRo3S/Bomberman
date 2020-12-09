package GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import view.GameViewManager;

import java.util.HashSet;

import static javafx.scene.paint.Color.*;

public class Green extends Player implements Destructible, Impassable {
    private HashSet<String> input = GameViewManager.getInput();
    private int direction;
    private int skillDuration;
    private final String bombPlantedSFX = "src/GameObject/sfx/bomb_planted.mp3";
    protected int[] coolDown = {0,0};

    public Green(double x, double y, GameMap map) {
        super(x, y, map);
        setHitBox(6, 1, 20, 30);
        speed = 3;
        direction = 1;
        code = SpriteSheetCode.GREEN;
        maxHp = 200;
        health = maxHp;
        maxEnergy = 150;
        energy = maxEnergy;
    }



    @Override
    public void update() {
        coolDown[0]--;
        coolDown[1]--;
        inputHandle();
        if(!isAnimateDying) {
            regen();
        } else {
            input.clear();
            if (dyingFrameCount == 0) {
                map.setMainWasDead(true);
            }
        }
        if(status.isBurning()){
            modifyHealth(-10);
        }
        if(health <= 0){
            runes.clear();
        }
        basicLogic();
        move();
        status.update();
    }

    @Override
    protected void solveCollision(Entity entity) {
        if (entity instanceof Portal) {
            map.setWalkedThrough(true);
        }
    }

    @Override
    public void animate(GraphicsContext gc, double time) {
        if(skillDuration >= 0){
            skillDuration--;
            if(maxRune < 3){
                maxRune = 5;
            }
            energyRegen = 40;
            energyModifier = 0.5;
        }
        else {
            maxRune = 2;
            energyRegen = 20;
            energyModifier = 1;
        }
        frame = (int) ((time % (4 * frameTime)) / frameTime);
        if (isAnimateDying) {
            direction = 4;
            frame = (48 - dyingFrameCount ) / 12;
        } else {
            if (!input.isEmpty()) {
                frame += 4;
            }
        }
        gc.drawImage(getSpriteSheet().getSprite(frame, direction), getX(), getY());
        drawHitBox(gc);
        drawHealthBar(gc);
        gc.setFill(BLUE);
        gc.fillRect(x,y - 7, energy / (maxEnergy/30), 2);

        //Skill indicator
        gc.setFill(SILVER);
        gc.fillRect(1024 - 40, 576 - 60 + ((1200 - (double) coolDown[1])/1200)*60,40,60);
        gc.setFill(GRAY);
        gc.fillRect(1024 - 80, 576 - 60 + ((300 - (double) coolDown[0])/300)*60,40,60);
    }

    private void inputHandle() {
        input = GameViewManager.getInput();
        dx = 0;
        dy = 0;
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
        if(input.contains("K")){
            if(energy >= 100 && runes.size() < maxRune) {
                boolean canPlace = true;
                for(Entity entity : map.getContent(x,y,GameMap.CHUNK_SIZE + 1)){
                    if(!(entity instanceof Floor) && entity != this &&(((Path) Shape.intersect(getHitBox(),entity.getHitBox())).getElements().size() > 0)){
                        canPlace = false;
                    }
                }
                if(canPlace) {
                    new FireRune(x, y, map, this);
                    modifyEnergy(-100);
                    sfx.playWithoutFlag(bombPlantedSFX);
                }
            }
            input.remove("K");
        }
        if(input.contains("J")){
            if(energy >= 50 && runes.size() < maxRune) {
                boolean canPlace = true;
                for(Entity entity : map.getContent(x,y,GameMap.CHUNK_SIZE + 1)){
                    if(!(entity instanceof Floor) && entity != this &&(((Path) Shape.intersect(getHitBox(),entity.getHitBox())).getElements().size() > 0)){
                        canPlace = false;
                    }
                }
                if(canPlace) {
                    new BasicRune(x, y, map, this);
                    modifyEnergy(-50);
                    sfx.playWithoutFlag(bombPlantedSFX);
                }
            }
            input.remove("J");
        }
        if(input.contains("Q")){
            input.remove("Q");
            if(coolDown[0] <= 0 && energy >= 50 && runes.size() > 0){
                double targetX = getCenterX();
                double targetY = getCenterY();
                for(Rune rune : runes){
                    switch (direction){
                        case 0:
                            targetY -= 150;
                            break;
                        case 1:
                            targetY += 150;
                            break;
                        case 2:
                            targetX += 150;
                            break;
                        case 3:
                            targetX -= 150;
                            break;
                    }
                    rune.moveTo(targetX,targetY);
                }
                modifyEnergy(-50);
                coolDown[0] = 5*60;
            }
        }
        if(input.contains("E")){
            input.remove("E");
            if(coolDown[1] <= 0){
                skillDuration = 600;
                coolDown[1] = 20*60;
            }
        }
    }

    public Image getSpriteSheet(int x, int y) {
        return getSpriteSheet().getSprite(x,y);
    }
}
