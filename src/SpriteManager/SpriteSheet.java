package SpriteManager;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheet {
    private ArrayList<ArrayList<Image>> sprites = new ArrayList<>();

    public SpriteSheet(String filepath, int spriteRows, int spriteCols){
        Image spriteSheet = new Image(filepath);
        int spriteW = (int) (spriteSheet.getWidth()/spriteCols);
        int spriteH = (int) (spriteSheet.getHeight()/spriteRows);
        for(int i = 0; i < spriteCols; i++){
            sprites.add(new ArrayList<>());
            for (int j = 0; j < spriteRows; j++){
                sprites.get(i).add(
                        new WritableImage(spriteSheet.getPixelReader(), i * spriteW, j * spriteH, spriteW, spriteH));
            }
        }
    }

    public Image getSprite(int x, int y){
        return sprites.get(x).get(y);
    }
}

