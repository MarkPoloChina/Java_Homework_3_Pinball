/**
 * @author XiongZhongwei 222000321
 */
package pinball;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PinballThread implements Runnable {
    static Boolean isRunning = false;
    static Boolean start = false;
    private final Graphics g;
    private final JPanel jp_ball;
    private PinballMouseMotionListener pinballMouseMotionListener;
    int x;
    int y;
    Point p;
    private final Random rand = new Random();
    Color color;
    int xv = rand.nextInt(10) + 1;
    int yv = rand.nextInt(10) + 1;

    public PinballThread(JPanel jp_ball, PinballMouseMotionListener pinballMouseMotionListener, Graphics g, int x, int y) {
        this.g = g;
        this.x = x;
        this.y = y;
        p = pinballMouseMotionListener.getP();
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        this.jp_ball = jp_ball;
        this.pinballMouseMotionListener = pinballMouseMotionListener;
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (start) {
                x += xv;
                y += yv;
                if (y >= jp_ball.getHeight() - 20 || y <= 0) {
                    yv = -yv;
                } else if (x >= jp_ball.getWidth() - 20 || x <= 0) {
                    xv = -xv;
                }
                p = pinballMouseMotionListener.getP();
                if (x >= p.x && x <= p.x + 40 && y >= p.y && y <= p.y + 40 || p.y >= jp_ball.getHeight() || p.y <= 0 || p.x >= jp_ball.getWidth() || p.x <= 0) {
                    isRunning = false;
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void draw() {
        g.setColor(color);
        g.fillOval(x, y, 20, 20);
        g.setColor(Color.red);
        g.fillRect(p.x, p.y, 40, 40);
    }
}
