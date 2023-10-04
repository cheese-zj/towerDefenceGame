package WizardTD.Monsters;

import WizardTD.App;
import processing.core.PImage;
public abstract class MonsterPresets {
    protected double x;
    protected double y;
    protected double speed;
    protected String type;
    protected int hp;
    protected int armour;
    protected int mana_gained_on_kill;
    protected int spawnTick;
    private PImage sprite;
    public boolean ticking = true;

    public MonsterPresets(double x, double y, double speed, String type, int hp, int armour, int mana_gained_on_kill, int spawnTick){
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
        if (ticking) {
            app.image(this.sprite, (float) this.x+6, (float) this.y+46);
        }
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public int getMana_gained_on_kill() {
        return this.mana_gained_on_kill;
    }
    public void getHit(int dmgTaken){
        hp -= dmgTaken / armour;
    }
}
