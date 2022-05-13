package pinball;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PinballMouseMotionListener implements MouseMotionListener {
    private Point p = new Point();

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        p = e.getPoint();
    }

    public Point getP() {
        return p;
    }
}
