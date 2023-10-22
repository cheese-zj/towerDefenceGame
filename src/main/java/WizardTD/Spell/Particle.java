package WizardTD.Spell;

import WizardTD.App;
import WizardTD.GameSys.ButtonClasses.Inventory;
import processing.core.PVector;

import java.util.Random;

/**
 * The {@code Particle} class represents individual particle effects in the WizardTD game, which
 * are displayed when a spell is cast by the player.
 *
 * <p>
 * These particles provide visual feedback and immersion to the player. Each particle has a position,
 * velocity, and lifespan which determine its movement and duration on the screen. The appearance
 * and color of the particle change based on the type of spell cast.
 * </p>
 *
 * @see SpellType
 */
public class Particle {

    //Particle effect for spell clicks using PVector


    PVector position;
    PVector velocity;
    public float lifespan = 90;

    /**
     * Constructs a new {@code Particle} with specified initial position and random velocity.
     *
     * @param x     The initial x-coordinate of the particle.
     * @param y     The initial y-coordinate of the particle.
     * @param rand  A {@code Random} object used to generate random velocities.
     */
    public Particle(float x, float y, Random rand) {
        position = new PVector(x, y);
        velocity = new PVector(((rand.nextFloat() * 6) - 3)*App.TICK_Multiplier, ((rand.nextFloat() * 6) - 3)*App.TICK_Multiplier);
    }

    /**
     * Updates the position of the particle based on its velocity and reduces its lifespan.
     *
     * <p>
     * The position is updated by adding the velocity to it. The lifespan is reduced by a
     * factor taking into account the game's tick multiplier.
     * </p>
     */
    public void update() {
        if (lifespan > 0) {
            position.add(velocity);
            lifespan -= 3F * App.TICK_Multiplier;
        }
    }

    /**
     * Displays the particle on the screen.
     *
     * <p>
     * The color and appearance of the particle are determined by the type of spell chosen
     * from the inventory. As the particle's lifespan decreases, it becomes more transparent.
     * </p>
     *
     * @param app The main application context used for rendering the particle.
     */
    public void display(App app) {
        app.stroke(0,0);
        if (Inventory.SpellChoice.equals(SpellType.BLAST)) app.fill(app.color(230,80,0), (lifespan/90)*255);
        if (Inventory.SpellChoice.equals(SpellType.SLOW)) app.fill(app.color(20,20,90), (lifespan/90)*255);
        if (Inventory.SpellChoice.equals(SpellType.POISON)) app.fill(app.color(200,50,200), (lifespan/90)*255);
        if (Inventory.SpellChoice.equals(SpellType.PROTECTION)) app.fill(app.color(230,255,20), (lifespan/90)*255);
        app.rect(position.x, position.y, 8, 8);
        app.stroke(0);
    }
}
