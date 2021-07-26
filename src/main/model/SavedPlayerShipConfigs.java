package model;

import Exceptions.FullConfigListException;
import Exceptions.InvalidConfigNumException;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public class SavedPlayerShipConfigs {
    public static final Integer MAX_SLOTS = 5;
    private List<ConfigSlot> configSlots;

    public SavedPlayerShipConfigs() {
        configSlots = new ArrayList<>();
    }

    //Modifies: this
    //Effects: saves playerShip to config with sequentially incremented num and adds to configSlots. If full, throw
    //         exception.
    public void addConfig(PlayerShip playerShip) throws FullConfigListException {
        if (!isFull()) {
            configSlots.add(new ConfigSlot(configSlots.size(), playerShip.clone()));
        } else {
            throw new FullConfigListException();
        }
    }

    //Modifies: this
    //Effect: Removes config with num value equal to given num. If config with given num doesn't exist, throw exception.
    private void removeConfig(Integer num) {
        configSlots.remove(num);
    }

    //Requires: string num
    //Modifies: this
    //Effects: converts given num to int and throws exception if bad data. Calls overrideConfig on integer num.
    public void overrideConfig(String num, PlayerShip playerShip) throws InvalidConfigNumException {
        int newNum = Integer.valueOf(num); //throws NumberFormatException
        overrideConfig(newNum, playerShip); //throws InvalidConfigNumException
    }

    //Requires: integer num.
    //Modifies: this
    //Effects: Replaces playerShip of config with given num with given playerShip. Called by overrideConfig(Str, PL)
    private void overrideConfig(Integer num, PlayerShip playerShip) throws InvalidConfigNumException {
        if (num < configSlots.size()) {
            configSlots.add(num, new ConfigSlot(num, playerShip.clone()));
            removeConfig(num + 1);
        } else {
            throw new InvalidConfigNumException();
        }
    }

    //Effects: prints all saved configs in configSlots
    public void viewConfigs() {
        for (int i = 0; i < MAX_SLOTS; i++) {
            if (i < configSlots.size()) {
                System.out.println(Integer.toString(i) + ": " + configSlots.get(i).getPlayerShip().getName());
            } else {
                System.out.println(Integer.toString(i) + ": " + "Empty");
            }
        }
    }

    //Effects: return true is configSlots have reached max allowable slots
    private boolean isFull() {
        boolean result = false;

        if (configSlots.size() == MAX_SLOTS) {
            result = true;
        }

        return result;
    }

    //Effects: returns playerShip from stored config at a given num index. Throw exception if index error.
    public PlayerShip getShipFromSlot(int num) throws InvalidConfigNumException {
        PlayerShip result = new PlayerShip();
        if (num < configSlots.size()) {
            result = configSlots.get(num).getPlayerShip();
        } else {
            throw new InvalidConfigNumException();
        }
        return result;
    }

}
