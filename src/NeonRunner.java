import javax.swing.*;
import java.awt.event.*;

public class NeonRunner extends JFrame {
    private GamePanel gamePanel;
    private Timer gameTimer;
    private GameState gameState;
    
    public NeonRunner() {
        initializeGame();
        setupWindow();
        startGameLoop();
    }
    
    private void initializeGame() {
        gameState = new GameState();
        gamePanel = new GamePanel(gameState);
    }
    
    private void setupWindow() {
        setTitle("Neon Runner - Cyberpunk Endless Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Add mouse listener for player input
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gameState.handleMouseClick();
            }
        });
        
        // Request focus for input
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }
    
    private void startGameLoop() {
        // 16ms timer for approximately 60 FPS
        gameTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.update();
                gamePanel.repaint();
            }
        });
        gameTimer.start();
    }    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NeonRunner();
        });
    }
}