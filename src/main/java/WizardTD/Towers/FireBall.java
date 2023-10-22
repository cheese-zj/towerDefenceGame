package WizardTD.Towers;

import WizardTD.App;
import WizardTD.Monsters.Monster;
import processing.core.PImage;

import java.util.HashMap;

/**
 * Represents a FireBall projectile in the WizardTD game.
 * <p>
 * This class encapsulates the properties and behavior of the FireBall,
 * including its position, speed, damage, target, and movement logic.
 * A FireBall is launched towards a target Monster and deals damage upon impact.
 * </p>
 */
public class FireBall {

    protected double x;
    protected double y;
    protected double speed;
    protected float damage;
    public boolean ticking;
    public final Monster target;
    private PImage sprite;

    /**
     * Constructs a new FireBall with the given attributes.
     *
     * @param x The initial X-coordinate of the FireBall.
     * @param y The initial Y-coordinate of the FireBall.
     * @param speed The speed of the FireBall.
     * @param damage The damage dealt by the FireBall upon impact.
     * @param target The Monster target of the FireBall.
     */
    public FireBall (double x, double y, double speed, float damage, Monster target) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.sprite = App.fireballpng;
        ticking = true;
    }

    /**
     * Moves the FireBall towards its target Monster.
     * <p>
     * This method updates the FireBall's position based on its speed
     * and the direction of the target. If the FireBall reaches its target,
     * it deals damage and stops moving and ticking.
     * </p>
     */
    protected void move() {
        if (App.GAME_TICKING && ticking) {
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
            app.image(sprite, (float) this.x, (float) this.y);
        }
    }
    public void tick() {
        move();
        // seperated public method, allowing extendability
    }

}
