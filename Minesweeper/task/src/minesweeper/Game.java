package minesweeper;

import java.util.Random;

public class Game {
    private String[][] field;
    private String[][] answers;
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
    }

    public void startNewGame() {
        createNewField();
    }

    public String[][] getField() {
        return field;
    }

    public String[][] getAnswers() {
        return answers;
    }

    public void setField(String[][] field) {
        this.field = field;
    }

    public void setAnswers(String[][] answers) {
        this.answers = answers;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMines() {
        return this.mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public void printField() {
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

    /**
     * this method generate field according to given rows and columns with specific number of mines
     * */
    private void createNewField() {
        Random random = new Random();
        int minesToInsert = mines;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = ".";
            }
        }

        /*inserting required numbers of mines into field by random().nextBoolean*/
        while (minesToInsert > 0) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);

            if (!field[x][y].equals("X")) {
                field[x][y] = "X";
                minesToInsert--;
            }
        }
        minesCounter(field);
        answers = field.clone();
        hideAllMines(field);
    }

    private void minesCounter(String[][] field) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int counter = 0;
                if (!field[i][j].equals("X")) {
                    for (int x = i - 1; x < i + 2; x++) {
                        for (int y = j - 1; y < j + 2; y++) {
                            try {
                                if (field[x][y].equals("X")) {
                                    counter++;
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {}
                        }
                    }
                    if (counter > 0) {
                        field[i][j] = String.valueOf(counter);
                    }
                }
            }
        }
    }

    private void hideAllMines(String[][] field) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ("0123456789X".contains(field[i][j])) {
                    field[i][j] = ".";
                }
            }
        }
    }

    public void userShot(int row, int col) {
        if ("0123456789".contains(answers[row][col])) {
            System.out.println("There is a number here!");
        } else if (answers[row][col].equals("X")) {
            field[row][col] = "*";
        }
    }
}