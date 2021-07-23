package pacman.model;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The game class handles the game main logic.
 *
 * This is the "model" part of the MVC.
 */
public class Game implements Runnable {

    private Pacman pacman;
    private Ghost[] ghosts;
    private pacmanMap map;
    private int score;
    private boolean gameOver, youWon;
    Thread selfThread;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private void gameOver() {
        gameOver = true;
        pcs.firePropertyChange("gameOver", false, true);
    }

    private void youWon() {
        youWon = true;
        pcs.firePropertyChange("youWon", false, true);
    }

    private void updateScore(int added) {
        score += added;
        pcs.firePropertyChange("score", score - added, score);
    }

    public pacmanMap getMap() {
        return map;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public int getScore() {
        return score;
    }

    public boolean over() {
        return gameOver;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void start() {
        selfThread = new Thread(this);
        selfThread.start();
    }

    public void startNewGame() {
        map = new pacmanMap();

        Point pacmanPos = map.getPacmanSpawnSpot();
        map.addPacman(pacmanPos);
        pacman = new Pacman(this, pacmanPos);

        // TODO: Make this configurable ?
        ghosts = new Ghost[2];
        for (int i = 0; i < ghosts.length; i++) {
            Point pos = map.getGhostSpawnSpot();
            map.addGhost(pos);
            ghosts[i] = new Ghost(this, pos);
        }

        gameOver = false;
        youWon = false;

        start();
    }

    public Game() {
        startNewGame();
    }

    public void moveGhost(Ghost g, Point targetPos) {
        Point currentPos = g.getPosition();

        // There are tunnels at the left and right sides of the map.
        if (targetPos.x > map.getWidth() - 1) {
            targetPos.setLocation(0, targetPos.y);
        } else if (targetPos.x < 0) {
            targetPos.setLocation(map.getWidth() - 1, targetPos.y);
        }

        if (map.containsPacman(targetPos)) {
            gameOver();
            return;
        }

        if (map.containsGhost(currentPos)) {
            map.removeGhost(currentPos);
        }
        map.addGhost(targetPos);
        g.setPosition(targetPos);
    }

    public void movePacman(Pacman p, Direction d) {
        Point targetPos = p.getNextPos();

        // There are tunnels at the left and right sides of the map.
        if (targetPos.x > map.getWidth() - 1) {
            targetPos.setLocation(0, targetPos.y);
        } else if (targetPos.x < 0) {
            targetPos.setLocation(map.getWidth() - 1, targetPos.y);
        }

        if (map.containsGhost(targetPos)) {
            gameOver();
            return;
        }

        if (!map.containsWall(targetPos)) {
            map.removePacman(p.getPosition());
            map.addPacman(targetPos);
            p.setPosition(targetPos);

            if (map.containsDot(targetPos)) {
                // Based on https://pacman.live/play.html
                updateScore(10);
                map.removeDot(targetPos);
                if (map.getRemainingDots() == 0) {
                    youWon();
                }
            }
        }
    }

    // Today it's easter, so I felt like adding a few eggs..
    public void easterEgg() {
        youWon();
    }

    @Override
    public void run() {
        long time, duration;
        while (!(gameOver || youWon)) {
            // This allows us to record how much time the operations took *before*
            // sleeping, so that we smooth the lags.
            time = System.currentTimeMillis();

            pacman.run();
            for (Ghost g : ghosts) {
                g.run();
            }

            try {
                duration = 350L - (System.currentTimeMillis() - time);
                if (duration > 0) {
                    Thread.sleep(duration);
                }
            } catch (InterruptedException ex) {
                // Game over!
                return;
            }
        }
    }
}
