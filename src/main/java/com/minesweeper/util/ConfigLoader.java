package com.minesweeper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loader class for providing the Game configurations
 */
public class ConfigLoader {
    private static final int MAX_DEFAULT_GRID_SIZE = 26;
    private static final double MAX_MINE_DENSITY = 0.35;

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("Missing config.properties file");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static double getMaxMineDensity() {
        return Double.parseDouble(props.getProperty("max.mine.density", String.valueOf(MAX_MINE_DENSITY)));
    }
}
