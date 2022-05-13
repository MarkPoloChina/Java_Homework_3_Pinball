/**
 * @author XiongZhongwei 222000321
 */
package pinball;

import javax.swing.*;

public class PinballRepaintThread implements Runnable {
    JPanel jp;

    PinballRepaintThread(JPanel jp) {
        this.jp = jp;
    }

    @Override
    public void run() {
        while (PinballThread.isRunning) {
            jp.update(jp.getGraphics());
            if (PinballThread.start) {
                for (PinballThread t : PinballListener.pinballThreadList) {
                    t.draw();
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
