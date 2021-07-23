package model;

import Exceptions.InvalidAppearanceException;

public class PlayerShip {
    private String name;
    private String shipAppearance;
    private String bulletAppearance;

    //Modifies: This
    //Effects: Constructor. Sets parameters to default values
    public PlayerShip() {
        setDefault();
    }

    //Modifies: This;
    //Effects: Sets parameters to default values
    public void setDefault() {
        name = "My Ship";
        shipAppearance = "Default";
        bulletAppearance = "Default";
    }

    //Effects: prints values of parameters
    public void printConfig() {
        System.out.println("Name: " + name);
        System.out.println("Ship Appearance: " + shipAppearance);
        System.out.println("Bullet Appearance: " + bulletAppearance);
    }

    //Effects: name Getter
    public String getName() {
        return name;
    }

    //Effects: Appearance Getter
    public String getShipAppearance() {
        return shipAppearance;
    }

    //Effects Bullet Appearance Getter
    public String getBulletAppearance() {
        return bulletAppearance;
    }

    //Modifies: This
    //Effects: Name setter
    public void setName(String name) {
        this.name = name;
    }

    //Requires: shipAppearance is a valid appearance
    //Modifies: This
    //Effects: Appearance setter
    //WIP: I want the String shipAppearance to throw an exception if it is not one of the valid types. How to do this?
    public void setShipAppearance(String shipAppearance) throws InvalidAppearanceException {
        this.shipAppearance = shipAppearance;
    }

    //Requires: bulletAppearance is a valid appearance
    //Modifies: This
    //Effects: Bullet appearance setter
    //WIP: see setShipAppearance
    public void setBulletAppearance(String bulletAppearance) {
        this.bulletAppearance = bulletAppearance;
    }
}
