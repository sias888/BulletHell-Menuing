package model;

import exceptions.InvalidAppearanceException;

public class PlayerShip implements Cloneable {
    private String name;
    private String shipAppearance;
    private String shipAppearanceName;
    private String bulletAppearance;
    private String bulletAppearanceName;

    //Effects: Constructor. Sets parameters to default values
    public PlayerShip() {
        setDefault();
    }

    //Modifies: This;
    //Effects: Sets parameters to default values
    public void setDefault() {
        name = "My Ship";
        shipAppearance = "src/main/Resources/" + "Spaceship_0" + "1" + ".png";
        shipAppearanceName = "Default";
        bulletAppearance = "src/main/Resources/" + "Player_Bullet_0" + "1" + ".png";
        bulletAppearanceName = "Blue";
    }

    //Effects: prints values of parameters
    public void printConfig() {
        System.out.println("Name: " + name);
        System.out.println("Ship Appearance: " + shipAppearanceName);
        System.out.println("Bullet Appearance: " + bulletAppearanceName);
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
    //Effects: ShipAppearance and ShipAppearanceName setter.
    public void setShipAppearance(String shipAppearance) throws InvalidAppearanceException {
        if (checkValidShipAppearance(shipAppearance)) {
            this.shipAppearance = "src/main/Resources/" + "Spaceship_0" + shipAppearance + ".png";
            switch (shipAppearance) {
                case "1":
                    shipAppearanceName = "Default";
                    break;
                case "2":
                    shipAppearanceName = "Jester";
                    break;
                case "3":
                    shipAppearanceName = "Trident";
                    break;
                default:
                    setShipAppearanceHelper(shipAppearance);
            }
        } else {
            throw new InvalidAppearanceException();
        }
    }

    //Requires: shipAppearance is a valid appearance
    //Modifies: This
    //Effects: setShipAppearance helper. Same functionality.
    private void setShipAppearanceHelper(String shipAppearance) {
        switch (shipAppearance) {
            case "4":
                shipAppearanceName = "Scorpion";
                break;
            case "5":
                shipAppearanceName = "Carrier";
                break;
            default:
                shipAppearanceName = "Stream";
                break;
        }
    }

    //Effects: Returns true if given name is one of the valid ship assets
    private boolean checkValidShipAppearance(String name) {
        boolean result = false;

        for (int i = 1; i <= 6; i++) {
            if (Integer.toString(i).equals(name)) {
                result = true;
                break;
            }
        }

        return result;
    }

    //Requires: bulletAppearance is a valid appearance
    //Modifies: This
    //Effects: Bullet appearance setter
    public void setBulletAppearance(String bulletAppearance) throws InvalidAppearanceException {
        if (checkValidBulletAppearance(bulletAppearance)) {
            this.bulletAppearance = "src/main/Resources/" + "Player_Bullet_0" + bulletAppearance + ".png";
            switch (bulletAppearance) {
                case "1":
                    bulletAppearanceName = "Blue";
                    break;
                case "2":
                    bulletAppearanceName = "Violet";
                    break;
                case "3":
                    bulletAppearanceName = "Light Blue";
                    break;
                case "4":
                    bulletAppearanceName = "Green";
                    break;
                default:
                    bulletAppearanceName = "Yellow";
                    break;
            }
        } else {
            throw new InvalidAppearanceException();
        }
    }

    //Effects: Returns true if given name is one of the valid bullet assets
    private boolean checkValidBulletAppearance(String name) {
        boolean result = false;

        for (int i = 1; i <= 5; i++) {
            if (Integer.toString(i).equals(name)) {
                result = true;
                break;
            }
        }

        return result;
    }

    //Effects: returns bullet appearance name
    public String getBulletAppearanceName() {
        return bulletAppearanceName;
    }

    //Effects: returns ship appearance name
    public String getShipAppearanceName() {
        return shipAppearanceName;
    }

    //Requires: non-null playerShip Parameter
    //Effects: return true if this and playerShip parameter have identical member values.
    public boolean identicalTo(PlayerShip playerShip) {
        return name.equals(playerShip.getName())
                && shipAppearance.equals(playerShip.getShipAppearance())
                && bulletAppearance.equals(playerShip.getBulletAppearance());
    }

    //clone method taken from https://www.edureka.co/blog/shallow-and-deep-copy-java/.
    //Effects: returns a shallow clone of this
    public PlayerShip clone() throws CloneNotSupportedException {
        return (PlayerShip) super.clone();
    }
}

