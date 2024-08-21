package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class RoundLoadingPanel extends JPanel {
    private final int diameter = 50;
    private final int arcWidth = 10;
    private final int arcHeight = 10;
    private int angle = 0;

    public RoundLoadingPanel() {
        setPreferredSize(new Dimension(diameter, diameter));
        setOpaque(false);
        Timer timer = new Timer(50, e -> {
            angle += 10;
            if (angle >= 360)
                angle = 0;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = 0;
        int y = 0;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, diameter, diameter);
        g2d.setColor(Color.WHITE);
        g2d.fillArc(x, y, diameter, diameter, angle, 180);
        g2d.setColor(Color.decode("#FAAA70"));
        g2d.fillArc(x, y, diameter, diameter, angle + 180, 180);
    }
}
