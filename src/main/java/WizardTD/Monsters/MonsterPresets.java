package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.GameSys.ManaBar;
import processing.core.PImage;
import processing.core.PShape;

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
    public boolean selfDelete = false;

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
        } else if (deathAnimationCounter<28) {
            selfDelete = true;
        }
        if (App.GAME_TICKING) deathAnimationCounter++;
    }



    protected void drawHpBar(App app) {
        PShape hpBar = app.createShape(app.RECT,
                (float) this.x+6-5, (float) this.y+40+6-5, 30*((float) hp/initialHp),(float) 2);
        hpBar.setFill(app.color(0,233,2));
        if (poisoned) hpBar.setFill(app.color(200,0,255));
        PShape hpBarBase = app.createShape(app.RECT,
                (float) this.x+6-5, (float) this.y+40+6-5, 30,(float) 2);
        hpBarBase.setFill(app.color(255,0,0));
        app.strokeWeight(1);
        app.shape(hpBarBase);
        app.shape(hpBar);
        hpBar.setFill(app.color(0,233,2));
    }
    public void update(App app) {
        if (hp <= 0) hp = 0;
        if (ticking) {
            if (type.equals("beetle")) app.image(this.sprite, (float) this.x, (float) this.y+40+2);
            else app.image(this.sprite, (float) this.x+6, (float) this.y+40+6);
            drawHpBar(app);

            if (poisoned && poisonedLasting>0) {
                if (poisonedLasting%60==0) hp -= hp/15+1;
                poisonedLasting-=App.TICK_Multiplier;
            }
            if (poisonedLasting == 0) poisoned = false;

            if (hp<=0) {
                if (ManaBar.mana < ManaBar.manaCap) {
                    ManaBar.mana += mana_gained_on_kill;
                }
                dead = true;
                ticking = false;
            }
        }
        if (!ticking && dead && type.equals("gremlin")) {
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
        if (hp <= 0) hp = 0;
    }
    public void getBlasted(){
        hp /= 2.5F;
    }

    private boolean poisoned = false;
    private int poisonedLasting = 0;
    public void getPoisoned(int lasting){
        poisoned = true;
        poisonedLasting = lasting*60;
    }

    public void getStonised() {
        speed /= 1.5;
    }

    protected void hitWizard(){
        ManaBar.getAttacked((int) hp);
    }
}
