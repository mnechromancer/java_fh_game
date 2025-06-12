import java.awt.*;

public class Obstacle {
    private int x, y;
    private int width, height;
    private int gapY, gapHeight;
    private static final int SPEED = 3;    private static final int MIN_GAP_HEIGHT = 150; // Increased for better visibility
    private static final int MAX_GAP_HEIGHT = 200;
    
    public Obstacle(int startX, int screenHeight) {
        this.x = startX;
        this.width = 40;
          // Create a gap in the middle of the screen
        this.gapHeight = MIN_GAP_HEIGHT + (int)(Math.random() * (MAX_GAP_HEIGHT - MIN_GAP_HEIGHT));
        
        // Ensure minimum vertical space for gaps
        int minTopHeight = 80;  // Minimum height of top building
        int minBottomHeight = 80;  // Minimum height of bottom building
        int maxGapY = screenHeight - gapHeight - minBottomHeight;
        int minGapY = minTopHeight;
        
        // Ensure gap is within reasonable bounds
        this.gapY = minGapY + (int)(Math.random() * (maxGapY - minGapY));
        
        // Total height spans the screen
        this.height = screenHeight;
        this.y = 0;
    }
      public void update() {
        x -= SPEED;
    }
    
    public void update(float speedMultiplier) {
        x -= SPEED * speedMultiplier;
    }
    
    public void render(Graphics2D g2d) {
        // Draw top building
        renderBuilding(g2d, x, y, width, gapY);
        
        // Draw bottom building
        renderBuilding(g2d, x, gapY + gapHeight, width, height - (gapY + gapHeight));
    }
    
    private void renderBuilding(Graphics2D g2d, int buildingX, int buildingY, int buildingWidth, int buildingHeight) {
        if (buildingHeight <= 0) return;
        
        // Main building body
        g2d.setColor(new Color(40, 40, 60));
        g2d.fillRect(buildingX, buildingY, buildingWidth, buildingHeight);
        
        // Neon outline
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.MAGENTA);
        g2d.drawRect(buildingX, buildingY, buildingWidth, buildingHeight);
        
        // Add some window details
        g2d.setColor(new Color(100, 200, 255, 100));
        for (int windowY = buildingY + 10; windowY < buildingY + buildingHeight - 10; windowY += 20) {
            for (int windowX = buildingX + 5; windowX < buildingX + buildingWidth - 5; windowX += 15) {
                if (Math.random() < 0.3) { // Random windows lit up
                    g2d.fillRect(windowX, windowY, 8, 8);
                }
            }
        }
        
        // Hazard stripes on the edge facing the gap
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.YELLOW);
        if (buildingY + buildingHeight < 400) { // Top building
            int stripeY = buildingY + buildingHeight - 5;
            for (int i = 0; i < buildingWidth; i += 10) {
                g2d.drawLine(buildingX + i, stripeY, buildingX + i + 5, stripeY);
            }
        } else { // Bottom building
            int stripeY = buildingY + 5;
            for (int i = 0; i < buildingWidth; i += 10) {
                g2d.drawLine(buildingX + i, stripeY, buildingX + i + 5, stripeY);
            }
        }
    }
    
    public Rectangle getBounds() {
        // Return a rectangle that represents the collision area (buildings, not the gap)
        // We'll check collision with top and bottom parts separately
        return new Rectangle(x, y, width, height);
    }
    
    public boolean collidesWithPlayer(Rectangle playerBounds) {
        // Check collision with top building
        Rectangle topBuilding = new Rectangle(x, y, width, gapY);
        if (playerBounds.intersects(topBuilding)) {
            return true;
        }
        
        // Check collision with bottom building
        Rectangle bottomBuilding = new Rectangle(x, gapY + gapHeight, width, height - (gapY + gapHeight));
        if (playerBounds.intersects(bottomBuilding)) {
            return true;
        }
        
        return false;
    }
      // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getGapY() { return gapY; }
    public int getGapHeight() { return gapHeight; }
}
