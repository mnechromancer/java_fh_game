import java.awt.*;

public class Player {
    private int x, y;
    private double velocityY;
    private int health;
    private boolean invulnerable;
    private int invulnerabilityTimer;
    private boolean boosted;
    private int boostTimer;
    
    private static final int WIDTH = 30;
    private static final int HEIGHT = 15;
    private static final int THRUST_POWER = -8;
    private static final double GRAVITY = 0.5;
    private static final int MAX_HEALTH = 3;
    private static final int INVULNERABILITY_TIME = 120; // 2 seconds at 60fps
    
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocityY = 0;
        this.health = MAX_HEALTH;
        this.invulnerable = false;
        this.invulnerabilityTimer = 0;
        this.boosted = false;
        this.boostTimer = 0;
    }
    
    public void update() {
        // Apply gravity
        velocityY += GRAVITY;
        
        // Apply velocity to position
        y += velocityY;
        
        // Update invulnerability timer
        if (invulnerable) {
            invulnerabilityTimer--;
            if (invulnerabilityTimer <= 0) {
                invulnerable = false;
            }
        }
        
        // Update boost timer
        if (boosted) {
            boostTimer--;
            if (boostTimer <= 0) {
                boosted = false;
            }
        }
    }
    
    public void thrust() {
        velocityY = THRUST_POWER;
    }
      public void takeDamage() {
        if (!invulnerable && !boosted) {
            health--;
            invulnerable = true;
            invulnerabilityTimer = INVULNERABILITY_TIME;
        }
    }
    
    public void takeDamageFromBoundary() {
        // Always take damage from boundaries, even with boosts
        if (!invulnerable) {
            health--;
            invulnerable = true;
            invulnerabilityTimer = INVULNERABILITY_TIME;
        }
    }
    
    public void restoreHealth() {
        if (health < MAX_HEALTH) {
            health++;
        }
    }
    
    public void activateBoost(int duration) {
        boosted = true;
        boostTimer = duration;
        invulnerable = true; // Boost provides invincibility
    }
    
    public void render(Graphics2D g2d) {
        // Create neon glow effect
        if (boosted) {
            // Bright glow when boosted
            g2d.setColor(new Color(255, 255, 0, 100));
            g2d.fillRect(x - 5, y - 5, WIDTH + 10, HEIGHT + 10);
        } else if (invulnerable && invulnerabilityTimer % 10 < 5) {
            // Flashing when invulnerable
            return;
        }
        
        // Draw neon outline
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.CYAN);
        g2d.drawRect(x, y, WIDTH, HEIGHT);
        
        // Fill with darker cyan
        g2d.setColor(new Color(0, 150, 200, 150));
        g2d.fillRect(x + 1, y + 1, WIDTH - 2, HEIGHT - 2);
        
        // Add some detail lines for hover-bike look
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(x + 5, y + HEIGHT/2, x + WIDTH - 5, y + HEIGHT/2);
        g2d.drawLine(x + WIDTH - 8, y + 3, x + WIDTH - 3, y + HEIGHT - 3);
    }
    
    public void renderHealthBar(Graphics2D g2d) {
        int barX = 10;
        int barY = 80;
        int barWidth = 20;
        int barHeight = 15;
        int spacing = 5;
        
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Health:", barX, barY - 5);
        
        for (int i = 0; i < MAX_HEALTH; i++) {
            int currentX = barX + i * (barWidth + spacing);
            
            if (i < health) {
                // Filled health bar
                g2d.setColor(Color.GREEN);
                g2d.fillRect(currentX, barY, barWidth, barHeight);
                g2d.setColor(Color.CYAN);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(currentX, barY, barWidth, barHeight);
            } else {
                // Empty health bar
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(currentX, barY, barWidth, barHeight);
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRect(currentX, barY, barWidth, barHeight);
            }
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }
    public int getHealth() { return health; }
    public boolean isBoosted() { return boosted; }
}
