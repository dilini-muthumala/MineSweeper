package com.minesweeper;

import com.minesweeper.util.ConfigLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    @AfterEach
    void cleanup() {
        // Reset singleton after each test to avoid cross-test pollution
        ConfigLoader.resetInstance();
    }


    @Test
    void testGetMaxMineDensityDefault() {
        ConfigLoader loader = ConfigLoader.getInstance();
        double val = loader.getMaxMineDensity();
        assertTrue(val >= 0.0 && val <= 1.0);
    }

    @Test
    void testCreateForTestWithValidValue() {
        String props = "max.mine.density=0.42\n";
        ByteArrayInputStream input = new ByteArrayInputStream(props.getBytes());
        ConfigLoader testLoader = ConfigLoader.createForTest(input);
        assertEquals(0.42, testLoader.getMaxMineDensity(), 0.0001);
    }

    @Test
    void testCreateForTestWithMissingProperty() {
        ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
        ConfigLoader testLoader = ConfigLoader.createForTest(input);
        assertEquals(0.35, testLoader.getMaxMineDensity(), 0.0001);
    }

    @Test
    void testCreateForTestWithInvalidNumber() {
        String props = "max.mine.density=abc\n";
        ByteArrayInputStream input = new ByteArrayInputStream(props.getBytes());
        ConfigLoader testLoader = ConfigLoader.createForTest(input);
        assertEquals(0.35, testLoader.getMaxMineDensity(), 0.0001);
    }

    @Test
    void testCreateForTestWithNullStreamThrows() {
        assertThrows(RuntimeException.class, () -> ConfigLoader.createForTest(null));
    }
}
