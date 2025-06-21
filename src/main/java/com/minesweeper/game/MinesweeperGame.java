package com.minesweeper.game;

import com.minesweeper.board.MinesweeperBoard;

public class MinesweeperGame {
    private final MinesweeperBoard board;
    private boolean gameOver = false;
    private boolean won = false;

    public MinesweeperGame(int size, java.util.Set<String> mineCoordinates) {
        this.board = new MinesweeperBoard(size, mineCoordinates);
    }

    public String playMove(String input) {
        if (gameOver) return "Game is already over.";
        String result = board.reveal(input);
        gameOver = result.contains("Game over");
        if (!gameOver && board.allSafeCellsRevealed()) {
            won = true;
            gameOver = true;
            return result + "\nCongratulations, you have won the game!";
        }
        return result;
    }

    public boolean hasWon() {
        return won;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public MinesweeperBoard getBoard() {
        return board;
    }
}