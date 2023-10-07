package WizardTD.Towers;

import WizardTD.App;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

import java.util.HashMap;

public abstract class TowerPreset {
    protected double x;
    protected double y;
    protected double towerRange;
    protected double firingSpeed;
    protected double towerDamage;
    protected boolean checking = false;
    protected boolean ticking = true;
    protected HashMap<UpgradeChoices, Integer> upgradeLevels;
    private PImage sprite;

    public TowerPreset(double x, double y, double towerRange, double firingSpeed, double towerDamage) {
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

    private PShape createRangeDisplay(App app){
        PShape range = app.createShape(app.ELLIPSE,
                (float) ((0.5+x)*32), (float) ((0.5+y)*32+40), (float) towerRange*2, (float) towerRange*2);
        //app.ellipse((float) ((0.5+x)*32), (float) ((0.5+y)*32+40), (float) towerRange*2, (float) towerRange*2);
        range.setStroke(app.color(255,247,0));
        range.setStrokeWeight(3);
        range.setFill(false);
        return range;
    }
    public void rangeDisplay(App app, int mouseX, int mouseY) {
        if (mouseX-(1+this.x)*32<=0 && mouseX-(1+this.x)*32>=-32
                && mouseY-((1+this.y)*32+40)<=0 && mouseY-((1+this.y)*32+40) >=-32){
            //app. ((int) this.x*32+16, (int)(this.y*32+40+16));
            app.shape(createRangeDisplay(app));
        }
    }
    public abstract void tick();
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    public void draw(App app, int mouseX, int mouseY) {

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
