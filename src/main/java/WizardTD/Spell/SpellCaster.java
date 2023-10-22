package WizardTD.Spell;

import WizardTD.App;
import WizardTD.GameSys.*;
import WizardTD.GameSys.ButtonClasses.*;
import WizardTD.Helpers.CheckHover;
import WizardTD.Monsters.Monster;

import java.util.ArrayList;

/**
 * The {@code SpellCaster} class is responsible for casting spells in the WizardTD game.
 *
 * <p>
 * This class detects when the player attempts to cast a spell by clicking on the map.
 * Once a spell is cast, it may affect the monsters in its vicinity. Depending on the type
 * of spell chosen from the inventory, different effects will be applied to the monsters.
 * </p>
 *
 * <p>
 * Additionally, the class manages particles that are shown when a spell is cast.
 * These particles give a visual feedback to the player and are managed in an array list.
 * </p>
 *
 * @see SpellType
 * @see Monster
 * @see Inventory
 * @see Particle
 */
public class SpellCaster implements CheckHover {
    /**
     * The cooldown period between spell casts. Once a spell is cast, the player
     * has to wait for this duration before casting again.
     */
   public static int coolDown = 0;

    /**
     * A list of particles to be displayed when a spell is cast.
     */
    ArrayList<Particle> particles = new ArrayList<>();

    /**
     * Renders the particles on the screen and handles spell casting logic.
     *
     * <p>
     * If the player clicks on the map and has selected a spell from the inventory,
     * the spell will be cast and its effects will be applied to any nearby monsters.
     * Particles will be shown at the location of the spell cast.
     * </p>
     *
     * @param app The main application context.
     */
    public void DrawParticles(App app) {
        if (hoverInMap(app.mouseX, app.mouseY) && App.isMousePressed && Inventory.InvChecked) {
            if (coolDown == 0) {
                for (int i = 0; i < 15; i++) {
                    particles.add(new Particle(app.mouseX, app.mouseY, app.random));
                }
                coolDown = 25;
                Inventory.spellCount --;
            }
            for (Monster monster : App.runningMonsterList) {
                if (monster.getX()<=app.mouseX+32*3 && monster.getX()>=app.mouseX-32*3
                && monster.getY()+40<=app.mouseY+32*3 && monster.getY()+40>=app.mouseY-32*3 && monster.canTrack){
                    if (Inventory.SpellChoice.equals(SpellType.POISON)) monster.getPoisoned(10);
                    if (Inventory.SpellChoice.equals(SpellType.SLOW)) monster.getStonised();
                    if (Inventory.SpellChoice.equals(SpellType.BLAST)) monster.getBlasted();
                }
                if (Inventory.SpellChoice.equals(SpellType.PROTECTION)) ManaBar.manaBarGetShielded();
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
            particles.get(i).display(app);
            if (particles.get(i).lifespan <= 0){
                particles.remove(i);
            }
        }
    }
}
