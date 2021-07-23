package pacman.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class rotatableJLabel extends JLabel {

    private double theta;

    public rotatableJLabel(ImageIcon image) {
        super(image);
        theta = 0.0;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.rotate(theta, getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
    }

    public void setTheta(double t) {
        theta = t;
    }
}
