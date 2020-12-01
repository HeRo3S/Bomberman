package Editor;

import javafx.scene.input.MouseEvent;
import java.util.EventListener;

public interface MouseListener extends EventListener {
    public void mouseClicked(MouseEvent e);

    public void mouseEntered (MouseEvent e);

    public void mouseExited (MouseEvent e);

    public void mousePressed (MouseEvent e);

    public void mouseReleased (MouseEvent e);
}
