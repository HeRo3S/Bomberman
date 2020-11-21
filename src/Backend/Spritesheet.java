package Backend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spritesheet {
    private String filePath;
    private int rows;
    private int cols;
    private int width;
    private int height;
    private BufferedImage[] sprites;

    public Spritesheet(String filepath, int spriteWidth, int spriteHeight, int spriteRows, int spriteCols) throws IOException {
        filePath = filepath;
        rows = spriteRows;
        cols = spriteCols;
        width = spriteWidth;
        height = spriteHeight;
        sprites = new BufferedImage[rows * cols];
        createSprites();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void createSprites() throws IOException {
        BufferedImage spritesheet = ImageIO.read(new File(filePath));
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                sprites[(i * cols) + j] = spritesheet.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
    }

    public BufferedImage getSprite(int number) {
        return sprites[number];
    }
}

