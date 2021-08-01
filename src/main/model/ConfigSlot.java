package model;

import org.json.JSONObject;

import java.util.Objects;

public class ConfigSlot {
    private PlayerShip playerShip;
    private Integer num;

    //Effects: sets num and playerShip to given values.
    public ConfigSlot(int num, PlayerShip playerShip) {
        this.num = num;
        this.playerShip = playerShip;
    }

    //Effects: gets num
    public Integer getNum() {
        return num;
    }

    //Effects: gets playerShip
    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("num", Integer.toString(this.num));
        jsonObject.put("playerShip", playerShip.toJson());

        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigSlot that = (ConfigSlot) o;
        return Objects.equals(playerShip, that.playerShip) && Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerShip, num);
    }
}
