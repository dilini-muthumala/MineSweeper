package com.minesweeper;

import com.minesweeper.board.MinesweeperBoard;
import com.minesweeper.config.GameConfiguration;
import com.minesweeper.game.MinesweeperGame;
import com.minesweeper.ui.ConsoleUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTest {
    private MinesweeperGame game;

    @BeforeEach
    void setUp() {
        game = new MinesweeperGame(4, Set.of("A1", "B2", "C3"));
    }

    @Test
    void testInvalidFormatInput() {
        String input = "11\nA_\nB1\n"; // invalid -> invalid -> valid
        ConsoleUI ui = new ConsoleUI(new ByteArrayInputStream(input.getBytes()));
        String move = ui.askForMove(game);
        assertEquals("B1", move);
    }

    @Test
    void testOutOfBoundsInput() {
        String input = "Z9\nA5\nC3\n"; // out of bounds -> out of bounds -> valid
        ConsoleUI ui = new ConsoleUI(new ByteArrayInputStream(input.getBytes()));
        String move = ui.askForMove(game);
        assertEquals("C3", move);
    }

    @Test
    void testAlreadyRevealedInput() {
        game.playMove("D4"); // simulate revealing D4
        String input = "D4\nC4\n"; // revealed -> valid
        ConsoleUI ui = new ConsoleUI(new ByteArrayInputStream(input.getBytes()));
        String move = ui.askForMove(game);
        assertEquals("C4", move);
    }

    @Test
    void testMaxMinesValidation() {
        String input = "4\n20\n3\n"; // too many mines -> valid count
        ConsoleUI ui = new ConsoleUI(new ByteArrayInputStream(input.getBytes()));
        GameConfiguration config = ui.askForConfig();
        assertEquals(4, config.getSize());
        assertEquals(3, config.getMineCoordinates().size());
    }

    @Test
    void testPrintBoardOutputsGrid() {
        ConsoleUI ui = new ConsoleUI();
        MinesweeperBoard board = new MinesweeperBoard(2, Set.of("A1"));
        board.reveal("A2"); // reveal A2

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ui.printBoard(board);

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.contains("Here is your updated minefield"));
        assertTrue(output.contains("A _"));
        assertTrue(output.contains("B _ _"));
    }
}
