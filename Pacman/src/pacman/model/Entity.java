package pacman.model;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**
 * The Entity class represents any moving entity of the game.
 */
public abstract class Entity implements Runnable {

    protected Game game;
    // Its position in the map
    private Point position;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    protected Point getNextPosition(Point pos, Direction dir) {
        switch(dir) {
            case top:
                return new Point(pos.x, pos.y - 1);
            case bottom:
                return new Point(pos.x, pos.y + 1);
            case left:
                return new Point(pos.x - 1, pos.y);
            case right:
                return new Point(pos.x + 1, pos.y);
            default:
                // Should not happen !!
                throw new java.lang.RuntimeException("Bad direction");
        }
    }

    public Entity(Game _game, Point pos) {
        game = _game;
        position = pos;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point pos) {
        Point oldPos = position;
        position = pos;
        pcs.firePropertyChange("position", oldPos, position);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public abstract void moveForward();

    @Override
    public void run() {
        if (!game.over()) {
            moveForward();
        }
    }
}
