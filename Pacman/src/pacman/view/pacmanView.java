package pacman.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.Point;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pacman.model.Game;


/**
 * This is the View part of the MVC. It uses Swing to render the graphics.
 */
public class pacmanView extends JFrame {

    // A reference to the model, used to update graphical output
    private Game game;
    // This is the size of *our* grid !
    private int nColums;
    private int nRows;

    private ImageIcon icoPacman, icoGhost, icoBackground, icoWall, icoDot;

    // The graphical content of each box of the map
    private rotatableJLabel[][] grid;
    private JLabel scoreLabel;

    private ImageIcon loadIcon(String urlIcone) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(pacmanView.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return new ImageIcon(image);
    }

    private void loadIcons() {
        icoPacman = loadIcon("res/Pacman.png");
        icoBackground = loadIcon("res/Background.png");
        icoGhost = loadIcon("res/Ghost.png");
        icoWall = loadIcon("res/Wall.png");
        // The background with a dot on top of it
        icoDot = loadIcon("res/Dot.png");
    }

    private void loadGraphics() {
        setTitle("PacMan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.BLACK);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(Color.BLACK);
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        scoreLabel.setForeground(Color.WHITE);
        topPanel.add(scoreLabel);

        JPanel mazePanel = new JPanel(new GridLayout(nRows, nColums, 0, 0));
        mazePanel.setBackground(Color.BLACK);
        grid = new rotatableJLabel[nRows][nColums];
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nColums; col++) {
                rotatableJLabel jlab = new rotatableJLabel(icoBackground);
                jlab.setSize(new Dimension(200, 200));
                grid[row][col] = jlab;
                mazePanel.add(jlab);
            }
        }

        add(topPanel);
        add(mazePanel);
        pack();
    }

    private void showGameOver() {
        // A little animation on game over, please appreciate the art.
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nColums; col++) {
                grid[row][col].setIcon(icoBackground);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    Logger.getLogger(pacmanView.class.getName()).log(Level.SEVERE, null, e);
                    return;
                }
            }
        }

        getContentPane().removeAll();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.BLACK);

        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.X_AXIS));
        gameOverPanel.setBackground(Color.BLACK);
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
        gameOverLabel.setForeground(Color.YELLOW);
        gameOverPanel.add(gameOverLabel);
        add(gameOverPanel);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.setBackground(Color.BLACK);
        scorePanel.add(scoreLabel);
        add(scorePanel);

        JPanel restartPanel = new JPanel();
        restartPanel.setLayout(new BoxLayout(restartPanel, BoxLayout.X_AXIS));
        restartPanel.setBackground(Color.BLACK);
        JLabel restartLabel = new JLabel("Press space to restart");
        restartLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        restartLabel.setForeground(Color.WHITE);
        restartPanel.add(restartLabel);
        add(restartPanel);

        validate();
    }

    // The user achieved to make Pacman eat all the dots, we use the same layout as for
    // the game over case.
    private void showYouWon() {
        setVisible(false);
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nColums; col++) {
                grid[row][col].setIcon(icoBackground);
            }
        }
        setVisible(true);

        getContentPane().removeAll();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.BLACK);

        JPanel congratsPanel = new JPanel();
        congratsPanel.setLayout(new BoxLayout(congratsPanel, BoxLayout.X_AXIS));
        congratsPanel.setBackground(Color.BLACK);
        JLabel congratsLabel = new JLabel("CONGRATULATIONS!");
        congratsLabel.setFont(new Font("Helvetica", Font.BOLD, 35));
        congratsLabel.setForeground(Color.YELLOW);
        congratsPanel.add(congratsLabel);
        add(congratsPanel);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.setBackground(Color.BLACK);
        scorePanel.add(scoreLabel);
        add(scorePanel);

        JPanel restartPanel = new JPanel();
        restartPanel.setLayout(new BoxLayout(restartPanel, BoxLayout.X_AXIS));
        restartPanel.setBackground(Color.BLACK);
        JLabel restartLabel = new JLabel("Press space to restart");
        restartLabel.setFont(new Font("Helvetica", Font.ITALIC, 15));
        restartLabel.setForeground(Color.WHITE);
        restartPanel.add(restartLabel);
        add(restartPanel);

        validate();
    }

    // Initialize the maze
    private void drawMaze() {
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nColums; col++) {
                if (game.getMap().containsPacman(col, row)) {
                    grid[row][col].setIcon(icoPacman);
                } else if (game.getMap().containsGhost(col, row)) {
                    grid[row][col].setIcon(icoGhost);
                } else if (game.getMap().containsWall(col, row)) {
                    grid[row][col].setIcon(icoWall);
                } else if (game.getMap().containsDot(col, row)) {
                    grid[row][col].setIcon(icoDot);
                } else {
                    grid[row][col].setIcon(icoBackground);
                }
            }
        }
    }

    public pacmanView(Game _game) {
        game = _game;
        nColums = game.getMap().getWidth();
        nRows = game.getMap().getHeight();
        loadIcons();
        loadGraphics();
        drawMaze();
        setVisible(true);
    }

    // Update the position of the Pacman
    public void updatePacmanPos(Point oldPos, Point newPos) {
        // Pacman eats the dots so don't care about them.
        grid[oldPos.y][oldPos.x].setIcon(icoBackground);
        grid[newPos.y][newPos.x].setIcon(icoPacman);

        // Rotate it depending on the direction
        switch (game.getPacman().getDirection()) {
            case top:
                grid[newPos.y][newPos.x].setTheta(-Math.PI / 2);
                break;
            case bottom:
                grid[newPos.y][newPos.x].setTheta(Math.PI / 2);
                break;
            case left:
                grid[newPos.y][newPos.x].setTheta(Math.PI);
                break;
            case right:
                grid[newPos.y][newPos.x].setTheta(0);
                break;
            default:
                // Should not happen !!
                throw new java.lang.RuntimeException("Bad direction");
        }
    }

    // Update the position of one of the ghosts
    public void updateGhostPos(Point oldPos, Point newPos) {
        // Put the right object back on the old cell..
        if (game.getMap().containsDot(oldPos)) {
            grid[oldPos.y][oldPos.x].setIcon(icoDot);
        } else {
            grid[oldPos.y][oldPos.x].setIcon(icoBackground);
        }

        // And the ghost on its new cell
        grid[newPos.y][newPos.x].setIcon(icoGhost);
    }

    // Pacman ate a dot: make the user happy!
    public void updateScore(int newScore) {
        scoreLabel.setText("Score: " + newScore);
    }

    public void gameOver() {
        showGameOver();
    }

    public void youWon() {
        showYouWon();
    }
}
