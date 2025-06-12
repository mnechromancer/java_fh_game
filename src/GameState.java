import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import java.util.Properties;

public class GameState {
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private ParticleSystem particles;
    
    private int score;
    private int highScore;
    private boolean gameOver;
    private boolean gameStarted;
    
    private int obstacleSpawnTimer;
    private int enemySpawnTimer;
    private int powerUpSpawnTimer;
    
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    
    public GameState() {
        initializeGame();
        loadHighScore();
    }
    
    private void initializeGame() {
        player = new Player(100, PANEL_HEIGHT / 2);
        obstacles = new ArrayList<>();
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        particles = new ParticleSystem();
        
        score = 0;
        gameOver = false;
        gameStarted = false;
        
        obstacleSpawnTimer = 0;
        enemySpawnTimer = 0;
        powerUpSpawnTimer = 0;
    }
    
    public void handleMouseClick() {
        if (gameOver) {
            restartGame();
        } else {
            if (!gameStarted) {
                gameStarted = true;
            }
            player.thrust();
            
            // Add thrust particles
            particles.addThrustParticles(player.getX(), player.getY() + player.getHeight());
        }
    }
    
    public void update() {
        if (!gameStarted || gameOver) {
            return;
        }
        
        // Update player
        player.update();
        
        // Check if player is out of bounds
        if (player.getY() < 0 || player.getY() > PANEL_HEIGHT) {
            player.takeDamage();
            particles.addExplosion(player.getX(), player.getY());
        }
        
        // Update obstacles
        updateObstacles();
        
        // Update enemies
        updateEnemies();
        
        // Update power-ups
        updatePowerUps();
        
        // Update particles
        particles.update();
        
        // Check collisions
        checkCollisions();
        
        // Check game over
        if (player.getHealth() <= 0) {
            gameOver = true;
            if (score > highScore) {
                highScore = score;
                saveHighScore();
            }
        }
        
        // Increment score based on survival time
        score += 1;
    }
    
    private void updateObstacles() {
        obstacleSpawnTimer++;
        
        // Spawn new obstacles every 2 seconds (120 frames at 60fps)
        if (obstacleSpawnTimer >= 120) {
            obstacles.add(new Obstacle(PANEL_WIDTH, PANEL_HEIGHT));
            obstacleSpawnTimer = 0;
        }
        
        // Update existing obstacles
        Iterator<Obstacle> obsIter = obstacles.iterator();
        while (obsIter.hasNext()) {
            Obstacle obs = obsIter.next();
            obs.update();
            
            // Remove off-screen obstacles and award points
            if (obs.getX() + obs.getWidth() < 0) {
                obsIter.remove();
                score += 10; // Bonus for passing obstacle
            }
        }
    }
    
    private void updateEnemies() {
        enemySpawnTimer++;
        
        // Spawn enemies less frequently than obstacles
        if (enemySpawnTimer >= 180) { // Every 3 seconds
            int enemyType = (int)(Math.random() * 3);
            switch (enemyType) {
                case 0:
                    enemies.add(new SecurityDrone(PANEL_WIDTH, (int)(Math.random() * PANEL_HEIGHT)));
                    break;
                case 1:
                    enemies.add(new HunterBot(PANEL_WIDTH, (int)(Math.random() * PANEL_HEIGHT)));
                    break;
                case 2:
                    enemies.add(new Turret(PANEL_WIDTH, (int)(Math.random() * PANEL_HEIGHT)));
                    break;
            }
            enemySpawnTimer = 0;
        }
        
        // Update existing enemies
        Iterator<Enemy> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();
            enemy.update(player);
            
            // Remove off-screen enemies
            if (enemy.getX() + enemy.getWidth() < 0) {
                enemyIter.remove();
            }
        }
    }
    
    private void updatePowerUps() {
        powerUpSpawnTimer++;
        
        // Spawn power-ups rarely
        if (powerUpSpawnTimer >= 300) { // Every 5 seconds
            PowerUp.PowerUpType type = Math.random() < 0.5 ? 
                PowerUp.PowerUpType.ROCKET_BOOST : PowerUp.PowerUpType.DATA_PACK;
            powerUps.add(new PowerUp(PANEL_WIDTH, (int)(Math.random() * PANEL_HEIGHT), type));
            powerUpSpawnTimer = 0;
        }
        
        // Update existing power-ups
        Iterator<PowerUp> powerUpIter = powerUps.iterator();
        while (powerUpIter.hasNext()) {
            PowerUp powerUp = powerUpIter.next();
            powerUp.update();
            
            // Remove off-screen power-ups
            if (powerUp.getX() + powerUp.getWidth() < 0) {
                powerUpIter.remove();
            }
        }
    }
    
    private void checkCollisions() {
        Rectangle playerBounds = player.getBounds();
          // Check obstacle collisions
        for (Obstacle obs : obstacles) {
            if (obs.collidesWithPlayer(playerBounds)) {
                player.takeDamage();
                particles.addExplosion(player.getX(), player.getY());
                break;
            }
        }
        
        // Check enemy collisions
        for (Enemy enemy : enemies) {
            if (playerBounds.intersects(enemy.getBounds())) {
                player.takeDamage();
                particles.addExplosion(player.getX(), player.getY());
                break;
            }
        }
        
        // Check power-up collisions
        Iterator<PowerUp> powerUpIter = powerUps.iterator();
        while (powerUpIter.hasNext()) {
            PowerUp powerUp = powerUpIter.next();
            if (playerBounds.intersects(powerUp.getBounds())) {
                powerUp.applyEffect(player);
                particles.addPowerUpEffect(powerUp.getX(), powerUp.getY());
                powerUpIter.remove();
                score += 25; // Bonus for collecting power-up
            }
        }
    }
    
    private void restartGame() {
        initializeGame();
    }
    
    private void loadHighScore() {
        try {
            Properties props = new Properties();
            File file = new File("highscore.properties");
            if (file.exists()) {
                props.load(new FileInputStream(file));
                highScore = Integer.parseInt(props.getProperty("highscore", "0"));
            }
        } catch (Exception e) {
            highScore = 0;
        }
    }
    
    private void saveHighScore() {
        try {
            Properties props = new Properties();
            props.setProperty("highscore", String.valueOf(highScore));
            props.store(new FileOutputStream("highscore.properties"), "Neon Runner High Score");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void render(Graphics2D g2d) {
        // Render all game objects
        player.render(g2d);
        
        for (Obstacle obs : obstacles) {
            obs.render(g2d);
        }
        
        for (Enemy enemy : enemies) {
            enemy.render(g2d);
        }
        
        for (PowerUp powerUp : powerUps) {
            powerUp.render(g2d);
        }
        
        particles.render(g2d);
        
        // Render UI
        renderUI(g2d);
        
        // Render game over screen
        if (gameOver) {
            renderGameOver(g2d);
        } else if (!gameStarted) {
            renderStartScreen(g2d);
        }
    }
    
    private void renderUI(Graphics2D g2d) {
        // Score
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 24));
        g2d.drawString("Score: " + score, 10, 30);
        
        // High Score
        g2d.setColor(Color.MAGENTA);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
        g2d.drawString("High Score: " + highScore, 10, 55);
        
        // Render health bar
        player.renderHealthBar(g2d);
    }
    
    private void renderGameOver(Graphics2D g2d) {
        // Semi-transparent overlay
        g2d.setColor(new Color(0, 0, 0, 128));
        g2d.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        
        // Game Over text
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 48));
        FontMetrics fm = g2d.getFontMetrics();
        String gameOverText = "GAME OVER";
        int x = (PANEL_WIDTH - fm.stringWidth(gameOverText)) / 2;
        g2d.drawString(gameOverText, x, PANEL_HEIGHT / 2 - 50);
        
        // Final score
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 24));
        fm = g2d.getFontMetrics();
        String scoreText = "Final Score: " + score;
        x = (PANEL_WIDTH - fm.stringWidth(scoreText)) / 2;
        g2d.drawString(scoreText, x, PANEL_HEIGHT / 2);
        
        // Restart instruction
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
        fm = g2d.getFontMetrics();
        String restartText = "Click to restart";
        x = (PANEL_WIDTH - fm.stringWidth(restartText)) / 2;
        g2d.drawString(restartText, x, PANEL_HEIGHT / 2 + 50);
    }
    
    private void renderStartScreen(Graphics2D g2d) {
        // Title
        g2d.setColor(Color.CYAN);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 48));
        FontMetrics fm = g2d.getFontMetrics();
        String titleText = "NEON RUNNER";
        int x = (PANEL_WIDTH - fm.stringWidth(titleText)) / 2;
        g2d.drawString(titleText, x, PANEL_HEIGHT / 2 - 50);
        
        // Instructions
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
        fm = g2d.getFontMetrics();
        String instructText = "Click to thrust and start game";
        x = (PANEL_WIDTH - fm.stringWidth(instructText)) / 2;
        g2d.drawString(instructText, x, PANEL_HEIGHT / 2 + 50);
    }
}
