/**
 * @author XiongZhongwei 222000321
 */
package pinball;

import javax.swing.*;

public class PinballMainThread implements Runnable {
    private int curt = -3;
    private JLabel jl;

    PinballMainThread(JLabel jl) {
        this.jl = jl;
    }

    @Override
    public void run() {
        while (true) {
            jl.setText(curt <= 0 ? "倒计时" + -curt : "" + curt);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (PinballThread.isRunning) {
                curt++;
                if (curt > 0) {
                    PinballThread.start = true;
                }
            } else if (curt > 0) {
                jl.setText("游戏结束，成绩：" + curt);
                break;
            }
        }
    }
}
