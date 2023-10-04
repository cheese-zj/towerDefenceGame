package WizardTD.Towers;

import WizardTD.App;
import processing.core.PImage;

import java.util.HashMap;

public abstract class TowerPresets {
    protected double x;
    protected double y;
    protected double towerRange;
    protected double firingSpeed;
    protected double towerDamage;
    protected boolean ticking = true;
    protected HashMap<UpgradeChoices, Integer> upgradeLevels;
    private PImage sprite;

    public TowerPresets(double x, double y, double towerRange, double firingSpeed, double towerDamage) {
        this.x = x;
        this.y = y;
        this.towerRange = towerRange;
        this.firingSpeed = firingSpeed;
        this.towerDamage = towerDamage;
//        for (fs : UpgradeChoices.values()) {
//
//        }
//        this.upgradeLevels.put()
    }

    public abstract void tick();
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    public void draw(App app) {
        if (ticking) {
            app.image(this.sprite, (int) this.x * 32, (int) this.y * 32 + 40);
        }
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
}
