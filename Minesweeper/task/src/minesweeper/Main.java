package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("How many mines do you want on the field?");
        game.setMines(scanner.nextInt());
        game.startNewGame();

        while (game.getMines() > 0) {
            game.printField();

            System.out.println("Set/delete mines marks (x and y coordinates):");
            game.userShot(scanner.nextInt(), scanner.nextInt());
        }
        System.out.println("Congratulations! You found all mines!");
    }
}