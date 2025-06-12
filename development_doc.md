# Neon Runner - Java Development Document

## Technology Stack

### Core Technologies (Minimal Java Setup)
- **Java SE 8+:** Core language and standard libraries
- **Swing/AWT:** Built-in GUI framework for rendering and input
- **Graphics2D:** Java's 2D graphics API for drawing
- **No External Dependencies:** Pure Java standard library only

### Development Environment
- **JDK 8+:** Java Development Kit
- **Text Editor:** Any basic editor (Notepad++, VS Code, etc.) or simple IDE
- **Command Line Compilation:** `javac` and `java` commands
- **No Build Tools:** Direct compilation with javac, no Maven/Gradle

### Project Structure (Single Directory)
```
neon-runner/
├── NeonRunner.java         # Main class with game loop
├── Player.java             # Player hover-bike class
├── Obstacle.java           # Building gaps and barriers
├── Enemy.java              # Drones, hunters, turrets
├── PowerUp.java            # Rocket boost and data packs
├── GamePanel.java          # Swing JPanel for rendering
├── GameState.java          # Game state management
└── README.md               # Basic documentation
```

## Development Process Tasks

### Phase 1: Core Systems (Hours 1-3)

#### Task 1.1: Basic Setup (30 minutes)
- [ ] Create `NeonRunner.java` with main method
- [ ] Set up JFrame window (800x600 pixels)
- [ ] Create `GamePanel` extending JPanel
- [ ] Implement basic game loop using Timer
- [ ] Add dark gradient background using Graphics2D

**Code Structure:**
```java
public class NeonRunner extends JFrame {
    private GamePanel gamePanel;
    private Timer gameTimer;
    
    public static void main(String[] args) {
        new NeonRunner();
    }
}

class GamePanel extends JPanel {
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Rendering code here
    }
}
```

**Deliverable:** Window with running game loop and background

#### Task 1.2: Player Movement (45 minutes)
- [ ] Create `Player.java` class with position (x, y) and velocity
- [ ] Implement mouse click listener for thrust input
- [ ] Add gravity physics in update method
- [ ] Draw simple player rectangle with cyberpunk colors

**Player Class Structure:**
```java
public class Player {
    private int x, y;
    private double velocityY;
    private int health;
    private static final int THRUST_POWER = -8;
    private static final double GRAVITY = 0.5;
    
    public void update() {
        velocityY += GRAVITY;
        y += velocityY;
    }
    
    public void thrust() {
        velocityY = THRUST_POWER;
    }
    
    public void render(Graphics2D g2d) {
        // Draw hover-bike rectangle
    }
}
```

**Deliverable:** Controllable player responding to mouse clicks

#### Task 1.3: Basic Obstacles (60 minutes)
- [ ] Create `Obstacle.java` class for building gaps
- [ ] Implement ArrayList to manage multiple obstacles
- [ ] Add obstacle generation and horizontal movement
- [ ] Basic rectangular collision detection

**Obstacle Management:**
```java
public class GameState {
    private ArrayList<Obstacle> obstacles;
    private int obstacleSpawnTimer;
    
    public void updateObstacles() {
        // Move existing obstacles
        // Remove off-screen obstacles
        // Spawn new obstacles
    }
    
    public boolean checkCollisions(Player player) {
        // Rectangle intersection testing
    }
}
```

**Deliverable:** Player navigating through scrolling obstacles

#### Task 1.4: Health System (45 minutes)
- [ ] Add health property to Player (starts at 3)
- [ ] Implement damage on collision
- [ ] Create health bar UI using rectangles
- [ ] Add game over state and restart functionality

**Health System:**
```java
public class Player {
    private int health = 3;
    private boolean invulnerable = false;
    
    public void takeDamage() {
        if (!invulnerable) {
            health--;
            // Add brief invulnerability period
        }
    }
    
    public void renderHealthBar(Graphics2D g2d) {
        // Draw 3 health rectangles with cyberpunk styling
    }
}
```

**Deliverable:** Functional health system with game over

### Phase 2: Content & Features (Hours 4-6)

#### Task 2.1: Enemy Types (75 minutes)
- [ ] Create `Enemy.java` base class
- [ ] SecurityDrone: Simple moving rectangle
- [ ] HunterBot: Tracks player position slowly
- [ ] Turret: Static enemy with projectile shooting
- [ ] Add enemy collision detection

**Enemy Implementation:**
```java
public abstract class Enemy {
    protected int x, y, width, height;
    
    public abstract void update(Player player);
    public abstract void render(Graphics2D g2d);
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

public class SecurityDrone extends Enemy {
    private double angle = 0;
    
    public void update(Player player) {
        x -= 3; // Move left
        angle += 0.1; // Simple rotation
    }
}
```

**Deliverable:** Three enemy types with unique behaviors

#### Task 2.2: Power-Up System (60 minutes)
- [ ] Create `PowerUp.java` class
- [ ] RocketBoost: Speed increase and invincibility timer
- [ ] DataPack: Health restoration with visual effect
- [ ] Power-up spawning and collection logic

**Power-Up System:**
```java
public class PowerUp {
    private PowerUpType type;
    private int x, y;
    
    public enum PowerUpType {
        ROCKET_BOOST, DATA_PACK
    }
    
    public void applyEffect(Player player) {
        switch(type) {
            case ROCKET_BOOST:
                player.activateBoost(5000); // 5 seconds
                break;
            case DATA_PACK:
                player.restoreHealth();
                break;
        }
    }
}
```

**Deliverable:** Two functional power-ups with effects

#### Task 2.3: Scoring System (45 minutes)
- [ ] Score increment for passing obstacles
- [ ] Bonus points for power-up collection
- [ ] High score persistence using Properties file
- [ ] Score display with cyberpunk font styling

**Scoring Implementation:**
```java
public class GameState {
    private int score = 0;
    private int highScore;
    
    public void loadHighScore() {
        // Read from properties file
    }
    
    public void saveHighScore() {
        // Write to properties file
    }
    
    public void renderScore(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 24));
        g2d.drawString("Score: " + score, 10, 30);
    }
}
```

**Deliverable:** Complete scoring system with persistence

### Phase 3: Polish & Effects (Hours 7-8)

#### Task 3.1: Visual Effects (45 minutes)
- [ ] Neon glow effects using Graphics2D stroke and color
- [ ] Simple particle system for thrust and damage
- [ ] Screen flash effects for feedback
- [ ] Cyberpunk color scheme (cyan, magenta, purple)

**Visual Effects:**
```java
public class ParticleSystem {
    private ArrayList<Particle> particles;
    
    public void addThrustParticles(int x, int y) {
        // Add cyan particles behind player
    }
    
    public void addExplosion(int x, int y) {
        // Add red/orange damage particles
    }
    
    public void render(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(
            AlphaComposite.SRC_OVER, 0.7f));
        // Render semi-transparent particles
    }
}
```

**Deliverable:** Enhanced visual feedback and cyberpunk aesthetic

#### Task 3.2: Audio Integration (Optional - 30 minutes)
- [ ] Basic sound effects using Java's Clip class
- [ ] Simple electronic sounds for actions
- [ ] Background music loop (if time permits)

**Audio System:**
```java
public class AudioManager {
    private HashMap<String, Clip> sounds;
    
    public void loadSound(String name, String filename) {
        // Load WAV file using AudioSystem
    }
    
    public void playSound(String name) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
```

**Deliverable:** Audio enhancement (if time permits)

#### Task 3.3: Final Polish (45 minutes)
- [ ] Bug fixes and edge case handling
- [ ] Performance optimization (object pooling)
- [ ] Smooth animation and timing adjustments
- [ ] Final testing and difficulty balancing

**Deliverable:** Complete, polished game ready for execution

## Compilation & Execution

### Command Line Build Process
```bash
# Compile all Java files
javac *.java

# Run the game
java NeonRunner

# Create JAR file (optional)
jar cvfe NeonRunner.jar NeonRunner *.class
java -jar NeonRunner.jar
```

### Key Java Technologies Used

#### Graphics2D Features
- **BasicStroke:** For neon outline effects
- **GradientPaint:** For background gradients
- **AlphaComposite:** For transparency effects
- **AffineTransform:** For rotation (enemies, particles)

#### Swing Components
- **JFrame:** Main window container
- **JPanel:** Custom game panel for rendering
- **Timer:** Game loop timing (16ms for ~60fps)
- **KeyListener/MouseListener:** Input handling

#### Performance Considerations
- **Rectangle.intersects():** Efficient collision detection
- **ArrayList management:** Remove off-screen objects
- **Graphics2D hints:** Antialiasing for smooth rendering
- **Object pooling:** Reuse particle and enemy objects

## Testing & Quality Assurance

### Manual Testing Checklist
- [ ] Player responds correctly to mouse clicks
- [ ] Collision detection works for all object types
- [ ] Health system decrements properly and triggers game over
- [ ] Power-ups provide correct effects and visual feedback
- [ ] Score increments and high score persists between sessions
- [ ] Game runs smoothly without frame drops
- [ ] No runtime exceptions or null pointer errors

### Distribution
- **Single JAR File:** Package all classes into executable JAR
- **Source Code:** Provide .java files for compilation
- **Documentation:** Include compilation and execution instructions

## Success Criteria Validation

### Minimum Viable Product (MVP)
- [ ] Player can control hover-bike with mouse clicks
- [ ] Obstacles spawn and scroll across screen
- [ ] Collision detection causes damage and game over
- [ ] Score tracking and display functional
- [ ] Game can be restarted after game over

### Enhanced Version (If Time Permits)
- [ ] All three enemy types implemented and functional
- [ ] Both power-ups working with visual effects
- [ ] Cyberpunk aesthetic with neon glow effects
- [ ] Basic audio feedback system
- [ ] Smooth 60fps performance with polished feel

This development document provides a comprehensive Java implementation guide using only standard library features while maintaining the core cyberpunk endless runner design within the 8-hour development constraint.