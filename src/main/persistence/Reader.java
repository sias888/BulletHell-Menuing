package persistence;

import exceptions.FullConfigListException;
import exceptions.InvalidAppearanceException;
import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Implementation taken from CPSC 210's JsonSerializationDemo's JsonReader implementation.
public class Reader {
    private String source;
    private PlayerShip playerShip = new PlayerShip();
    private SavedPlayerShipConfigs savedPlayerShipConfigs = new SavedPlayerShipConfigs();

    //Constructor: sets source from which data is to be read
    public Reader(String source) {
        this.source = source;
    }

    //Effect: gets playerShip and savedPlayerShipConfigs from file at source and stores in respective parameters
    public void read() throws
            InvalidAppearanceException, FullConfigListException, CloneNotSupportedException, IOException {
        JSONObject json = getJson();
        JSONObject playerJson = (JSONObject) json.get("currentShip");
        JSONArray shipsJson = (JSONArray) json.get("savedShips");

        setShip(playerShip, playerJson);
        setShips(savedPlayerShipConfigs, shipsJson);

    }

    //Effects: Retrieves data from source and returns as json
    private JSONObject getJson() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        Stream<String> stringStream = Files.lines(Paths.get(source));

        stringStream.forEach(stringBuilder::append);

        return new JSONObject(stringBuilder.toString());
    }

    //Effects: sets ship to value of currentShip found in data. Two helper methods are used.
    private void setShip(PlayerShip ship, JSONObject json) throws InvalidAppearanceException {
        ship.setName((String) json.get("name"));

        setShipAppearanceHelper(ship, json);

        setShipBulletHelper(ship, json);
    }

    //Effects: sets appearance of playerShip
    private void setShipAppearanceHelper(PlayerShip ship, JSONObject json) throws InvalidAppearanceException {
        switch ((String) json.get("shipAppearanceName")) {
            case "Default":
                ship.setShipAppearance("1");
                break;
            case "Jester":
                ship.setShipAppearance("2");
                break;
            case "Trident":
                ship.setShipAppearance("3");
                break;
            case "Scorpion":
                ship.setShipAppearance("4");
                break;
            case "Carrier":
                ship.setShipAppearance("5");
                break;
            case "Stream":
                ship.setShipAppearance("6");
                break;
            default:
                throw new InvalidAppearanceException();
        }
    }

    //Effects: sets bullet appearance of ship
    private void setShipBulletHelper(PlayerShip ship, JSONObject json) throws InvalidAppearanceException {
        switch ((String) json.get("bulletAppearanceName")) {
            case "Blue":
                ship.setBulletAppearance("1");
                break;
            case "Violet":
                ship.setBulletAppearance("2");
                break;
            case "Light Blue":
                ship.setBulletAppearance("3");
                break;
            case "Green":
                ship.setBulletAppearance("4");
                break;
            case "Yellow":
                ship.setBulletAppearance("5");
                break;
            default:
                throw new InvalidAppearanceException();
        }
    }

    //Effects: for each element in passed JsonArray, parse data into PlayerShip and add to savedPlayerShipConfigs.
    private void setShips(SavedPlayerShipConfigs savedPlayerShipConfigs, JSONArray json)
            throws InvalidAppearanceException, FullConfigListException, CloneNotSupportedException {
        for (Object o : json) {

            JSONObject configJson = (JSONObject) o;
            JSONObject playerJson = (JSONObject) configJson.get("playerShip");

            PlayerShip savedShip = new PlayerShip();
            setShip(savedShip, playerJson);

            savedPlayerShipConfigs.addConfig(savedShip);
        }
    }

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public SavedPlayerShipConfigs getSavedPlayerShipConfigs() {
        return savedPlayerShipConfigs;
    }
}
