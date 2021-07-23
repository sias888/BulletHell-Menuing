package model;

import Exceptions.InvalidAppearanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PlayerShipTest {
    PlayerShip playerShip;

    @BeforeEach
    void init() {
        playerShip = new PlayerShip();
    }

    @Test
    void testConstructor() {
        Assertions.assertEquals(playerShip.getName(),"My Ship");
        Assertions.assertEquals(playerShip.getShipAppearance(),"Default");
        Assertions.assertEquals(playerShip.getBulletAppearance(), "Default");
    }

    @Test
    void testSetDefault() {
        playerShip.setDefault();
        Assertions.assertEquals(playerShip.getName(),"My Ship");
        Assertions.assertEquals(playerShip.getShipAppearance(),"Default");
        Assertions.assertEquals(playerShip.getBulletAppearance(), "Default");
    }


    // The following method for testing methods that print to console was found from Stackoverflow user Codebender at
    // https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit.
    @Test
    void testPrintConfig() {
        String expectedOut;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        playerShip.printConfig();

        expectedOut = "Name: " + playerShip.getName() +
                "\r\nShip Appearance: " + playerShip.getShipAppearance() +
                "\r\nBullet Appearance: " + playerShip.getBulletAppearance() + "\r\n";

        Assertions.assertEquals(expectedOut, outContent.toString());
    }

    @Test
    void testSetName() {
        playerShip.setName("TEST NAME");
        Assertions.assertEquals(playerShip.getName(),"TEST NAME");
    }

    @Test
    void testSetShipAppearance() throws InvalidAppearanceException {
        playerShip.setShipAppearance("TEST NAME");
        Assertions.assertEquals(playerShip.getShipAppearance(),"TEST NAME");
    }

    @Test
    void testSetBulletAppearance() {
        playerShip.setBulletAppearance("TEST NAME");
        Assertions.assertEquals(playerShip.getBulletAppearance(),"TEST NAME");
    }
}
