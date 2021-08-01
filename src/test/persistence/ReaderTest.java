package persistence;

import exceptions.FullConfigListException;
import exceptions.InvalidAppearanceException;
import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @Test
    void readInvalid() {
        try {
            Reader reader = new Reader("data/DoesNotExist.json");
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            System.out.println("good!");
        } catch (FullConfigListException | InvalidAppearanceException | CloneNotSupportedException e) {
            fail("Wrong exception...");
        }
    }

    @Test
    void readEmpty() {

        try {
            Reader reader = new Reader("data/userDataEmpty.json");
            reader.read();
            assertEquals(reader.getPlayerShip(), new PlayerShip());
            assertEquals(reader.getSavedPlayerShipConfigs(), new SavedPlayerShipConfigs());
        } catch (FullConfigListException | InvalidAppearanceException | CloneNotSupportedException | IOException e) {
            fail("Wrong exception...");
        }
    }

    @Test
    void readStandard() throws FullConfigListException, CloneNotSupportedException {
        PlayerShip playerShip = new PlayerShip();
        playerShip.setName("Curr");

        PlayerShip emptyShip = new PlayerShip();
        emptyShip.setName("New Ship");

        SavedPlayerShipConfigs savedPlayerShipConfigs = new SavedPlayerShipConfigs();

        for (int i = 0; i < 4; i++) {
            savedPlayerShipConfigs.addConfig(emptyShip);
        }
        savedPlayerShipConfigs.addConfig(playerShip);

        try {
            Reader reader = new Reader("data/userDataNotEmpty.json");
            reader.read();
            assertEquals(reader.getPlayerShip(), playerShip);
            assertEquals(reader.getSavedPlayerShipConfigs(), savedPlayerShipConfigs);
        } catch (InvalidAppearanceException | IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void readInvalidData() {
        try {
            Reader reader = new Reader("data/userDataInvalid.json");
            reader.read();
            fail("InvalidAppearance expected...");
        } catch (InvalidAppearanceException e) {
            System.out.println("good!");
        } catch (FullConfigListException | IOException | CloneNotSupportedException e) {
            fail("Wrong exception...");
        }
    }

    @Test
    void readInvalidData2() {
        try {
            Reader reader = new Reader("data/userDataInvalid2.json");
            reader.read();
            fail("InvalidAppearance expected...");
        } catch (InvalidAppearanceException e) {
            System.out.println("good!");
        } catch (FullConfigListException | IOException | CloneNotSupportedException e) {
            fail("Wrong exception...");
        }
    }
}