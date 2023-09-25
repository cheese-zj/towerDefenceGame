package WizardTD.Tiles;

import WizardTD.App;
import processing.core.PImage;

abstract class Tiles{
    private boolean buildOn;
    private boolean walkOn;
    protected int x;
    protected int y;
    private PImage sprite;

    public Tiles(int x, int y, boolean buildOn, boolean walkOn){
        this.x = x;
        this.y = y;
    }

    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    public void draw(App app) {
        app.image(this.sprite, this.x, this.y);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean canBuild() {
        return this.buildOn;
    }
    public boolean canWalk() {
        return this.walkOn;
    }
}