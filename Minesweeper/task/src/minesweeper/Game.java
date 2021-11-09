package minesweeper;

import java.util.Random;

public class Game {
    private final String[][] field;
    private final String[][] answers;
    private int rows;
    private int cols;
    private int mines;

    public Game() {
        this(9, 9, 10);
    }

    public Game(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        field = new String[rows][cols];
        answers = new String[rows][cols];
    }

    public void startNewGame(int firstShotRow, int firstShotCol, String typeOfFirstShot) {
        createNewField(firstShotRow, firstShotCol);
        userShot(firstShotRow, firstShotCol, typeOfFirstShot);
    }

    public int getMines() {
        return this.mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void displayField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < cols; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
    public void displayEmptyField() {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < cols; j++) {
                System.out.print(".");
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    /**
     * this method generate field according to given rows and columns with specific number of mines
     * */
    private void createNewField(int freeRow, int freeCol) {
        Random random = new Random();
        int minesToInsert = this.mines;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = ".";
                answers[i][j] = ".";
            }
        }

        /*inserting required numbers of mines into field by random().nextBoolean*/
        if (minesToInsert / answers.length > .75) {
            for (int i = 0; i < answers.length; i++) {
                for (int j = 0; j < answers.length; j++) {
                    if (!answers[i][j].equals("X") && (i != freeRow && j != freeCol)) {
                        if (random.nextBoolean()) {
                            answers[i][j] = "X";
                            minesToInsert--;
                        }
                    }
                }
            }
        } else {

            while (minesToInsert > 0) {
                int x = random.nextInt(rows);
                int y = random.nextInt(cols);

                if (!answers[x][y].equals("X") && (x != freeRow && y != freeCol)) {
                    answers[x][y] = "X";
                    minesToInsert--;
                }
            }
        }
        minesCounter(answers);
    }

    private void minesCounter(String[][] answers) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int counter = 0;
                if (!answers[i][j].equals("X")) {
                    for (int x = i - 1; x < i + 2; x++) {
                        for (int y = j - 1; y < j + 2; y++) {
                            try {
                                if (answers[x][y].equals("X")) {
                                    counter++;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {}
                        }
                    }
                    if (counter > 0) {
                        answers[i][j] = String.valueOf(counter);
                    }
                }
            }
        }
    }

    public void userShot(int row, int col, String typeOfShot) {
        if (typeOfShot.trim().equals("mine")) {
            if ("123456789".contains(field[row][col])) {
                System.out.println("There is a number here!");
            } else if (field[row][col].contains("*")) {
                field[row][col] = ".";
            } else {
                field[row][col] = "*";
            }
        } else if (typeOfShot.equals("free")) {
            if (answers[row][col].equals("X")) {
                field[row][col] = "X";
                System.out.println("You stepped on a mine and failed!");
                System.exit(8);
            } else {
                openEmptyFields(row, col);
            }
        }
        if (checkIsWin()) {
            System.out.println("Congratulations! You found all the mines!");
        }
    }

    private void openEmptyFields(int row, int col) {
        // base cases
        if (row < 0 || row >= answers.length || col < 0 || col >= answers.length) {
            return;
        }

        if ("123456789".contains(answers[row][col])) {
            field[row][col] = answers[row][col];
            return;
        } if (answers[row][col].equals("X")) {
            return;
        }
            if (answers[row][col].equals("/")) {
            return;
        }
        answers[row][col] = "/";
        field[row][col] = "/";

        // recur for north, east, south and west
        openEmptyFields(row + 1, col);
        openEmptyFields(row - 1, col);
        openEmptyFields(row, col + 1);
        openEmptyFields(row, col - 1);

        openEmptyFields(row + 1, col + 1);
        openEmptyFields(row + 1, col -1);
        openEmptyFields(row - 1, col +1);
        openEmptyFields(row - 1, col -1);
    }

    private boolean checkIsWin() {
        int minesFoundedCounter = 0;
        int asterixCounter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j].equals("*")) {
                    asterixCounter++;
                }
                if (answers[i][j].equals("X")) {
                    if (field[i][j].equals("*")) {
                        minesFoundedCounter++;
                    } else {
                        boolean mineFounded = true;
                        for (int x = i - 1; x < i + 2; x++) {
                            for (int y = j - 1; y < j + 2; y++) {
                                try {
                                    if (!"123456789/".contains(field[i][j])) {
                                        mineFounded = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {}
                            }
                        }
                        if (mineFounded) {
                            minesFoundedCounter++;
                        }
                    }
                }
            }
        }
        return minesFoundedCounter == mines && asterixCounter <= mines;
    }
}