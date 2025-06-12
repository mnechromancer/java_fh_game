import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ParticleSystem {
    private ArrayList<Particle> particles;
    
    public ParticleSystem() {
        particles = new ArrayList<>();
    }
    
    public void addThrustParticles(int x, int y) {
        for (int i = 0; i < 5; i++) {
            particles.add(new Particle(
                x - 10 - (int)(Math.random() * 10),
                y + (int)(Math.random() * 10 - 5),
                -2 - Math.random() * 3, // Velocity X (leftward)
                Math.random() * 4 - 2,  // Velocity Y (random)
                Color.CYAN,
                30 + (int)(Math.random() * 20) // Lifetime
            ));
        }
    }
    
    public void addExplosion(int x, int y) {
        for (int i = 0; i < 15; i++) {
            double angle = Math.random() * Math.PI * 2;
            double speed = 2 + Math.random() * 4;
            particles.add(new Particle(
                x + (int)(Math.random() * 20 - 10),
                y + (int)(Math.random() * 20 - 10),
                Math.cos(angle) * speed,
                Math.sin(angle) * speed,
                new Color(255, (int)(Math.random() * 100), 0), // Red-orange
                20 + (int)(Math.random() * 30)
            ));
        }
    }
    
    public void addPowerUpEffect(int x, int y) {
        for (int i = 0; i < 10; i++) {
            double angle = Math.random() * Math.PI * 2;
            double speed = 1 + Math.random() * 2;
            particles.add(new Particle(
                x + (int)(Math.random() * 10 - 5),
                y + (int)(Math.random() * 10 - 5),
                Math.cos(angle) * speed,
                Math.sin(angle) * speed,
                new Color(255, 255, 0), // Bright yellow
                40 + (int)(Math.random() * 20)
            ));
        }
    }
    
    public void update() {
        Iterator<Particle> iter = particles.iterator();
        while (iter.hasNext()) {
            Particle p = iter.next();
            p.update();
            if (p.isDead()) {
                iter.remove();
            }
        }
    }
    
    public void render(Graphics2D g2d) {
        for (Particle p : particles) {
            p.render(g2d);
        }
    }
    
    private class Particle {
        private double x, y;
        private double velocityX, velocityY;
        private Color color;
        private int lifetime;
        private int maxLifetime;
        
        public Particle(double x, double y, double vx, double vy, Color color, int lifetime) {
            this.x = x;
            this.y = y;
            this.velocityX = vx;
            this.velocityY = vy;
            this.color = color;
            this.lifetime = lifetime;
            this.maxLifetime = lifetime;
        }
        
        public void update() {
            x += velocityX;
            y += velocityY;
            velocityY += 0.1; // Slight gravity
            lifetime--;
        }
        
        public void render(Graphics2D g2d) {
            float alpha = (float)lifetime / maxLifetime;
            Color particleColor = new Color(
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                (int)(alpha * 255)
            );
            
            g2d.setColor(particleColor);
            g2d.fillOval((int)x, (int)y, 3, 3);
        }
        
        public boolean isDead() {
            return lifetime <= 0;
        }
    }
}
