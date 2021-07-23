package pacman.controller;

import java.beans.PropertyChangeEvent;

import pacman.view.pacmanView;


public class gameChangeListener extends abstractChangeListener {

    private pacmanController controller;

    public gameChangeListener(pacmanView v, pacmanController c) {
        super(v);
        controller = c;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "gameOver") {
            if ((boolean) evt.getNewValue()) {
                view.gameOver();
                controller.gameEnded();
            }
        } else if (evt.getPropertyName() == "youWon") {
            if ((boolean) evt.getNewValue()) {
                view.youWon();
                controller.gameEnded();
            }
        } else if (evt.getPropertyName() == "score") {
            view.updateScore((int) evt.getNewValue());
        }
    }
}
