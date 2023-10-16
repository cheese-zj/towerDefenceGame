package WizardTD.Spell;

import WizardTD.App;
import WizardTD.GameSys.Inventory;
import processing.core.PVector;

import java.util.Objects;
import java.util.Random;

public class Particle {


    PVector position;
    PVector velocity;
    public float lifespan = 90;

    public Particle(float x, float y, Random rand) {
        position = new PVector(x, y);
        velocity = new PVector(((rand.nextFloat() * 6) - 3)*App.TICK_Multiplier, ((rand.nextFloat() * 6) - 3)*App.TICK_Multiplier);
    }

    public void update() {
        if (lifespan > 0) {
            position.add(velocity);
            lifespan -= 3F * App.TICK_Multiplier;
        }
    }

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
