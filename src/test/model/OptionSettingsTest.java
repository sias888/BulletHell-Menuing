package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OptionSettingsTest {
    OptionSettings optionSettings;

    @BeforeEach
    void init() {
        optionSettings = new OptionSettings();
    }

    @Test
    void testConstructor() {
        assertTrue(optionSettings.getBackgroundSound());
        assertTrue(optionSettings.getBulletSound());
    }

    @Test
    void testSetDefault() {
        optionSettings.setDefault();
        assertTrue(optionSettings.getBackgroundSound());
        assertTrue(optionSettings.getBulletSound());
    }

    @Test
    void testSetBulletSound() {
        optionSettings.setBulletSound(false);
        assertFalse(optionSettings.getBulletSound());
    }

    @Test
    void testSetBackgroundSound() {
        optionSettings.setBackgroundSound(false);
        assertFalse(optionSettings.getBackgroundSound());
    }
}