package persistence;

import exceptions.FullConfigListException;
import exceptions.InvalidAppearanceException;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Writer writer = new Writer("/data\ndoesn'tExist.json");
            writer.open();
            fail("Exception expected.");
        } catch (FileNotFoundException e) {
            System.out.println("good!");
        }
    }

    @Test
    void testWriterEmptyFile() throws
            FullConfigListException, InvalidAppearanceException, IOException, CloneNotSupportedException {

        PlayerShip playerShip = new PlayerShip();
        SavedPlayerShipConfigs playerShipConfigs = new SavedPlayerShipConfigs();

        try {

            Writer writer = new Writer("data/WriterEmptyFile");
            writer.open();
            writer.write(playerShip, playerShipConfigs);
            writer.close();

            Reader reader = new Reader("data/WriterEmptyFile");
            reader.read();
            assertEquals(reader.getPlayerShip(), playerShip);
            assertEquals(reader.getSavedPlayerShipConfigs(), playerShipConfigs);

        } catch (FileNotFoundException e) {
            fail("should work...");
        }

    }

    @Test
    void testWriterStandardFile() throws
            FullConfigListException, InvalidAppearanceException, IOException, CloneNotSupportedException {

        PlayerShip playerShip = new PlayerShip();
        playerShip.setName("Test");
        playerShip.setShipAppearance("2");
        playerShip.setBulletAppearance("3");

        SavedPlayerShipConfigs playerShipConfigs = new SavedPlayerShipConfigs();
        playerShipConfigs.addConfig(playerShip);
        playerShipConfigs.addConfig(playerShip);
        playerShipConfigs.addConfig(playerShip);

        try {

            Writer writer = new Writer("data/WriterEmptyFile");
            writer.open();
            writer.write(playerShip, playerShipConfigs);
            writer.close();

            Reader reader = new Reader("data/WriterEmptyFile");
            reader.read();
            assertEquals(reader.getPlayerShip(), playerShip);
            assertEquals(reader.getSavedPlayerShipConfigs(), playerShipConfigs);

        } catch (FileNotFoundException e) {
            fail("should work...");
        }

    }

    @Test
    void testWriterStandardFileVariations() throws
            FullConfigListException, InvalidAppearanceException, IOException, CloneNotSupportedException {
        SavedPlayerShipConfigs playerShipConfigs = new SavedPlayerShipConfigs();
        PlayerShip playerShip = new PlayerShip();

        playerShip.setName("Test");

        playerShip.setShipAppearance("6");
        playerShipConfigs.addConfig(playerShip);

        for (int i = 2; i <= 5; i++) {
            playerShip.setBulletAppearance(Integer.toString(i));
            playerShip.setShipAppearance(Integer.toString(i));

            playerShipConfigs.addConfig(playerShip);
        }

        try {

            Writer writer = new Writer("data/WriterEmptyFileVariations");
            writer.open();
            writer.write(playerShip, playerShipConfigs);
            writer.close();

            Reader reader = new Reader("data/WriterEmptyFileVariations");
            reader.read();
            assertEquals(reader.getPlayerShip(), playerShip);
            assertEquals(reader.getSavedPlayerShipConfigs(), playerShipConfigs);

        } catch (FileNotFoundException e) {
            fail("should work...");
        }

    }
}