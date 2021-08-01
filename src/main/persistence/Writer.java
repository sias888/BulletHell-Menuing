package persistence;

import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Writer implementation taken from CPSC 210's JsonSerializationDemo's JsonWriter implementation.
public class Writer {
    private PrintWriter printWriter;
    private String destination;

    //Constructor: sets destination
    public Writer(String destination) {
        this.destination = destination;
    }

    //Modifies: this
    //Effects: sets printWriter's destination to file with path == destination
    public void open() throws FileNotFoundException {
        printWriter = new PrintWriter(destination);
    }

    //Modifies: userData.json, this
    //Effects: saves given currentShip and savedShips to json file
    public void write(PlayerShip currentShip, SavedPlayerShipConfigs savedShips) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("currentShip", currentShip.toJson());
        jsonObject.put("savedShips", savedShips.toJson());

        printWriter.print(jsonObject.toString(4));
    }

    //Modifies: this
    //Effects: closes printWriter's open file
    public void close() {
        printWriter.close();
    }
}
