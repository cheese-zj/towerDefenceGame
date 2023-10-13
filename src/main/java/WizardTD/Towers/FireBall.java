package WizardTD.Towers;

import WizardTD.App;
import WizardTD.Monsters.Monster;
import processing.core.PImage;

import java.util.HashMap;

public class FireBall {

    protected double x;
    protected double y;
    protected double speed;
    protected float damage;
    public boolean ticking;
    private final Monster target;
    private PImage sprite;
    public FireBall (double x, double y, double speed, float damage, Monster target) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.sprite = App.fireballpng;
        ticking = true;
    }

    protected void move() {
        if (App.GAME_TICKING) {
            double xDis = target.getX() + 12 - this.x;
            double yDis = target.getY() + 40 + 12 - this.y;
            double distance = Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
            if ((int) distance <= 10 && ticking) {
                ticking = false;
                target.getHit(damage);
            }
            // Normalize the direction vector
            double xDir = xDis / distance;
            double yDir = yDis / distance;

            // Update the position
            this.x += xDir * speed *App.TICK_Multiplier;
            this.y += yDir * speed *App.TICK_Multiplier;
        }
    }
    public void draw(App app) {
        if (ticking) {
            app.image(App.fireballpng, (float) this.x, (float) this.y);
        }
    }
    public void tick() {
        move();
    }

}
