package WizardTD.Monsters;

import WizardTD.App;
import processing.core.PImage;
public abstract class Monsters {
    protected int x;
    protected int y;
    protected int speed;
    protected String type;
    private int hp;
    private int armour;
    private int mana_gained_on_kill;
    private PImage sprite;

    public Monsters(int x, int y, int speed, String type){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;

    }
    public abstract void tick();
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
    public int getMana_gained_on_kill() {
        return this.mana_gained_on_kill;
    }

}
