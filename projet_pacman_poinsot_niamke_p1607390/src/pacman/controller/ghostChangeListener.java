package pacman.controller;

import java.awt.Point;
import java.beans.PropertyChangeEvent;

import pacman.view.pacmanView;


public class ghostChangeListener extends abstractChangeListener {

    public ghostChangeListener(pacmanView v) {
        super(v);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "position") {
            view.updateGhostPos((Point) evt.getOldValue(), (Point) evt.getNewValue());
        }
    }
}
