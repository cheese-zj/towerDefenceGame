package WizardTD.Towers;

import WizardTD.App;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

import java.util.HashMap;

public abstract class TowerPreset {
    protected double x;
    protected double y;
    protected int towerRange=App.json.getInt("initial_tower_range");
    protected double firingSpeed=App.json.getDouble("initial_tower_firing_speed");
    protected double towerDamage=App.json.getDouble("initial_tower_damage");
    protected int towerRangeLv;
    protected int towerFireLv;
    protected int towerDmgLv;
    private PImage sprite;
    protected int displayFix = 0;

    public TowerPreset(double x, double y, int towerRangeLv, int towerFireLv, int towerDmgLv) {
        this.x = x;
        this.y = y;
        this.towerRangeLv = towerRangeLv;
        this.towerFireLv = towerFireLv;
        this.towerDmgLv = towerDmgLv;
    }

    private PShape createRangeDisplay(App app){
        PShape range = app.createShape(app.ELLIPSE,
                (float) ((0.5+x)*32), (float) ((0.5+y)*32+40),
                (float) (towerRange+(towerRangeLv)*32)*2, (float) (towerRange+(towerRangeLv)*32)*2);
        //app.ellipse((float) ((0.5+x)*32), (float) ((0.5+y)*32+40), (float) towerRange*2, (float) towerRange*2);
        range.setStroke(app.color(255,247,0));
        range.setStrokeWeight(3);
        range.setFill(false);
        return range;
    }
    public void rangeDisplay(App app, int mouseX, int mouseY) {
        if (mouseX-(1+this.x)*32<=0 && mouseX-(1+this.x)*32>=-32
                && mouseY-((1+this.y)*32+40)<=0 && mouseY-((1+this.y)*32+40) >=-32){
            app.shape(createRangeDisplay(app));
        }
    }
    public abstract void tick();
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    private PImage TowerImageIdentifier(int rangeLv, int fireSpeedLv, int dmgLv) {
        PImage output;
        output = App.tower0png;
        if (rangeLv >= 1 && fireSpeedLv >= 1 && dmgLv >= 1){
            output = App.tower1png;
            displayFix = 1;
        }
        if (rangeLv >= 2 && fireSpeedLv >= 2 && dmgLv >= 2){
            output = App.tower2png;
            displayFix = 2;
        }
        return output;
    }

    public static String repeatString(String text, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(text);
        }
        return result.toString();
    }
    public void draw(App app, int mouseX, int mouseY) {
        setSprite(TowerImageIdentifier(towerRangeLv,towerFireLv,towerDmgLv));
        app.image(this.sprite, (int) this.x * 32, (int) this.y * 32 + 40);
        {
            app.textFont(App.gameFont, 10);
            app.fill(app.color(255, 0, 255));
            {
                String rangeText = repeatString("O", towerRangeLv - displayFix);
                app.text(rangeText, (float) (this.x * 32), (float) this.y * 32 + 40 + 8);
                String dmgText = repeatString("X", towerDmgLv - displayFix);
                app.text(dmgText, (float) (this.x * 32), (float) (this.y * 32 + 40 + 32));
            }
            app.fill(0);
            {
                PShape speedRing = app.createShape(
                        PConstants.RECT, (float) (this.x * 32) + 6, (float) (this.y * 32 + 40 + 6), 20, 20);
                speedRing.setStrokeWeight(towerFireLv-displayFix);
                speedRing.setFill(false);
                speedRing.setStroke(app.color(130,100,255));
                app.shape(speedRing);
            }
        }
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
}
