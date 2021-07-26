package model;

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
}
