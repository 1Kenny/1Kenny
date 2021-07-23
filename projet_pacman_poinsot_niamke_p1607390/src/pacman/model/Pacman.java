package pacman.model;

import java.awt.Point;


public class Pacman extends Entity {
    // A direction was set by the user but we cant move yet, set it as soon as we can move
    // in that direction!
    private Direction d, cachedDir;

    private void maybeSetCachedDirection() {
        if (cachedDir != null) {
            setDirection(cachedDir);
            // setDirection() might not actually set it!
            if (d == cachedDir) {
                cachedDir = null;
            }
        }
    }

    public Pacman(Game _game, Point pos) {
        super(_game, pos);
        d = Direction.right;
    }

    public void setDirection(Direction _d) {
        Direction tmp = d;

        d = _d;
        // Don't change the direction if we cant! This would
        // effectively allow to stop Pacman.
        if (game.getMap().containsWall(getNextPos())) {
            d = tmp;
            cachedDir = _d;
        }
    }

    public Direction getDirection() {
        return d;
    }

    public Point getNextPos() {
        return getNextPosition(getPosition(), d);
    }

    @Override
    public void moveForward() {
        // If we had a cached direction, use it !
        maybeSetCachedDirection();
        game.movePacman(this, d);
    }
}
