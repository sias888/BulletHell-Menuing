package model;

public class OptionSettings {
    //true == on; false == off
    private Boolean bulletSound;
    //true == on; false == off
    private Boolean backgroundSound;

    public OptionSettings() {
        setDefault();
    }

    public void setDefault() {
        setBackgroundSound(true);
        setBulletSound(true);
    }

    public Boolean getBulletSound() {
        return bulletSound;
    }

    public void setBulletSound(Boolean bulletSound) {
        this.bulletSound = bulletSound;
    }

    public Boolean getBackgroundSound() {
        return backgroundSound;
    }

    public void setBackgroundSound(Boolean backgroundSound) {
        this.backgroundSound = backgroundSound;
    }
}
