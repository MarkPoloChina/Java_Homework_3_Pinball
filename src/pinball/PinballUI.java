/**
 * @author XiongZhongwei 222000321
 */
package pinball;

import javax.swing.*;
import java.awt.*;

public class PinballUI {
    public static void main(String[] arg) {
        PinballUI bf = new PinballUI();
        bf.showUI();
    }

    private void showUI() {

        JFrame jf = new JFrame("弹球PoweredByMP");
        jf.setSize(500, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        JPanel jp_control = new JPanel();
        JPanel jp_ball = new JPanel();
        jp_control.setBackground(Color.decode("#45a9fa"));
        jp_control.setPreferredSize(new Dimension(500, 100));
        jp_ball.setBackground(Color.white);
        jf.add(jp_control, BorderLayout.NORTH);
        jf.add(jp_ball, BorderLayout.CENTER);

        JTextField jt = new JTextField(10);

        JButton jb = new JButton("开始");
        jb.setBackground(Color.white);
        jb.setBorderPainted(false);
        jb.setFocusPainted(false);
        jb.setSize(60, 20);
        jb.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        JLabel jl = new JLabel("0");
        jl.setFont(new Font("微软雅黑", Font.BOLD, 38));
        jl.setPreferredSize(new Dimension(400, 50));
        jl.setHorizontalAlignment(SwingConstants.CENTER);

        jp_control.add(jl);
        jp_control.add(jt);
        jp_control.add(jb);

        jf.setVisible(true);

        PinballMouseMotionListener pinballMouseMotionListener = new PinballMouseMotionListener();
        jp_ball.addMouseMotionListener(pinballMouseMotionListener);
        jb.addActionListener(new PinballListener(jp_ball, pinballMouseMotionListener, jt));

        new Thread(new PinballMainThread(jl)).start();
    }
}
