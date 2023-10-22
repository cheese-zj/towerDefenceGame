package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;
import WizardTD.Spell.SpellType;

/**
 * The {@code Inventory} class represents the player's spell inventory in the WizardTD game.
 * This class extends the abstract {@code Buttons} class, and provides a way to manage, display,
 * and switch between different types of spells available to the player.
 *
 * <p>
 * The player's inventory has a limited number of spell charges, which decrease when spells are used.
 * Each spell type is visually represented by a unique color. When the inventory button is activated,
 * the user can cycle through different spell types to select a spell of their choice.
 * </p>
 *
 * @see SpellType
 * @see Buttons
 */
public class Inventory extends Buttons {

    public static boolean InvChecked;
    public static int spellCount = App.json.getInt("initial_spell_amount");
    public static SpellType SpellChoice = SpellType.POISON;
    public Inventory(float x, float y, App app) {
        //spellCount = 0;
        super(x, y, app,"I", "Inventory\n" + "     Charges",'i');
    }

    /**
     * Implements the functionality of the inventory button. When pressed, this method displays the
     * spell choices and the overall charges. If the inventory is empty, the button becomes uncheckable.
     *
     * @param app The main application context, bringing drawing function in.
     */
    @Override
    public void functionality(App app) {
        title = "Inventory\n" + spellCount +  " Spells";
        if (checked) {
            InvChecked = true;
            drawChoices(app);
        } else {
            InvChecked = false;
        }

        if (spellCount == 0){
            checked = false;
        }
    }

    /**
     * Draws the selected spell choice from the inventory on the screen.
     *
     * <p>
     * This method displays a colored ellipse, representative of the spell's type,
     * and its corresponding name below the inventory button. Clicking on the spell
     * allows the player to switch to the next available spell type.
     * </p>
     *
     * @param app The main application context used for rendering.
     */
    public void drawChoices(App app) {
        if (SpellChoice.equals(SpellType.BLAST)) app.fill(255,0,0);
        if (SpellChoice.equals(SpellType.POISON)) app.fill(200,80,255);
        if (SpellChoice.equals(SpellType.SLOW)) app.fill(0,30,255);
        if (SpellChoice.equals(SpellType.PROTECTION)) app.fill(230,230,80);
        app.strokeWeight(2);
        app.ellipse(this.x+15, this.y+70,20,20);
        app.strokeWeight(1);
        app.fill(0);
        app.textFont(App.gameFont, 15);
        app.text(SpellChoice.toString(),this.x+30, this.y+75);
        if (App.isMousePressed && checkHoverInventorySpec(app.mouseX, app.mouseY,this.x,this.y)){
            SpellChoice = SpellChoice.next();
        }
    }

}
