import java.awt.*;

public class PowerUp {
    private int x, y;
    private int width, height;
    private PowerUpType type;
    private double pulseAngle;
    private static final int SPEED = 2;
    
    public enum PowerUpType {
        ROCKET_BOOST, DATA_PACK
    }
    
    public PowerUp(int x, int y, PowerUpType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = 20;
        this.height = 20;
        this.pulseAngle = 0;
    }
      public void update() {
        update(1.0f);
    }
    
    public void update(float speedMultiplier) {
        x -= SPEED * speedMultiplier;
        pulseAngle = (pulseAngle + 0.2) % (2 * Math.PI); // For pulsing effect, reset periodically
    }
    
    public void render(Graphics2D g2d) {
        // Pulsing glow effect
        double pulseSize = 2 + Math.sin(pulseAngle) * 2;
        int glowSize = (int)(width + pulseSize);
        
        if (type == PowerUpType.ROCKET_BOOST) {
            // Rocket boost - yellow/orange
            g2d.setColor(new Color(255, 200, 0, 80));
            g2d.fillOval(x - (int)pulseSize/2, y - (int)pulseSize/2, glowSize, glowSize);
            
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(x, y, width, height);
            
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.ORANGE);
            g2d.drawOval(x, y, width, height);
            
            // Rocket flame symbol
            g2d.setColor(Color.RED);
            int[] flameX = {x + width/2, x + width/2 - 4, x + width/2 + 4};
            int[] flameY = {y + 5, y + height - 5, y + height - 5};
            g2d.fillPolygon(flameX, flameY, 3);
            
        } else if (type == PowerUpType.DATA_PACK) {
            // Data pack - green/cyan
            g2d.setColor(new Color(0, 255, 150, 80));
            g2d.fillOval(x - (int)pulseSize/2, y - (int)pulseSize/2, glowSize, glowSize);
            
            g2d.setColor(Color.GREEN);
            g2d.fillRect(x, y, width, height);
            
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.CYAN);
            g2d.drawRect(x, y, width, height);
            
            // Health cross symbol
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(x + width/2, y + 4, x + width/2, y + height - 4);
            g2d.drawLine(x + 4, y + height/2, x + width - 4, y + height/2);
        }
    }
    
    public void applyEffect(Player player) {
        switch(type) {
            case ROCKET_BOOST:
                player.activateBoost(300); // 5 seconds at 60fps
                break;
            case DATA_PACK:
                player.restoreHealth();
                break;
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public PowerUpType getType() { return type; }
}
