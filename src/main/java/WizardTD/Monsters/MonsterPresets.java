package WizardTD.Monsters;

import WizardTD.App;
import WizardTD.GameSys.ManaBar;
import processing.core.PImage;
import processing.core.PShape;

/**
 * This class provides a blueprint for creating monsters with different properties and behaviors.
 * <p>
 * It manages monster's attributes such as position, speed, type, health, and armor. It also handles
 * visual aspects such as rendering monsters, their health bars, and death animations.
 * </p>
 */
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
    private final float initialHp;
    public boolean selfDelete = false;

    /**
     * Constructs a new monster with the given properties.
     *
     * @param x                The x-coordinate of the monster.
     * @param y                The y-coordinate of the monster.
     * @param speed            The speed of the monster.
     * @param type             The type of monster.
     * @param hp               The health points of the monster.
     * @param armour           The armor value of the monster.
     * @param mana_gained_on_kill The amount of mana gained upon killing this monster.
     * @param spawnTick        The tick value when the monster is spawned.
     */
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
    /**
     * Abstract method to be implemented by subclasses for monster behavior.
     */
    public abstract void tick();

    /**
     * Sets the sprite image for the monster.
     *
     * @param sprite The sprite image.
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Draws the death animation for the monster, specifically for gremlins.
     *
     * @param app The application window where the animation is drawn.
     */
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
    /**
     * Draws the health bar for the monster.
     *
     * @param app The application window where the health bar is drawn.
     */
    protected void drawHpBar(App app) {
        PShape hpBar = app.createShape(app.RECT,
                (float) this.x+6-5, (float) this.y+40+6-5, 30*(hp /initialHp),(float) 2);
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
    /**
     * Updates the monster's visual representation and checks for its status.
     *
     * @param app The application window where the monster is updated.
     */
    public void update(App app) {
        if (hp <= 0) hp = 0;
        if (ticking) {
            if (type.equals("beetle")) app.image(this.sprite, (float) this.x, (float) this.y+40+2);
            else app.image(this.sprite, (float) this.x+6, (float) this.y+40+6);
            drawHpBar(app);

            if (poisoned && poisonedLasting>0 && App.GAME_TICKING) {
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

    //getter methods: X and Y
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    /**
     * Decreases the monster's health by a given damage amount, considering its armor.
     *
     * @param dmgTaken The damage to be taken by the monster.
     */
    public void getHit(float dmgTaken){
        hp -= dmgTaken * armour;
        if (hp <= 0) hp = 0;
    }
    /**
     * Reduces the monster's health by a factor.
     */
    public void getBlasted(){
        hp /= 1.8F;
    }

    private boolean poisoned = false;
    private int poisonedLasting = 0;
    /**
     * Applies a poisoned status effect on the monster for a specified duration.
     *
     * @param lasting The duration of the poison effect in seconds.
     */
    public void getPoisoned(int lasting){
        poisoned = true;
        poisonedLasting = lasting*60;
    }
    /**
     * Reduces the monster's speed by a factor.
     */
    public void getStonised() {
        speed /= 1.5;
    }
    /**
     * Attacks the wizard by reducing the mana by the monster's health points.
     */
    protected void hitWizard(){
        ManaBar.getAttacked((int) hp);
    }
}
