import java.awt.*;

public abstract class Enemy {
    protected int x, y, width, height;
    protected static final float SPEED = 1.5f; // Reduced speed for easier dodging
    
    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void update(Player player);
    public abstract void update(Player player, float speedMultiplier);
    public abstract void render(Graphics2D g2d);
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
