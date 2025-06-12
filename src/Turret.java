import java.awt.*;

public class Turret extends Enemy {
    private int fireTimer;
    private boolean charging;
    private static final int FIRE_INTERVAL = 120; // 2 seconds at 60fps
    private static final int CHARGE_TIME = 30; // 0.5 seconds
    
    public Turret(int x, int y) {
        super(x, y, 30, 40);
        this.fireTimer = 0;
        this.charging = false;
    }
      // Target Y position for aiming
    private int targetY;
      @Override
    public void update(Player player) {
        // Default speed
        update(player, 1.0f);
    }
    
    @Override
    public void update(Player player, float speedMultiplier) {
        // Move left
        x -= SPEED * speedMultiplier;
        
        // Calculate target Y for aiming at the player
        targetY = player.getY() + player.getHeight() / 2;
        
        // Fire timer
        fireTimer++;
        if (fireTimer >= FIRE_INTERVAL - CHARGE_TIME) {
            charging = true;
        }
        if (fireTimer >= FIRE_INTERVAL) {
            // Reset for next shot
            fireTimer = 0;
            charging = false;
        }
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // Base
        g2d.setColor(new Color(60, 60, 80));
        g2d.fillRect(x, y + height - 15, width, 15);
        
        // Main turret body
        g2d.setColor(new Color(100, 100, 140));
        g2d.fillRect(x + 5, y, width - 10, height - 15);
        
        // Neon outline
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLUE);
        g2d.drawRect(x + 5, y, width - 10, height - 15);
        g2d.drawRect(x, y + height - 15, width, 15);
        
        // Weapon barrel
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(x - 15, y + 8, 20, 6);
        
        // Charging effect
        if (charging) {
            g2d.setColor(new Color(255, 255, 0, 150));
            g2d.fillOval(x - 18, y + 5, 12, 12);
            
            // Electrical charging effect
            g2d.setStroke(new BasicStroke(1));
            g2d.setColor(Color.YELLOW);
            for (int i = 0; i < 5; i++) {
                int startX = x - 15 + (int)(Math.random() * 15);
                int startY = y + 5 + (int)(Math.random() * 12);
                int endX = startX + (int)(Math.random() * 10 - 5);
                int endY = startY + (int)(Math.random() * 10 - 5);
                g2d.drawLine(startX, startY, endX, endY);
            }
        }
        
        // Status light
        g2d.setColor(charging ? Color.RED : Color.GREEN);
        g2d.fillOval(x + width/2 - 2, y + 5, 4, 4);
          // Warning beam when charging - aim at player's position
        if (charging) {
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(new Color(255, 0, 0, 100));
            
            // Calculate angle to target
            double angle = Math.atan2(targetY - (y + 11), -200);
            int beamLength = 200;
            int endX = x - 15 - (int)(Math.cos(angle) * beamLength);
            int endY = y + 11 + (int)(Math.sin(angle) * beamLength);
            
            // Draw aimed laser beam
            g2d.drawLine(x - 15, y + 11, endX, endY);
        }
    }
}
