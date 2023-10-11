package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.GameSys.ManaBar;
import processing.core.PImage;
import processing.core.PShape;

import java.util.Objects;

public abstract class MonsterPresets {
    protected double x;
    protected double y;
    protected double speed;
    protected String type;
    protected float hp;
    protected float armour;
    protected int mana_gained_on_kill;
    protected float spawnTick;
    private PImage sprite;
    public boolean ticking = true;
    public boolean canTrack = false;
    public boolean dead = false;
    private int deathAnimationCounter = 0;
    float initialHp = hp;

    public MonsterPresets(double x, double y, double speed, String type, float hp, float armour, int mana_gained_on_kill, float spawnTick){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        this.hp = hp;
        this.armour = armour;
        this.mana_gained_on_kill = mana_gained_on_kill;
        this.spawnTick = spawnTick;
        this.initialHp = hp;
    }
    public abstract void tick();
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    private void drawDeathAnimation(App app) {

        if (deathAnimationCounter<4) {
            app.image(App.gremlin1png, (float) this.x + 6, (float) this.y + 40 + 6);
        } else if (deathAnimationCounter<8) {
            app.image(App.gremlin2png, (float) this.x + 6, (float) this.y + 40 + 6);
        } else if (deathAnimationCounter<16) {
            app.image(App.gremlin3png, (float) this.x + 6, (float) this.y + 40 + 6);
        } else if (deathAnimationCounter<20) {
            app.image(App.gremlin4png, (float) this.x + 6, (float) this.y + 40 + 6);
        } else if (deathAnimationCounter<24) {
            app.image(App.gremlin5png, (float) this.x + 6, (float) this.y + 40 + 6);
        }
        if (App.GAME_TICKING) deathAnimationCounter++;
    }



    private void drawHpBar(App app) {
        PShape hpBar = app.createShape(app.RECT,
                (float) this.x+6-5, (float) this.y+40+6-5, 30*((float) hp/initialHp),(float) 2);
        hpBar.setFill(app.color(0,233,2));
        PShape hpBarBase = app.createShape(app.RECT,
                (float) this.x+6-5, (float) this.y+40+6-5, 30,(float) 2);
        hpBarBase.setFill(app.color(255,0,0));
        app.shape(hpBarBase);
        app.shape(hpBar);
    }
    public void draw(App app) {
        if (ticking) {
            app.image(this.sprite, (float) this.x+6, (float) this.y+40+6);
            drawHpBar(app);
        }
        if (!ticking && dead && Objects.equals(type, "gremlin")) {
            //death animation
            drawDeathAnimation(app);
        }
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    public void getHit(float dmgTaken){
        hp -= dmgTaken * armour;
        if (hp<=0) {
            if (ManaBar.mana < ManaBar.manaCap) {
                ManaBar.mana += mana_gained_on_kill;
            }
            dead = true;
            ticking = false;
        }
    }

    protected void hitWizard(){
        ManaBar.getAttacked((int) hp);
    }
}
