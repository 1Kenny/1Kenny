package pacman.model;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Random;


public class Ghost extends Entity {

    private Random r;
    private ArrayDeque<Point> currentPath;

    // The Lee algorithm is great, but comports a big bias toward the first direction chosen
    // for the "wave expansion". So we reorder the (4-items long) directions array.
    private Direction[] optimizedDirections(Point destPoint) {
        // Is Pacman above us ?
        boolean above = destPoint.y < getPosition().y;
        // Is it at our left ?
        boolean left = destPoint.x < getPosition().x;
        Direction[] directions = new Direction[4];

        directions[0] = above ? Direction.top : Direction.bottom;
        directions[1] = left ? Direction.left : Direction.right;
        directions[2] = above ? Direction.bottom : Direction.top;
        directions[3] = left ? Direction.right : Direction.left;

        return directions;
    }

    // This is the "2)" of https://en.wikipedia.org/wiki/Lee_algorithm, the "wave
    // expansion".
    private void markNeighbors(HashMap<Point, Integer> marks, HashMap<Point, Boolean> visited,
                               Point currentPoint, int currentMark, Point destPoint) {
        Direction[] directions = optimizedDirections(destPoint);
        for (Direction dir : directions) {
            Point neighbor = getNextPosition(currentPoint, dir);
            if (game.getMap().containsPoint(neighbor) && !game.getMap().containsWall(neighbor)
                    && (!visited.containsKey(neighbor) || !visited.get(neighbor))) {
                marks.put(neighbor, currentMark);
                visited.put(neighbor, true);
                if (neighbor.equals(destPoint)) {
                    // FIXME: The algorithm is biased toward the chosen direction !!
                    break;
                }
                markNeighbors(marks, visited, neighbor, currentMark + 1, destPoint);
            }
        }
    }

    // This the "3)" from https://en.wikipedia.org/wiki/Lee_algorithm, the "backtrace".
    // We construct a **backward** path.
    private ArrayDeque<Point> getPath(Point sourcePoint, Point destPoint, HashMap<Point, Integer> marks) {
        ArrayDeque<Point> path = new ArrayDeque<Point>();
        Point currentPoint = destPoint;

        path.add(destPoint);
        while (true) {
            // FIXME: This is suboptimal, we could cache a list of neighbors to avoid
            // looping through points we didnt even visit!
            for (Direction dir : Direction.values()) {
                Point neighbor = getNextPosition(currentPoint, dir);
                if (game.getMap().containsPoint(neighbor) && !game.getMap().containsWall(neighbor)
                        && marks.containsKey(neighbor) ) {
                    if (marks.get(neighbor) < marks.get(currentPoint)) {
                        if (neighbor.equals(sourcePoint)) {
                            return path;
                        }
                        path.add(neighbor);
                        currentPoint = neighbor;
                        break;
                    }
                }
            }
        }
    }

    // We use Lee algorithm to find the best path to Pacman. It's O(N^2) but it will find
    // the optimal path and we'll only update it once each X tick (which sets the
    // intelligence of the ghost).
    // See https://en.wikipedia.org/wiki/Lee_algorithm .
    private ArrayDeque<Point> leeAlgorithm(Point destination) {
        HashMap<Point, Integer> marks = new HashMap<Point, Integer>();
        HashMap<Point, Boolean> visited = new HashMap<Point, Boolean>();
        Point startPos = getPosition();
        marks.put(startPos, 0);
        visited.put(startPos, true);
        markNeighbors(marks, visited, startPos, 1, destination);
        return getPath(startPos, destination, marks);
    }

    private void updateCurrentPath() {
        Point pacmanPos = game.getPacman().getPosition();
        currentPath = leeAlgorithm(pacmanPos);
    }

    public Ghost(Game _game, Point pos) {
        super(_game, pos);
        r = new Random();
        updateCurrentPath();
    }

    @Override
    public void moveForward() {
        if (currentPath.size() == 0 || r.nextInt(100) % 5 == 0) {
            updateCurrentPath();
        }
        if (currentPath.size() > 0) {
            game.moveGhost(this, currentPath.removeLast());
        }
    }
}
