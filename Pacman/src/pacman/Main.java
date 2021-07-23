package pacman;

import pacman.controller.pacmanController;
import pacman.model.Game;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        new pacmanController(game);

        game.start();
    }
}
