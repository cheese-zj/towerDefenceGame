package WizardTD.Monsters;

import WizardTD.App;
import processing.core.PImage;
public abstract class Monsters {
    protected int x;
    protected int y;
    protected int speed;
    protected String type;
    protected int hp;
    protected int armour;
    protected int mana_gained_on_kill;
    protected int spawnTick;
    private PImage sprite;

    public Monsters(int x, int y, int speed, String type, int hp, int armour, int mana_gained_on_kill, int spawnTick){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        this.hp = hp;
        this.armour = armour;
        this.mana_gained_on_kill = mana_gained_on_kill;
        this.spawnTick = spawnTick;

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
    public void getHit(int dmgTaken){
        hp -= dmgTaken / armour;
    }
}
