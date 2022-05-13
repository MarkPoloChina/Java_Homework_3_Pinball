/**
 * @author XiongZhongwei 222000321
 */
package pinball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PinballListener implements ActionListener {
    private final Graphics g;
    private final JPanel jp_ball;
    private final JTextField jt;
    private final PinballMouseMotionListener pinballMouseMotionListener;
    static final ArrayList<PinballThread> pinballThreadList = new ArrayList<>();

    public PinballListener(JPanel jp_ball, PinballMouseMotionListener pinballMouseMotionListener, JTextField jt) {
        this.g = jp_ball.getGraphics();
        this.jp_ball = jp_ball;
        this.pinballMouseMotionListener = pinballMouseMotionListener;
        this.jt = jt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int cnt = Integer.parseInt(jt.getText());
            if (cnt <= 0) {
                throw new IllegalArgumentException("非法参数");
            }
            Random rand = new Random();
            for (int i = 0; i < cnt; i++) {
                int x = (rand.nextInt(jp_ball.getWidth()));
                int y = (rand.nextInt(jp_ball.getHeight()));
                PinballThread pinballThread = new PinballThread(jp_ball, pinballMouseMotionListener, g, x, y);
                new Thread(pinballThread).start();
                pinballThreadList.add(pinballThread);
            }
            PinballRepaintThread pinballMainThread = new PinballRepaintThread(jp_ball);
            new Thread(pinballMainThread).start();
        } catch (Exception exception) {
            System.out.println("非法参数");
            exception.printStackTrace();
        }
    }


}
