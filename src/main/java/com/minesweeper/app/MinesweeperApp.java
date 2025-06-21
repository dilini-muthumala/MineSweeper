package com.minesweeper.app;

import com.minesweeper.ui.ConsoleUI;
import com.minesweeper.game.MinesweeperGame;
import com.minesweeper.config.GameConfiguration;

public class MinesweeperApp {
    public static void main(String[] args) {
        while (true) {
            ConsoleUI ui = new ConsoleUI();
            GameConfiguration config = ui.askForConfig();
            MinesweeperGame game = new MinesweeperGame(config.getSize(), config.getMineCoordinates());

            while (!game.isGameOver()) {
                ui.printBoard(game.getBoard());
                String input = ui.askForMove(game);
                String result = game.playMove(input);
                ui.showMessage(result);
            }

            ui.printFinalBoard(game.getBoard(), game.hasWon());
        }
    }
}