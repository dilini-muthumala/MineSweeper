package com.minesweeper.ui;

import com.minesweeper.board.MinesweeperBoard;
import com.minesweeper.config.GameConfiguration;
import com.minesweeper.game.MinesweeperGame;
import com.minesweeper.util.ConfigLoader;

import java.io.InputStream;
import java.util.*;

/**
 * Handles rendering and user interaction in the console.
 * Makes the UI layer swappable, for CLI or test mocks.
 */
public class ConsoleUI {
    private final Scanner scanner;

    // Default constructor for production
    public ConsoleUI() {
        this(System.in);
    }

    // Constructor for testability
    public ConsoleUI(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public GameConfiguration askForConfig() {
        System.out.println("Welcome to Minesweeper!\n");
        System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): \n> ");
        int size = Integer.parseInt(scanner.nextLine());

        int maxMines = (int) Math.floor(size * size * ConfigLoader.getInstance().getMaxMineDensity());
        int numMines;
        while (true) {
            System.out.print("Enter the number of mines to place on the grid (maximum is " + maxMines + "): \n> ");
            numMines = Integer.parseInt(scanner.nextLine());
            if (numMines > maxMines) {
                System.out.println("Too many mines. Please enter a number less than or equal to " + maxMines + ".");
            } else {
                break;
            }
        }

        Set<String> mines = new HashSet<>();
        Random random = new Random();
        while (mines.size() < numMines) {
            char row = (char) ('A' + random.nextInt(size));
            int col = 1 + random.nextInt(size);
            mines.add(row + String.valueOf(col));
        }

        return new GameConfiguration(size, mines);
    }

    public void printBoard(MinesweeperBoard board) {
        String[][] grid = board.getDisplayGrid();
        int size = board.getSize();
        System.out.println("\nHere is your updated minefield:");
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int r = 0; r < size; r++) {
            System.out.print((char) ('A' + r) + " ");
            for (int c = 0; c < size; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    public String askForMove(MinesweeperGame game) {
        while (true) {
            System.out.print("\nSelect a square to reveal (e.g. A1): ");
            String input = scanner.nextLine().toUpperCase();

            if (!input.matches("[A-Z][0-9]+")) {
                System.out.println("Invalid format. Use format like A1.");
                continue;
            }

            int row = input.charAt(0) - 'A';
            int col;
            try {
                col = Integer.parseInt(input.substring(1)) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid column number.");
                continue;
            }

            if (!game.getBoard().inBounds(row, col)) {
                System.out.println("That coordinate is out of bounds.");
                continue;
            }

            if (game.getBoard().isRevealed(row, col)) {
                System.out.println("That square is already revealed.");
                continue;
            }

            return input;
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void printFinalBoard(MinesweeperBoard board, boolean won) {
        board.revealAllMines();
        printBoard(board);
        if (won) {
            System.out.println("\nCongratulations, you have won the game!");
        } else {
            System.out.println("\nOh no, you detonated a mine! Game over.");
        }
        System.out.println("Press Enter to play again...");
        scanner.nextLine();
    }
}
