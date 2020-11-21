package Backend;

import java.io.IOException;

public class Sprite {
    public static final int DEFAULT_SIZE = 32;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    private String filepath = "/resources/Sprite4.png";
    private Spritesheet spritesheet = new Spritesheet(filepath, 12, 12);

    public Sprite() throws IOException {
    }
}
