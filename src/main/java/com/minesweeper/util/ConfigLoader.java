package com.minesweeper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton loader class for providing the Game configurations.
 */
public class ConfigLoader {

    private static volatile ConfigLoader instance;

    private static final double DEFAULT_MAX_MINE_DENSITY = 0.35;
    private final Properties props = new Properties();

    private ConfigLoader() {
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

    private ConfigLoader(InputStream input) {
        if (input == null) {
            throw new RuntimeException("InputStream cannot be null");
        }
        try {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = new ConfigLoader();
                }
            }
        }
        return instance;
    }

    /**
     * Returns the maximum mine density configuration.
     * Defaults to 0.35 if property not set or invalid.
     */
    public double getMaxMineDensity() {
        String prop = props.getProperty("max.mine.density");
        if (prop == null) {
            return DEFAULT_MAX_MINE_DENSITY;
        }
        try {
            return Double.parseDouble(prop);
        } catch (NumberFormatException e) {
            return DEFAULT_MAX_MINE_DENSITY;
        }
    }


    public static ConfigLoader createForTest(InputStream input) {
        return new ConfigLoader(input);
    }

    public static void resetInstance() {
        instance = null;
    }
}
