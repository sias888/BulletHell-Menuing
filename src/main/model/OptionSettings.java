package model;

public class OptionSettings {
    //true == on; false == off
    private Boolean bulletSound;
    //true == on; false == off
    private Boolean backgroundSound;

    //Constructor
    //Effect: sets options to default values
    public OptionSettings() {
        setDefault();
    }

    //Modifies: this
    //Effects: set bullet and background sound to on
    public void setDefault() {
        setBackgroundSound(true);
        setBulletSound(true);
    }

    //Effects: returns bulletSound
    public Boolean getBulletSound() {
        return bulletSound;
    }

    //Modifies: this
    //Effects: sets bulletSound
    public void setBulletSound(Boolean bulletSound) {
        this.bulletSound = bulletSound;
    }

    //Effects: returns backgroundSound
    public Boolean getBackgroundSound() {
        return backgroundSound;
    }

    //Modifies: this
    //Effects: sets backgroundSound
    public void setBackgroundSound(Boolean backgroundSound) {
        this.backgroundSound = backgroundSound;
    }
}
