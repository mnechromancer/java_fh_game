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
      // Background image cache for optimization
    private Image backgroundCache;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Enable rendering optimizations
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
        // Draw cyberpunk gradient background (cached for performance)
        if (backgroundCache == null) {
            createBackgroundCache();
        }
        g2d.drawImage(backgroundCache, 0, 0, null);
        
        // Render all game objects
        gameState.render(g2d);
    }
      private void createBackgroundCache() {
        backgroundCache = createImage(PANEL_WIDTH, PANEL_HEIGHT);
        Graphics2D g2d = (Graphics2D) backgroundCache.getGraphics();
        
        // Enable antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
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
        
        // Vertical lines with some randomized gap patterns for more cyberpunk feel
        for (int x = 0; x < getWidth(); x += 50) {
            if (Math.random() > 0.1) { // 10% chance to skip a line for visual variation
                g2d.drawLine(x, 0, x, getHeight());
            }
        }
        
        // Horizontal lines with variation
        for (int y = 0; y < getHeight(); y += 50) {
            if (Math.random() > 0.1) { // 10% chance to skip a line
                g2d.drawLine(0, y, getWidth(), y);
            }
        }
        
        // Add some small digital noise dots
        g2d.setColor(new Color(0, 200, 255, 40));
        for (int i = 0; i < 100; i++) {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int size = 1 + (int)(Math.random() * 3);
            g2d.fillRect(x, y, size, size);
        }
        
        g2d.dispose(); // Clean up graphics resource
    }
}
