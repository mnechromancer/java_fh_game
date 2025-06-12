import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameState gameState;
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    
    public GamePanel(GameState gameState) {
        this.gameState = gameState;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable antialiasing for smooth graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw cyberpunk gradient background
        drawBackground(g2d);
        
        // Render all game objects
        gameState.render(g2d);
    }
    
    private void drawBackground(Graphics2D g2d) {
        // Create cyberpunk gradient from dark blue to black
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(0, 20, 40),
            0, getHeight(), new Color(0, 0, 0)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Add some subtle grid lines for cyberpunk feel
        g2d.setColor(new Color(0, 100, 150, 30));
        g2d.setStroke(new BasicStroke(1));
        
        // Vertical lines
        for (int x = 0; x < getWidth(); x += 50) {
            g2d.drawLine(x, 0, x, getHeight());
        }
        
        // Horizontal lines
        for (int y = 0; y < getHeight(); y += 50) {
            g2d.drawLine(0, y, getWidth(), y);
        }
    }
}
