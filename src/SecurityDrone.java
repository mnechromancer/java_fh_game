import java.awt.*;

public class SecurityDrone extends Enemy {
    private double angle;
    private int oscillationOffset;
    
    public SecurityDrone(int x, int y) {
        super(x, y, 25, 25);
        this.angle = 0;
        this.oscillationOffset = (int)(Math.random() * 100);
    }
    
    @Override
    public void update(Player player) {
        // Move left
        x -= SPEED;
        
        // Add slight vertical oscillation
        angle += 0.1;
        y += Math.sin(angle + oscillationOffset) * 2;
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // Create diamond shape for drone
        int[] xPoints = {x + width/2, x + width, x + width/2, x};
        int[] yPoints = {y, y + height/2, y + height, y + height/2};
        
        // Main body
        g2d.setColor(new Color(150, 50, 50));
        g2d.fillPolygon(xPoints, yPoints, 4);
        
        // Neon outline
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.RED);
        g2d.drawPolygon(xPoints, yPoints, 4);
        
        // Center light
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x + width/2 - 3, y + height/2 - 3, 6, 6);
        
        // Scanning beam effect
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(new Color(255, 0, 0, 100));
        g2d.drawLine(x, y + height/2, x - 50, y + height/2);
    }
}
