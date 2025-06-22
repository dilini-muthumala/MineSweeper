package com.minesweeper.board;

import java.util.*;

/**
 * Represents the game board for Minesweeper.
 * Responsible for initializing the grid, placing mines, and revealing cells.
 * Contains core game logic including flood fill, mine detection, and win condition.
 * Follows SRP and is decoupled from input/output logic.
 */
public class MinesweeperBoard {
    private final int size;
    private final Cell[][] grid;

    public MinesweeperBoard(int size, Set<String> mineCoordinates) {
        this.size = size;
        this.grid = new Cell[size][size];

        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                grid[r][c] = new Cell();

        for (String coord : mineCoordinates) {
            int[] rc = toRowCol(coord);
            grid[rc[0]][rc[1]].setMine(true);
        }
    }

    public String reveal(String coord) {
        int[] rc = toRowCol(coord);
        return revealRecursive(rc[0], rc[1]);
    }

    public boolean hasMineAt(String coord) {
        int[] rc = toRowCol(coord);
        return grid[rc[0]][rc[1]].isMine();
    }

    public char getDisplayCharAt(String coord) {
        int[] rc = toRowCol(coord);
        return grid[rc[0]][rc[1]].getDisplayChar();
    }

    public int getSize() {
        return size;
    }

    public boolean allSafeCellsRevealed() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (!grid[r][c].isRevealed() && !grid[r][c].isMine())
                    return false;
            }
        }
        return true;
    }

    private String revealRecursive(int r, int c) {
        if (!inBounds(r, c) || grid[r][c].isRevealed()) return "";
        grid[r][c].reveal();

        if (grid[r][c].isMine()) {
            return "Oh no, you detonated a mine! Game over.";
        }

        int count = countAdjacentMines(r, c);
        grid[r][c].setDisplayChar(count == 0 ? '0' : (char) ('0' + count));

        if (count == 0) {
            for (int dr = -1; dr <= 1; dr++)
                for (int dc = -1; dc <= 1; dc++)
                    if (dr != 0 || dc != 0)
                        revealRecursive(r + dr, c + dc);
        }

        return "This square contains " + count + " adjacent mines.";
    }

    private int countAdjacentMines(int r, int c) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++)
            for (int dc = -1; dc <= 1; dc++)
                if ((dr != 0 || dc != 0) && inBounds(r + dr, c + dc) && grid[r + dr][c + dc].isMine())
                    count++;
        return count;
    }


    private int[] toRowCol(String coord) {
        int row = coord.charAt(0) - 'A';
        int col = Integer.parseInt(coord.substring(1)) - 1;
        return new int[]{row, col};
    }

    public String[][] getDisplayGrid() {
        String[][] display = new String[size][size];
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                display[r][c] = String.valueOf(grid[r][c].getDisplayChar());
        return display;
    }

    public boolean isRevealed(int row, int col) {
        return grid[row][col].isRevealed();
    }

    public boolean inBounds(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    public void revealAllMines() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c].isMine()) {
                    grid[r][c].reveal();
                    grid[r][c].setDisplayChar('*');
                }
            }
        }
    }
}