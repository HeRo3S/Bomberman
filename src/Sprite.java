import java.io.IOException;

public class Sprite {
    private String filepath = "/resources/Sprite4.png";
    private Spritesheet spritesheet = new Spritesheet(filepath,32,32,12,12);

    public Sprite() throws IOException {
    }
}
