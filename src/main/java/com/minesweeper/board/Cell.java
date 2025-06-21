package com.minesweeper.board;

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