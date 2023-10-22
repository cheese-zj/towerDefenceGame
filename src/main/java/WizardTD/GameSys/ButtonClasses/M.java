package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;
import WizardTD.GameSys.ManaBar;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * The {@code M} class represents the Mana Pool upgrade button in the WizardTD game.
 * Extending the abstract {@code Buttons} class, the {@code M} class provides functionality
 * for players to increase their maximum mana capacity and mana regeneration rate.
 *
 * <p>
 * When the Mana Pool button is activated (checked), it deducts a certain amount of mana
 * (equal to the current upgrade cost) from the player's mana bar and then increases the
 * maximum mana capacity and mana regeneration rate based on predefined multipliers.
 * The upgrade cost also increases after each upgrade.
 * </p>
 *
 * <p>
 * In addition, when a player hovers over the Mana Pool button, a hint box is displayed,
 * showing the current upgrade cost.
 * </p>
 *
 * @see Buttons
 * @see ManaBar
 */
public class M extends Buttons {

    /**
     * Represents the hint box shape displayed when the player hovers over the Mana Pool button.
     */
    PShape hintBox;

    /**
     * Constructs a new {@code M} button (Mana Pool upgrade button) with the specified position
     * and reference to the main application.
     *
     * @param x   The x-coordinate of the button's position.
     * @param y   The y-coordinate of the button's position.
     * @param app The main application context.
     */
    public M(float x, float y, App app) {
        super(x, y, app,"M", "Mana pool\ncost: ",'m');
        hintBox = app.createShape(PConstants.RECT, x-110,y,90,24);
        hintBox.setStrokeWeight(1);
        hintBox.setFill(app.color(255,255,255));
    }

    /**
     * Implements the functionality of the Mana Pool button.
     *
     * <p>
     * When activated, this method calculates and updates the player's mana,
     * the maximum mana capacity, mana regeneration rate, and the upgrade cost.
     * If the player's current mana is less than the upgrade cost, the upgrade
     * cannot be purchased.
     * </p>
     *
     * <p>
     * This method also handles the display of the hint box when a player hovers
     * over the Mana Pool button.
     * </p>
     *
     * @param app The main application context for drawing.
     */
    @Override
    public void functionality(App app) {
        title = "Mana pool\ncost: " + ManaBar.manaPoolCost;
        String hint = "Cost: "+ManaBar.manaPoolCost;
        //ManaBar.manaPoolCost++;
        if (checked) {
            if (ManaBar.manaPoolCost<ManaBar.mana) {
                ManaBar.mana -= ManaBar.manaPoolCost;
                ManaBar.manaPoolCost += App.json.getInt("mana_pool_spell_cost_increase_per_use");
                ManaBar.manaCap = (int)(ManaBar.manaCap * App.json.getFloat("mana_pool_spell_cap_multiplier"));
                ManaBar.manaGain *= App.json.getFloat("mana_pool_spell_mana_gained_multiplier");
            }
            checked = false;
        }
        if (checkHover(app.mouseX,app.mouseY, this.x, this.y)){
            app.shape(hintBox);
            app.textFont(App.gameFont,15);
            app.text(hint, x-100, y+19);
        }
    }
}
