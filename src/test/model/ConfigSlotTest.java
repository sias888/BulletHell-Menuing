package model;

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
}