package com.minesweeper;

import com.minesweeper.board.MinesweeperBoard;
import com.minesweeper.game.MinesweeperGame;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEndTests {
    @Test
    void shouldInitializeEmptyGridOfCorrectSize() {
        Set<String> mines = Set.of("B2", "C3", "D1");
        MinesweeperBoard board = new MinesweeperBoard(4,  mines);
        assertEquals(4, board.getSize());
        assertEquals('_', board.getDisplayCharAt("A1"));
    }

    @Test
    void shouldPlaceMinesCorrectly() {
        Set<String> mines = Set.of("B2", "C3", "D1");
        MinesweeperBoard board = new MinesweeperBoard(4, mines);
        assertTrue(board.hasMineAt("B2"));
        assertFalse(board.hasMineAt("A1"));
    }

    @Test
    void shouldRevealAndShowAdjacentMineCount() {
        Set<String> mines = Set.of("B2", "C3", "D1");
        MinesweeperBoard board = new MinesweeperBoard(4, mines);

        String result = board.reveal("A1");
        assertEquals("This square contains 1 adjacent mines.", result);
        assertEquals('1', board.getDisplayCharAt("A1"));
    }

    @Test
    void shouldCascadeRevealWhenZeroAdjacentMines() {
        Set<String> mines = Set.of("A1", "B2", "D4");
        MinesweeperBoard board = new MinesweeperBoard(4, mines);

        board.reveal("D1");

        assertEquals('0', board.getDisplayCharAt("D1"));
        assertEquals('1', board.getDisplayCharAt("C1"));
        assertEquals('1', board.getDisplayCharAt("C2"));
    }

    @Test
    void shouldEndGameOnMineReveal() {
        Set<String> mines = Set.of("C3");
        MinesweeperGame game = new MinesweeperGame(3, mines);

        String message = game.playMove("C3");
        assertTrue(message.contains("Game over"));
    }

    @Test
    void shouldWinGameWhenAllSafeSquaresAreRevealed() {
        Set<String> mines = Set.of("B1");
        MinesweeperGame game = new MinesweeperGame(2, mines);

        game.playMove("A1");
        game.playMove("A2");
        game.playMove("B2");

        assertTrue(game.hasWon());
    }
}
