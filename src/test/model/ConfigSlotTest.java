package model;

import exceptions.InvalidAppearanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigSlotTest {
    ConfigSlot configSlot;
    PlayerShip playerShip;

    @BeforeEach
    void setUp() {
        playerShip = new PlayerShip();
        configSlot = new ConfigSlot(1, playerShip);
    }

    @Test
    void testGetNum() {
        assertEquals(configSlot.getNum(),1);
    }

    @Test
    void testGetPlayerShip() {
        assertEquals(configSlot.getPlayerShip(), playerShip);
    }

    @Test
    void testEqualsTrue() {
        assertTrue(configSlot.equals(new ConfigSlot(1, new PlayerShip())));
    }

    @Test
    void testEqualsTrueSame() {
        assertTrue(configSlot.equals(configSlot));
    }

    @Test
    void testEqualsFalseShip() {
        playerShip.setName("New Name");
        assertFalse(configSlot.equals(new ConfigSlot(1, new PlayerShip())));
    }

    @Test
    void testEqualsFalseNum() {
        assertFalse(configSlot.equals(new ConfigSlot(2, new PlayerShip())));
    }

    @Test
    void testEqualsNull() {
        assertFalse(configSlot.equals(null));
    }

    @Test
    void testEqualsNotShip() {
        assertFalse(configSlot.equals(new SavedPlayerShipConfigs()));
    }

    @Test
    void trueHashCode() {
        assertEquals(configSlot.hashCode(),1068405430);
    }
}