package pacman.controller;

import java.awt.Point;
import java.beans.PropertyChangeEvent;

import pacman.view.pacmanView;


public class pacmanChangeListener extends abstractChangeListener {

    public pacmanChangeListener(pacmanView v) {
        super(v);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "position") {
            view.updatePacmanPos((Point) evt.getOldValue(), (Point) evt.getNewValue());
        }
    }
}
