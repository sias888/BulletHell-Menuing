package model;

import exceptions.FullConfigListException;
import exceptions.InvalidConfigNumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SavedPlayerShipConfigsTest {
    SavedPlayerShipConfigs savedPlayerShipConfigs;

    PlayerShip playerShip1;
    PlayerShip playerShip2;

    @BeforeEach
    void setUp() {
        savedPlayerShipConfigs = new SavedPlayerShipConfigs();
        playerShip1 = new PlayerShip();
        playerShip2 = new PlayerShip();

    }

    @Test
    void addConfigEmpty() throws CloneNotSupportedException {
        try {
            savedPlayerShipConfigs.addConfig(playerShip1);
            assertTrue(playerShip1.identicalTo(savedPlayerShipConfigs.getShipFromSlot(0)));
        } catch (FullConfigListException | InvalidConfigNumException e) {
            fail("Should be empty...");
        }
    }

    @Test
    void addConfigFull() throws CloneNotSupportedException {
        for (int i = 0; i < savedPlayerShipConfigs.MAX_SLOTS; i++) {
            try {
                savedPlayerShipConfigs.addConfig(new PlayerShip());
            } catch (FullConfigListException e) {
                fail();
            }
        }

        try {
            savedPlayerShipConfigs.addConfig(playerShip1);
            fail("Should be full...");
        } catch (FullConfigListException e) {
            System.out.println("Good!");
        }
    }

    @Test
    void overrideConfigFormFail() throws CloneNotSupportedException {
        for (int i = 0; i < savedPlayerShipConfigs.MAX_SLOTS; i++) {
            try {
                savedPlayerShipConfigs.addConfig(new PlayerShip());
            } catch (FullConfigListException e) {
                fail();
            }
        }

        try {
            savedPlayerShipConfigs.overrideConfig("f", playerShip1);
            fail("Didn't expect to get this far...");
        } catch (InvalidConfigNumException invalidConfigNumException) {
            fail("Wrong exception!");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("good!");
        }
    }

    @Test
    void overrideConfigNumFail() throws CloneNotSupportedException {
        for (int i = 0; i < savedPlayerShipConfigs.MAX_SLOTS - 1; i++) {
            try {
                savedPlayerShipConfigs.addConfig(new PlayerShip());
            } catch (FullConfigListException e) {
                fail();
            }
        }

        try {
            savedPlayerShipConfigs.overrideConfig(Integer.toString(savedPlayerShipConfigs.MAX_SLOTS - 1), playerShip1);
            fail("Didn't expect to get this far...");
        } catch (NumberFormatException numberFormatException) {
            fail("Wrong exception!");
        } catch (InvalidConfigNumException invalidConfigNumException) {
            System.out.println("good!");
        }
    }

    @Test
    void overrideConfig() throws CloneNotSupportedException {
        playerShip1.setName("Penguin");
        for (int i = 0; i < savedPlayerShipConfigs.MAX_SLOTS; i++) {
            try {
                savedPlayerShipConfigs.addConfig(new PlayerShip());
            } catch (FullConfigListException e) {
                fail();
            }
        }

        try {
            savedPlayerShipConfigs.overrideConfig("0", playerShip1);
            assertTrue(playerShip1.identicalTo(savedPlayerShipConfigs.getShipFromSlot(0)));
        } catch (NumberFormatException numberFormatException) {
            fail("Wrong exception!");
        } catch (InvalidConfigNumException invalidConfigNumException) {
            fail("Wrong exception!");
        }
    }

    @Test
    void viewConfigs() throws CloneNotSupportedException {
        String expectedOut = new String();

        for (int i = 0; i < savedPlayerShipConfigs.MAX_SLOTS - 1; i++) {
            try {
                savedPlayerShipConfigs.addConfig(new PlayerShip());
                expectedOut = expectedOut + i + ": My Ship\r\n";
            } catch (FullConfigListException e) {
                fail();
            }
        }
        expectedOut = expectedOut + Integer.toString(savedPlayerShipConfigs.MAX_SLOTS - 1) + ": Empty\r\n";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        savedPlayerShipConfigs.viewConfigs();

        assertEquals(expectedOut, outContent.toString());
    }

    @Test
    void getShipFromSlotFail() {
        try {
            savedPlayerShipConfigs.getShipFromSlot(0);
            fail();
        } catch (InvalidConfigNumException invalidConfigNumException) {
            System.out.println("good!");
        }
    }

    @Test
    void testEqualsTrue() {
        assertTrue(savedPlayerShipConfigs.equals(new SavedPlayerShipConfigs()));
    }

    @Test
    void testEqualsTrueSame() {
        assertTrue(savedPlayerShipConfigs.equals(savedPlayerShipConfigs));
    }

    @Test
    void testEqualsFalseShip() throws FullConfigListException, CloneNotSupportedException {
        playerShip1.setName("New Name");
        savedPlayerShipConfigs.addConfig(playerShip1);
        assertFalse(savedPlayerShipConfigs.equals(new SavedPlayerShipConfigs()));
    }

    @Test
    void testEqualsNull() {
        assertFalse(savedPlayerShipConfigs.equals(null));
    }

    @Test
    void testEqualsNotShip() {
        assertFalse(savedPlayerShipConfigs.equals(new PlayerShip()));
    }

    @Test
    void trueHashCode() {
        assertEquals(savedPlayerShipConfigs.hashCode(),32);
    }

}
