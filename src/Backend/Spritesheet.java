package Backend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spritesheet {
    private String filePath;
    private int rows;
    private int cols;
    final static private int width = 32;
    final static private int height = 32;
    private BufferedImage[][] sprites;

    public Spritesheet(String filepath, int spriteRows, int spriteCols) throws IOException {
        filePath = filepath;
        rows = spriteRows;
        cols = spriteCols;
        sprites = new BufferedImage[rows][cols];
        BufferedImage spritesheet = ImageIO.read(new File(filePath));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sprites[i][j] = spritesheet.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                );
            }
        }
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

    public int getHeight() {
        return height;
    }

//    public void createSprites() throws IOException {
//        BufferedImage spritesheet = ImageIO.read(new File(filePath));
//        for (int i = 0; i < rows; i++)
//        {
//            for (int j = 0; j < cols; j++)
//            {
//                sprites[i][j] = spritesheet.getSubimage(
//                        j * width,
//                        i * height,
//                        width,
//                        height
//                );
//            }
//        }
//    }

    public BufferedImage getSprite(int number1, int number2) {
        return sprites[number1][number2];
    }
}

