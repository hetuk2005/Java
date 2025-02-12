import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class TurtleGraphics extends JPanel {
    private static final Color[] colors = {Color.RED, Color.YELLOW, new Color(75, 0, 130), new Color(186, 85, 211), Color.BLUE};
    private int step = 0; // To keep track of the current step in the drawing
    private final int maxSteps = 170; // Total number of steps to draw

    public TurtleGraphics() {
        setBackground(Color.BLACK);
        Timer timer = new Timer(50, e -> {
            step++;
            if (step <= maxSteps) {
                repaint(); // Request a repaint for the next step
            } else {
                ((Timer) e.getSource()).stop(); // Stop the timer when done
            }
        });
        timer.start(); // Start the timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));

        int width = getWidth();
        int height = getHeight();
        double angle = 150; // Angle to turn
        double x = width / 2; // Starting x position
        double y = height / 2; // Starting y position
        double currentAngle = 0; // Current angle in degrees

        // Draw only up to the current step
        for (int i = 0; i < step; i++) {
            g2d.setColor(colors[i % colors.length]);
            double nextX = x + (i * 4) * Math.cos(Math.toRadians(currentAngle));
            double nextY = y + (i * 4) * Math.sin(Math.toRadians(currentAngle));
            g2d.draw(new Line2D.Double(x, y, nextX, nextY));
            x = nextX;
            y = nextY;
            currentAngle += angle; // Turn left by the specified angle
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Turtle Graphics");
        TurtleGraphics turtleGraphics = new TurtleGraphics();
        frame.add(turtleGraphics);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}