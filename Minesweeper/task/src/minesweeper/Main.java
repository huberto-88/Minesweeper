package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many mines do you want on the field?");
        int mines = Integer.parseInt(scanner.nextLine());

        game.displayEmptyField();
        System.out.println("Set/unset mines marks or claim a cell as free: ");
        String[] userShot = scanner.nextLine().split("\\s+");

        if (userShot.length < 3) {
            System.out.println("Type of shot has not given!");
            System.out.println("Try again:");
            while (userShot.length < 3) {
                userShot = scanner.nextLine().split("\\s+");
            }
        }
        game.setMines(mines);
        game.startNewGame(Integer.parseInt(userShot[1]) - 1,
                Integer.parseInt(userShot[0]) - 1, userShot[2]);


        while (game.getMines() > 0) {
            game.displayField();
            System.out.println("Set/delete mines marks (x and y coordinates):");
            int row = Integer.parseInt(scanner.next()) - 1;
            int col = Integer.parseInt(scanner.next()) - 1;
            game.userShot(col, row, scanner.next());


        }
        System.out.println("Congratulations! You found all mines!");
    }
}