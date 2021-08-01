package model;

import exceptions.InvalidAppearanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(playerShip.getName(),"My Ship");
        assertEquals(playerShip.getShipAppearanceName(),"Default");
        assertEquals(playerShip.getBulletAppearanceName(), "Blue");
        assertEquals(playerShip.getShipAppearance(),"src/main/resources/Spaceship_01.png");
        assertEquals(playerShip.getBulletAppearance(),"src/main/resources/Player_Bullet_01.png");
    }

    @Test
    void testSetDefault() {
        playerShip.setDefault();
        assertEquals(playerShip.getName(),"My Ship");
        assertEquals(playerShip.getShipAppearanceName(),"Default");
        assertEquals(playerShip.getBulletAppearanceName(), "Blue");
        assertEquals(playerShip.getShipAppearance(),"src/main/resources/Spaceship_01.png");
        assertEquals(playerShip.getBulletAppearance(),"src/main/resources/Player_Bullet_01.png");
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
                "\r\nShip Appearance: " + playerShip.getShipAppearanceName() +
                "\r\nBullet Appearance: " + playerShip.getBulletAppearanceName() + "\r\n";

        assertEquals(expectedOut, outContent.toString());
    }

    @Test
    void testSetName() {
        playerShip.setName("TEST NAME");
        assertEquals(playerShip.getName(),"TEST NAME");
    }

    @Test
    void testSetShipAppearanceFail(){
        try {
            playerShip.setShipAppearance("f");
            fail("I didn't expect to reach this step of execution!");
        } catch (InvalidAppearanceException e) {
            System.out.println("great!");
        }
    }

    @Test
    void testSetAppearanceAll(){
        for (int i = 1; i <= 6; i++) {

            try {
                playerShip.setShipAppearance(Integer.toString(i));
                assertEquals(playerShip.getShipAppearance(), "src/main/resources/Spaceship_0" +
                        i + ".png");
                switch (i) {
                    case 1:
                        assertEquals(playerShip.getShipAppearanceName(), "Default");
                        break;
                    case 2:
                        assertEquals(playerShip.getShipAppearanceName(), "Jester");
                        break;
                    case 3:
                        assertEquals(playerShip.getShipAppearanceName(), "Trident");
                        break;
                    case 4:
                        assertEquals(playerShip.getShipAppearanceName(), "Scorpion");
                        break;
                    case 5:
                        assertEquals(playerShip.getShipAppearanceName(), "Carrier");
                        break;
                    case 6:
                        assertEquals(playerShip.getShipAppearanceName(), "Stream");
                        break;
                }
            } catch (InvalidAppearanceException invalidAppearanceException) {
                fail("I didn't expect to catch this exception!");
            }
        }
    }

    @Test
    void testSetBulletAppearanceException() {
        try {
            playerShip.setBulletAppearance("f");
            System.out.println("Should not print");
            fail("I didn't expect to reach this step of execution!");
        } catch (InvalidAppearanceException e) {
            System.out.println("great!");
        }

    }

    @Test
    void testSetBulletAppearanceAll(){
        for (int i = 1; i <= 5; i++) {
            try {
                playerShip.setBulletAppearance(Integer.toString(i));
                assertEquals(playerShip.getBulletAppearance(), "src/main/resources/Player_Bullet_0" +
                        i + ".png");
                switch (i) {
                    case 1:
                        assertEquals(playerShip.getBulletAppearanceName(), "Blue");
                        break;
                    case 2:
                        assertEquals(playerShip.getBulletAppearanceName(), "Violet");
                        break;
                    case 3:
                        assertEquals(playerShip.getBulletAppearanceName(), "Light Blue");
                        break;
                    case 4:
                        assertEquals(playerShip.getBulletAppearanceName(), "Green");
                        break;
                    case 5:
                        assertEquals(playerShip.getBulletAppearanceName(), "Yellow");
                        break;
                }
            } catch (InvalidAppearanceException invalidAppearanceException) {
                fail("I didn't expect to catch this exception!");
            }
        }
    }

    @Test
    void testClone() throws InvalidAppearanceException, CloneNotSupportedException{
        PlayerShip playerShip2 = playerShip.clone();
        assertEquals(playerShip.getShipAppearanceName(), "Default");
        assertEquals(playerShip2.getShipAppearanceName(), "Default");
        playerShip2.setShipAppearance("2");
        assertEquals(playerShip.getShipAppearanceName(), "Default");
        assertEquals(playerShip2.getShipAppearanceName(), "Jester");
    }

    @Test
    void testIdenticalToFalseName() {
        playerShip.setName("New Name");
        assertFalse(playerShip.identicalTo(new PlayerShip()));
    }

    @Test
    void testIdenticalToFalseShip() {
        try {
            playerShip.setShipAppearance("2");
        } catch (InvalidAppearanceException e) {
            fail();
        }
        assertFalse(playerShip.identicalTo(new PlayerShip()));
    }

    @Test
    void testIdenticalToFalseBullet() {
        try {
            playerShip.setBulletAppearance("2");
        } catch (InvalidAppearanceException e) {
            fail();
        }
        assertFalse(playerShip.identicalTo(new PlayerShip()));
    }

    @Test
    void testIdenticalToTrue() {
        assertTrue(playerShip.identicalTo(new PlayerShip()));
    }

    @Test
    void testEqualsTrue() {
        assertTrue(playerShip.equals(new PlayerShip()));
    }

    @Test
    void testEqualsFalseName() {
        playerShip.setName("New Name");
        assertFalse(playerShip.equals(new PlayerShip()));
    }

    @Test
    void testEqualsFalseShip() {
        try {
            playerShip.setShipAppearance("2");
        } catch (InvalidAppearanceException e) {
            fail();
        }
        assertFalse(playerShip.equals(new PlayerShip()));
    }

    @Test
    void testEqualsFalseBullet() {
        try {
            playerShip.setBulletAppearance("2");
        } catch (InvalidAppearanceException e) {
            fail();
        }
        assertFalse(playerShip.equals(new PlayerShip()));
    }

    @Test
    void testEqualsNull() {
        assertFalse(playerShip.equals(null));
    }

    @Test
    void testEqualsNotShip() {
        assertFalse(playerShip.equals(new SavedPlayerShipConfigs()));
    }

    @Test
    void trueHashCode() {
        assertEquals(playerShip.hashCode(),-242630004);
    }
}
