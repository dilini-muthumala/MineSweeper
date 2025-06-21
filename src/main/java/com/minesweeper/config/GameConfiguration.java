package com.minesweeper.config;

import java.util.Set;

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
