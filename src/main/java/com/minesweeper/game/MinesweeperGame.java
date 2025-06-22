package com.minesweeper.game;

import com.minesweeper.board.MinesweeperBoard;

/**
 * Manages the gameplay lifecycle for a single session of Minesweeper.
 * Coordinates player moves, tracks game state (in progress, win, or loss),
 * and interacts with the underlying MinesweeperBoard to apply game rules.
 *
 * Responsibilities:
 * - Accepts and processes user moves
 * - Detects win or loss conditions
 * - Delegates grid reveal logic to MinesweeperBoard
 *
 * This class encapsulates the core game state (game over, won) and exposes
 * them via query methods, making it easily testable and decoupled from UI.
 */
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