package GameObject;

import javafx.scene.canvas.GraphicsContext;

public interface Animate {
    void animate(GraphicsContext gc, double time);
    int getLayer();
}
