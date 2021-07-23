package pacman.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import pacman.model.Direction;
import pacman.model.Game;
import pacman.model.Ghost;
import pacman.view.pacmanView;


public class pacmanController {

    private Game game;
    private pacmanView view;
    private KeyAdapter gameControls, restartControls;
    private int easterEggCount;

    private void addGameControls() {
        gameControls = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.getPacman().setDirection(Direction.left);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.getPacman().setDirection(Direction.right);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.getPacman().setDirection(Direction.bottom);
                        break;
                    case KeyEvent.VK_UP:
                        game.getPacman().setDirection(Direction.top);
                        break;
                    case KeyEvent.VK_W:
                        // In case the readers are not skilled enough, this allows to test
                        // the "winning" functionality without eating all the dots :p
                        if (easterEggCount++ == 5) {
                            game.easterEgg();
                        }
                        break;
                }
            }
        };
        view.addKeyListener(gameControls);
    }

    private void setupListeners() {
        game.addPropertyChangeListener(new gameChangeListener(view, this));
        game.getPacman().addPropertyChangeListener(new pacmanChangeListener(view));
        for (Ghost ghost : game.getGhosts()) {
            ghost.addPropertyChangeListener(new ghostChangeListener(view));
        }
    }

    // This a function, not a 10-years old magic word used to make helicopter money flow.
    private void startup() {
        easterEggCount = 0;
        addGameControls();
        setupListeners();
    }

    private void addRestartEvent() {
        restartControls = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        game.startNewGame();
                        view = new pacmanView(game);
                        startup();
                        break;
                }
            }
        };
        view.addKeyListener(restartControls);
    }

    public pacmanController(Game g) {
        game = g;
        view = new pacmanView(game);
        startup();
    }

    public void gameEnded() {
        // The game ended, remove the game controls and add the possibility to restart by
        // pressing "space".
        view.removeKeyListener(gameControls);
        addRestartEvent();
    }
}
