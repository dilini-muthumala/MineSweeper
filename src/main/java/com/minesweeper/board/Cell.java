package com.minesweeper.board;

/**
 * Represents a single cell in the Minesweeper grid.
 * Holds state for whether it's a mine, revealed, or flagged (future), and what character to display.
 */
public class Cell {
    private boolean isMine;
    private boolean revealed;
    private char displayChar = '_';

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

    public char getDisplayChar() {
        return revealed ? displayChar : '_';
    }

    public void setDisplayChar(char displayChar) {
        this.displayChar = displayChar;
    }
}