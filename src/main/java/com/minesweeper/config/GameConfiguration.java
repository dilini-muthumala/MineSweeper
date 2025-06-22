package com.minesweeper.config;

import java.util.Set;

/**
 * Handles user-provided game configuration such as grid size and mine count.
 *
 */
public class GameConfiguration {
    private final int size;
    private final Set<String> mineCoordinates;

    public GameConfiguration(int size, Set<String> mineCoordinates) {
        this.size = size;
        this.mineCoordinates = mineCoordinates;
    }

    public int getSize() {
        return size;
    }

    public Set<String> getMineCoordinates() {
        return mineCoordinates;
    }
}
