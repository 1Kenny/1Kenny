package pacman.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import pacman.view.pacmanView;


public abstract class abstractChangeListener implements PropertyChangeListener {

    protected pacmanView view;

    public abstractChangeListener(pacmanView v) {
        view = v;
    }

    public abstract void propertyChange(PropertyChangeEvent event);
}
