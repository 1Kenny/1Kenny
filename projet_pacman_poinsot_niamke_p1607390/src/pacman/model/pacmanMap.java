package pacman.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class pacmanMap {
    // We use a bitmap to store the current game map. We use the least significant bit as
    // the first bit.
    // No bit set => nothing
    // First bit set => a wall
    // Second bit set => a dot
    // Third bit set => a ghost
    // Fourth bit set => the Pacman !
    // This is a by-hand hacky reproduction of the map at
    // https://www.classicgaming.cc/classics/pac-man/play-guide by hand but I eventually
    private final int[][] map = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1},
        {1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
        {1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
        {1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
        {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
        {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private int nDots;

    private int countDots() {
        int dots = 0;

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (containsDot(new Point(x, y))) {
                    dots++;
                }
            }
        }

        return dots;
    }

    public pacmanMap() {
        nDots = countDots();
    }

    public int getHeight() {
        return map.length;
    }

    public int getWidth() {
        // All rows are of the same size
        return map[0].length;
    }

    // Out of bounds ?
    public boolean containsPoint(Point pos) {
        return pos.x >= 0 && pos.x < getWidth() && pos.y >= 0 && pos.x < getHeight();
    }

    // Is there a wall at this position ?
    public boolean containsWall(Point pos) {
        return (map[pos.y][pos.x] & 1) != 0;
    }

    // Is there a dot at this position ?
    public boolean containsDot(Point pos) {
        return (map[pos.y][pos.x] & (1 << 1)) != 0;
    }

    // Is there a ghost at this position ?
    public boolean containsGhost(Point pos) {
        return (map[pos.y][pos.x] & (1 << 2)) != 0;
    }

    // Is there Pacman at this position ?
    public boolean containsPacman(Point pos) {
        return (map[pos.y][pos.x] & (1 << 3)) != 0;
    }

    // Some flexibility is sometimes useful...

    public boolean containsWall(int x, int y) {
        return (map[y][x] & 1) != 0;
    }

    public boolean containsDot(int x, int y) {
        return (map[y][x] & (1 << 1)) != 0;
    }

    public boolean containsGhost(int x, int y) {
        return (map[y][x] & (1 << 2)) != 0;
    }

    public boolean containsPacman(int x, int y) {
        return (map[y][x] & (1 << 3)) != 0;
    }

    // Add a ghost at this position
    public void addGhost(Point pos) {
        if (containsWall(pos)) {
            throw new java.lang.RuntimeException("Can't add a ghost at (" + pos.toString() + "): there is a wall.");
        } else if (containsPacman(pos)) {
            throw new java.lang.RuntimeException("Can't add a ghost at (" + pos.toString() + "): there is Pacman!");
        }
        map[pos.y][pos.x] |= (1 << 2);
    }

    // Add Pacman at this position
    public void addPacman(Point pos) {
        if (containsWall(pos)) {
            throw new java.lang.RuntimeException("Can't add Pacman at (" + pos.toString() + "): there is a wall.");
        } else if (containsGhost(pos)) {
            throw new java.lang.RuntimeException("Can't add Pacman at (" + pos.toString() + "): there is a ghost.");
        }
        map[pos.y][pos.x] |= (1 << 3);
    }

    // Remove a dot at this position
    public void removeDot(Point pos) {
        if ((map[pos.y][pos.x] & (1 << 1)) == 0) {
            throw new java.lang.RuntimeException("Can't remove a dot at (" + pos.toString() + "): there is none.");
        }
        map[pos.y][pos.x] &= ~(1 << 1);
        nDots--;
    }

    // Remove a ghost at this position
    public void removeGhost(Point pos) {
        if ((map[pos.y][pos.x] & (1 << 2)) == 0) {
            throw new java.lang.RuntimeException("Can't remove a ghost at (" + pos.toString() + "): there is none.");
        }
        map[pos.y][pos.x] &= ~(1 << 2);
    }

    // remove Pacman at this position
    public void removePacman(Point pos) {
        if ((map[pos.y][pos.x] & (1 << 3)) == 0) {
            throw new java.lang.RuntimeException("Can't remove Pacman at (" + pos.toString() + "): there is none.");
        }
        map[pos.y][pos.x] &= ~(1 << 3);
    }

    // Where should Pacman be put at the begining of the game ?
    public Point getPacmanSpawnSpot() {
        return new Point(14, 23);
    }

    // Where should a ghost be put at the begining of the game ?
    public Point getGhostSpawnSpot() {
        ArrayList<Point> points = new ArrayList<Point>();
        // Include pseudo-randomness, because we love pseudo-entropy !
        Random r = new Random();

        for (int y = 13; y <= 15; y++) {
            for (int x = 11; x <= 15; x++) {
                points.add(new Point(x, y));
            }
        }

        return points.get(r.nextInt(points.size()));
    }

    public int getRemainingDots() {
        return nDots;
    }
}
