package tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static boolean doneAsking, doneAskingX, doneAskingO = false;
    private final static int XXX = 264;
    private final static int OOO = 237;
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] ticTacToe = new char[5][9];
        populateGridEdge(ticTacToe);
        printGrid(ticTacToe);

        while (!doneAsking) {
            while (!doneAskingX) {
                try {
                    int firstXCoordinate = scanner.nextInt();
                    int secondXCoordinate = scanner.nextInt();

                    String resultX = checkIfOutOfBounds(firstXCoordinate, secondXCoordinate) ? "Coordinates should be from 1 to 3!"
                            : checkIfOccupiedForX(ticTacToe, firstXCoordinate, secondXCoordinate) ? "This cell is occupied! Choose another one!"
                            : printGrid(ticTacToe);

                    System.out.println(resultX);

                } catch (InputMismatchException ime) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                }
            }

            checkXWins(ticTacToe);

            while(!doneAskingO) {
                try {
                    int firstOCoordinate = scanner.nextInt();
                    int secondOCoordinate = scanner.nextInt();

                    String resultO = checkIfOutOfBounds(firstOCoordinate, secondOCoordinate) ? "Coordinates should be from 1 to 3!"
                            : checkIfOccupiedForO(ticTacToe, firstOCoordinate, secondOCoordinate) ? "This cell is occupied! Choose another one!"
                            : printGrid(ticTacToe);

                    System.out.println(resultO);
                    printGrid(ticTacToe);

                } catch (InputMismatchException ime) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                }
            }
            checkOWins(ticTacToe);
        }

        String finalResult = checkXWins(ticTacToe) ? "X wins" : checkOWins(ticTacToe) ? "O wins" : "Draw";
        System.out.println(finalResult);

    }

    public static boolean checkIfOccupiedForX (char[][] grid, int coord1, int coord2) {
        boolean cellOccupied = false;
        if (grid[coord1][coord2 * 2] == 'X' || grid[coord1][coord2 * 2] == 'O') {
            cellOccupied = true;
            doneAskingX = false;
            doneAskingO = false;
            doneAsking = false;
        } else {
            cellOccupied = false;
            grid[coord1][coord2 * 2] = 'X';
            doneAskingX = true;
            doneAskingO = false;
            doneAsking = false;
        }
        return cellOccupied;
    }

    public static boolean checkIfOccupiedForO (char[][] grid, int coord1, int coord2) {
        boolean cellOccupied = false;
        if (grid[coord1][coord2 * 2] == 'X' || grid[coord1][coord2 * 2] == 'O') {
            cellOccupied = true;
            doneAskingO = false;
            doneAskingX = false;
            doneAsking = false;
        } else {
            cellOccupied = false;
            grid[coord1][coord2 * 2] = 'O';
            doneAskingO = true;
            doneAskingX = false;
            doneAsking = false;
        }
        return cellOccupied;
    }

    public static boolean checkIfOutOfBounds (int coord1, int coord2) {
        boolean outOfBounds = false;
        if (coord1 < 1 || coord1 > 3 || coord2 < 1 || coord2 > 3) {
            outOfBounds = true;
        } else {
            outOfBounds = false;
        }
        return outOfBounds;
    }

    public static boolean checkOWins (char[][] grid) {
        boolean oWins = false;
        for (int i = 1; i <= 3; i++) {
            if ((grid[i][2] + grid[i][4] + grid[i][6] == OOO) || (grid[1][i * 2] + grid[2][i * 2] + grid[3][i * 2] == OOO)
                || (grid[1][2] + grid[2][4] + grid[3][6] == OOO) || (grid[1][6] + grid[2][4] + grid[3][2] == OOO)) {
                oWins = true;
                doneAskingO = true;
                doneAskingX = true;
                doneAsking = true;
                break;
            } else {
                oWins = false;
            }
        }
        return oWins;
    }

    public static boolean checkXWins (char[][] grid) {
        boolean xWins = false;
        for (int i = 1; i <= 3; i++) {
            if ((grid[i][2] + grid[i][4] + grid[i][6] == XXX) || (grid[1][i * 2] + grid[2][i * 2] + grid[3][i * 2] == XXX)
                || (grid[1][2] + grid[2][4] + grid[3][6] == XXX) || (grid[1][6] + grid[2][4] + grid[3][2] == XXX)) {
                xWins = true;
                doneAskingX = true;
                doneAskingO = true;
                doneAsking = true;
                break;
            } else {
                xWins = false;
            }
        }
        return xWins;
    }

    public static String printGrid (char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        return "";
    }

    public static void populateGridEdge (char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 || i == grid.length - 1) {
                    grid[i][j] = '-';
                } else if (j == 0 || j == grid[i].length - 1) {
                    grid[i][j] = '|';
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
    }
}