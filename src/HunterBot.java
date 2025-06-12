import java.awt.*;

public class HunterBot extends Enemy {
    private int targetY;
    private static final float TRACKING_SPEED = 0.7f; // Reduced tracking speed
    
    public HunterBot(int x, int y) {
        super(x, y, 35, 20);
        this.targetY = y;
    }
      @Override
    public void update(Player player) {
        // Default speed
        update(player, 1.0f);
    }
    
    @Override
    public void update(Player player, float speedMultiplier) {
        // Move left
        x -= SPEED * speedMultiplier;
          // Slowly track player's Y position
        targetY = player.getY();
        if (y < targetY) {
            y += TRACKING_SPEED * speedMultiplier;
        } else if (y > targetY) {
            y -= TRACKING_SPEED * speedMultiplier;
        }
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // Main body - rectangular hunter bot
        g2d.setColor(new Color(80, 80, 120));
        g2d.fillRect(x, y, width, height);
        
        // Neon outline
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.ORANGE);
        g2d.drawRect(x, y, width, height);
        
        // Weapon barrel
        g2d.setColor(Color.GRAY);
        g2d.fillRect(x - 10, y + height/2 - 2, 15, 4);
        
        // Eye/sensor
        g2d.setColor(Color.RED);
        g2d.fillOval(x + width - 8, y + 4, 6, 6);
        
        // Targeting line to player (visual feedback)
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(new Color(255, 100, 0, 80));
        g2d.drawLine(x, y + height/2, x - 100, targetY);
        
        // Thruster effects
        g2d.setColor(new Color(0, 150, 255, 150));
        for (int i = 0; i < 3; i++) {
            g2d.drawLine(x + width, y + 5 + i * 3, x + width + 8, y + 5 + i * 3);
        }
    }
}
