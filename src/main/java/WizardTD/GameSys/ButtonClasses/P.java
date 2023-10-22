package WizardTD.GameSys.ButtonClasses;

import WizardTD.App;
import WizardTD.GameSys.Buttons;

/**
 * The {@code P} class represents the Pause button in the WizardTD game.
 * Extending the abstract {@code Buttons} class, the {@code P} class provides
 * functionality to pause or resume the game.
 *
 * <p>
 * When activated (checked), this button will pause the game by setting
 * the {@code GAME_TICKING} variable in the {@code App} class to {@code false}.
 * When deactivated, the game will resume and {@code GAME_TICKING} will be
 * set to {@code true}.
 * </p>
 *
 * @see Buttons
 * @see App
 */
public class P extends Buttons {

    /**
     * Constructs a new {@code P} button (Pause button) with the specified position
     * and reference to the main application.
     *
     * @param x   The x-coordinate of the button's position.
     * @param y   The y-coordinate of the button's position.
     * @param app The main application context.
     */
    public P(float x, float y, App app) {
        super(x, y, app,"P", "PAUSE",'p');
    }

    /**
     * Implements the functionality of the Pause button.
     *
     * <p>
     * This method toggles the game's paused state. When the button is checked,
     * the game is paused, and when unchecked, the game resumes.
     * </p>
     *
     * @param app The main application context.
     */
    @Override
    public void functionality(App app) {
        if (checked) {
            App.GAME_TICKING = false;
        } else {
            App.GAME_TICKING = true;
        }
    }
}
