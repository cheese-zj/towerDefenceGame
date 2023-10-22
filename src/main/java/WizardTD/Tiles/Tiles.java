package WizardTD.Tiles;

import WizardTD.App;
import processing.core.PImage;

/**
 * Tiles itself is an abstract class including basic function of tiles, recording coordinate and Image to display.
 * Multiple types of tiles are extended and have separated features.
 * Tiles are not directly interacting with monsters in my game thus not having much complexity.
 */
abstract class Tiles{

    protected int x;
    protected int y;
    private PImage sprite;

    public Tiles(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    public void draw(App app) {
        app.image(this.sprite, this.x, this.y + 40);
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

}