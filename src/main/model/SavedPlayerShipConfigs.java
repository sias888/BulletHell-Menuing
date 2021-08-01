package model;

import exceptions.FullConfigListException;
import exceptions.InvalidConfigNumException;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SavedPlayerShipConfigs {
    public static final Integer MAX_SLOTS = 5;
    private List<ConfigSlot> configSlots;

    public SavedPlayerShipConfigs() {
        configSlots = new ArrayList<>();
    }

    //Modifies: this
    //Effects: saves playerShip to config with sequentially incremented num and adds to configSlots. If full, throw
    //         exception.
    public void addConfig(PlayerShip playerShip) throws FullConfigListException, CloneNotSupportedException {
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
    public void overrideConfig(String num, PlayerShip playerShip) throws InvalidConfigNumException,
            CloneNotSupportedException {
        int newNum = Integer.parseInt(num); //throws NumberFormatException
        overrideConfig(newNum, playerShip); //throws InvalidConfigNumException
    }

    //Requires: integer num.
    //Modifies: this
    //Effects: Replaces playerShip of config with given num with given playerShip. Called by overrideConfig(Str, PL)
    private void overrideConfig(Integer num, PlayerShip playerShip) throws InvalidConfigNumException,
            CloneNotSupportedException {
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
                System.out.println(i + ": " + configSlots.get(i).getPlayerShip().getName());
            } else {
                System.out.println(i + ": " + "Empty");
            }
        }
    }

    //Effects: return true is configSlots have reached max allowable slots
    private boolean isFull() {
        boolean result = configSlots.size() == MAX_SLOTS;

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

    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (ConfigSlot slot : configSlots) {
            jsonArray.put(slot.toJson());
        }

        return jsonArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SavedPlayerShipConfigs that = (SavedPlayerShipConfigs) o;
        return Objects.equals(configSlots, that.configSlots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configSlots);
    }
}
